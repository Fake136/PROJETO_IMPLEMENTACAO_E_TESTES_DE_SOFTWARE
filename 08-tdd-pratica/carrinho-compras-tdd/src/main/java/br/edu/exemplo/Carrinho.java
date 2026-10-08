package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<Produto> produtos = new ArrayList<>();

    public double getValorTotal() {
        return produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    public void adicionar(Produto produto) {
        if (produto != null) {
            produtos.add(produto);
        }
    }

    public int getQuantidadeItens() {
        return produtos.size();
    }

    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    public void limpar() {
        produtos.clear();
    }
}
