package entidades;

import itens.ArmaPrincipal;
import itens.Consumivel;
import itens.ConsumivelCombate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cavaleiro extends Heroi {

    /**
     * Construtor da classe Cavaleiro.
     *
     * @param nome Nome do cavaleiro.
     * @param vida Vida máxima do cavaleiro.
     * @param forca Força do cavaleiro.
     * @param ouro Ouro inicial do cavaleiro.
     * @param armaPrincipal Arma principal equipada pelo cavaleiro.
     */
    public Cavaleiro(String nome, int vida, int forca, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vida, forca, ouro, armaPrincipal);
    }

    @Override
    public boolean atacar(NPC npc) {
        Scanner scanner = new Scanner(System.in);
        boolean ataqueEspecialUsado = false;

        System.out.println("\nInício do combate contra: " + npc.getNome());

        while (this.getHp() > 0 && npc.getHp() > 0) {
            // NPC ataca primeiro, com 80% da força devido à armadura do cavaleiro
            int danoInimigo = (int)(npc.getForca() * 0.8);
            this.receberDano(danoInimigo);
            System.out.println(npc.getNome() + " atacou! Cavaleiro recebeu " + danoInimigo + " de dano. Vida atual: " + this.getHp());

            // Verifica se herói morreu
            if (this.getHp() <= 0) break;

            // Menu de ataque
            System.out.println("\nEscolha seu tipo de ataque:");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Ataque Especial" + (ataqueEspecialUsado ? " (já usado)" : ""));
            System.out.println("3. Ataque com Consumível");
            System.out.print(">> ");
            int escolha = scanner.nextInt();
            int dano;

            switch (escolha) {
                case 1:
                    dano = this.getForca() + this.getArmaPrincipal().getAtaque();
                    npc.receberDano(dano);
                    System.out.println("Você atacou com força total! Causou " + dano + " de dano.");
                    break;

                case 2:
                    if (!ataqueEspecialUsado) {
                        dano = this.getForca() + this.getArmaPrincipal().getAtaqueEspecial();
                        npc.receberDano(dano);
                        ataqueEspecialUsado = true;
                        System.out.println("Você usou seu ataque especial! Causou " + dano + " de dano.");
                    } else {
                        System.out.println("Ataque especial já foi usado nesta luta.");
                    }
                    break;

            case 3:
                    ArrayList<Consumivel> consumiveis = this.getInventario();
                    if (consumiveis.isEmpty()) {
                        System.out.println("Você não tem consumíveis de combate.");
                        break;
                    }
                    System.out.println("Escolha um consumível:");
                    for (int i = 0; i < consumiveis.size(); i++) {
                        System.out.println((i + 1) + ". " + consumiveis.get(i).getNome());
                    }
                    System.out.println("0. Cancelar");
                    int opt = scanner.nextInt();
                    if (opt > 0 && opt <= consumiveis.size()) {
                        ConsumivelCombate item = (ConsumivelCombate) consumiveis.remove(opt - 1);
                        npc.receberDano(item.getAtaqueInstantaneo());
                        System.out.println("Você usou " + item.getNome() + " e causou " + item.getAtaqueInstantaneo() + " de dano.");
                    } else {
                        System.out.println("Ataque com consumível cancelado.");
                    }
                    break;
                default: System.out.println("Opção inválida.");
                break;
            }

            // Verifica se NPC morreu
            if (npc.getHp() <= 0) break;
        }

        if (this.getHp() > 0) {
            // Vitória do herói
            System.out.println("\n" + this.getNome() + " venceu a batalha!");
            this.setNivel();
            this.setHp(this.getHp() + 10);
            this.setForca(this.getForca() + 1);
            this.setOuro(this.getOuro() + npc.getOuro());
            return true;
        } else {
            System.out.println("\n" + this.getNome() + " foi derrotado...");
            return false;
        }
    }
}