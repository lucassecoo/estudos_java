package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    public static void listarLivros(List<Livro> livros){
        for (Livro l : livros){
            System.out.printf("%s de %s com %d paginas", l.titulo, l.autor, l.paginas);
        }
    }

    public static void main(String[] args){
        Livro livro1 = new Livro( "O Guia do Mochileiro das Galáxias", "Douglas Adams", 208);
        List<Livro> livros = new ArrayList<>();
        livros.add(livro1);
        listarLivros(livros);
    }
}
