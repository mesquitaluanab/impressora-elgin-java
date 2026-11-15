# Integração Java com Impressora Elgin i9

## Sobre o Projeto
Programa em Java, feito para a disciplina de Algoritmos e Programação, que se comunica com a impressora térmica Elgin i9 (ou outra impressora, se necessário). Ele pede login, mostra um menu e envia comandos para a impressora: imprimir texto, QR Code, código de barras, cupom fiscal SAT, abrir gaveta, sinal sonoro e avanço de papel.

## Tecnologias Utilizadas
- Java
- JNA (jna-5.15.0.jar)
- Biblioteca E1_Impressora01.dll (Elgin)
- VS Code

## Como Executar
1. Baixe o projeto e abra a pasta no VS Code (ou IntelliJ).
2. Adicione o `jna-5.15.0.jar` (pasta `libs Java`) em *Referenced Libraries*.
3. Deixe a `E1_Impressora01.dll` na mesma pasta do `Main.java`.
4. Ligue a impressora e conecte o cabo USB.
5. Execute o `Main.java`.

## Login de Teste
- Usuário: `admin`
- Senha: `1234`

## Opções do Menu
1. Configurar conexão
2. Abrir conexão
3. Fechar conexão
4. Imprimir texto
5. Imprimir QR Code
6. Imprimir código de barras
7. Imprimir XML SAT
8. Imprimir XML cancelamento SAT
9. Abrir gaveta
10. Sinal sonoro
11. Avançar papel
0. Sair

## Como Testar
Escolha a opção 1 e use: tipo `1`, modelo `i9`, conexão `USB`, parâmetro `0`. Depois abra a conexão (opção 2) e use as outras opções.

## Autores
- César Davi Soares Januário
- Luana Mesquita Bezerra
- Rayanne Tomaz da Silva
- Vitor Rebouças Mendes