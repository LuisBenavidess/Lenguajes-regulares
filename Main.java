import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        cositas hola = new cositas();
        AFND fin=hola.desarmar("a|b");
        
        ArrayList<Nodo> nodos = new ArrayList<>();
        hola.imprimir_good(fin.primer, nodos);



    
    }
}

