import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciaContato gerenciador = GerenciaContato.carregarContatos();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Gerenciador de Contatos ---");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Remover Contato");
            System.out.println("3. Listar Contatos");
            System.out.println("4. Buscar Contato por Nome");
            System.out.println("5. Buscar Contato por Telefone");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    adicionarContato(gerenciador, scanner);
                    break;
                case 2:
                    removerContato(gerenciador, scanner);
                    break;
                case 3:
                    listarContatos(gerenciador);
                    break;
                case 4:
                    buscarPorNome(gerenciador, scanner);
                    break;
                case 5:
                    buscarPorTelefone(gerenciador, scanner);
                    break;
                case 6:
                    gerenciador.salvarContatos();
                    System.out.println("Saindo e salvando contatos...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void adicionarContato(GerenciaContato gerenciador, Scanner scanner) {
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do contato: ");
        String email = scanner.nextLine();

        // Verificar se já existe um contato com o mesmo email
        if (gerenciador.verificarEmailDuplicado(email)) {
            System.out.println("E-mail já cadastrado!");
            return;
        }

        System.out.print("Digite o telefone do contato: ");
        String telefone = scanner.nextLine();

        // Verificar se já existe um contato com o mesmo telefone
        if (gerenciador.verificarTelefoneDuplicado(telefone)) {
            System.out.println("Telefone já cadastrado!");
            return;
        }

        Contato contato = new Contato(nome, email);
        contato.adicionarTelefone(telefone);

        String resultado = gerenciador.adicionarContato(contato);
        System.out.println(resultado);
    }

    private static void removerContato(GerenciaContato gerenciador, Scanner scanner) {
        System.out.print("Digite o nome do contato a ser removido: ");
        String nome = scanner.nextLine();
        boolean sucesso = gerenciador.removerContato(nome);
        if (sucesso) {
            System.out.println("Contato removido com sucesso.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void listarContatos(GerenciaContato gerenciador) {
        System.out.println("\n--- Lista de Contatos ---");
        for (Contato contato : gerenciador.getListaContatos()) {
            System.out.println(contato);
        }
    }

    private static void buscarPorNome(GerenciaContato gerenciador, Scanner scanner) {
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine();

        List<Contato> contatosEncontrados = gerenciador.buscarPorNome(nome);

        if (!contatosEncontrados.isEmpty()) {
            Contato contato = contatosEncontrados.get(0);
            System.out.println("Contato encontrado: " + contato);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void buscarPorTelefone(GerenciaContato gerenciador, Scanner scanner) {
        System.out.print("Digite o telefone do contato: ");
        String telefone = scanner.nextLine();
        Contato contato = gerenciador.buscarPorTelefone(telefone);
        if (contato != null) {
            System.out.println("Contato encontrado: " + contato);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
}