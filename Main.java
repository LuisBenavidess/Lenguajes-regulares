public class Main {
    public static void main(String[] args) {

        operadores operador = new operadores();

        AFND afnd=operador.mapeo("a.b.c");

        afnd.imprimirFormal();


    }
}
