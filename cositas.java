import java.util.ArrayList;
// Clase que hara todo
public class cositas {

    //Atributos
    public ArrayList<Character> letras;
    public ArrayList<AFND> traslaciones; //cambiar a clase nodo
    public int can_estados;

    //Constructos 
    public cositas(){
        letras= new ArrayList<>();
        traslaciones= new ArrayList<>(); // cambiar clase nodo
    
    }

    //Metodos
    public void get_letras(){
        System.out.println(letras);
    }




    public AFND desarmar(String ER){

        
        AFND fin =new AFND();
       
        if(ER.indexOf('|')!=-1){ 
           fin= disyun(ER);
        }else{
            if(ER.indexOf('*')!=-1){
                fin= clausura_k(ER);
            }else{
                if(ER.indexOf('.')!=-1){
                    fin=concatenacion(ER);
                }else{
                    
                    fin.Agregar(ER.charAt(0), 1);
                    //traslaciones.add(fin);
                }
            }
        }
        //parentesis primero

        
        

        
        return fin;
        

    }


    
    public AFND concatenacion(String ER){
        int i=0;
        AFND fin = new AFND();
        while(i<ER.length()){
            if(ER.charAt(i)=='.'){
                AFND concatenacion =new AFND();
                Cocate coca=new Cocate();
                if(fin.primer==null){
                    concatenacion = coca.crear(ER.charAt(i-1),ER.charAt(i+1));
                    
                    
                }else{
                    
                    concatenacion = coca.crear(ER.charAt(i+1));
                }
               
                fin.fusion_salida_1(concatenacion,1);
            }
            
            i++;
        }
        traslaciones.add(fin);
        return fin;
    }

    public AFND clausura_k(String ER){
        //metodo para que saque solo lo de la clausura 
    
        AFND fin = new AFND();
        if(ER.length()<=2){
            
            Klene clausu = new Klene();
            char let=ER.charAt(0);
          //  System.out.println(let);
            fin=clausu.crear1(let);

        } 
        traslaciones.add(fin);
        return fin;
    }

    public AFND disyun(String ER){
        // falta un metodo para asegurarse de que una disyuncion que no tenga paretesis :)tu puedes luis del futuro y martin del futuro
        // hacer un mapeo de todo y buscar parentesis y todo lo que este dentro no se pesca :)
        AFND fin = new AFND();
        int i=0;
        while(i<ER.length()){
            if(ER.charAt(i)=='|'){
                
                String primeraMitad = ER.substring(0, i);   // desde el inicio hasta antes de mitad
                String segundaMitad = ER.substring(i+1);      // desde mitad hasta el final
               
                AFND mitad1=desarmar(primeraMitad);
                
                AFND mitad2=desarmar(segundaMitad);
                
                

                Disyuncion disyu = new Disyuncion();
                fin = disyu.crear(mitad1, mitad2);

            }
            i++;
        }
        return fin;
       
    }
  

    public void imprimir_good(Nodo actual,ArrayList<Nodo> nodos){
        enumerar(actual, 0);
        if(actual.nodo_sig1!=null){
            Nodo nuevo=actual;
            System.out.println("actual "+ nuevo.numero);
            System.out.println("dirigido 1 "+ nuevo.nodo_sig1.numero);
            System.out.println("mensaje "+ nuevo.mensaje);
            nuevo=nuevo.nodo_sig1;
                
            if(!nodos.contains(nuevo)){
                
                nodos.add(nuevo);
                imprimir_good(nuevo,nodos);
                    
            }
                
                
        }
        System.out.println("/////////////////////////////////");
        if(actual.nodo_sig2!=null){
 
            Nodo nuevo=actual;
            System.out.println("actual "+ nuevo.numero);
            
            System.out.println("dirigido 2 "+ nuevo.nodo_sig2.numero);
           
            if(nuevo.mensaje2==' '){
                
                System.out.println("mensaje "+ nuevo.mensaje);
            }else{
                System.out.println("mensaje "+ nuevo.mensaje2);                                       
            }
            
            nuevo=nuevo.nodo_sig2;
                
            if(!nodos.contains(nuevo)){
                nodos.add(nuevo);
                imprimir_good(nuevo,nodos);
                    
            }
        }
    }

    public int enumerar(Nodo nodo, int i) {
        if (nodo == null) return i;
        if (nodo.numero != -1) return i; 

        nodo.numero = i++;
        if (nodo.nodo_sig1 != null) {
            i = enumerar(nodo.nodo_sig1, i);
        }
        if (nodo.nodo_sig2 != null) {
            i = enumerar(nodo.nodo_sig2, i);
        }
        return i;
}
}
