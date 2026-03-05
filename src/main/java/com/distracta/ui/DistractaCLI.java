package com.distracta.ui;

import com.distracta.core.FocusManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "distracta",
        description = "🚫 Bloqueador de sites para foco e produtividade",
        mixinStandardHelpOptions = true,
        subcommands = {
                DistractaCLI.StartCommand.class,
                DistractaCLI.StopCommand.class,
                DistractaCLI.AddCommand.class,
                DistractaCLI.RemoveCommand.class,
                DistractaCLI.ListCommand.class
        }
)
public class DistractaCLI implements Runnable {

    @Override
    public void run() {
        System.out.println("Use 'distracta --help' para ver os comandos disponíveis.");
    }

    @Command(name = "start", description = "Ativa o modo foco")
    static class StartCommand implements Runnable {
        public void run() { new FocusManager().startFocus(); }
    }

    @Command(name = "stop", description = "Desativa o modo foco")
    static class StopCommand implements Runnable {
        public void run() { new FocusManager().stopFocus(); }
    }

    @Command(name = "add", description = "Adiciona um site à lista de bloqueio")
    static class AddCommand implements Runnable {
        @Parameters(paramLabel = "<dominio>", description = "Ex: youtube.com")
        private String domain;
        public void run() { new FocusManager().addSite(domain); }
    }

    @Command(name = "remove", description = "Remove um site da lista de bloqueio")
    static class RemoveCommand implements Runnable {
        @Parameters(paramLabel = "<dominio>", description = "Ex: youtube.com")
        private String domain;
        public void run() { new FocusManager().removeSite(domain); }
    }

    @Command(name = "list", description = "Lista todos os sites bloqueados")
    static class ListCommand implements Runnable {
        public void run() { new FocusManager().listBlockedSites(); }
    }
}