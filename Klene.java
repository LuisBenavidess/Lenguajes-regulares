public class Klene {
    
   

    public AFND crear1(char a){
        AFND clausu = new AFND();
        Nodo nodo_inicial=clausu.Agregar('-',1);


        
        Nodo inicio=clausu.Agregar(a,1);
        

        Nodo bucle=clausu.Agregar('-',1);

      
        

        clausu.Agregar2('-',inicio,bucle,2); 

        
        
        Nodo fin=clausu.agregar_fin(1);
        clausu.Agregar2('-', nodo_inicial,fin,1);

        
        return clausu;

    }


    

}
