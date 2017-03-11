package com.simiacryptus.util.text;

import com.simiacryptus.util.lang.TimedResult;

import java.io.*;
import java.net.URI;
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

    public static MarkdownPrintStream get() {
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[2];
            File path = new File(mkString(File.separator, "reports", callingFrame.getClassName(), callingFrame.getMethodName() + ".md"));
            path.getParentFile().mkdirs();
            return new MarkdownPrintStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mkString(String separator, String... strs) {
        return Arrays.asList(strs).stream().collect(Collectors.joining(separator));
    }

    public MarkdownPrintStream(File path) throws FileNotFoundException {
        super(new FileOutputStream(path));
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
        return code(fn, 4 * 1024, 3);
    }

    public <T> T code(Supplier<T> fn, int maxLog, int framesNo) {
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[framesNo];
            TimedResult<SysOutInterceptor.LoggedResult<T>> result = TimedResult.time(() -> SysOutInterceptor.withOutput(() -> fn.get()));
            String sourceCode = getInnerText(callingFrame);
            out("Code from [%s:%s](%s#L%s) executed in %.2f seconds: ",
                    callingFrame.getFileName(), callingFrame.getLineNumber(),
                    pathTo(file.getParentFile(), findFile(callingFrame)), callingFrame.getLineNumber(), result.seconds());
            out("```java");
            out("  " + sourceCode.replaceAll("\n","\n  "));
            out("```");
            T eval = result.obj.obj;
            out("Returns: ");
            out("```");
            String valTxt = eval.toString().replaceAll("\n", "\n    ");
            if(valTxt.length() > maxLog) {
                valTxt = valTxt.substring(0, maxLog) + String.format("... and %s more bytes", valTxt.length() - maxLog);
            }
            out("    " + valTxt);
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
            return eval;
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
