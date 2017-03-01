package com.simiacryptus.util.text;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.simiacryptus.util.data.SerialType;

class NodeType implements SerialType<NodeData> {

  static NodeType INSTANCE = new NodeType();

  @Override
  public int getSize() {
    return 16;
  }

  @Override
  public NodeData read(ByteBuffer input) throws IOException {
    return new NodeData(input.getChar(), input.getShort(), input.getInt(), input.getInt(), input.getInt());
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