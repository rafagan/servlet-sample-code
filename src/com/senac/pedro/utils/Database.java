package com.senac.pedro.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private String path;

    public Database(String path) {
        this.path = path;
    }

    public List<List<String>> readData() {
        List<List<String>> result = new ArrayList<>();
        String line;
        String separator = "\\|";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                result.add(new ArrayList<>(Arrays.asList(data)));

                System.out.println("Line " + lineNumber++ + " has been read: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return result;
    }

    public void saveData(List<List<String>> data) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);

            int lineNumber = 0;
            for(List<String> line: data) {
                StringBuilder lineData = new StringBuilder();

                for(int i = 0; i < line.size(); i++) {
                    String item = line.get(i);
                    String separator = i < line.size() - 1 ? "|" : "";
                    lineData.append(item).append(separator);
                }

                System.out.println("Line " + lineNumber++ + " has been written: " + lineData.toString());
                lineData.append('\n');
                fileWriter.write(lineData.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
