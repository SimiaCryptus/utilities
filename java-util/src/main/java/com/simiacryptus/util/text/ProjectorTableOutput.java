package com.simiacryptus.util.text;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ProjectorTableOutput {

    Map<String,Class<?>> schema = new LinkedHashMap<>();
    List<Map<String,Object>> rows = new ArrayList<>();

    public void putRow(Map<String,Object> properties) {
        for(Map.Entry<String, Object> prop : properties.entrySet()) {
            String propKey = prop.getKey();
            Class<?> propClass = prop.getValue().getClass();
            if(!propClass.equals(schema.getOrDefault(propKey, propClass))) {
                throw new RuntimeException("Schema mismatch for " + propKey);
            }
            schema.putIfAbsent(propKey, propClass);
        }
        rows.add(new HashMap<>(properties));
    }

    public void writeProjectorData(File path, URL baseUrl) throws IOException {
        path.mkdirs();
        List<Map.Entry<String, Class<?>>> scalaCols = schema.entrySet().stream()
                .filter(e -> Number.class.isAssignableFrom(e.getValue()))
                .collect(Collectors.toList());
        try(FileOutputStream file = new FileOutputStream(new File(path, "tensors.tsv"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                printStream.println(scalaCols.stream()
                        .map(e->e.getKey()).collect(Collectors.joining("\t")));
                for(Map<String, Object> row : rows) {
                    printStream.println(scalaCols.stream()
                            .map(e->((Number)row.get(e.getKey())).doubleValue())
                            .map(x->x.toString()).collect(Collectors.joining("\t")));
                }
            }
        }
        try(FileOutputStream file = new FileOutputStream(new File(path, "metadata.tsv"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                printStream.println(schema.entrySet().stream()
                        .filter(e->String.class.isAssignableFrom(e.getValue()))
                        .map(e->e.getKey()).collect(Collectors.joining("\t")));
                for(Map<String, Object> row : rows) {
                    printStream.println(schema.entrySet().stream()
                            .filter(e->String.class.isAssignableFrom(e.getValue()))
                            .map(e->((String)row.get(e.getKey())))
                            .collect(Collectors.joining("\t")));
                }
            }
        }
        try(FileOutputStream file = new FileOutputStream(new File(path, "config.json"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                printStream.println("{\n" +
                        "  \"embeddings\": [\n" +
                        "    {\n" +
                        "      \"tensorName\": \""+ path.getName() +"\",\n" +
                        "      \"tensorShape\": [\n" +
                        "        "+rows.size()+",\n" +
                        "        "+scalaCols.size()+"\n" +
                        "      ],\n" +
                        "      \"tensorPath\": \"" + new URL(baseUrl,"tensors.tsv") + "\",\n" +
                        "      \"metadataPath\": \"" + new URL(baseUrl,"metadata.tsv") + "\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
            }
        }
    }

    public String toTextTable() throws IOException {
        try(ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            try(PrintStream printStream = new PrintStream(buffer)) {
                String formatString = schema.entrySet().stream()
                        .map(e -> {
                            switch (e.getValue().getSimpleName()) {
                                case "String":
                                    return "%" + rows.stream().mapToInt(x -> x.getOrDefault(e.getKey(), "").toString().length()).max().getAsInt() + "s";
                                case "Integer":
                                    return "%6d";
                                case "Double":
                                    return "%.4f";
                                default:
                                    return "%s";
                            }
                        }).collect(Collectors.joining("\t"));
                printStream.println(schema.entrySet().stream().map(x->x.getKey()).collect(Collectors.joining("\t")));
                for(Map<String, Object> row : rows) {
                    printStream.println(String.format(formatString, schema.entrySet().stream().map(e->row.get(e.getKey())).toArray()));
                }
            }
            return buffer.toString();
        }
    }
}
