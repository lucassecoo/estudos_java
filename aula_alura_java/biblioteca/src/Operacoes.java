public class Operacoes {
    public static int soma(int a, int b){
        return a + b;
    }

    public static int subtracao(int a, int b){
        return a - b;
    }

    public static void main(String [] args){
        int soma = soma(10, 5);
        int subtracao = subtracao(10, 5);
        System.out.println("Soma: " + soma);
        System.out.println("Subtracao: " + subtracao);
    }


}
