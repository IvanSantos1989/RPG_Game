package entidades;

public abstract class Entidade {
    private String nome;
    private int vidaMax;
    private int vidaAtual;
    private int forca;

    public Entidade(String nome, int vidaMax, int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax; // começa igual à vida máxima
        this.forca = forca;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Vida Máxima: " + vidaMax);
        System.out.println("Vida Atual: " + vidaAtual);
        System.out.println("Força: " + forca);
    }


    public String getNome() {
        return nome;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = Math.min(vidaAtual, vidaMax); // evita passar do máximo
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void tomarDano(int dano) {
        vidaAtual -= dano;
        if (vidaAtual < 0) vidaAtual = 0;
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }
}