package Jogo;

import Entidades.*;
import Itens.ArmaPrincipal;
import java.util.Scanner;

import Entidades.Heroi;
import Entidades.Vendedor;
import Itens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Jogo {

    public static Heroi criarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Criação do Personagem ===");

        // Escolher tipo de herói
        System.out.println("Escolha o tipo de herói:");
        System.out.println("1. Cavaleiro");
        System.out.println("2. Feiticeiro");
        System.out.println("3. Arqueiro");
        System.out.print(">> ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        System.out.print("Digite o nome do seu herói: ");
        String nome = scanner.nextLine();

        // Escolher dificuldade
        System.out.println("\nEscolha a dificuldade:");
        System.out.println("1. Fácil (300 pontos, 20 ouro)");
        System.out.println("2. Difícil (220 pontos, 15 ouro)");
        System.out.print(">> ");
        int dificuldade = scanner.nextInt();

        int pontos = (dificuldade == 1) ? 300 : 220;
        int ouro = (dificuldade == 1) ? 20 : 15;

        int vida = 0, forca = 0;

        // Distribuição dos pontos
        while (true) {
            System.out.println("\nVocê tem " + pontos + " pontos para distribuir.");
            System.out.print("Quantos pontos quer usar para VIDA? (1 ponto = 1 vida): ");
            vida = scanner.nextInt();

            System.out.print("Quantos pontos quer usar para FORÇA? (1 ponto de força = 5 pontos de criação): ");
            forca = scanner.nextInt();

            int totalGasto = vida + (forca * 5);
            if (totalGasto > pontos) {
                System.out.println("Você gastou mais do que os " + pontos + " disponíveis. Tente novamente.");
            } else if (totalGasto < pontos) {
                System.out.println("Você ainda tem pontos sobrando. Use exatamente os " + pontos + " pontos.");
            } else {
                break;
            }
        }

        // Criar arma padrão inicial
        ArmaPrincipal armaInicial = new ArmaPrincipal("Arma Básica", 5, 10);
        armaInicial.adicionarHeroiPermitido("Cavaleiro");
        armaInicial.adicionarHeroiPermitido("Feiticeiro");
        armaInicial.adicionarHeroiPermitido("Arqueiro");

        Heroi heroiCriado;
        switch (tipo) {
            case 1 -> heroiCriado = new Cavaleiro(nome, vida, forca, ouro, armaInicial);
            case 2 -> heroiCriado = new Feiticeiro(nome, vida, forca, ouro, armaInicial);
            case 3 -> heroiCriado = new Arqueiro(nome, vida, forca, ouro, armaInicial);
            default -> {
                System.out.println("Tipo inválido. Criando Cavaleiro por padrão.");
                heroiCriado = new Cavaleiro(nome, vida, forca, ouro, armaInicial);
            }
        }

        System.out.println("\nSeu herói foi criado com sucesso!");
        heroiCriado.mostrarDetalhes();
        return heroiCriado;
    }

    // Aqui começa a aventura do RPG
    public static void labirintoMortal(Heroi heroi) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n🌀 Bem-vindo ao Labirinto Mortal!");
        System.out.println("Você acorda no centro de um templo sombrio, ecoando sons estranhos...");
        System.out.println("Sua missão: sobreviver, lutar e encontrar a saída desse labirinto maldito.");

        // Vendedor e seus itens
        ArrayList<ItemHeroi> todosItens = new ArrayList<>();

        // Criar 14 itens variados
        todosItens.add(new ArmaPrincipal("Espada Flamejante", 10, 20));
        todosItens.add(new ArmaPrincipal("Cajado Arcano", 8, 25));
        todosItens.add(new ArmaPrincipal("Arco de Prata", 9, 18));
        todosItens.add(new ConsumivelCombate("Bomba de Fogo", 15, 10));
        todosItens.add(new ConsumivelCombate("Flecha Envenenada", 12, 8));
        todosItens.add(new Pocao("Poção de Vida", 5, 25, 0));
        todosItens.add(new Pocao("Poção de Força", 6, 0, 2));
        todosItens.add(new Pocao("Poção Completa", 10, 20, 3));
        todosItens.add(new Pocao("Poção Fraca", 3, 10, 0));
        todosItens.add(new Pocao("Elixir do Mago", 8, 15, 2));
        todosItens.add(new ConsumivelCombate("Estilhaço de Gelo", 11, 12));
        todosItens.add(new ConsumivelCombate("Poção Tóxica", 9, 16));
        todosItens.add(new ArmaPrincipal("Espada de Bronze", 6, 12));
        todosItens.add(new ArmaPrincipal("Cajado Sombrio", 7, 19));

        // Permissões básicas
        for (ItemHeroi item : todosItens) {
            item.adicionarHeroiPermitido("Cavaleiro");
            item.adicionarHeroiPermitido("Feiticeiro");
            item.adicionarHeroiPermitido("Arqueiro");
        }

        Vendedor vendedor = new Vendedor(todosItens);

        System.out.println("\n🧙‍♂️ Um velho vendedor aparece em sua frente:");
        vendedor.imprimirLoja();  // Mostra 10 itens aleatórios

        while (true) {
            System.out.println("\nDeseja comprar um item? (s/n)");
            String escolha = scanner.nextLine().toLowerCase();
            if (escolha.equals("s")) {
                vendedor.vender(heroi); // Tenta vender um item
            } else {
                break;
            }
        }

        System.out.println("\nVocê se prepara para entrar na primeira sala...");



        // Primeira sala com combate obrigatório
        System.out.println("\n🚪 Você entra na primeira sala... uma figura sombria surge!");

        // Criar inimigo simples
        Entidades.NPC inimigo1 = new Entidades.NPC("Esqueleto Guerreiro", 30, 30, 7, 10);

        System.out.println("⚔️ Enfrentará " + inimigo1.getNome() + " com " + inimigo1.getHp() + " HP e " + inimigo1.getForca() + " de força.");

        System.out.println("Deseja lutar ou tentar fugir? (lutar/fugir)");
        String escolhaLuta = scanner.nextLine().toLowerCase();

        if (!escolhaLuta.equals("lutar")) {
            System.out.println("Você tentou fugir, mas o inimigo te viu! A luta é inevitável.");
        }

        // Início do combate
        boolean venceu = heroi.atacar(inimigo1);
        if (!venceu) {
            System.out.println("\n☠️ Você foi derrotado no primeiro desafio.");
            System.out.println("Deseja tentar novamente com o mesmo personagem? (s/n)");
            String retry = scanner.nextLine().toLowerCase();
            if (retry.equals("s")) {
                labirintoMortal(heroi);  // reinicia com o mesmo personagem
            } else {
                System.out.println("Fim do jogo.");
                return;
            }
        }

        // Usar poções após vitória
        System.out.println("\n🎁 Deseja usar uma poção antes de prosseguir? (s/n)");
        if (scanner.nextLine().toLowerCase().equals("s")) {
            heroi.usarPocao();
        }

        // Escolha de caminho
        System.out.println("\nVocê vê dois caminhos pela frente:");
        System.out.println("1. Sala da Névoa Roxa");
        System.out.println("2. Corredor das Lâminas");
        System.out.print("Para onde deseja ir? ");
        String proximaSala = scanner.nextLine();

        if (proximaSala.equals("1")) {
            System.out.println("Você caminha em direção à Sala da Névoa Roxa...");

        } else if (proximaSala.equals("1")) {
            System.out.println("\n🌫️ A Sala da Névoa Roxa é densa e sufocante...");
            int dano = rand.nextInt(30) + 1;  // dano entre 1 e 30
            heroi.receberDano(dano);
            System.out.println("⚠️ Você inalou a névoa e recebeu " + dano + " de dano! Vida atual: " + heroi.getHp());

            if (heroi.getHp() <= 0) {
                System.out.println("\n☠️ Você sucumbiu à névoa venenosa.");
                return;
            }

            // Oferecer poção
            System.out.println("\nDeseja usar uma poção antes de continuar? (s/n)");
            if (scanner.nextLine().toLowerCase().equals("s")) {
                heroi.usarPocao();
            }

            System.out.println("Você segue em frente...");

        }

    } else {
            System.out.println("Você entra no Corredor das Lâminas...");


            System.out.println("\n🪓 O Corredor das Lâminas é escuro e perigoso...");

            int chance = rand.nextInt(100);  // 0 a 99
            if (chance < 10) {
                System.out.println("🎉 Você encontrou um item secreto escondido entre as pedras!");

                ConsumivelCombate itemSecreto = new ConsumivelCombate("Lâmina de Sombra", 0, 20);
                heroi.getInventario().add(itemSecreto);

                System.out.println("Item adicionado ao seu inventário: " + itemSecreto.getNome());
            } else {
                System.out.println("Você procurou ao redor, mas não encontrou nada...");
            }

            System.out.println("\nDeseja usar uma poção antes de seguir adiante? (s/n)");
            if (scanner.nextLine().toLowerCase().equals("s")) {
                heroi.usarPocao();
            }

            System.out.println("Você continua sua jornada...");



    }


    // Sala com múltiplos inimigos
        System.out.println("\n🩸 Você entra numa arena coberta de sangue seco...");
    int qtdInimigos = rand.nextInt(100);
    int numInimigos;

        if (qtdInimigos < 50) numInimigos = 1;
        else if (qtdInimigos < 80) numInimigos = 2;
        else numInimigos = 3;

        System.out.println("⚠️ Você será atacado por " + numInimigos + " inimigo(s) de uma vez!");

        for (int i = 1; i <= numInimigos; i++) {
        Entidades.NPC inimigo = new Entidades.NPC("Sombra Maldita " + i, 25 + rand.nextInt(10), 25 + rand.nextInt(10), 8 + rand.nextInt(5), 5);
        System.out.println("\n⚔️ Enfrentando: " + inimigo.getNome());

        boolean venceu = heroi.atacar(inimigo);
        if (!venceu) {
            System.out.println("Você caiu antes de alcançar o final do labirinto...");
            return;
        }

        if (i < numInimigos) {
            System.out.println("Deseja usar uma poção antes do próximo inimigo? (s/n)");
            if (scanner.nextLine().toLowerCase().equals("s")) {
                heroi.usarPocao();
            }
        }
    }

        System.out.println("\n🏪 Após sobreviver, você encontra novamente o vendedor misterioso.");
        vendedor.imprimirLoja();

        while (true) {
        System.out.println("\nDeseja comprar algo antes da batalha final? (s/n)");
        String escolha = scanner.nextLine().toLowerCase();
        if (escolha.equals("s")) {
            vendedor.vender(heroi);
        } else {
            break;
        }
    }

        System.out.println("\n⚡ Você segue pelo túnel iluminado por tochas negras... uma presença aterradora se aproxima!");

    Entidades.NPC chefeFinal = new Entidades.NPC("Guardião das Trevas", 70, 70, 12, 50);
        System.out.println("🛡️ Boss Final: " + chefeFinal.getNome() + " | Vida: " + chefeFinal.getHp() + " | Força: " + chefeFinal.getForca());

    boolean venceuChefe = heroi.atacar(chefeFinal);
        if (venceuChefe) {
        System.out.println("\n🏆 Você derrotou o Guardião das Trevas e escapou do Labirinto Mortal!");
        System.out.println("🎉 PARABÉNS, CAMPEÃO!");
    } else {
        System.out.println("\n☠️ O Guardião esmagou sua alma. Fim da jornada...");
        System.out.println("Deseja tentar novamente com o mesmo personagem? (s/n)");
        String retry = scanner.nextLine().toLowerCase();
        if (retry.equals("s")) {
            labirintoMortal(heroi);  // recomeça do início da aventura
        } else {
            System.out.println("Jogo encerrado.");
        }
    }


}
}