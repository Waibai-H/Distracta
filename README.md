# 🚫 Distracta

Bloqueador de sites para foco e produtividade — feito em Java.

---

## ⚡ Como abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA
2. Clique em **File → Open**
3. Selecione a pasta `distracta` que você baixou
4. O IntelliJ vai detectar o `pom.xml` automaticamente — clique em **Trust Project**
5. Aguarde o Maven baixar as dependências (barra de progresso no canto inferior)

---

## ▶️ Como executar

### Pela primeira vez (menu interativo):
- Abra `Main.java`
- Clique no ▶️ verde ao lado de `public static void main`
- Um menu vai aparecer no console do IntelliJ

### Via terminal (depois de buildar):
```bash
# Buildar o projeto
mvn package

# Executar (menu interativo)
java -jar target/distracta-1.0.0-jar-with-dependencies.jar

# Comandos diretos
java -jar target/distracta-1.0.0-jar-with-dependencies.jar add youtube.com
java -jar target/distracta-1.0.0-jar-with-dependencies.jar start
java -jar target/distracta-1.0.0-jar-with-dependencies.jar list
java -jar target/distracta-1.0.0-jar-with-dependencies.jar stop
```

> ⚠️ **No Windows**: execute o terminal como **Administrador** para que o bloqueio funcione.
> Clique com botão direito no terminal → "Executar como administrador"

---

## 📁 Estrutura do projeto

```
distracta/
├── pom.xml                          ← Configuração do Maven (dependências)
└── src/
    └── main/
        └── java/com/distracta/
            ├── Main.java            ← Ponto de entrada
            ├── ui/
            │   ├── DistractaApp.java   ← Launcher (CLI ou menu)
            │   └── DistractaCLI.java   ← Comandos do terminal
            ├── core/
            │   └── FocusManager.java   ← Lógica principal
            ├── system/
            │   └── HostsFileManager.java ← Edita o arquivo hosts
            └── data/
                └── ConfigRepository.java ← Salva/carrega configurações
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

## 🛣️ Próximos passos (futuro)

- [ ] Interface gráfica com JavaFX (janela com switch bonito)
- [ ] Timer Pomodoro (25min foco / 5min pausa)
- [ ] Múltiplos perfis de bloqueio ("Trabalho", "Estudos")
- [ ] Ícone na bandeja do sistema
- [ ] Instalador `.exe` com jpackage
