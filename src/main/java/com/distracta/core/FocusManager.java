package com.distracta.core;

import com.distracta.data.ConfigRepository;
import com.distracta.system.HostsFileManager;

import java.util.List;

public class FocusManager {

    private final ConfigRepository configRepo;
    private final HostsFileManager hostsManager;

    public FocusManager() {
        this.configRepo   = new ConfigRepository();
        this.hostsManager = new HostsFileManager();
    }

    public void startFocus() {
        List<String> sites = configRepo.loadBlockedSites();

        if (sites.isEmpty()) {
            System.out.println("⚠️  Nenhum site na lista de bloqueio.");
            System.out.println("   Use 'distracta add youtube.com' para adicionar um site.");
            return;
        }

        System.out.println("🎯 Ativando modo foco...");
        boolean success = hostsManager.blockSites(sites);

        if (success) {
            System.out.println("✅ Modo foco ativado! " + sites.size() + " site(s) bloqueado(s):");
            sites.forEach(site -> System.out.println("   🚫 " + site));
        } else {
            System.out.println("❌ Erro ao bloquear sites. Tente executar como Administrador.");
        }
    }

    public void stopFocus() {
        System.out.println("🔓 Desativando modo foco...");
        boolean success = hostsManager.unblockSites();

        if (success) {
            System.out.println("✅ Modo foco desativado! Sites liberados.");
        } else {
            System.out.println("❌ Erro ao desbloquear. Tente executar como Administrador.");
        }
    }

    public void addSite(String domain) {
        domain = cleanDomain(domain);
        List<String> sites = configRepo.loadBlockedSites();

        if (sites.contains(domain)) {
            System.out.println("⚠️  '" + domain + "' já está na lista.");
            return;
        }

        sites.add(domain);
        configRepo.saveBlockedSites(sites);
        System.out.println("✅ '" + domain + "' adicionado à lista de bloqueio.");
    }

    public void removeSite(String domain) {
        domain = cleanDomain(domain);
        List<String> sites = configRepo.loadBlockedSites();

        if (!sites.contains(domain)) {
            System.out.println("⚠️  '" + domain + "' não está na lista.");
            return;
        }

        sites.remove(domain);
        configRepo.saveBlockedSites(sites);
        System.out.println("✅ '" + domain + "' removido da lista.");
    }

    public void listBlockedSites() {
        List<String> sites = configRepo.loadBlockedSites();

        if (sites.isEmpty()) {
            System.out.println("📋 Sua lista de bloqueio está vazia.");
            System.out.println("   Use 'distracta add youtube.com' para adicionar.");
            return;
        }

        System.out.println("📋 Sites bloqueados (" + sites.size() + "):");
        sites.forEach(site -> System.out.println("   🚫 " + site));
    }

    // Extrai o domínio de uma URL completa: "https://www.youtube.com/watch" → "youtube.com"
    private String cleanDomain(String input) {
        return input
                .replace("https://", "")
                .replace("http://", "")
                .replace("www.", "")
                .split("/")[0]
                .trim()
                .toLowerCase();
    }
}