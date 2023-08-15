package com.gugucon.datageneration.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVReader<T> {

    private final Parser<T> parser;

    public CSVReader(final Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readCSV(String path) {
        List<T> csvList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            br.readLine(); // read title;
            for (int i=0; i<10000; i++) {
                if (i % 1000 == 0) {
                    System.out.println(i +"개째 데이터 파싱중");
                }
                line = br.readLine();
                csvList.add(parser.parse(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvList;
    }

}
