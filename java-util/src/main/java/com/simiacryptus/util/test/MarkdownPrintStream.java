package com.simiacryptus.util.test;

import com.simiacryptus.util.lang.TimedResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MarkdownPrintStream extends PrintStream {

    private final List<PrintStream> teeStreams = new ArrayList<>();
    private final File file;
    private final String methodName;
    private int imageNumber = 0;

    public static MarkdownPrintStream get(Object source) {
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[2];
            String className = null==source?callingFrame.getClassName():source.getClass().getCanonicalName();
            String methodName = callingFrame.getMethodName();
            File path = new File(mkString(File.separator, "reports", className, methodName + ".md"));
            path.getParentFile().mkdirs();
            return new MarkdownPrintStream(path, methodName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mkString(String separator, String... strs) {
        return Arrays.asList(strs).stream().collect(Collectors.joining(separator));
    }

    public MarkdownPrintStream(File path, String methodName) throws FileNotFoundException {
        super(new FileOutputStream(path));
        this.methodName = methodName;
        this.file = path;
    }

    public MarkdownPrintStream addCopy(PrintStream out) {
        teeStreams.add(out);
        return this;
    }

    @Override
    public void println(String x) {
        super.println(x);
        teeStreams.forEach(t->t.println(x));
    }

    public void out(String fmt, Object... args) {
        this.println(0==args.length?fmt:String.format(fmt, args));
    }

    public void p(String fmt, Object... args) {
        this.out(fmt + "\n", args);
    }

    public void h1(String fmt, Object... args) {
        this.out("# " + fmt, args);
    }

    public void h2(String fmt, Object... args) {
        this.out("## " + fmt, args);
    }

    public void h3(String fmt, Object... args) {
        this.out("### " + fmt, args);
    }

    public <T> T code(Supplier<T> fn) {
        return code(fn, 16 * 1024, 3);
    }

    public <T> T code(Supplier<T> fn, int maxLog, int framesNo) {
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[framesNo];
            String sourceCode = getInnerText(callingFrame);
            SysOutInterceptor.LoggedResult<TimedResult<Object>> result = SysOutInterceptor.withOutput(() -> {
                try {
                    return TimedResult.time(() -> fn.get());
                } catch (Throwable e) {
                    return new TimedResult(e,0);
                }
            });
            out("Code from [%s:%s](%s#L%s) executed in %.2f seconds: ",
                    callingFrame.getFileName(), callingFrame.getLineNumber(),
                    pathTo(file.getParentFile(), findFile(callingFrame)), callingFrame.getLineNumber(), result.obj.seconds());
            out("```java");
            out("  " + sourceCode.replaceAll("\n","\n  "));
            out("```");

            if(!result.log.isEmpty()) {
                out("Logging: ");
                out("```");
                String logSrc = result.log;
                if(logSrc.length() > maxLog * 2) {
                    logSrc = logSrc.substring(0, maxLog) + String.format("\n...skipping %s bytes...\n", logSrc.length() - 2 * maxLog) + logSrc.substring(logSrc.length()-maxLog);
                } else if(logSrc.length() > 0){
                    logSrc = logSrc;
                }
                logSrc = logSrc.replaceAll("\n", "\n    ");
                out("    " + logSrc);
                out("```");
            }
            out("");

            Object eval = result.obj.obj;
            out("Returns: ");
            String str;
            boolean escape;
            if(eval instanceof Throwable) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ((Throwable) eval).printStackTrace(new PrintStream(out));
                str = new String(out.toByteArray(), "UTF-8");
                escape = true;
            } else if(eval instanceof BufferedImage) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                File file = new File(this.file.getParentFile(), this.methodName + "." + (imageNumber++) + ".png");
                ImageIO.write((BufferedImage) eval, "png", file);
                str = "![Result]("+file.getName()+")";
                escape = false;
            } else {
                str = eval.toString();
                escape = true;
            }
            if(escape) out("```");
            String valTxt = escape?str:str.replaceAll("\n", "\n    ");
            if(escape && valTxt.length() > maxLog) {
                valTxt = valTxt.substring(0, maxLog) + String.format("... and %s more bytes", valTxt.length() - maxLog);
            }
            out(escape?("    " + valTxt):valTxt);
            if(escape) out("```");
            if(result.obj.obj instanceof Throwable) {
                throw new RuntimeException((Throwable) result.obj.obj);
            }
            return (T) eval;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String pathTo(File from, File to) {
        Path fromUrl = from.toPath();
        Path toUrl = to.toPath();
        Path relativize = fromUrl.relativize(toUrl);
        return relativize.toString().replaceAll("\\\\","/");
    }

    public void code(Runnable fn) {
        code(fn, 4 * 1024, 3);
    }

    public void code(Runnable fn, int maxLog, int framesNo) {
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[framesNo];
            String sourceCode = getInnerText(callingFrame);
            TimedResult<SysOutInterceptor.LoggedResult<Void>> result = TimedResult.time(() -> SysOutInterceptor.withOutput(() -> fn.run()));
            out("Code from [%s:%s](%s#L%s) executed in %.2f seconds: ",
                    callingFrame.getFileName(), callingFrame.getLineNumber(),
                    pathTo(file.getParentFile(), findFile(callingFrame)), callingFrame.getLineNumber(), result.seconds());
            out("```java");
            out("  " + sourceCode.replaceAll("\n","\n  "));
            out("```");
            if(!result.obj.log.isEmpty()) {
                out("Logging: ");
                out("```");
                String logSrc = result.obj.log.replaceAll("\n", "\n    ");
                if(logSrc.length() > maxLog) {
                    logSrc = logSrc.substring(0, maxLog) + String.format("... and %s more bytes", logSrc.length() - maxLog);
                }
                out("    " + logSrc);
                out("```");
            }
            out("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getInnerText(StackTraceElement callingFrame) throws IOException {
        File file = findFile(callingFrame);
        assert(null != file);
        int start = callingFrame.getLineNumber()-1;
        int end = 3 + start;
        List<String> allLines = Files.readAllLines(file.toPath());
        List<String> window = allLines.subList(start, end);
        String txt = allLines.get(start);
        String indent = getIndent(txt);
        ArrayList<String> lines = new ArrayList<>();
        for(int i=start+1;getIndent(allLines.get(i)).length()>indent.length();i++) {
            lines.add(allLines.get(i).substring(indent.length()));
        }
        return lines.stream().collect(Collectors.joining("\n"));
    }

    private static File findFile(StackTraceElement callingFrame) {
        return findFile(callingFrame.getClassName(), callingFrame.getFileName());
    }

    private static File findFile(String className, String fileName) {
        String[] packagePath = className.split("\\.");
        String path = Arrays.stream(packagePath).limit(packagePath.length - 1).collect(Collectors.joining(File.separator)) + File.separator + fileName;
        return findFile(path);
    }

    private static String getIndent(String txt) {
        Matcher matcher = Pattern.compile("^\\s+").matcher(txt);
        return matcher.find() ?matcher.group(0):"";
    }

    private static List<File> codeRoots = Arrays.asList(
            "src/main/java", "src/test/java"
    ).stream().map(x->new File(x)).collect(Collectors.toList());
    private static File findFile(String path) {
        for(File root : codeRoots) {
            File file = new File(root, path);
            if(file.exists()) return file;
        }
        return null;
    }

}
