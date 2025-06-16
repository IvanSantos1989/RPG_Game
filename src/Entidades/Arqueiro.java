package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ConsumivelCombate;
import java.util.ArrayList;
import java.util.Scanner;

public class Arqueiro extends Heroi {

    public Arqueiro(String nome, int vida, int forca, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vida, forca, ouro, armaPrincipal);
    }

    @Override
    public boolean atacar(NPC npc) {
        Scanner scanner = new Scanner(System.in);
        boolean ataqueEspecialUsado = false;

        System.out.println("\n--- Combate iniciado contra: " + npc.getNome() + " ---");

        while (this.getHp() > 0 && npc.getHp() > 0) {
            // --- TURNO DO ARQUEIRO ---
            System.out.println("\nEscolha seu tipo de ataque:");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Ataque Especial" + (ataqueEspecialUsado ? " (já usado)" : ""));
            System.out.println("3. Ataque com Consumível");
            System.out.print(">> ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> {
                    int dano = this.getForca() + this.getArmaPrincipal().getAtaque();
                    npc.receberDano(dano);
                    System.out.println("Você disparou uma flecha precisa! Causou " + dano + " de dano.");
                }
                case 2 -> {
                    if (!ataqueEspecialUsado) {
                        int dano = this.getForca() + this.getArmaPrincipal().getAtaqueEspecial();
                        npc.receberDano(dano);
                        ataqueEspecialUsado = true;
                        System.out.println("Você disparou uma flecha explosiva! Causou " + dano + " de dano.");
                    } else {
                        System.out.println("Ataque especial já foi usado nesta luta.");
                    }
                }
                case 3 -> {
                    ArrayList<Consumivel> consumiveis = this.getInventarioConsumiveisCombate();
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
                }
                default -> System.out.println("Opção inválida.");
            }

            // Verifica se NPC morreu
            if (npc.getHp() <= 0) break;

            // --- TURNO DO NPC ---
            int danoInimigo = (int)(npc.getForca() * 1.10); // 10% a mais
            this.receberDano(danoInimigo);
            System.out.println(npc.getNome() + " revidou! Arqueiro recebeu " + danoInimigo + " de dano. Vida atual: " + this.getHp());
        }

        if (this.getHp() > 0) {
            System.out.println("\n" + this.getNome() + " venceu o combate com precisão!");
            this.setNivel(this.getNivel() + 1);
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