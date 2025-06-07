package itens;

import java.util.ArrayList;

public abstract class Consumivel extends ItemHeroi {
    public Consumivel(String nome, int preco, ArrayList<String> heroisPermitidos) {
        super(nome, preco, heroisPermitidos);
    }

    @Override
    public abstract void mostrarDetalhes(); // forçado a ser implementado pelas subclasses
}