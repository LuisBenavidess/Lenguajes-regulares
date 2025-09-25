//Utilizaremos listas enlazadas para imitar los nodos del AFND

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class AFND {
    Nodo primer;
    
    public AFND(){
        primer=null;
    }
    //Metodo que agrega un nodo al siguiente o una concatenacion
    public Nodo Agregar(char let, int opcion){

        Nodo nuevo=new Nodo();

        Nodo ahora = primer;

        if(opcion==1){
            nuevo.agregar1(let);
            if(primer==null){
             
             primer=nuevo;
             
            

            }else{
                
              
                while(ahora.nodo_sig1!=null){
                    
                    ahora=ahora.nodo_sig1;


                }   
                
                ahora.nodo_sig1=nuevo;
               
            }
        }else{
            nuevo.agregar2(let);
            
            if(primer==null){

             primer=nuevo;
            

            }else{

               
                
                while(ahora.nodo_sig2!=null){
                    
                    ahora=ahora.nodo_sig2;

                }   
                ahora.nodo_sig2=nuevo;
            }
        }
        
    
        return nuevo;
    }

    //Metodo agregar en uno especifico con un nodo inicial y final definidos
    public void Agregar2(char let,Nodo Nodo_i,Nodo Nodo_f,int num){
        //siendo la opcion uno para juntar al final y la opcion 2 es para bucles
        if(num==1){  
            Nodo_i.nodo_sig2=Nodo_f;
        }else{
            Nodo_f.nodo_sig2=Nodo_i;
        }
        Nodo_i.mensaje2=let;
        Nodo_f.mensaje='-';
       

    }

    //Metodo para añadir el nodo final
    public Nodo agregar_fin(int opcion){

        Nodo nuevo = new Nodo();
        Nodo ahora = primer;
        nuevo.mensaje='f';
        nuevo.mensaje2='f';
        if(opcion==1){
            while(ahora.nodo_sig1!=null){
                ahora=ahora.nodo_sig1;
            }
            ahora.nodo_sig1=nuevo;
            return ahora.nodo_sig1;
        }else{
            while(ahora.nodo_sig2!=null){
                ahora=ahora.nodo_sig2;
            }
            ahora.nodo_sig2=nuevo;
            return ahora.nodo_sig2;
        }
        
    }

    //Metodo que agregqa un afnd al final 
   public Nodo fusion_salida_1(AFND nodos, int opcion){
        if(opcion==1){
            if(primer==null){
                primer=nodos.primer;
            }else{
                Nodo ahora=primer;
                while (ahora.nodo_sig1!=null) {
                    ahora=ahora.nodo_sig1;
                }
                ahora.nodo_sig1=nodos.primer;
            }
        }else{
            
            if(primer==null){
                
                primer=nodos.primer;
            }else{
                
                Nodo ahora=primer;
                while (ahora.nodo_sig2!=null) {
                   
                    ahora=ahora.nodo_sig2;

                }
                ahora.nodo_sig2=nodos.primer; 
            }
        }
        return nodos.primer;
        
        
    }

    //Metodo que agrega un nodo al final
    public void Agregar_nodo(Nodo nodo){

        if(nodo==null){
            primer=nodo;
        }else{
            Nodo ahora=primer;
            while(ahora.nodo_sig1!=null){
                ahora=ahora.nodo_sig1;
            }
            ahora.nodo_sig1=nodo;
        }
    }

    //Metodo que devuelve un final
    public ArrayList<Nodo> getFinales() {
        ArrayList<Nodo> finales = new ArrayList<>();
        if (primer == null) return finales;

        Set<Nodo> visitados = new HashSet<>();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(primer);
        visitados.add(primer);

        while (!cola.isEmpty()) {
            Nodo ahora = cola.poll();

            if (ahora.mensaje == 'f' || ahora.mensaje2 == 'f') {
                finales.add(ahora);
            }

            if (ahora.nodo_sig1 != null && !visitados.contains(ahora.nodo_sig1)) {
                visitados.add(ahora.nodo_sig1);
                cola.add(ahora.nodo_sig1);
            }
            if (ahora.nodo_sig2 != null && !visitados.contains(ahora.nodo_sig2)) {
                visitados.add(ahora.nodo_sig2);
                cola.add(ahora.nodo_sig2);
            }
        }

        return finales;
    }

    //Metodo que imprime el afnd para verificar las traslaciones
    public void imprimir() {

        if (primer == null) {
            System.out.println("AFND vacío.");
            return;
        }

        // Usamos un Set para evitar imprimir nodos repetidos
        Set<Nodo> visitados = new HashSet<>();
        Queue<Nodo> cola = new LinkedList<>();

        primer.numero = 0; // numeramos el inicial
        cola.add(primer);
        visitados.add(primer);

        int contador = 1;

        System.out.println("=== Estados y transiciones del AFND ===");

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            System.out.print("Estado " + actual.numero);

            // Marcar estado final
            if (actual.mensaje == 'f' && actual.mensaje2 == 'f') {
                System.out.print(" (FINAL)");
            }
            System.out.println();

            // Transición 1
            if (actual.nodo_sig1 != null) {
                if (actual.nodo_sig1.numero == -1) {
                    actual.nodo_sig1.numero = contador++;
                }
                System.out.println("  --" + actual.mensaje + "--> Estado " + actual.nodo_sig1.numero);

                if (!visitados.contains(actual.nodo_sig1)) {
                    visitados.add(actual.nodo_sig1);
                    cola.add(actual.nodo_sig1);
                }
            }

            // Transición 2
            if (actual.nodo_sig2 != null) {
                if (actual.nodo_sig2.numero == -1) {
                    actual.nodo_sig2.numero = contador++;
                }
                System.out.println("  --" + actual.mensaje2 + "--> Estado " + actual.nodo_sig2.numero);

                if (!visitados.contains(actual.nodo_sig2)) {
                    visitados.add(actual.nodo_sig2);
                    cola.add(actual.nodo_sig2);
                }
            }
        }
        System.out.println("======================================");
    }
    
    // Metodo que formaliza un AFND
    public List<String> imprimirFormal() {
    if (primer == null) {
        System.out.println("AFND vacío.");
        return null;
    }

    List<Nodo> nodos = new ArrayList<>();
    List<String> nombres = new ArrayList<>();
    List<String> estadosFinales = new ArrayList<>();
    List<String> delta = new ArrayList<>();
    List<String> sigma = new ArrayList<>();

    Queue<Nodo> cola = new LinkedList<>();
    Set<Nodo> visitados = new HashSet<>();

    cola.add(primer);
    visitados.add(primer);
    nodos.add(primer);
    nombres.add("q0");

    int contador = 1;

    while (!cola.isEmpty()) {
        Nodo actual = cola.poll();
        int idxActual = nodos.indexOf(actual);
        String nombreActual = nombres.get(idxActual);

        // nodo_sig1
        if (actual.nodo_sig1 != null) {
            Nodo sig = actual.nodo_sig1;
            int idx = nodos.indexOf(sig);
            if (idx == -1) {
                nodos.add(sig);
                nombres.add("q" + contador);
                idx = contador;
                contador++;
            }
            String nombreSig = nombres.get(idx);
            char simbolo = actual.mensaje == '-' ? '_' : actual.mensaje;
            if (actual.mensaje != '-' && actual.mensaje != 'f' && !sigma.contains(String.valueOf(simbolo))) {
                sigma.add(String.valueOf(simbolo));
            }
            delta.add("(" + nombreActual + "," + simbolo + "," + nombreSig + ")");
            if (!visitados.contains(sig)) {
                cola.add(sig);
                visitados.add(sig);
            }
        }

        // nodo_sig2
        if (actual.nodo_sig2 != null) {
            Nodo sig = actual.nodo_sig2;
            int idx = nodos.indexOf(sig);
            if (idx == -1) {
                nodos.add(sig);
                nombres.add("q" + contador);
                idx = contador;
                contador++;
            }
            String nombreSig = nombres.get(idx);
            char simbolo = actual.mensaje2 == '-' ? '_' : actual.mensaje2;
            if (actual.mensaje2 != '-' && actual.mensaje2 != 'f' && !sigma.contains(String.valueOf(simbolo))) {
                sigma.add(String.valueOf(simbolo));
            }
            delta.add("(" + nombreActual + "," + simbolo + "," + nombreSig + ")");
            if (!visitados.contains(sig)) {
                cola.add(sig);
                visitados.add(sig);
            }
        }

        // finales
        if (actual.mensaje == 'f' || actual.mensaje2 == 'f') {
            estadosFinales.add(nombreActual);
        }
    }

    // K
    String K = "{" + String.join(",", nombres) + "}";
    // Sigma
    String Sigma = "{" + String.join(",", sigma) + "}";
    // Delta
    String Delta = "{" + String.join(",", delta) + "}";
    // s
    String s = "q0";
    // F
    String F = "{" + String.join(",", estadosFinales) + "}";

    System.out.println("AFND M:");
    System.out.println("K=" + K);
    System.out.println("Sigma=" + Sigma);
    System.out.println("Delta=" + Delta);
    System.out.println("s=" + s);
    System.out.println("F=" + F);
    return sigma;
}

    
    
}
