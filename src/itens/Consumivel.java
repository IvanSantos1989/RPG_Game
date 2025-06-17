package itens;

public abstract class Consumivel extends ItemHeroi {
    public Consumivel(String nome, int preco) {
        super(nome, preco);
    }

    @Override
    public abstract void mostrarDetalhes(); // forçado a ser implementado pelas subclasses
}