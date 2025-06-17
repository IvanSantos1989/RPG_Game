package jogo;

import entidades.*;
import itens.ArmaPrincipal;
import itens.ConsumivelCombate;
import itens.ItemHeroi;
import itens.Pocao;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

    /**
     * Método para criar um personagem com base nas escolhas do usuário.
     * Permite escolher o tipo de herói, nome, dificuldade e distribuir pontos.
     *
     * @return Heroi - O herói criado pelo usuário.
     */
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

        int vida, forca;

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

        // Criar arma básica
        ArmaPrincipal armaInicial = new ArmaPrincipal("Arma Básica", 5, 10, 20);
        armaInicial.adicionarHeroiPermitido("Cavaleiro");
        armaInicial.adicionarHeroiPermitido("Feiticeiro");
        armaInicial.adicionarHeroiPermitido("Arqueiro");

        Heroi heroiCriado;
        switch (tipo) {
            case 1:
                heroiCriado = new Cavaleiro(nome, vida, forca, ouro, armaInicial);
                break;
            case 2:
                heroiCriado = new Feiticeiro(nome, vida, forca, ouro, armaInicial);
                break;
            case 3:
                heroiCriado = new Arqueiro(nome, vida, forca, ouro, armaInicial);
                break;
            default:
                System.out.println("Tipo inválido. Criando Cavaleiro por padrão.");
                heroiCriado = new Cavaleiro(nome, vida, forca, ouro, armaInicial);
        }

        System.out.println("\nSeu herói foi criado com sucesso!");
        heroiCriado.mostrarDetalhes();
        return heroiCriado;
    }

    public static void labirintoMortal(Heroi heroi) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n🌀 Bem-vindo ao Labirinto Mortal!");
        System.out.println("Você acorda no centro de um templo sombrio...");
        System.out.println("Sua missão: sobreviver, lutar e escapar.");

        // Criar vendedor e loja
        ArrayList<ItemHeroi> todosItens = new ArrayList<>();
        todosItens.add(new ArmaPrincipal("Espada Flamejante", 10, 20, 25));
        todosItens.add(new ArmaPrincipal("Cajado Arcano", 8, 25, 30));
        todosItens.add(new ArmaPrincipal("Arco de Prata", 9, 18, 27));
        todosItens.add(new ConsumivelCombate("Bomba de Fogo", 15, 10));
        todosItens.add(new ConsumivelCombate("Flecha Envenenada", 12, 8));
        todosItens.add(new Pocao("Poção de Vida", 5, 25, 8));
        todosItens.add(new Pocao("Poção de Força", 6, 2, 4));
        todosItens.add(new Pocao("Poção Completa", 10, 20, 3));
        todosItens.add(new Pocao("Poção Fraca", 3, 10, 5));
        todosItens.add(new Pocao("Elixir do Mago", 8, 15, 2));
        todosItens.add(new ConsumivelCombate("Estilhaço de Gelo", 11, 12));
        todosItens.add(new ConsumivelCombate("Poção Tóxica", 9, 16));
        todosItens.add(new ArmaPrincipal("Espada de Bronze", 6, 12, 17));
        todosItens.add(new ArmaPrincipal("Cajado Sombrio", 7, 19, 26));

        Vendedor vendedor = new Vendedor();

        for (ItemHeroi item : todosItens) {
            item.adicionarHeroiPermitido("Cavaleiro");
            item.adicionarHeroiPermitido("Feiticeiro");
            item.adicionarHeroiPermitido("Arqueiro");

            vendedor.adicionarItem(item);
        }

        System.out.println("\n Um velho vendedor aparece:");
        ArrayList<ItemHeroi> itensExibidos = vendedor.imprimirLoja();

        while (true) {
            System.out.println("Deseja comprar algo? (s/n)");
            String escolha = scanner.nextLine().toLowerCase();
            if (!escolha.equals("s")) break;

            System.out.print("Digite o número do item que deseja comprar (1-" + itensExibidos.size() + "): ");
            int numeroItem = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (numeroItem < 1 || numeroItem > itensExibidos.size()) {
                System.out.println("Número inválido.");
                continue;
            }

            ItemHeroi itemEscolhido = itensExibidos.get(numeroItem - 1);
            vendedor.vender(heroi, itemEscolhido);
        }

        System.out.println("\n Primeira sala: um Esqueleto aparece!");
        NPC inimigo1 = new NPC("Esqueleto Guerreiro", 30, 30, 7);
        System.out.println(" Enfrentará: " + inimigo1.getNome());

        System.out.println("Deseja lutar ou tentar fugir? (lutar/fugir)");
        String escolhaLuta = scanner.nextLine().toLowerCase();
        if (!escolhaLuta.equals("lutar")) {
            System.out.println("Você tentou fugir, mas o inimigo te viu!");
        }

        boolean venceu = heroi.atacar(inimigo1);
        if (!venceu) {
            System.out.println(" Você morreu. Tentar novamente? (s/n)");
            if (scanner.nextLine().toLowerCase().equals("s")) labirintoMortal(heroi);
            else return;
        }

        System.out.println("Usar poção? (s/n)");
        if (scanner.nextLine().toLowerCase().equals("s")) heroi.usarPocao();

        System.out.println("Escolha o caminho:");
        System.out.println("1. Sala da Névoa Roxa");
        System.out.println("2. Corredor das Lâminas");
        String proximaSala = scanner.nextLine();

        if (proximaSala.equals("1")) {
            System.out.println(" A névoa envenena o ar...");
            int dano = rand.nextInt(30) + 1;
            heroi.receberDano(dano);
            System.out.println(" Você recebeu " + dano + " de dano! HP: " + heroi.getHp());
            if (heroi.getHp() <= 0) return;
        } else {
            System.out.println(" Você caminha pelo corredor escuro...");
            int chance = rand.nextInt(100);
            if (chance < 10) {
                ConsumivelCombate item = new ConsumivelCombate("Lâmina de Sombra", 1, 20);
                heroi.getInventario().add(item);
                System.out.println(" Você achou: " + item.getNome());
            } else {
                System.out.println("Você não encontrou nada.");
            }
        }

        System.out.println("Deseja usar uma poção antes da próxima área? (s/n)");
        if (scanner.nextLine().toLowerCase().equals("s")) heroi.usarPocao();

        // Múltiplos inimigos
        System.out.println(" Você entra numa arena...");
        int qtd = rand.nextInt(100);
        int numInimigos = (qtd < 50) ? 1 : (qtd < 80) ? 2 : 3;

        for (int i = 1; i <= numInimigos; i++) {
            NPC inimigo = new NPC("Sombra Maldita " + i, 25 + rand.nextInt(10), 25 + rand.nextInt(10), 8 + rand.nextInt(5));
            boolean sobreviveu = heroi.atacar(inimigo);
            if (!sobreviveu) return;
        }

        System.out.println("\n O vendedor aparece novamente!");
        itensExibidos = vendedor.imprimirLoja();

        while (true) {
            System.out.println("Deseja comprar algo antes do chefe? (s/n)");
            if (!scanner.nextLine().equalsIgnoreCase("s")) break;

            System.out.print("Digite o número do item que deseja comprar (1-" + itensExibidos.size() + "): ");
            int numeroItem = scanner.nextInt();
            scanner.nextLine();

            if (numeroItem < 1 || numeroItem > itensExibidos.size()) {
                System.out.println("Número inválido.");
                continue;
            }

            ItemHeroi itemEscolhido = itensExibidos.get(numeroItem - 1);
            vendedor.vender(heroi, itemEscolhido);
        }

        // Chefe final
        NPC chefe = new NPC("Guardião das Trevas", 70, 70, 12);
        System.out.println(" Boss: " + chefe.getNome());

        if (heroi.atacar(chefe)) {
            System.out.println(" Vitória! Você escapou do Labirinto Mortal!");
        } else {
            System.out.println(" Você foi derrotado pelo chefe final.");
        }
    }
}