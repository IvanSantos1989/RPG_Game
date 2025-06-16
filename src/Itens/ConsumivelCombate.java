package Itens;

import java.util.ArrayList;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preco, ArrayList<String> heroisPermitidos, int ataqueInstantaneo) {
        super(nome, preco, heroisPermitidos);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        // Mostra o valor do ataque instantâneo causado por este consumível
        System.out.println("Ataque Instantâneo: " + ataqueInstantaneo);
    }

    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }
}