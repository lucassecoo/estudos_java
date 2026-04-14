public class ControleTemp {

    public static boolean verificaTemperatura(float temp){
        return temp <= 37.5;
    }

    public static String retornaVerificacao(String local, float temperatura){
        boolean tempIdeal = verificaTemperatura(temperatura);

        String alerta = tempIdeal
                ? "Temperatura dentro do limite!"
                : "Temperatura acima do limite!";

        return """
        Sensor no local: %s
        Temperatura: %.1f ºC
        Alerta: %s
        """.formatted(local, temperatura, alerta);
    }

    public static void main(String[] args){
        String resultado = retornaVerificacao("Sala", 30F);
        System.out.println(resultado);
    }
}