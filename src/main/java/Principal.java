
import java.math.BigDecimal;

public class Principal {

    public static void main(String[] args) {
        
        //Imprecisão númerica do real
        double a = 0.1;
        double b = 0.2;
        double resultado = a + b;

        System.out.println(resultado);
        System.out.println(resultado == 0.3);
        
        //Resolvendo o problema com BigDecimal
        BigDecimal x = new BigDecimal("0.1");
        BigDecimal y = new BigDecimal("0.2");

        BigDecimal soma = x.add(y);

        System.out.println(soma);
        System.out.println(soma.compareTo(new BigDecimal("0.3")) == 0);
    }
}
