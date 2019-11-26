package com.example.map_crime;

import android.provider.SyncStateContract;
import com.example.map_crime.CsvTransfer;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BeanExamples {

    public static List<CsvBean> beanBuilderExample(Path path, Class clazz) {
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        return beanBuilderExample(path, clazz, ms);
    }

    public static List<CsvBean> beanBuilderExample(Path path, Class clazz, MappingStrategy ms) {
        CsvTransfer csvTransfer = new CsvTransfer();
        try {
            ms.setType(clazz);

            Reader reader = Files.newBufferedReader(path);
            CsvToBean cb = new CsvToBeanBuilder(reader).withType(clazz)
                    .withMappingStrategy(ms)
                    .build();

            csvTransfer.setCsvList(cb.parse());
            reader.close();

        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return csvTransfer.getCsvList();
    }
}
