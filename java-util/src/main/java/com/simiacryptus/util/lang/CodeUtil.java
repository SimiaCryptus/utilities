/*
 * Copyright (c) 2018 by Andrew Charneski.
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

package com.simiacryptus.util.lang;

import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The type Code util.
 */
public class CodeUtil {
  private static final List<String> sourceFolders = Arrays.asList("src/main/java", "src/test/java", "src/main/scala", "src/test/scala");
  /**
   * The constant projectRoot.
   */
  @javax.annotation.Nonnull
  public static File projectRoot = new File(System.getProperty("codeRoot", ".."));
  private static final List<File> codeRoots = com.simiacryptus.util.lang.CodeUtil.loadCodeRoots();
  
  /**
   * Find file file.
   *
   * @param clazz the clazz
   * @return the file
   */
  @Nullable
  public static File findFile(@Nullable final Class<?> clazz) {
    if (null == clazz) return null;
    String name = clazz.getName();
    if (null == name) return null;
    final String path = name.replaceAll("\\.", "/").replaceAll("\\$.*", "");
    return com.simiacryptus.util.lang.CodeUtil.findFile(path + ".java");
  }
  
  
  /**
   * Find file file.
   *
   * @param callingFrame the calling frame
   * @return the file
   */
  @javax.annotation.Nonnull
  public static File findFile(@javax.annotation.Nonnull final StackTraceElement callingFrame) {
    @javax.annotation.Nonnull final String[] packagePath = callingFrame.getClassName().split("\\.");
    @javax.annotation.Nonnull final String path = Arrays.stream(packagePath).limit(packagePath.length - 1).collect(Collectors.joining(File.separator)) + File.separator + callingFrame.getFileName();
    return com.simiacryptus.util.lang.CodeUtil.findFile(path);
  }
  
  /**
   * Find file file.
   *
   * @param path the path
   * @return the file
   */
  @javax.annotation.Nonnull
  public static File findFile(@javax.annotation.Nonnull final String path) {
    for (final File root : com.simiacryptus.util.lang.CodeUtil.codeRoots) {
      @javax.annotation.Nonnull final File file = new File(root, path);
      if (file.exists()) return file;
    }
    throw new RuntimeException(String.format("Not Found: %s; Project Roots = %s", path, com.simiacryptus.util.lang.CodeUtil.codeRoots));
  }
  
  /**
   * Gets indent.
   *
   * @param txt the txt
   * @return the indent
   */
  @javax.annotation.Nonnull
  public static String getIndent(@javax.annotation.Nonnull final String txt) {
    @javax.annotation.Nonnull final Matcher matcher = Pattern.compile("^\\s+").matcher(txt);
    return matcher.find() ? matcher.group(0) : "";
  }
  
  /**
   * Gets heapCopy text.
   *
   * @param callingFrame the calling frame
   * @return the heapCopy text
   * @throws IOException the io exception
   */
  public static String getInnerText(@javax.annotation.Nonnull final StackTraceElement callingFrame) throws IOException {
    try {
      @javax.annotation.Nonnull final File file = com.simiacryptus.util.lang.CodeUtil.findFile(callingFrame);
      assert null != file;
      final int start = callingFrame.getLineNumber() - 1;
      final List<String> allLines = Files.readAllLines(file.toPath());
      final String txt = allLines.get(start);
      @javax.annotation.Nonnull final String indent = com.simiacryptus.util.lang.CodeUtil.getIndent(txt);
      @javax.annotation.Nonnull final ArrayList<String> lines = new ArrayList<>();
      for (int i = start + 1; i < allLines.size() && (com.simiacryptus.util.lang.CodeUtil.getIndent(allLines.get(i)).length() > indent.length() || allLines.get(i).trim().isEmpty()); i++) {
        final String line = allLines.get(i);
        lines.add(line.substring(Math.min(indent.length(), line.length())));
      }
      return lines.stream().collect(Collectors.joining("\n"));
    } catch (@javax.annotation.Nonnull final Throwable e) {
      return "";
    }
  }
  
  /**
   * Gets javadoc.
   *
   * @param clazz the clazz
   * @return the javadoc
   */
  public static String getJavadoc(@Nullable final Class<?> clazz) {
    try {
      if (null == clazz) return null;
      @Nullable final File source = com.simiacryptus.util.lang.CodeUtil.findFile(clazz);
      if (null == source) return clazz.getName() + " not found";
      final List<String> lines = IOUtils.readLines(new FileInputStream(source), Charset.forName("UTF-8"));
      final int classDeclarationLine = IntStream.range(0, lines.size())
        .filter(i -> lines.get(i).contains("class " + clazz.getSimpleName())).findFirst().getAsInt();
      final int firstLine = IntStream.rangeClosed(1, classDeclarationLine).map(i -> classDeclarationLine - i)
        .filter(i -> !lines.get(i).matches("\\s*[/\\*@].*")).findFirst().orElse(-1) + 1;
      final String javadoc = lines.subList(firstLine, classDeclarationLine).stream()
        .filter(s -> s.matches("\\s*[/\\*].*"))
        .map(s -> s.replaceFirst("^[ \t]*[/\\*]+", "").trim())
        .filter(x -> !x.isEmpty()).reduce((a, b) -> a + "\n" + b).orElse("");
      return javadoc.replaceAll("<p>", "\n");
    } catch (@javax.annotation.Nonnull final Throwable e) {
      e.printStackTrace();
      return "";
    }
  }
  
  private static List<File> loadCodeRoots() {
    return Stream.concat(
      Stream.of(com.simiacryptus.util.lang.CodeUtil.projectRoot),
      Arrays.stream(com.simiacryptus.util.lang.CodeUtil.projectRoot.listFiles())
        .filter(file -> file.exists() && file.isDirectory())
        .collect(Collectors.toList()).stream()).flatMap(x -> scanProject(x).stream())
      .distinct().collect(Collectors.toList());
  }
  
  private static List<File> scanProject(File file) {
    return sourceFolders.stream().map(name -> new File(file, name))
      .filter(f -> f.exists() && f.isDirectory())
      .collect(Collectors.toList());
  }
}
