package itens;

import java.util.ArrayList;

/**
 * Classe que representa uma arma principal de um herói.
 * Herda de ItemHeroi e adiciona atributos específicos de armas.
 */
public abstract class ItemHeroi {
    private String nome;
    private int preco;
    private ArrayList<String> heroisPermitidos;

    public ItemHeroi(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = new ArrayList<>();
    }

    public void mostrarDetalhes() {
        System.out.println("Item: " + nome);
        System.out.println("Preço: " + preco + " moedas de ouro");
        System.out.println("Permitido para: " + heroisPermitidos);
    }


    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public ArrayList<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }

    // Verifica se o item é compatível com um herói
    public boolean adicionarHeroiPermitido(String tipoHeroi) {
        if (!getHeroisPermitidos().contains(tipoHeroi)) {
            getHeroisPermitidos().add(tipoHeroi);
        }
        return true; // Permite todos os tipos de herói
    }
}