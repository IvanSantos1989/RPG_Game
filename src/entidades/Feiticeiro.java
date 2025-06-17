package entidades;

import itens.ArmaPrincipal;
import itens.Consumivel;
import itens.ConsumivelCombate;
import java.util.ArrayList;
import java.util.Scanner;

public class Feiticeiro extends Heroi {

    /**
     * Construtor da classe Feiticeiro.
     *
     * @param nome Nome do feiticeiro.
     * @param vida Vida máxima do feiticeiro.
     * @param forca Força do feiticeiro.
     * @param ouro Ouro inicial do feiticeiro.
     * @param armaPrincipal Arma principal equipada pelo feiticeiro.
     */
    public Feiticeiro(String nome, int vida, int forca, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vida, forca, ouro, armaPrincipal);
    }

    @Override
    public boolean atacar(NPC npc) {
        Scanner scanner = new Scanner(System.in);
        boolean ataqueEspecialUsado = false;

        System.out.println("\n--- Batalha contra " + npc.getNome() + " ---");

        while (this.getHp() > 0 && npc.getHp() > 0) {
            // --- TURNO DO FEITICEIRO ---
            System.out.println("\nSua vez de atacar:");
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
                    System.out.println("Você atacou com magia comum! Causou " + dano + " de dano.");
                break;

                case 2:
                    if (!ataqueEspecialUsado) {
                        dano = this.getForca() + this.getArmaPrincipal().getAtaqueEspecial();
                        npc.receberDano(dano);
                        ataqueEspecialUsado = true;
                        System.out.println("Você lançou uma magia poderosa! Causou " + dano + " de dano.");
                    } else {
                        System.out.println("Magia especial já usada.");
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
            }

            // Verifica se NPC foi derrotado
            if (npc.getHp() <= 0) break;

            // --- TURNO DO NPC ---
            int danoInimigo = npc.getForca();
            this.receberDano(danoInimigo);
            System.out.println(npc.getNome() + " contra-atacou! Feiticeiro recebeu " + danoInimigo + " de dano. Vida atual: " + this.getHp());
        }

        // Resultado do combate
        if (this.getHp() > 0) {
            System.out.println("\n" + this.getNome() + " venceu com sabedoria arcana!");
            this.setNivel();
            this.setHp(this.getHp() + 10);
            this.setForca(this.getForca() + 1);
            this.setOuro(this.getOuro() + npc.getOuro());
            return true;
        } else {
            System.out.println("\n" + this.getNome() + " caiu em batalha...");
            return false;
        }
    }
}