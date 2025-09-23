import java.util.ArrayList;
// Clase que hara todo
public class cositas {

    //Atributos
    public ArrayList<Character> letras;
    public ArrayList<AFND> traslaciones; //cambiar a clase nodo

    //Constructos 
    public cositas(){
        letras= new ArrayList<>();
        traslaciones= new ArrayList<>(); // cambiar clase nodo
    }

    //Metodos
    public void get_letras(){
        System.out.println(letras);
    }


    public void get_traslaciones(int j){
        int i=0;

       /*  while(i<traslaciones.size()){

            System.out.println("Traslacion "+i);
            if(j==1){
                traslaciones.get(i).imprimir();
            }else{
                ArrayList<Nodo> nodos = new ArrayList<>();
                imprimir_good(traslaciones.get(i).primer,nodos);
            }
            
            i++;
        }*/
    }



    public AFND desarmar(String ER){

        
        AFND fin =new AFND();

        if(ER.indexOf('|')!=-1){ 
            disyun(ER);
        }else{
            if(ER.indexOf('*')!=-1){
                fin= clausura_k(ER);
            }else{
                if(ER.indexOf('.')!=-1){
                    concatenacion(ER);
                }else{
                    
                    fin.Agregar(ER.charAt(0), 1);
                    traslaciones.add(fin);
                }
            }
        }
        //parentesis primero

        
        

        
        return fin;
        

    }


    public boolean validar_pal(char pal){
        if(pal=='.' || pal=='|' || pal=='*'|| pal=='('|| pal==')'){
            return false;
        }
        return true;
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

    public void disyun(String ER){
        // falta un metodo para asegurarse de que una disyuncion que no tenga paretesis :)tu puedes luis del futuro y martin del futuro
        // hacer un mapeo de todo y buscar parentesis y todo lo que este dentro no se pesca :)
        AFND fin = new AFND();
        int i=0;
        while(i<ER.length()){
            if(ER.charAt(i)=='|'){
                
                String primeraMitad = ER.substring(0, i);   // desde el inicio hasta antes de mitad
                String segundaMitad = ER.substring(i+1);      // desde mitad hasta el final
               
                AFND mitad1=desarmar(primeraMitad);
                
               // Nodo nuevo= mitad1.primer;
                
                /*while(nuevo.nodo_sig2!=null){
                    System.out.println(nuevo.mensaje);
                    nuevo=nuevo.nodo_sig2;
                }
                System.out.println(nuevo.mensaje);*/
                
                AFND mitad2=desarmar(segundaMitad);
                
                Disyuncion disyu = new Disyuncion();
                fin = disyu.crear(mitad1, mitad2);

            }
            i++;
        }
       // traslaciones.add(fin);
    }
  

    public void imprimir_good(Nodo actual,ArrayList<Nodo> nodos){
        
        if(actual.nodo_sig1!=null){
            Nodo nuevo=actual;
            System.out.println("actual "+ nuevo);
            System.out.println("dirigido 1 "+ nuevo.nodo_sig1);
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
            System.out.println("actual "+ nuevo);
            System.out.println("dirigido 2 "+ nuevo.nodo_sig2);
            System.out.println("mensaje "+ nuevo.mensaje2);
            nuevo=nuevo.nodo_sig2;
                
            if(!nodos.contains(nuevo)){
                nodos.add(nuevo);
                imprimir_good(nuevo,nodos);
                    
            }
        }
    }
}
