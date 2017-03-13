package com.simiacryptus.util.text;

import com.simiacryptus.util.data.SerialType;

import java.io.IOException;
import java.nio.ByteBuffer;

class NodeType implements SerialType<NodeData> {

  static NodeType INSTANCE = new NodeType();

  @Override
  public int getSize() {
    return 24;
  }

  @Override
  public NodeData read(ByteBuffer input) throws IOException {
    return new NodeData(input.getChar(), input.getShort(), input.getInt(), input.getLong(), input.getLong());
  }

  @Override
  public void write(ByteBuffer output, NodeData value) throws IOException {
    output.putChar(value.token);
    output.putShort(value.numberOfChildren);
    output.putInt(value.firstChildIndex);
    output.putLong(value.cursorCount);
    output.putLong(value.firstCursorIndex);
  }
}