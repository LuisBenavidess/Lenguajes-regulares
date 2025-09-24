public class Disyuncion {
    
    public AFND crear(AFND a,AFND b){
        AFND fin= new AFND();
        

        fin.Agregar('-',1);
        
        fin.fusion_salida_1(a,1);
       
        fin.Agregar('-',1);

        Nodo fi= fin.agregar_fin(1);
        
        
     
        // parte 2

        fin.primer.agregar2('-');
        
        fin.fusion_salida_1(b, 2);

        Nodo inicio=fin.Agregar('-',2);
        
        
        
        
        fin.Agregar2('-', inicio, fi,1);
        

        return fin;


    }

}
