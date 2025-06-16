package Itens;

import java.util.ArrayList;

public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;

    public ArmaPrincipal(String nome, int preco, ArrayList<String> heroisPermitidos, int ataque, int ataqueEspecial) {
        super(nome, preco, heroisPermitidos);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Ataque: " + ataque);
        System.out.println("Ataque Especial: " + ataqueEspecial);
    }

    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
}