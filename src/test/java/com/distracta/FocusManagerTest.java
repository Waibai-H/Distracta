package com.distracta;

import com.distracta.core.FocusManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class FocusManagerTest {

    @Test
    @DisplayName("Deve adicionar e remover site sem erros")
    void deveAdicionarERemoverSite() {
        FocusManager manager = new FocusManager();

        manager.addSite("teste-distracta-123.com");
        manager.removeSite("teste-distracta-123.com");
    }

    @Test
    @DisplayName("Deve listar sites sem erros")
    void deveListarSites() {
        FocusManager manager = new FocusManager();
        manager.listBlockedSites();
    }

    @Test
    @DisplayName("Adicionar URL completa deve extrair só o domínio")
    void deveExtrairDominioDaUrl() {
        FocusManager manager = new FocusManager();

        manager.addSite("https://www.youtube.com/watch?v=abc123");
        manager.removeSite("youtube.com");
    }
}
