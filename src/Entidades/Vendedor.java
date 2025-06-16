package entidades;

import Itens.*;

import java.util.ArrayList;
import java.util.Random;

public class Vendedor {
    private ArrayList<ItemHeroi> loja;

    // Construtor: inicializa a lista da loja
    public Vendedor() {
        this.loja = new ArrayList<>();
    }

    // Adiciona um item à loja
    public void adicionarItem(ItemHeroi item) {
        loja.add(item);
    }

    // Mostra até 10 itens aleatórios diferentes da loja (sem repetir, sem usar contains)
    public void imprimirLoja() {
        System.out.println("Itens disponíveis na loja:");

        Random random = new Random();
        int quantidade = Math.min(10, loja.size());
        int[] indicesUsados = new int[quantidade];
        int count = 0;

        while (count < quantidade) {
            int indice = random.nextInt(loja.size());

            // Verifica se o índice já foi sorteado
            boolean jaUsado = false;
            for (int i = 0; i < count; i++) {
                if (indicesUsados[i] == indice) {
                    jaUsado = true;
                    break;
                }
            }

            // Se o índice ainda não foi usado, mostra o item
            if (!jaUsado) {
                indicesUsados[count] = indice;
                System.out.println("\nItem #" + (count + 1));
                loja.get(indice).mostrarDetalhes();
                count++;
            }
        }
    }

    // Vende um item ao herói (se for compatível e ele tiver ouro suficiente)
    public void vender(Heroi heroi, ItemHeroi item) {
        String tipoHeroi = heroi.getClass().getSimpleName();

        // Verifica se o herói pode usar o item
        if (!item.podeSerUsadoPor(tipoHeroi)) {
            System.out.println("Este item não pode ser usado por um " + tipoHeroi + ".");
            return;
        }

        // Verifica se o herói tem ouro suficiente
        if (heroi.getOuro() < item.getPreco()) {
            System.out.println("Você não tem ouro suficiente para comprar este item.");
            return;
        }

        // Compra: subtrai ouro e adiciona item
        heroi.removerOuro(item.getPreco());

        if (item instanceof ArmaPrincipal) {
            heroi.setArmaPrincipal((ArmaPrincipal) item);
            System.out.println("Você equipou a arma: " + item.getNome());
        } else if (item instanceof Consumivel) {
            heroi.adicionarItem((Consumivel) item);
            System.out.println("Você adquiriu: " + item.getNome());
        }
    }

    // Retorna a lista de itens da loja (para uso externo)
    public ArrayList<ItemHeroi> getLoja() {
        return loja;
    }
}