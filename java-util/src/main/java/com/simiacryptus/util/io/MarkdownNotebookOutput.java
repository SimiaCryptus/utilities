/*
 * Copyright (c) 2017 by Andrew Charneski.
 *
 * The author licenses this file to you under the
 * Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance
 * with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.simiacryptus.util.io;

import com.simiacryptus.util.lang.CodeUtil;
import com.simiacryptus.util.Util;
import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.SysOutInterceptor;
import com.simiacryptus.util.text.TableOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MarkdownNotebookOutput implements NotebookOutput {
  
  private final List<PrintStream> outs = new ArrayList<>();
  private final File fileName;
  private final String name;
  private final OutputStream primaryOut;
  private int imageNumber = 0;
  
  public MarkdownNotebookOutput(File fileName, String name) throws FileNotFoundException {
    this.name = name;
    this.primaryOut = new FileOutputStream(fileName);
    outs.add(new PrintStream(primaryOut));
    this.fileName = fileName;
  }
  
  public static MarkdownNotebookOutput get(Object source) {
    try {
      StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[2];
      String className = null == source ? callingFrame.getClassName() : source.getClass().getCanonicalName();
      String methodName = callingFrame.getMethodName();
      String fileName = methodName + ".md";
      File path = new File(Util.mkString(File.separator, "reports", className, fileName));
      path.getParentFile().mkdirs();
      return new MarkdownNotebookOutput(path, methodName);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public OutputStream file(String name) {
    try {
      return new FileOutputStream(new File(getResourceDir(), name));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
  public NotebookOutput addCopy(PrintStream out) {
    outs.add(out);
    return this;
  }
  
  @Override
  public void out(String fmt, Object... args) {
    String msg = 0 == args.length ? fmt : String.format(fmt, args);
    outs.forEach(out->out.println(msg));
  }
  
  @Override
  public void p(String fmt, Object... args) {
    this.out(fmt + "\n", args);
  }
  
  @Override
  public void h1(String fmt, Object... args) {
    this.out("# " + fmt, args);
  }
  
  @Override
  public void h2(String fmt, Object... args) {
    this.out("## " + fmt, args);
  }
  
  @Override
  public void h3(String fmt, Object... args) {
    this.out("### " + fmt, args);
  }
  
  @Override
  public <T> T code(Supplier<T> fn, int maxLog, int framesNo) {
    try {
      StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[framesNo];
      String sourceCode = CodeUtil.getInnerText(callingFrame);
      SysOutInterceptor.LoggedResult<TimedResult<Object>> result = SysOutInterceptor.withOutput(() -> {
        try {
          return TimedResult.time(() -> fn.get());
        } catch (Throwable e) {
          return new TimedResult(e, 0);
        }
      });
      out("Code from [%s:%s](%s#L%s) executed in %.2f seconds: ",
          callingFrame.getFileName(), callingFrame.getLineNumber(),
          Util.pathTo(fileName.getParentFile(), CodeUtil.findFile(callingFrame)), callingFrame.getLineNumber(), result.obj.seconds());
      out("```java");
      out("  " + sourceCode.replaceAll("\n", "\n  "));
      out("```");
      
      if (!result.log.isEmpty()) {
        out("Logging: ");
        out("```");
        String logSrc = result.log;
        if (logSrc.length() > maxLog * 2) {
          logSrc = logSrc.substring(0, maxLog) + String.format("\n...skipping %s bytes...\n", logSrc.length() - 2 * maxLog) + logSrc.substring(logSrc.length() - maxLog);
        } else if (logSrc.length() > 0) {
          logSrc = logSrc;
        }
        logSrc = logSrc.replaceAll("\n", "\n    ");
        out("    " + logSrc);
        out("```");
      }
      out("");
      
      Object eval = result.obj.obj;
      if(null != eval) {
        out("Returns: \n");
        String str;
        boolean escape;
        if (eval instanceof Throwable) {
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          ((Throwable) eval).printStackTrace(new PrintStream(out));
          str = new String(out.toByteArray(), "UTF-8");
          escape = true;//
        } else if (eval instanceof Component) {
          str = image(Util.toImage((Component) eval), "Result");
          escape = false;
        } else if (eval instanceof BufferedImage) {
          str = image((BufferedImage) eval, "Result");
          escape = false;
        } else if (eval instanceof TableOutput) {
          str = ((TableOutput) eval).toTextTable();
          escape = false;
        } else {
          str = eval.toString();
          escape = true;
        }
        if (escape) out("```");
        String valTxt = escape ? str.replaceAll("\n", "\n    ") : str;
        if (escape && valTxt.length() > maxLog) {
          valTxt = valTxt.substring(0, maxLog) + String.format("... and %s more bytes", valTxt.length() - maxLog);
        }
        out(escape ? ("    " + valTxt) : valTxt);
        if (escape) out("```");
        out("\n\n");
        if (eval instanceof Throwable) {
          throw new RuntimeException((Throwable) result.obj.obj);
        }
      }
      return (T) eval;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public String image(BufferedImage rawImage, String caption) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int thisImage = ++imageNumber;
    File file = new File(getResourceDir(), this.name + "." + thisImage + ".png");
    BufferedImage stdImage = Util.resize(rawImage);
    if (stdImage != rawImage) {
      ImageIO.write(rawImage, "png", new File(getResourceDir(), this.name + "_raw." + thisImage + ".png"));
    }
    ImageIO.write(stdImage, "png", file);
    return "![" + caption + "](etc/" + file.getName() + ")";
  }
  
  public File getResourceDir() {
    File etc = new File(this.fileName.getParentFile(), "etc");
    etc.mkdirs();
    return etc;
  }
  
  @Override
  public void close() throws IOException {
    if(null != primaryOut) primaryOut.close();
  }
  
}
