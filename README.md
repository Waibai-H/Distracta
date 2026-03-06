> ⚠️ **No Windows**: execute o terminal como **Administrador** para que o bloqueio funcione.

---

## 📁 Estrutura do projeto

```
distracta/
├── pom.xml                         
└── src/
    └── main/
        └── java/com/distracta/
            ├── Main.java            
            ├── ui/
            │   ├── DistractaApp.java  
            │   └── DistractaCLI.java  
            ├── core/
            │   └── FocusManager.java  
            ├── system/
            │   └── HostsFileManager.java 
            └── data/
                └── ConfigRepository.java 
```

---

## 🗂️ Onde os dados ficam salvos

```
C:\Users\SeuNome\.distracta\
├── config.json        ← Lista de sites bloqueados
└── logs\
    └── distracta.log  ← Histórico de ações
```

---

## 🛣️ Próximos passos 

- [ ] Interface gráfica com JavaFX (janela com switch bonito)
- [ ] Timer Pomodoro (25min foco / 5min pausa)
- [ ] Múltiplos perfis de bloqueio ("Trabalho", "Estudos")
- [ ] Ícone na bandeja do sistema
- [ ] Instalador `.exe` com jpackage
