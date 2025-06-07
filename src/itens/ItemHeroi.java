package itens;

import java.util.ArrayList;

public abstract class ItemHeroi {
    private String nome;
    private int preco;
    private ArrayList<String> heroisPermitidos;

    public ItemHeroi(String nome, int preco, ArrayList<String> heroisPermitidos) {
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = heroisPermitidos;
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
    public boolean podeSerUsadoPor(String tipoHeroi) {
        return heroisPermitidos.contains(tipoHeroi);
    }
}