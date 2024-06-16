//Antônio de Souza Prata, Marco Antônio e Tiago Macedo


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class BancoDados {
    private ArrayList<Map<String, Object>> clientes;

    public BancoDados() {
        this.clientes = new ArrayList<>();
    }

    private boolean verificaClienteCadastrado(String nome) {
        boolean encontrou = false;
        for (Map<String, Object> cliente : clientes) {
            if (cliente.get("nome").equals(nome)) {
                encontrou = true;
            }
        }
        return encontrou;
    }

    public Map<String, Object> lsUpdatedInformation(ClientCadastro clientCadastro) {
        Map<String, Object> clientInformations = new HashMap<>();
        clientInformations.put("nome", clientCadastro.getNome());
        clientInformations.put("idade", clientCadastro.getIdade());
        clientInformations.put("cpf", clientCadastro.getCpf());
        clientInformations.put("telefone", clientCadastro.getTelefone());
        clientInformations.put("email", clientCadastro.getEmail());
        clientInformations.put("senha", clientCadastro.getSenha());

        clientes.add(clientInformations);
        return clientInformations;
    }

    private void bancoDados(Map<String, Object> cliente) {
        if (cliente.get("nome") != null && cliente.get("senha") != null) {
            clientes.add(cliente);
        }
    }
}

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

class CadastroProduto {
    private Map<String, Integer> preco;
    private Map<String, String> produtos;

    public CadastroProduto() {
        this.preco = new HashMap<>();
        this.produtos = new HashMap<>();
    }

    public void cadastro(Map<String, String> produtos) {
        for (String produto : produtos.keySet()) {
            this.produtos.put(produto, produtos.get(produto));
            System.out.println("Insira um preco para o produto " + produto + ": ");
            int price = new java.util.Scanner(System.in).nextInt();
            this.preco.put(produto, price);
        }
    }

    public void alteracaoNomeProduto(String nomeAntigo, String nomeNovo) {
        if (produtos.containsKey(nomeAntigo)) {
            produtos.put(nomeNovo, produtos.remove(nomeAntigo));
            preco.put(nomeNovo, preco.remove(nomeAntigo));
        }
    }

    public void alteracaoPreco(String produtoAlvo, float precoNovo) {
        if (preco.containsKey(produtoAlvo)) {
            preco.put(produtoAlvo, (int) precoNovo);
        }
    }
}

class ComprarProduto {
    private Map<String, String> produtos;
    private Map<String, Integer> preco;

    public ComprarProduto(Map<String, String> produtos, Map<String, Integer> preco) {
        this.produtos = produtos;
        this.preco = preco;
    }

    public float getProdutosDesejado(String produto) {
        if (produtos.containsKey(produto)) {
            return preco.get(produto);
        }
        return 0;
    }
}
