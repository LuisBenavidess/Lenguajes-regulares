public class Klene {
    
   

    public AFND crear1(char a){
        AFND clausu = new AFND();
        Nodo nodo_inicial=clausu.Agregar('-',1);
        
        Nodo inicio=clausu.Agregar(a,1);

        Nodo bucle=clausu.Agregar('-',1);
        
        clausu.Agregar2('-',inicio,bucle);
        mostrar(clausu.primer);
        Nodo fin=clausu.Agregar('-',1);
        clausu.Agregar2('-', nodo_inicial,fin);
        return clausu;

    }

    public void mostrar(Nodo nuevo){
        while(nuevo!=null){
            System.out.println("actual "+nuevo);
            System.out.println("dirigido "+nuevo.nodo_sig1);
            System.out.println("Mensaje "+ nuevo.mensaje);
            nuevo=nuevo.nodo_sig1;
        }
        
    }
}
