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

import com.simiacryptus.util.Util;
import com.simiacryptus.util.lang.CodeUtil;
import com.simiacryptus.util.lang.TimedResult;
import com.simiacryptus.util.test.SysOutInterceptor;
import com.simiacryptus.util.text.TableOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class HtmlNotebookOutput implements NotebookOutput {
  
  private final List<PrintStream> outs = new ArrayList<>();
  public final File workingDir;
  private final OutputStream primaryOut;
  
  public static HtmlNotebookOutput create(File parentDirectory) throws FileNotFoundException {
    FileOutputStream out = new FileOutputStream(new File(parentDirectory, "index.html"));
    return new HtmlNotebookOutput(parentDirectory, out) {
      @Override
      public void close() throws IOException {
        out.close();
      }
    };
  }

  public HtmlNotebookOutput(File parentDirectory, OutputStream out) throws FileNotFoundException {
    this.primaryOut = out;
    outs.add(new PrintStream(out));
    this.workingDir = parentDirectory;
    out("<html><head></head><body>");
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
    outs.forEach(out->{
      out.println(msg);
      out.flush();
    });
  }
  
  @Override
  public void p(String fmt, Object... args) {
    this.out("<p>" + fmt + "</p>", args);
  }
  
  @Override
  public void h1(String fmt, Object... args) {
    this.out("<h1>" + fmt + "</h1>", args);
  }
  
  @Override
  public void h2(String fmt, Object... args) {
    this.out("<h2>" + fmt + "</h2>", args);
  }
  
  @Override
  public void h3(String fmt, Object... args) {
    this.out("<h3>" + fmt + "</h3>", args);
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
      out("<p>Code from %s:%s executed in %.2f seconds: <br/>",
          callingFrame.getFileName(), callingFrame.getLineNumber(), result.obj.seconds());
      out("<pre>");
      out(sourceCode);
      out("</pre>");
      
      if (!result.log.isEmpty()) {
        out("Logging: <br/>");
        out("<pre>");
        String logSrc = result.log;
        if (logSrc.length() > maxLog * 2) {
          logSrc = logSrc.substring(0, maxLog) + String.format("\n...skipping %s bytes...\n", logSrc.length() - 2 * maxLog) + logSrc.substring(logSrc.length() - maxLog);
        } else if (logSrc.length() > 0) {
          logSrc = logSrc;
        }
        out(logSrc);
        out("</pre>");
      }
      out("");
      
      Object eval = result.obj.obj;
      if(null != eval) {
        out("Returns: <br/>");
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
          str = ((TableOutput) eval).toHtmlTable();
          escape = false;
        } else {
          str = eval.toString();
          escape = true;
        }
        if (escape) out("<pre>");
        String valTxt = str;
        if (escape && valTxt.length() > maxLog) {
          valTxt = valTxt.substring(0, maxLog) + String.format("... and %s more bytes", valTxt.length() - maxLog);
        }
        out(valTxt);
        if (escape) out("</pre>");
        out("\n\n");
        if (eval instanceof Throwable) {
          throw new RuntimeException((Throwable) result.obj.obj);
        }
      }
      out("</p>");
      return (T) eval;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public String image(BufferedImage rawImage, String caption) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    String thisImage = UUID.randomUUID().toString().substring(0,8);
    File file = new File(getResourceDir(), "img" + thisImage + ".png");
    BufferedImage stdImage = Util.resize(rawImage);
    if (stdImage != rawImage) {
      ImageIO.write(rawImage, "png", new File(getResourceDir(), "raw" + thisImage + ".png"));
    }
    ImageIO.write(stdImage, "png", file);
    return "<img src='etc/" + file.getName() + "' alt='" + caption + "'/>";
  }
  
  public File getResourceDir() {
    File etc = new File(this.workingDir, "etc");
    etc.mkdirs();
    return etc;
  }
  
  @Override
  public void close() throws IOException {
    out("</body></html>");
    if(null != primaryOut) primaryOut.close();
  }
}
