import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import java.nio.file.*;

public class Main {

    
    public interface ImpressoraDLL extends Library {
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                Paths.get("E1_Impressora01.dll").toAbsolutePath().toString(), ImpressoraDLL.class); 

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int FechaConexaoImpressora();

        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int AvancaPapel(int linhas);

        int StatusImpressora(int param);

        int AbreGavetaElgin(int pino, int ti, int tf);

        int AbreGaveta(int pino, int ti, int tf);

        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int ModoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int ImprimeXMLSAT(String dados, int param);

        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner sc = new Scanner(System.in);

     private static final String USUARIO = "admin";
     private static final String SENHA = "1234";
     private static final String ASS_QRCODE = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";


    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine();
    }
  
    private static int lerInteiro(String mensagem) {
        while (true) {
            String texto = capturarEntrada(mensagem);
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
            }
        }
    }

  
    private static String lerArquivo(String nome) {
        try {
            return new String(Files.readAllBytes(Paths.get(nome)));
        } catch (java.io.IOException e) {
            System.out.println("Não foi possível ler o arquivo.");
            return null;
        }
    }

        public static boolean fazerLogin() {
        for (int cont = 1; cont <= 3; cont++) {
            String usuario = capturarEntrada("Usuário: ");
            String senha = capturarEntrada("Senha: ");
            if (usuario.equals(USUARIO) && senha.equals(SENHA)) {
                return true;
            }
            System.out.println("Usuário ou senha inválidos. Tentativas restantes: " + (3 - cont));
        }
        return false;
    }

        // Pede ao usuário os dados da conexão e guarda nas variáveis
        public static void configurarConexao() {
        tipo = lerInteiro("Tipo (1=USB, 2=Serial, 3=Rede, 4=Bluetooth): ");
        modelo = capturarEntrada("Modelo (i8 ou i9): ");
        conexao = capturarEntrada("Conexão (USB para USB): ");
        parametro = lerInteiro("Parâmetro (0 para USB): ");
        System.out.println("Configuração salva.");
    }

    public static void abrirConexao() {
        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }

            // Fecha a conexão com a impressora
            public static void fecharConexao() {
        if (!conexaoAberta) {
            System.out.println("Conexão já está fechada.");
            return;
        }
        int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
        if (retorno == 0) {
            conexaoAberta = false;
            System.out.println("Conexão fechada com sucesso.");
        } else {
            System.out.println("Erro ao fechar conexão. Código: " + retorno);
        }
    }

        // Imprime um texto na quantidade de cópias escolhida e corta o papel
        public static void imprimirTexto() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        String texto = capturarEntrada("Texto a imprimir: ");
        int copias = lerInteiro("Quantas cópias? ");
        for (int i = 1; i <= copias; i++) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 1, 4, 0);
            if (retorno >= 0) {
                System.out.println("Cópia " + i + " impressa!");
            } else {
                System.out.println("Erro ao imprimir. Código: " + retorno);
            }
        }
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    // Imprime um QR Code com o conteúdo digitado
    public static void imprimirQRCode() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        String dados = capturarEntrada("Conteúdo do QR Code: ");
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, 6, 4);
        if (retorno == 0) {
            System.out.println("QR Code impresso!");
        } else {
            System.out.println("Erro ao imprimir QR Code. Código: " + retorno);
        }
        ImpressoraDLL.INSTANCE.Corte(5);
    }

     // Imprime um código de barras CODE 128 fixo
    public static void imprimirCodigoBarras() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);
        if (retorno == 0) {
            System.out.println("Código de barras impresso!");
        } else {
            System.out.println("Erro ao imprimir código de barras. Código: " + retorno);
        }
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    // Lê o arquivo XMLSAT.xml e imprime o cupom fiscal
    public static void imprimirXMLSAT() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        String nome = capturarEntrada("Nome do arquivo (ex: XMLSAT.xml): ");
        String xml = lerArquivo(nome);
        if (xml == null) {
            return;
        }
        int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);
        if (retorno == 0) {
            System.out.println("Cupom SAT impresso!");
        } else {
            System.out.println("Erro ao imprimir XML SAT. Código: " + retorno);
        }
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    // Lê o arquivo CANC_SAT.xml e imprime o cupom de cancelamento
    public static void imprimirXMLCancelamentoSAT() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        String nome = capturarEntrada("Nome do arquivo (ex: CANC_SAT.xml): ");
        String xml = lerArquivo(nome);
        if (xml == null) {
            return;
        }
        int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(xml, ASS_QRCODE, 0);
        if (retorno == 0) {
            System.out.println("Cancelamento SAT impresso!");
        } else {
            System.out.println("Erro ao imprimir cancelamento. Código: " + retorno);
        }
        ImpressoraDLL.INSTANCE.Corte(5);
    }

    // Abre a gaveta de dinheiro
    public static void abrirGaveta() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 10);
        if (retorno == 0) {
            System.out.println("Gaveta aberta!");
        } else {
            System.out.println("Erro ao abrir gaveta. Código: " + retorno);
        }
    }

    // Faz a impressora apitar a quantidade de vezes escolhida
    public static void sinalSonoro() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        int quantidade = lerInteiro("Quantos bips? ");
        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 50, 5);
        if (retorno == 0) {
            System.out.println("Bip!");
        } else {
            System.out.println("Erro no sinal sonoro. Código: " + retorno);
        }
    }

    // Avança o papel na quantidade de linhas escolhida
    public static void avancarPapel() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes (opção 2).");
            return;
        }
        int linhas = lerInteiro("Quantas linhas avançar? ");
        int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);
        if (retorno == 0) {
            System.out.println("Papel avançado!");
        } else {
            System.out.println("Erro ao avançar papel. Código: " + retorno);
        }
    }

        // Mostra o menu de opções na tela
        public static void exibirMenu() {
        System.out.println("\n*************************************************");
        System.out.println("**************** MENU IMPRESSORA ****************");
        System.out.println("*************************************************\n");
        System.out.println("1  - Configurar conexão");
        System.out.println("2  - Abrir conexão");
        System.out.println("3  - Fechar conexão");
        System.out.println("4  - Imprimir texto");
        System.out.println("5  - Imprimir QR Code");
        System.out.println("6  - Imprimir código de barras");
        System.out.println("7  - Imprimir XML SAT");
        System.out.println("8  - Imprimir XML cancelamento SAT");
        System.out.println("9  - Abrir gaveta");
        System.out.println("10 - Sinal sonoro");
        System.out.println("11 - Avançar papel");
        System.out.println("0  - Sair");
    }


    // Pede usuário e senha, com 3 tentativas
    public static void main(String[] args) {
        if (!fazerLogin()) {
            System.out.println("Login falhou 3 vezes. Programa encerrado.");
            return;
        }

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro("Digite a opção desejada: ");

                        switch (opcao) {
                case 1:
                    configurarConexao();
                    break;
                case 2:
                    abrirConexao();
                    break;
                case 3:
                    fecharConexao();
                    break;
                case 4:
                    imprimirTexto();
                    break;
                case 5:
                    imprimirQRCode();
                    break;
                case 6:
                    imprimirCodigoBarras();
                    break;
                case 7:
                    imprimirXMLSAT();
                    break;
                case 8:
                    imprimirXMLCancelamentoSAT();
                    break;
                case 9:
                    abrirGaveta();
                    break;
                case 10:
                    sinalSonoro();
                    break;
                case 11:
                    avancarPapel();
                    break;
                case 0:
                    fecharConexao();
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        sc.close();
    }
}

