package com.simiacryptus.util.text;

import com.simiacryptus.util.data.SerialArrayList;
import com.simiacryptus.util.data.SerialType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * A character sequence index using a prefix tree, commonly known as a full-text index or as the data structure behind markov chains.
 * This implementation uses serialized fixed-length ephemeral objects and a raw byte data store, preventing object/reference count overhead.
 */
public class CharTree {

    public int getMemorySize() {
        return cursors.getMemorySize() + nodes.getMemorySize();
    }

    public int getIndexedSize() {
        return documents.stream().mapToInt(doc->doc.length()).sum();
    }

    public class Node {
        private final NodeData data;
        public final short depth;
        private final int index;
        public final Node parent;

        public Node(NodeData data, short depth, int index, Node parent) {
            this.data = data;
            this.depth = depth;
            this.index = index;
            this.parent = parent;
        }

        public Node refresh() {
            return new Node(nodes.get(index), depth, index, parent);
        }

        public String getString() {
            return (null == parent ? "" : parent.getString()) + (0 == depth ? "" :data.token);
        }

        public char getToken() {
            return data.token;
        }

        public short getNumberOfChildren() {
            return data.numberOfChildren;
        }

        public short getDepth() {
            return depth;
        }

        public int getCursorCount() {
            return data.cursorCount;
        }

        public Node visitFirst(Consumer<Node> visitor) {
            visitor.accept(this);
            Node refresh = refresh();
            refresh.getChildren().forEach(n->n.visitFirst(visitor));
            return refresh;
        }

        public Node visitLast(Consumer<Node> visitor) {
            getChildren().forEach(n->n.visitLast(visitor));
            visitor.accept(this);
            return refresh();
        }

        public Stream<Node> getChildren() {
            if(data.firstChildIndex >= 0) {
                return IntStream.range(0, data.numberOfChildren).mapToObj(i->{
                    int childIndex = data.firstChildIndex + i;
                    NodeData child = nodes.get(childIndex);
                    return new Node(child, (short) (depth + 1), childIndex, Node.this);
                });
            } else {
                return Stream.empty();
            }
        }

        public Optional<Node> getChild(char token) {
            return getChildren().filter(x->x.getToken() == token).findFirst();
        }

        public Map<String, List<Cursor>> getCursorsByDocument() {
            return this.getCursors().collect(Collectors.groupingBy((Cursor x)->x.getDocument()));
        }

        public Stream<Cursor> getCursors() {
            return IntStream.range(0, data.cursorCount).mapToObj(i->{
                return new Cursor(cursors.get(i+data.firstCursorIndex), depth);
            });
        }

        public Node split() {
            if(data.firstChildIndex < 0) {
                Map<Character, SerialArrayList<CursorData>> sortedChildren = getCursors().parallel()
                    .collect(Collectors.groupingBy(y -> y.next().getToken(),
                    Collectors.reducing(
                        new SerialArrayList<>(_CursorType, 0),
                        cursor -> new SerialArrayList<>(_CursorType, cursor.data),
                        (left, right) -> left.add(right))));
                int cursorWriteIndex = data.firstCursorIndex;
                ArrayList<NodeData> childNodes = new ArrayList<>(sortedChildren.size());
                for(Map.Entry<Character, SerialArrayList<CursorData>> e : sortedChildren.entrySet())
                {
                    int length = e.getValue().length();
                    cursors.putAll(e.getValue(), cursorWriteIndex);
                    childNodes.add(new NodeData(e.getKey(), (short) -1, -1, length, cursorWriteIndex));
                    cursorWriteIndex += length;
                };
                return new Node(nodes.update(index, data -> data
                        .setFirstChildIndex(nodes.addAll(childNodes))
                        .setNumberOfChildren((short) childNodes.size())), depth, index, parent);
            } else {
                return this;
            }
        }

        private void decrementCursorCount(int count) {
            nodes.update(index, data->data.setCursorCount(Math.max(data.cursorCount-count, 0)));
            if(null != parent) parent.decrementCursorCount(count);
        }

        public void shadowCursors() {
            decrementCursorCount(getCursorCount());
        }
    }

    private static class NodeData {
        // Primary data defining the tree
        char token;
        short numberOfChildren;
        int firstChildIndex;
        // Associated data to be stored for each node
        int cursorCount;
        int firstCursorIndex;

        public NodeData(char token, short numberOfChildren, int firstChildIndex, int cursorCount, int firstCursorIndex) {
            this.token = token;
            this.numberOfChildren = numberOfChildren;
            this.firstChildIndex = firstChildIndex;
            this.cursorCount = cursorCount;
            this.firstCursorIndex = firstCursorIndex;
        }

        public NodeData setToken(char token) {
            this.token = token;
            return this;
        }

        public NodeData setNumberOfChildren(short numberOfChildren) {
            this.numberOfChildren = numberOfChildren;
            return this;
        }

        public NodeData setFirstChildIndex(int firstChildIndex) {
            this.firstChildIndex = firstChildIndex;
            return this;
        }

        public NodeData setCursorCount(int cursorCount) {
            this.cursorCount = cursorCount;
            return this;
        }

        public NodeData setFirstCursorIndex(int firstCursorIndex) {
            this.firstCursorIndex = firstCursorIndex;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NodeData nodeData = (NodeData) o;

            if (token != nodeData.token) return false;
            if (numberOfChildren != nodeData.numberOfChildren) return false;
            if (firstChildIndex != nodeData.firstChildIndex) return false;
            if (cursorCount != nodeData.cursorCount) return false;
            return firstCursorIndex == nodeData.firstCursorIndex;
        }

        @Override
        public int hashCode() {
            int result = (int) token;
            result = 31 * result + (int) numberOfChildren;
            result = 31 * result + firstChildIndex;
            result = 31 * result + cursorCount;
            result = 31 * result + firstCursorIndex;
            return result;
        }

    }

    private static NodeType _NodeType = new NodeType();
    private static class NodeType implements SerialType<NodeData> {

        @Override
        public int getSize() {
            return 16;
        }

        @Override
        public NodeData read(ByteBuffer input) throws IOException {
            return new NodeData(
                    input.getChar(),
                    input.getShort(),
                    input.getInt(),
                    input.getInt(),
                    input.getInt()
            );
        }

        @Override
        public void write(ByteBuffer output, NodeData value) throws IOException {
            output.putChar(value.token);
            output.putShort(value.numberOfChildren);
            output.putInt(value.firstChildIndex);
            output.putInt(value.cursorCount);
            output.putInt(value.firstCursorIndex);
        }
    }

    public class Cursor {
        private final CursorData data;
        private final short depth;

        public Cursor(CursorData data, short depth) {
            this.data = data;
            this.depth = depth;
        }

        public String getDocument() {
            return documents.get(data.documentId);
        }

        public boolean hasNext() {
            return (depth + data.position + 1) < getDocument().length();
        }

        public char getToken() {
            int index = depth + data.position;
            String document = getDocument();
            return index>=document.length()?Character.MIN_VALUE:document.charAt(index);
        }

        public Cursor next() {
            return new Cursor(data, (short) (depth+1));
        }

        public int getPosition() {
            return data.position + depth;
        }
    }

    private static class CursorData {
        int documentId;
        int position;

        public CursorData(int documentId, int position) {
            this.documentId = documentId;
            this.position = position;
        }

        public CursorData setDocumentId(int documentId) {
            this.documentId = documentId;
            return this;
        }

        public CursorData setPosition(int position) {
            this.position = position;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CursorData that = (CursorData) o;

            if (documentId != that.documentId) return false;
            return position == that.position;
        }

        @Override
        public int hashCode() {
            int result = documentId;
            result = 31 * result + position;
            return result;
        }

    }

    private static CursorType _CursorType = new CursorType();
    private static class CursorType implements SerialType<CursorData> {

        @Override
        public int getSize() {
            return 8;
        }

        @Override
        public CursorData read(ByteBuffer input) throws IOException {
            return new CursorData(input.getInt(), input.getInt());
        }

        @Override
        public void write(ByteBuffer output, CursorData value) throws IOException {
            output.putInt(value.documentId);
            output.putInt(value.position);
        }
    }

    private final SerialArrayList<NodeData> nodes;
    private final SerialArrayList<CursorData> cursors;
    private final ArrayList<String> documents;

    public CharTree() {
        this.nodes = _NodeType.newList(new NodeData(Character.MIN_VALUE, (short) -1, -1, -1, 0));
        this.cursors = _CursorType.newList();
        this.documents = new ArrayList<>();
    }

    public CharTree(CharTree copyFrom) {
        this.nodes = copyFrom.nodes.copy();
        this.cursors = copyFrom.cursors.copy();
        this.documents = new ArrayList<>(copyFrom.documents);
    }

    public Node root() {
        return new Node(nodes.get(0), (short) 0, 0, null);
    }

    /**
     * Locate a node by finding the maximum prefix match with the given string
     * @param search
     * @return
     */
    public Node traverse(String search) {
        Node cursor = root();
        for(char token : search.toCharArray()) {
            Optional<Node> child = cursor.getChild(token);
            if(child.isPresent()) {
                cursor = child.get();
            } else {
                break;
            }
        }
        return cursor;
    }

    /**
     * Removes cursor data, retaining only the tree of tokens and counts.
     * Subsequent calls to methods dealing with cursors will fail.
     * @return this
     */
    public CharTree truncate() {
        cursors.clear();
        return this;
    }

    public CharTree copy() {
        return new CharTree(this);
    }

    /**
     * Creates the index tree using the accumulated documents
     * @return this
     */
    public CharTree index() {
        return index(Integer.MAX_VALUE);
    }

    /**
     * Creates the index tree using the accumulated documents
     * @param maxLevels - Maximum depth of the tree to build
     * @return this
     */
    public CharTree index(int maxLevels) {
        return index(maxLevels, 1);
    }

    /**
     * Creates the index tree using the accumulated documents
     * @param maxLevels - Maximum depth of the tree to build
     * @param minWeight - Minimum number of cursors for a node to be index using, exclusive bound
     * @return this
     */
    public CharTree index(int maxLevels, int minWeight) {
        root().visitFirst(node->{
            if(node.depth < maxLevels && node.getCursorCount() > minWeight && (node.getToken() != Character.MIN_VALUE || node.depth == 0)) node.split();
        });
        return this;
    }

    /**
     * Adds a document to be indexed. This can only be performed before splitting.
     * @param document
     * @return this
     */
    public CharTree addDocument(String document) {
        if(root().getNumberOfChildren() >= 0) throw new IllegalStateException("Tree sorting has begun");
        final int index;
        synchronized (this) {
            index = documents.size();
            documents.add(document);
        }
        cursors.addAll(IntStream.range(0, document.length())
                .mapToObj(i->new CursorData(index, i))
                .collect(Collectors.toList()));
        nodes.update(0, node->node.setCursorCount(cursors.length()));
        return this;
    }

    public String generateMarkov(int length, int context) {
        return generateMarkov(length, context, "");
    }

    public String generateMarkov(int length, int context, String seed) {
        String str = seed;
        while(str.length() < length) {
            String prefix = str.substring(Math.max(str.length()- context,0),str.length());
            Node node = traverse(prefix);
            int cursorCount = node.getCursorCount();
            int fate = random.nextInt(cursorCount);
            String next = null;
            List<Node> children = node.getChildren().collect(Collectors.toList());
            for(Node child : children) {
                fate -= child.getCursorCount();
                if(fate <= 0) {
                    if(child.getToken() != Character.MIN_VALUE) {
                        next = new String(new char[]{child.getToken()});
                    }
                    break;
                }
            }
            if(null != next) str += next;
            else break;
        }
        return str;
    }

    public String generateDictionary(int length, int context) {
        return generateDictionary(length, context, "", 0, false);
    }

    private static final Random random = new Random();
    public String generateDictionary(int length, int context, final String seed, int lookahead, boolean destructive) {
        String str = seed;
        while(str.length() < length) {
            String prefix = str.substring(Math.max(str.length()- context,0),str.length());
            Node node = traverse(prefix);
            Node nextNode = nextDictionaryNode(node, lookahead);
            if(null == nextNode) break;
            if(destructive) nextNode.shadowCursors();
            String nextNodeString = nextNode.getString();
            String next = nextNodeString.substring(node.depth);
            str += next;
        }
        return str;
    }

    private Node nextDictionaryNode(Node node, int lookahead) {
        int cursorCount = node.getCursorCount();
        int fate = cursorCount>0?random.nextInt(cursorCount):0;
        Stream<Node> childStream = node.getChildren();
        for(int level = 0; level< lookahead; level++) {
            childStream = childStream.flatMap(child->child.getChildren());
        }
        return childStream.max(Comparator.comparingInt(x->x.getCursorCount())).orElse(null);
    }

    public static byte[] compress(String dictionary, String data) {
        byte[] output = new byte[data.length()*2];
        Deflater compresser = new Deflater();
        try {
            compresser.setInput(data.getBytes("UTF-8"));
            compresser.setDictionary(dictionary.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();
        return Arrays.copyOf(output, compressedDataLength);
    }

    public static String decompress(String dictionary, byte[] data) {
        try {
            Inflater decompresser = new Inflater();
            decompresser.setInput(data, 0, data.length);
            decompresser.setDictionary(dictionary.getBytes("UTF-8"));
            byte[] result = new byte[100];
            int resultLength = 0;
            resultLength = decompresser.inflate(result);
            decompresser.end();
            return new String(result, 0, resultLength, "UTF-8");
        } catch (DataFormatException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
