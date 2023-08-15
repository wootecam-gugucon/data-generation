package com.gugucon.datageneration.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVReader<T> {

    private final Parser<T> parser;
    private final Logger log = LoggerFactory.getLogger(CSVReader.class);

    public CSVReader(final Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readCSV(String path, int number) {
        List<T> csvList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            line = br.readLine(); // 제목 버림
            for (int i=0; i<number; i++) {
                if (i % 1000 == 0) {
                    log.info("{}개째 데이터 파싱중", i);
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
