import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        cositas hola = new cositas();
        AFND fin=hola.desarmar("a*");
        //hola.get_letras();
        //hola.get_traslaciones(0);
        ArrayList<Nodo> nodos = new ArrayList<>();
        hola.imprimir_good(fin.primer, nodos);


        /*AFND afnd=new AFND();
        afnd.Agregar('a');
        afnd.Agregar('b');
        afnd.imprimir();*/
    
    }
}

