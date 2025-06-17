package itens;


/* * Consumível de combate que causa dano instantâneo ao ser usado.
 * Pode ser utilizado por heróis para atacar NPCs ou outros heróis.
 */
public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preco, int ataqueInstantaneo) {
        super(nome, preco);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes() {
        //super.mostrarDetalhes();
        // Mostra o valor do ataque instantâneo causado por este consumível
        System.out.println("Ataque Instantâneo: " + ataqueInstantaneo);
    }

    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }
}