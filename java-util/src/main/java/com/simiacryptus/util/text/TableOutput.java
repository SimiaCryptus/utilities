package com.simiacryptus.util.text;

import com.simiacryptus.util.data.DoubleStatistics;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TableOutput {

    Map<String,Class<?>> schema = new LinkedHashMap<>();
    List<Map<String,Object>> rows = new ArrayList<>();

    public static TableOutput create(Map<String,Object>... rows) {
        TableOutput table = new TableOutput();
        Arrays.stream(rows).forEach(table::putRow);
        return table;

    }

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
        try(FileOutputStream file = new FileOutputStream(new File(path, "data.tsv"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                printStream.println(toTextTable());
            }
        }
        List<Map.Entry<String, Class<?>>> scalarCols = schema.entrySet().stream()
                .filter(e -> Number.class.isAssignableFrom(e.getValue()))
                .collect(Collectors.toList());
        try(FileOutputStream file = new FileOutputStream(new File(path, "tensors.tsv"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                for(Map<String, Object> row : rows) {
                    printStream.println(scalarCols.stream()
                            .map(e->((Number)row.getOrDefault(e.getKey(), 0)).doubleValue())
                            .map(x->x.toString()).collect(Collectors.joining("\t")));
                }
            }
        }
        List<Entry<String, Class<?>>> metadataCols = schema.entrySet().stream()
        		.filter(e->String.class.isAssignableFrom(e.getValue()))
        		.collect(Collectors.toList());
        try(FileOutputStream file = new FileOutputStream(new File(path, "metadata.tsv"))) {
            try(PrintStream printStream = new PrintStream(file)) {
            	if(1 < metadataCols.size()) {
            		printStream.println(metadataCols.stream().map(e->e.getKey()).collect(Collectors.joining("\t")));
            	}
                for(Map<String, Object> row : rows) {
                    printStream.println(metadataCols.stream()
                            .map(e->((String)row.getOrDefault(e.getKey(), "")))
                            .collect(Collectors.joining("\t")));
                }
            }
        }
        List<Entry<String, Class<?>>> urlCols = schema.entrySet().stream()
        		.filter(e->URL.class.isAssignableFrom(e.getValue()))
        		.collect(Collectors.toList());
        try(FileOutputStream file = new FileOutputStream(new File(path, "bookmarks.txt"))) {
            try(PrintStream printStream = new PrintStream(file)) {
                for(Map<String, Object> row : rows) {
                    printStream.println(urlCols.stream()
                            .map(e->((URL)row.get(e.getKey())).toString())
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
                        "        "+scalarCols.size()+"\n" +
                        "      ],\n" +
                        "      \"tensorPath\": \"" + new URL(baseUrl,"tensors.tsv") + 
                        ((0==metadataCols.size())?"":("\",\n      \"metadataPath\": \"" + new URL(baseUrl,"metadata.tsv"))) + 
                        "\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
            }
        }
        if(0 < urlCols.size()) {
            try(FileOutputStream file = new FileOutputStream(new File(path, "config_withLinks.json"))) {
                try(PrintStream printStream = new PrintStream(file)) {
                    printStream.println("{\n" +
                            "  \"embeddings\": [\n" +
                            "    {\n" +
                            "      \"tensorName\": \""+ path.getName() +"\",\n" +
                            "      \"tensorShape\": [\n" +
                            "        "+rows.size()+",\n" +
                            "        "+scalarCols.size()+"\n" +
                            "      ],\n" +
                            "      \"tensorPath\": \"" + new URL(baseUrl,"tensors.tsv") + 
                            ((0==metadataCols.size())?"":("\",\n      \"metadataPath\": \"" + new URL(baseUrl,"metadata.tsv"))) + 
                            ("\",\n      \"bookmarksPath\": \"" + new URL(baseUrl,"bookmarks.txt")) + 
                            "\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}");
                }
            }
        }

    }

    public String toTextTable() {
        try(ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            try(PrintStream printStream = new PrintStream(buffer)) {
                String formatString = schema.entrySet().stream()
                        .map(e -> {
                            switch (e.getValue().getSimpleName()) {
                                case "String":
                                    return "%-" + rows.stream().mapToInt(x -> x.getOrDefault(e.getKey(), "").toString().length()).max().getAsInt() + "s";
                                case "Integer":
                                    return "%6d";
                                case "Double":
                                    return "%.4f";
                                default:
                                    return "%s";
                            }
                        }).collect(Collectors.joining(" | "));
                printStream.println(schema.entrySet().stream().map(x->x.getKey()).collect(Collectors.joining(" | ")).trim());
                printStream.println(schema.entrySet().stream().map(x->x.getKey()).map(x->{
                    char[] t = new char[x.length()];
                    Arrays.fill(t, '-');
                    return new String(t);
                }).collect(Collectors.joining(" | ")).trim());
                for(Map<String, Object> row : rows) {
                    printStream.println(String.format(formatString, schema.entrySet().stream().map(e->row.get(e.getKey())).toArray()));
                }
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TableOutput calcNumberStats() {
        TableOutput tableOutput = new TableOutput();
        schema.entrySet().stream().filter(x->Number.class.isAssignableFrom(x.getValue())).map(col->{
            String key = col.getKey();
            DoubleStatistics stats = rows.stream().filter(x->x.containsKey(key)).map(x -> (Number)x.get(key)).collect(DoubleStatistics.NUMBERS);
            LinkedHashMap<String, Object> row = new LinkedHashMap<>();
            row.put("field", key);
            row.put("sum", stats.getSum());
            row.put("avg", stats.getAverage());
            row.put("stddev", stats.getStandardDeviation());
            row.put("nulls", rows.size() - stats.getCount());
            return row;
        }).sorted(Comparator.comparing(x->x.get("field").toString()))
        .forEach(row->tableOutput.putRow(row));
        return tableOutput;
    }
}
