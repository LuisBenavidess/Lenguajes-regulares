//Utilizaremos listas enlazadas para imitar los nodos del AFND
public class AFND {
    Nodo primer;
    
    public AFND(){
        primer=null;
    }
    // metodo que agrega un nodo al siguiente (normal) o una concatenacion
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

    
    
    //Metodo agregar en uno especifico  con un nodo inicial y final definidos  crear2(poscion, mensaje)
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

    public Nodo agregar_fin(int opcion){

        Nodo nuevo = new Nodo();
        Nodo ahora = primer;
        
        if(opcion==1){
            while(ahora.nodo_sig1!=null){
                ahora=ahora.nodo_sig1;
            }
            ahora.nodo_sig1=nuevo;
        }else{
            while(ahora.nodo_sig2!=null){
                ahora=ahora.nodo_sig2;
            }
            ahora.nodo_sig2=nuevo;
        }
        return nuevo;
    }

    // otro que tenga la opcion 2 salidad para cuando disyuncion 

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


    public void imprimir_con_codigo(){
        Nodo actual=primer;
        while(actual!=null){
            System.out.println("actual "+actual);
            if(actual.nodo_sig1!=null){
                System.out.println("dirigido "+actual.nodo_sig1);
            }
            System.out.println(actual.mensaje+" >>> ");
            actual=actual.nodo_sig1;
        }

        actual=primer;
        System.out.println(" ");
        System.out.println("///////////////////");
        while(actual!=null){
            System.out.println("actual "+actual);
            if(actual.nodo_sig2!=null){
                System.out.println("dirigido "+actual.nodo_sig2);
            }
            System.out.println(actual.mensaje+" >>> ");
            actual=actual.nodo_sig2;
        }
        //System.out.println("null");
    }

    
}
