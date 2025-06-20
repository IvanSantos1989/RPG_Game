package entidades;

import itens.*;

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

    // Mostra até 10 itens aleatórios diferentes da loja
    public ArrayList<ItemHeroi> imprimirLoja() {
        System.out.println("Itens disponíveis na loja:");
        ArrayList<ItemHeroi> exibidos = new ArrayList<>();

        Random random = new Random();
        int quantidade = Math.min(10, loja.size());
        int[] indicesUsados = new int[quantidade];
        int count = 0;

        while (count < quantidade) {
            int indice = random.nextInt(loja.size());
            boolean jaUsado = false;
            for (int i = 0; i < count; i++) {
                if (indicesUsados[i] == indice) {
                    jaUsado = true;
                    break;
                }
            }

            if (!jaUsado) {
                indicesUsados[count] = indice;
                ItemHeroi item = loja.get(indice);
                System.out.println("\nItem #" + (count + 1));
                item.mostrarDetalhes();
                exibidos.add(item);
                count++;
            }
        }

        return exibidos;
    }


    // Vende um item ao herói (se for compatível e ele tiver ouro suficiente)
    public void vender(Heroi heroi, ItemHeroi item) {
        String tipoHeroi = heroi.getClass().getSimpleName();

        // Verifica se o herói pode usar o item
        if (!item.adicionarHeroiPermitido(tipoHeroi)) {
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