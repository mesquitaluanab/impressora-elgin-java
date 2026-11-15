<div align="center">

# 🖨️ Integração Java com Impressora Elgin i9

### Controle de uma impressora térmica direto do Java: login, menu e mais...

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![VS Code](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

</div>

---

## Sobre o Projeto

Programa em **Java**, desenvolvido para a disciplina de **Algoritmos e Programação**, que se comunica com a impressora térmica **Elgin i9** (ou outra compatível, se necessário).

Ele pede **login**, mostra um **menu interativo** e envia comandos para a impressora:

- 📝 Imprimir texto
- 🔳 Imprimir QR Code
- 📊 Imprimir código de barras
- 🧾 Imprimir cupom fiscal SAT e cancelamento
- 💰 Abrir a gaveta de dinheiro
- 🔔 Emitir sinal sonoro
- 📜 Avançar o papel

---

## Tecnologias Utilizadas

| Tecnologia | Para que serve |
|---|---|
| ☕ **Java** | Linguagem do programa |
| 🌉 **JNA** ![jna](https://img.shields.io/badge/jna--5.15.0.jar-FF9E8A?style=flat-square) | "Ponte" entre o Java e a DLL |
| ⚙️ **E1_Impressora01.dll** | Biblioteca da Elgin com os comandos da impressora |
| 💻 **VS Code** | Ambiente de desenvolvimento |

---

## Como Executar

1. 📥 Baixe o projeto e abra a pasta no **VS Code** (ou IntelliJ).
2. 📚 Adicione o ![jna](https://img.shields.io/badge/jna--5.15.0.jar-FF9E8A?style=flat-square) (pasta ![libs](https://img.shields.io/badge/libs%20Java-FF9E8A?style=flat-square)) em **Referenced Libraries**.
3. 📌 Deixe a ![dll](https://img.shields.io/badge/E1__Impressora01.dll-FF9E8A?style=flat-square) na **mesma pasta** do ![main](https://img.shields.io/badge/Main.java-FF9E8A?style=flat-square).
4. 🔌 Ligue a impressora e conecte o cabo USB.
5. ▶️ Execute o ![main](https://img.shields.io/badge/Main.java-FF9E8A?style=flat-square).

---

## Login de Teste

| Usuário | Senha |
|---|---|
| ![admin](https://img.shields.io/badge/admin-FF9E8A?style=flat-square) | ![1234](https://img.shields.io/badge/1234-FF9E8A?style=flat-square) |

---

## Opções do Menu

| Nº | Opção | Nº | Opção |
|:---:|---|:---:|---|
| 1 | ⚙️ Configurar conexão | 7 | 🧾 Imprimir XML SAT |
| 2 | 🔌 Abrir conexão | 8 | ❌ Imprimir XML cancelamento SAT |
| 3 | 🔒 Fechar conexão | 9 | 💰 Abrir gaveta |
| 4 | 📝 Imprimir texto | 10 | 🔔 Sinal sonoro |
| 5 | 🔳 Imprimir QR Code | 11 | 📜 Avançar papel |
| 6 | 📊 Imprimir código de barras | 0 | 🚪 Sair |

---

## Como Testar

Escolha a **opção 1** e use:

| Campo | Valor |
|---|---|
| Tipo | ![1](https://img.shields.io/badge/1-FF9E8A?style=flat-square) |
| Modelo | ![i9](https://img.shields.io/badge/i9-FF9E8A?style=flat-square) |
| Conexão | ![USB](https://img.shields.io/badge/USB-FF9E8A?style=flat-square) |
| Parâmetro | ![0](https://img.shields.io/badge/0-FF9E8A?style=flat-square) |

Depois abra a conexão (**opção 2**) e use as outras opções.

---

## Autores

<table>
  <tr>
    <td align="center">👩‍💻<br><b>Luana Mesquita Bezerra</b></td>
    <td align="center">👩‍💻<br><b>Rayanne Tomaz da Silva</b></td>
    <td align="center">👨‍💻<br><b>César Davi Soares Januário</b></td>
    <td align="center">👨‍💻<br><b>Vitor Rebouças Mendes</b></td>
  </tr>
</table>

<div align="center">

Funciona na minha máquina (e espero que na sua também)

</div>