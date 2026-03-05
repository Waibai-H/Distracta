package com.distracta.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigRepository {

    private static final Logger log = LoggerFactory.getLogger(ConfigRepository.class);

    private static final String CONFIG_DIR  = System.getProperty("user.home") + "\\.distracta";
    private static final String CONFIG_FILE = CONFIG_DIR + "\\config.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<String> loadBlockedSites() {
        Path configPath = Path.of(CONFIG_FILE);

        if (!Files.exists(configPath)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(configPath);
            DistractaConfig config = gson.fromJson(json, DistractaConfig.class);

            if (config == null || config.blockedSites == null) {
                return new ArrayList<>();
            }

            return config.blockedSites;

        } catch (IOException e) {
            log.error("Erro ao ler configuração: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveBlockedSites(List<String> sites) {
        try {
            Files.createDirectories(Path.of(CONFIG_DIR));

            DistractaConfig config = new DistractaConfig();
            config.blockedSites = sites;

            Files.writeString(Path.of(CONFIG_FILE), gson.toJson(config));

        } catch (IOException e) {
            log.error("Erro ao salvar configuração: {}", e.getMessage());
            System.out.println("❌ Não foi possível salvar a configuração.");
        }
    }

    private static class DistractaConfig {
        List<String> blockedSites = new ArrayList<>();
    }
}