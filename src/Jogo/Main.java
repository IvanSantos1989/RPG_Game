package Jogo;

import Entidades.Heroi;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎮 Bem-vindo ao RPG - Labirinto Mortal!");

        boolean continuar = true;

        while (continuar) {
            Heroi jogador = Jogo.criarPersonagem();  // cria personagem via consola
            Jogo.labirintoMortal(jogador);           // inicia a aventura

            System.out.println("\nDeseja jogar novamente? (s/n)");
            String resposta = scanner.nextLine().toLowerCase();
            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("Obrigado por jogar! Até a próxima.");
            }
        }
    }
}