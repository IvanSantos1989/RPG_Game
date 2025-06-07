package entidades;

import itens.ArmaPrincipal;
import itens.Consumivel;
import java.util.ArrayList;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private ArmaPrincipal armaPrincipal;
    private ArrayList<Consumivel> inventario;

    public Heroi(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca);
        this.nivel = 1;
        this.ouro = ouro;
        this.inventario = new ArrayList<>();
    }

    public abstract Entidade atacar(NPC npc); // será implementado nas subclasses


    public int getNivel() {
        return nivel;
    }

    public void subirNivel() {
        this.nivel++;
        this.setVidaAtual(this.getVidaAtual() + 10);
        this.setForca(this.getForca() + 1);
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public void adicionarOuro(int quantidade) {
        this.ouro += quantidade;
    }

    public void removerOuro(int quantidade) {
        this.ouro -= quantidade;
        if (this.ouro < 0) this.ouro = 0;
    }

    public ArmaPrincipal getArmaPrincipal() {
        return armaPrincipal;
    }

    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }

    public ArrayList<Consumivel> getInventario() {
        return inventario;
    }

    public void adicionarItem(Consumivel item) {
        inventario.add(item);
    }

    public void removerItem(Consumivel item) {
        inventario.remove(item);
    }
}