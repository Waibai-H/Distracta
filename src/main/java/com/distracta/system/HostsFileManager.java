package com.distracta.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class HostsFileManager {

    private static final Logger log = LoggerFactory.getLogger(HostsFileManager.class);

    private static final String HOSTS_PATH   = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    private static final String MARKER_START = "# === DISTRACTA START ===";
    private static final String MARKER_END   = "# === DISTRACTA END ===";

    public boolean blockSites(List<String> domains) {
        try {
            String existing = removeOurLines(readHostsFile());

            StringBuilder block = new StringBuilder();
            block.append("\n").append(MARKER_START).append("\n");
            for (String domain : domains) {
                block.append("0.0.0.0 ").append(domain).append("\n");
                block.append("0.0.0.0 www.").append(domain).append("\n");
            }
            block.append(MARKER_END).append("\n");

            writeHostsFile(existing + block);
            flushDnsCache();

            log.info("Sites bloqueados: {}", domains);
            return true;

        } catch (IOException e) {
            log.error("Erro ao bloquear sites: {}", e.getMessage());
            return false;
        }
    }

    public boolean unblockSites() {
        try {
            writeHostsFile(removeOurLines(readHostsFile()));
            flushDnsCache();

            log.info("Sites desbloqueados.");
            return true;

        } catch (IOException e) {
            log.error("Erro ao desbloquear sites: {}", e.getMessage());
            return false;
        }
    }

    private String readHostsFile() throws IOException {
        return Files.readString(Path.of(HOSTS_PATH));
    }

    private void writeHostsFile(String content) throws IOException {
        Files.writeString(Path.of(HOSTS_PATH), content);
    }

    private String removeOurLines(String content) {
        return content.replaceAll(
                "(?s)\\n?" + MARKER_START + ".*?" + MARKER_END + "\\n?",
                ""
        );
    }

    private void flushDnsCache() {
        runCommand("ipconfig /flushdns");
        runCommand("net stop dnscache");
        runCommand("net start dnscache");
    }

    private void runCommand(String command) {
        try {
            Runtime.getRuntime().exec(command).waitFor();
        } catch (Exception e) {
            log.warn("Erro ao executar '{}': {}", command, e.getMessage());
        }
    }
}