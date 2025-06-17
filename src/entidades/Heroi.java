package entidades;

import itens.ArmaPrincipal;
import itens.Consumivel;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private ArmaPrincipal armaPrincipal;
    private ArrayList<Consumivel> inventario;

    public Heroi(String nome, int vidaMax, int forca, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vidaMax, forca);
        this.nivel = 1;
        this.ouro = ouro;
        this.inventario = new ArrayList<>();
        this.armaPrincipal = armaPrincipal;
    }

    public abstract boolean atacar(NPC npc); // será implementado nas subclasses


    public int getNivel() {
        return nivel;
    }

    public void setNivel() {
        this.nivel++;
        this.setHp(this.getHp() + 10);
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

    public void usarPocao() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Consumivel> pochetes = new ArrayList<>();

        // Filtrar poções
        for (Consumivel c : inventario) {
            if (c instanceof itens.Pocao) {
                pochetes.add(c);
            }
        }

        if (pochetes.isEmpty()) {
            System.out.println("Você não tem nenhuma poção no inventário.");
            return;
        }

        System.out.println("\n--- Poções Disponíveis ---");
        for (int i = 0; i < pochetes.size(); i++) {
            itens.Pocao p = (itens.Pocao) pochetes.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " | Cura: " + p.getVidaACurar() + ", Força: +" + p.getAumentoForca());
        }
        System.out.println("0. Cancelar");

        System.out.print("Escolha uma poção para usar: ");
        int escolha = scanner.nextInt();

        if (escolha == 0 || escolha > pochetes.size()) {
            System.out.println("Uso de poção cancelado.");
            return;
        }

        itens.Pocao pocaoEscolhida = (itens.Pocao) pochetes.get(escolha - 1);

        // Verificar excesso de cura
        int vidaAtual = this.getHp();
        int vidaMaxima = this.getVidaMax();
        int vidaACurar = pocaoEscolhida.getVidaACurar();

        if (vidaAtual == vidaMaxima) {
            System.out.println("Sua vida já está no máximo. Deseja mesmo usar a poção? (s/n)");
            String confirm = scanner.next().toLowerCase();
            if (!confirm.equals("s")) return;
        } else if (vidaAtual + vidaACurar > vidaMaxima) {
            int excesso = (vidaAtual + vidaACurar) - vidaMaxima;
            System.out.println("A poção curaria além do máximo de vida em " + excesso + " pontos.");
            System.out.print("Deseja usar mesmo assim? (s/n): ");
            String confirmar = scanner.next().toLowerCase();
            if (!confirmar.equals("s")) return;
        }

        // Aplicar efeitos da poção
        int novaVida = Math.min(vidaAtual + vidaACurar, vidaMaxima);
        this.setHp(novaVida);
        this.setForca(this.getForca() + pocaoEscolhida.getAumentoForca());

        inventario.remove(pocaoEscolhida);

        System.out.println("Você usou " + pocaoEscolhida.getNome() + ". Vida: " + this.getHp() + "/" + vidaMaxima +
                ", Força atual: " + this.getForca());
    }

}