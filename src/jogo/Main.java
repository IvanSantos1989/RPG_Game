package jogo;

import entidades.Heroi;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎮 Bem-vindo ao RPG - Labirinto Mortal!");
        System.out.println();

        boolean continuar = true;

        while (continuar) {
            Heroi jogador = Jogo.criarPersonagem();  // cria personagem via consola
            Jogo.labirintoMortal(jogador);           // inicia a aventura

            System.out.println("\nDeseja jogar novamente? (s/n)");
            String resposta = scanner.nextLine().toLowerCase();
            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("Fim de jogo! Até a próxima.");
            }
        }
    }
}