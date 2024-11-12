import java.util.ArrayList;
import java.util.List;

public class GerenciaContato {
    private List<Contato> listaContatos;

    public GerenciaContato() {
        listaContatos = new ArrayList<>();
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public String adicionarContato(Contato contato) {
        listaContatos.add(contato);
        return "Contato adicionado com sucesso!";
    }

    public boolean removerContato(String nome) {
        return listaContatos.removeIf(contato -> contato.getNome().equals(nome));
    }

    public List<Contato> buscarPorNome(String nome) {
        List<Contato> encontrados = new ArrayList<>();
        for (Contato contato : listaContatos) {
            if (contato.getNome().contains(nome)) {
                encontrados.add(contato);
            }
        }
        return encontrados;
    }

    public Contato buscarPorEmail(String email) {
        for (Contato contato : listaContatos) {
            if (contato.getEmail().equals(email)) {
                return contato;
            }
        }
        return null;
    }

    public Contato buscarPorTelefone(String telefone) {
        for (Contato contato : listaContatos) {
            if (contato.temTelefone(telefone)) {
                return contato;
            }
        }
        return null;
    }

    public boolean verificarEmailDuplicado(String email) {
        for (Contato contato : listaContatos) {
            if (contato.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarTelefoneDuplicado(String telefone) {
        for (Contato contato : listaContatos) {
            if (contato.temTelefone(telefone)) {
                return true;
            }
        }
        return false;
    }

    // Método para salvar contatos em um arquivo ou banco de dados
    public void salvarContatos() {
        // Implemente a lógica de salvar os contatos
    }

    public static GerenciaContato carregarContatos() {
        // Implemente a lógica para carregar os contatos de um arquivo ou banco de dados
        return new GerenciaContato();
    }
}