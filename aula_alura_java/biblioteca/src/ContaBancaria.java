import java.util.Scanner;

public class ContaBancaria {
    static double saldo = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu(){

        boolean continuar = true;
        while (continuar){
            String menu = """
                    1 - Ver saldo
                    2 - Depositar
                    3 - Sacar
                    4 - Sair
                  """;
            System.out.println(menu);
            String escolha = scanner.nextLine();

            switch (escolha){
                case "1":
                    exibirSaldo();
                    break;
                case "2":
                    depositar();
                    break;
                case "3":
                    break;
                case "4":
                    break;
            }
        }

    }

    public static void exibirSaldo(){
        System.out.println("Seu saldo é de: " + saldo);
    }

    public static void depositar(){
        System.out.println("Digite o valor que deseja depositar: ");
        String valorDeposito = scanner.nextLine();
        double valorFormatado = Double.parseDouble(valorDeposito);

        if (valorFormatado >= 0){
            saldo += valorFormatado;
            System.out.println("Deposito feito!");
        } else{
            throw new IllegalArgumentException("Valor de depósito inválido!");
        }
        return;
    }

    public static void main(String [] args){
        exibirMenu();
    }
}
