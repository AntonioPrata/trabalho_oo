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

