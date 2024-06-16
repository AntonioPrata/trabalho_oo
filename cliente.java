import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class ClientCadastro {
    protected Map<String, Object> information;
    private String nome;
    private int idade;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public ClientCadastro(Map<String, Object> information) {
        this.information = information;
        this.setNome();
        this.setIdade();
        this.setCpf();
        this.setTelefone();
        this.setEmail();
        this.setSenha();
    }

    private void setNome() {
        this.nome = (String) information.get("nome");
    }

    public String getNome() {
        return nome;
    }

    private void setIdade() {
        this.idade = (int) information.get("idade");
    }

    public int getIdade() {
        return idade;
    }

    private void setCpf() {
        this.cpf = (String) information.get("cpf");
    }

    public String getCpf() {
        return cpf;
    }

    private void setTelefone() {
        this.telefone = (String) information.get("telefone");
    }

    public String getTelefone() {
        return telefone;
    }

    private void setEmail() {
        String email = (String) information.get("email");
        if (email.contains("@")) {
            this.email = email;
        } else {
            this.email = null;
            System.out.println("Tipagem de email inválida");
            throw new IllegalArgumentException();
        }
    }

    public String getEmail() {
        return email;
    }

    private void setSenha() {
        String senha = (String) information.get("senha");
        Pattern specialCharactersPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        if (senha.length() <= 8) {
            this.senha = null;
            System.out.println("Senha curta");
            throw new IllegalArgumentException();
        }
        if (!specialCharactersPattern.matcher(senha).find()) {
            this.senha = null;
            System.out.println("Insira um caractere especial na string");
            throw new IllegalArgumentException();
        }
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}

class UpdateCadastro extends ClientCadastro {
    private BancoDados bancoDados;

    public UpdateCadastro(Map<String, Object> information, BancoDados bancoDados) {
        super(information);
        this.bancoDados = bancoDados;
    }

    public void trocaSenha() {
        try {
            this.setSenha();
            System.out.println("Senha atualizada com sucesso");
            bancoDados.lsUpdatedInformation(this);
        } catch (Exception e) {
            throw e;
        }
    }

    public void trocaEmail() {
        try {
            this.setEmail();
            System.out.println("Email atualizado com sucesso");
            bancoDados.lsUpdatedInformation(this);
        } catch (Exception e) {
            throw e;
        }
    }
}