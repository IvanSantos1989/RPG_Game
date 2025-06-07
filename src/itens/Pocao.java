package itens;

import java.util.ArrayList;

public class Pocao extends Consumivel {
    private int vidaACurar;
    private int aumentoForca;

    public Pocao(String nome, int preco, ArrayList<String> heroisPermitidos, int vidaACurar, int aumentoForca) {
        super(nome, preco, heroisPermitidos);
        this.vidaACurar = vidaACurar;
        this.aumentoForca = aumentoForca;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        // Mostra os efeitos da poção: cura e aumento de força
        System.out.println("Vida a Curar: " + vidaACurar);
        System.out.println("Aumento de Força: " + aumentoForca);
    }

    public int getVidaACurar() {
        return vidaACurar;
    }

    public int getAumentoForca() {
        return aumentoForca;
    }
}