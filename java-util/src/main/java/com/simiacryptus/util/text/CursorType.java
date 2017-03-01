package com.simiacryptus.util.text;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.simiacryptus.util.data.SerialType;

class CursorType implements SerialType<CursorData> {

  static CursorType INSTANCE = new CursorType();

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