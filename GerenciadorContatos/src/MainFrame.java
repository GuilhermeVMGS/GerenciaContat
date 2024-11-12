import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private GerenciaContato gerenciador;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextArea feedbackArea;

    public MainFrame() {
        gerenciador = GerenciaContato.carregarContatos();
        setTitle("Gerenciador de Contatos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout(10, 10));

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os elementos

        inputPanel.add(new JLabel("Nome:"), getLabelConstraints(0));
        nomeField = new JTextField(20);
        inputPanel.add(nomeField, getFieldConstraints(1));

        inputPanel.add(new JLabel("Email:"), getLabelConstraints(2));
        emailField = new JTextField(20);
        inputPanel.add(emailField, getFieldConstraints(3));

        inputPanel.add(new JLabel("Telefone:"), getLabelConstraints(4));
        telefoneField = new JTextField(20);
        inputPanel.add(telefoneField, getFieldConstraints(5));

        JButton addButton = new JButton("Adicionar Contato");
        addButton.setBackground(new Color(76, 175, 80));  // Cor verde
        addButton.setForeground(Color.WHITE);
        inputPanel.add(addButton, getButtonConstraints(6));

        add(inputPanel, BorderLayout.NORTH);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton listButton = new JButton("Listar Contatos");
        JButton searchButton = new JButton("Buscar por Nome/Email");

        listButton.setBackground(new Color(33, 150, 243)); // Cor azul
        searchButton.setBackground(new Color(233, 30, 99)); // Cor rosa

        buttonPanel.add(listButton);
        buttonPanel.add(searchButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Painel de Feedback
        feedbackArea = new JTextArea(5, 40);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setBackground(new Color(242, 242, 242));
        feedbackArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Ações dos botões
        addButton.addActionListener(e -> adicionarContato());
        listButton.addActionListener(e -> listarContatos());
        searchButton.addActionListener(e -> buscarPorNomeOuEmail());

        // Salvar contatos ao fechar
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                gerenciador.salvarContatos();
            }
        });
    }

    // Ajusta a posição dos labels
    private GridBagConstraints getLabelConstraints(int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        return gbc;
    }

    // Ajusta a posição dos campos de texto
    private GridBagConstraints getFieldConstraints(int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    // Ajusta a posição do botão
    private GridBagConstraints getButtonConstraints(int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);
        return gbc;
    }

    private void adicionarContato() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            showFeedback("Todos os campos devem ser preenchidos!", Color.RED);
            return;
        }

        if (gerenciador.verificarEmailDuplicado(email)) {
            showFeedback("E-mail já cadastrado!", Color.RED);
            return;
        }

        if (gerenciador.verificarTelefoneDuplicado(telefone)) {
            showFeedback("Telefone já cadastrado!", Color.RED);
            return;
        }

        Contato contato = new Contato(nome, email);
        contato.adicionarTelefone(telefone);
        showFeedback(gerenciador.adicionarContato(contato), Color.GREEN);

        nomeField.setText("");
        emailField.setText("");
        telefoneField.setText("");
    }

    private void listarContatos() {
        StringBuilder sb = new StringBuilder();
        for (Contato contato : gerenciador.getListaContatos()) {
            sb.append(contato).append("\n");
        }
        showFeedback(sb.toString(), Color.BLACK);
    }

    private void buscarPorNomeOuEmail() {
        String opcao = JOptionPane.showInputDialog("Para buscar por nome aperte 1, por email aperte 2");
        if (opcao != null) {
            if (opcao.equals("1")) {
                buscarPorNome();
            } else if (opcao.equals("2")) {
                buscarPorEmail();
            }
        }
    }

    private void buscarPorNome() {
        String nome = JOptionPane.showInputDialog("Digite o nome completo do contato:");
        List<Contato> contatos = gerenciador.buscarPorNome(nome);
        if (!contatos.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Contato contato : contatos) {
                sb.append(contato).append("\n");
            }
            showFeedback(sb.toString(), Color.BLACK);
        } else {
            showFeedback("Contato não encontrado.", Color.RED);
        }
    }

    private void buscarPorEmail() {
        String email = JOptionPane.showInputDialog("Digite o email do contato:");
        Contato contato = gerenciador.buscarPorEmail(email);
        if (contato != null) {
            showFeedback("Contato encontrado: " + contato, Color.BLACK);
        } else {
            showFeedback("Contato não encontrado.", Color.RED);
        }
    }

    private void showFeedback(String message, Color color) {
        feedbackArea.setForeground(color);
        feedbackArea.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
