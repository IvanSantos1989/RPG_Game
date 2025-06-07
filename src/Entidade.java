public abstract class Entidade {
    private String nome;
    private int vidaMax = 100;
    private int vidaAtual = 100;
    private int forca;


    public Entidade(String nome, int vidaMax, int vidaAtual, int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaAtual;
        this.forca = forca;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Vida Máxima: " + vidaMax);
        System.out.println("Vida Atual: " + vidaAtual);
        System.out.println("Força: " + forca);
    }
}

