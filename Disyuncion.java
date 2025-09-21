public class Disyuncion {
    
    public AFND crear(AFND a,AFND b){
        AFND fin= new AFND();
        
        fin.Agregar('-',1);
        
        fin.fusion_salida_1(a,1);
       
        Nodo fi=fin.Agregar('-',1);
        
        
        //fin.Agregar('-',2);
        
        Nodo inicio=fin.fusion_salida_1(b, 2);
        
        
        fin.Agregar2('e', inicio, fi);
        
      /*   Nodo nuevo=fin.primer;
        while(nuevo.nodo_sig2!=null){
            System.out.println(nuevo.mensaje);
            nuevo=nuevo.nodo_sig2;
        }
        System.out.println(nuevo.mensaje);*/

        return fin;


    }

}
