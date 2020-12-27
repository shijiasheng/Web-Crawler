package com.tongji;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        String pathname = "C:/Users/12549/Desktop/数据仓库/data.csv";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                System.out.println(Arrays.toString(lines));
                TimeUnit.SECONDS.sleep(5);

                //对一条电影数据进行读取，并且存放到不同的关系表中
                String id = lines[0];
                String title = lines[0];
                String releaseDate = lines[0];
                String week = lines[0];
                String genres = lines[0];
                String director = lines[0];
                String producers = lines[0];
                String actor = lines[0];
                String supportingActors = lines[0];
                String ratings = lines[0];
                String mediaFormat = lines[0];
                String runRime = lines[0];
                String MPAARating = lines[0];
                String subtitles = lines[0];
                String studio = lines[0];
                String itemModelNumber = lines[0];
                String dateFirstAvailable = lines[0];
                String audioLanguages = lines[0];
                String linkId = lines[0];
                String linkTitle = lines[0];


            }

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
