public class Klene {
    
   

    public AFND crear1(char a){
        AFND clausu = new AFND();
        Nodo nodo_inicial=clausu.Agregar('-',1);

        
        Nodo inicio=clausu.Agregar(a,1);

        Nodo bucle=clausu.Agregar('-',1);
        
        clausu.Agregar2('-',inicio,bucle,2); 
        mostrar(clausu.primer);
        Nodo fin=clausu.agregar_fin(1);
        clausu.Agregar2('-', nodo_inicial,fin,1);
        return clausu;

    }

    public void mostrar(Nodo nuevo){
       /*  while(nuevo!=null){
            System.out.println("actual "+nuevo);
            System.out.println("dirigido "+nuevo.nodo_sig1);
            System.out.println("Mensaje "+ nuevo.mensaje);
            nuevo=nuevo.nodo_sig1;
        }*/
        
    }

    public void mostrar2(Nodo nuevo){

       /*  System.out.println("actual "+ nuevo);
        
        while(nuevo.nodo_sig1!=null || nuevo.nodo_sig2!=null){

            if(nuevo.nodo_sig1!=null){

                System.out.println("dirigido 1 "+ nuevo.nodo_sig1);
                System.out.println("Mensaje "+ nuevo.mensaje);
                
            }
        }*/
        
    }

}
