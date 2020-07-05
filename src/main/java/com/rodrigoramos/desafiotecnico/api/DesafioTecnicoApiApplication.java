package com.rodrigoramos.desafiotecnico.api;

import com.rodrigoramos.desafiotecnico.api.service.WatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioTecnicoApiApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DesafioTecnicoApiApplication.class);
    public final WatcherService watcherService;

    @Autowired
    public DesafioTecnicoApiApplication(WatcherService watcherService) {
        this.watcherService = watcherService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("I'm running - WatchService!!!");
        new Thread(watcherService, "watcher-service").start();
    }
}
