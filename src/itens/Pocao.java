package itens;

public class Pocao extends Consumivel {
    private int vidaACurar;
    private int aumentoForca;

    public Pocao(String nome, int preco, int vidaACurar, int aumentoForca) {
        super(nome, preco);
        this.vidaACurar = vidaACurar;
        this.aumentoForca = aumentoForca;
    }

    @Override
    public void mostrarDetalhes() {
        //super.mostrarDetalhes();
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