import java.util.HashSet;

public class Contato {
    private String nome;
    private HashSet<String> telefones;
    private String email;

    public Contato(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.telefones = new HashSet<>();
    }

    // Métodos para adicionar telefone
    public boolean adicionarTelefone(String telefone) {
        return telefones.add(telefone); // Adiciona o telefone ao HashSet (não permite duplicatas)
    }

    // Método para verificar se o contato já tem um telefone
    public boolean temTelefone(String telefone) {
        return telefones.contains(telefone);
    }

    // Método para verificar se o e-mail é o mesmo
    public boolean temEmail(String email) {
        return this.email.equals(email);
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public HashSet<String> getTelefones() {
        return telefones;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email + ", Telefones: " + telefones;
    }
}