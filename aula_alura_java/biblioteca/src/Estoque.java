import java.util.List;
import java.util.ArrayList;

class Produto{
    String nome;
    double preco;
    int quantidade;

    public Produto(String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco =preco;
        this.quantidade = quantidade;
    }
}

public class Estoque {
    public static void listar_produtos(List<Produto> produtos){
        for (Produto p : produtos) {
            System.out.println("Nome: " + p.nome);
            System.out.println("Preço: " + String.format("%.2f", p.preco));
            System.out.println("Quantidade: " + p.quantidade);
            System.out.println("----------------------");
        }
    }

    public static void main(String [] args){
        List <Produto> produtos = new ArrayList<>();

        Produto produto1 = new Produto("Mouse Gamer", 159.9,10);

        produtos.add(produto1);
        listar_produtos(produtos);
    }

}
