package itens;

import java.util.ArrayList;

/**
 * Classe que representa uma arma principal de um herói.
 * Herda de ItemHeroi e adiciona atributos específicos de armas.
 */
public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;

    public ArmaPrincipal(String nome, int preco, int ataque, int ataqueEspecial) {
        super(nome, preco);
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

    // Verifica se o item é compatível com um herói
    public boolean adicionarHeroiPermitido(String tipoHeroi) {
        super.adicionarHeroiPermitido(tipoHeroi);
        return true; // Permite todos os tipos de herói
    }

    public ArrayList<String> getHeroisPermitidos() {
        //System.out.println("Heróis permitidos: " + super.getHeroisPermitidos());
        return super.getHeroisPermitidos();
    }
}