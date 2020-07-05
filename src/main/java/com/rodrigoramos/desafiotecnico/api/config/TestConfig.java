package com.rodrigoramos.desafiotecnico.api.config;

import com.rodrigoramos.desafiotecnico.api.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Configuration
@Profile("test")
public class TestConfig {

    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);
    private final DatabaseService dbService;

    @Autowired
    public TestConfig(DatabaseService dbService) {
        this.dbService = dbService;
    }

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
                logger.info("File: {}.", listOfFile.getName());
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