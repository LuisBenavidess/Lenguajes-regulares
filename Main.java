import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        operadores operador = new operadores();

        AFND afnd=operador.mapeo("a*");
        List<String> alfabeto= afnd.imprimirFormal();
        Trasformador transfor = new Trasformador();
        System.out.println("--------------------------------------");
        Trasformador convertidor = new Trasformador();
        List<Character> sigma =  new ArrayList<>();
        for (String s : alfabeto) {
            if (s.length() == 1) {          // asegurarse que solo tenga un carácter
                sigma.add(s.charAt(0));
            }
        }
        
        
        Trasformador.AFD afd = convertidor.convertir(afnd, sigma);

        transfor.imprimirAFD(afd); 

        validar val=new validar();
        
        while (true) {
            System.out.print("Ingrese cadena a evaluar (@ para terminar): ");
            String entrada = sc.nextLine();
            if (entrada.equals("@")) break;

            val.buscarCoincidencias(entrada, afd);
        }

    }
}
