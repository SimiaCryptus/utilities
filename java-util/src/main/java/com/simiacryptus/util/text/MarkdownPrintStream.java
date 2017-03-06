package com.simiacryptus.util.text;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MarkdownPrintStream extends PrintStream {

    private final List<PrintStream> teeStreams = new ArrayList<>();

    public MarkdownPrintStream(OutputStream inner) {
        super(inner);
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
        try {
            StackTraceElement callingFrame = Thread.currentThread().getStackTrace()[2];
            SysOutInterceptor.LoggedResult<T> result = SysOutInterceptor.withOutput(() -> fn.get());
            String sourceCode = getInnerText(callingFrame);
            out("Code: ");
            out("```java");
            out("  " + sourceCode.replaceAll("\n","\n  "));
            out("```");
            T eval = result.obj;
            out("Returns: ");
            out("      " + eval.toString().replaceAll("\n","\n      "));
            if(!result.log.isEmpty()) {
                out("Logging: ");
                out("      " + result.log.replaceAll("\n","\n      "));
            }
            return eval;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getInnerText(StackTraceElement callingFrame) throws IOException {
        String[] packagePath = callingFrame.getClassName().split("\\.");
        String path = Arrays.stream(packagePath).limit(packagePath.length - 1).collect(Collectors.joining(File.separator)) + File.separator + callingFrame.getFileName();
        File file = findFile(path);
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

    private String getIndent(String txt) {

        Matcher matcher = Pattern.compile("^\\s+").matcher(txt);
        boolean start = matcher.find();
        return matcher.group(0);
    }

    private static List<File> codeRoots = Arrays.asList(
            "src/main/java", "src/test/java"
    ).stream().map(x->new File(x)).collect(Collectors.toList());
    private File findFile(String path) {
        for(File root : codeRoots) {
            File file = new File(root, path);
            if(file.exists()) return file;
        }
        return null;
    }

}
