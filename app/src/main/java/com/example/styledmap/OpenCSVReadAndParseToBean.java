package com.example.map_crime;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class OpenCSVReadAndParseToBean {
        private static final String SAMPLE_CSV_FILE_PATH = "C:/Users/AllBen/Desktop/CS275-Final-project/StyledMap/app/src/main/res/raw/las6monthstrimmedbycrimenweapon.csv";

        public static void main() throws IOException {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            ) {
                CsvToBean<CrimeEntity> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CrimeEntity.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<CrimeEntity> csvUserIterator = csvToBean.iterator();

                while (csvUserIterator.hasNext()) {
                    CrimeEntity crime = csvUserIterator.next();
                    crime.mDate = crime.getDate();
                    crime.mCrimeType = crime.getCrimeType();
                    crime.mWeapon = crime.getWeapon();
                    crime.mLatitude = crime.getLatitude();
                    crime.mLongitude = crime.getLongitude();
                    /*System.out.println("Date : " + crime.getDate());
                    System.out.println("Crime Code Desc : " + crime.getCrimeType());
                    System.out.println("Weapon : " + crime.getWeapon());
                    System.out.println("Latitude : " + crime.getLatitude());
                    System.out.println("Longitude : " + crime.getLongitude());
                    System.out.println("==========================");*/
                }
            }
        }

    /*public List makeList (Context context) {
        List<CrimeEntity> beans = new CsvToBeanBuilder(FileReader(getCSV(Context context)))
                .withType(CrimeEntity.class).build().parse();
        return beans;
    }*/

    }
