package com.rodrigoramos.desafiotecnico.api.config;

import com.rodrigoramos.desafiotecnico.api.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Log4j2
@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    private final DatabaseService dbService;

    @Value("${data.caminho-entrada}")
    private String pathStr;

    @Bean
    public boolean instantiateDatabase() {
        boolean fileExists = Boolean.FALSE;
        File folder = new File(pathStr);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            String extension = getFileExtension(listOfFile);
            if (listOfFile.isFile() && extension.equals("dat")) {
                dbService.instantiateDatabase(listOfFile.getName());
                //log.info("File: {}." + listOfFile.getName());
                fileExists = Boolean.TRUE;
            }
        }
        if (Boolean.TRUE.equals(fileExists))
            dbService.generateReport();
        return true;
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        String strDot = ".";

        if (fileName.lastIndexOf(strDot) != -1 && fileName.lastIndexOf(strDot) != 0)
            return fileName.substring(fileName.lastIndexOf(strDot) + 1);
        else return "";
    }

}