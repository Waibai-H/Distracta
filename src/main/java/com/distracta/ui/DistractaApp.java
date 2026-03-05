package com.distracta.ui;

import com.distracta.core.FocusManager;
import picocli.CommandLine;

import java.util.Scanner;

public class DistractaApp {

    public static void launch(String[] args) {
        if (args.length > 0) {
            int exitCode = new CommandLine(new DistractaCLI()).execute(args);
            System.exit(exitCode);
        } else {
            showInteractiveMenu();
        }
    }

    private static void showInteractiveMenu() {
        FocusManager focusManager = new FocusManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     🚫  D I S T R A C T A    ║");
        System.out.println("║   Bloqueador de distrações   ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();

        boolean running = true;

        while (running) {
            System.out.println("O que você quer fazer?");
            System.out.println("  [1] Ativar modo foco");
            System.out.println("  [2] Desativar modo foco");
            System.out.println("  [3] Ver sites bloqueados");
            System.out.println("  [4] Adicionar site");
            System.out.println("  [5] Remover site");
            System.out.println("  [0] Sair");
            System.out.print("> ");

            switch (scanner.nextLine().trim()) {
                case "1" -> focusManager.startFocus();
                case "2" -> focusManager.stopFocus();
                case "3" -> focusManager.listBlockedSites();
                case "4" -> {
                    System.out.print("Digite o domínio (ex: youtube.com): ");
                    focusManager.addSite(scanner.nextLine().trim());
                }
                case "5" -> {
                    System.out.print("Digite o domínio para remover: ");
                    focusManager.removeSite(scanner.nextLine().trim());
                }
                case "0" -> {
                    System.out.println("Até logo! 👋");
                    running = false;
                }
                default -> System.out.println("❌ Opção inválida.\n");
            }
        }
    }
}