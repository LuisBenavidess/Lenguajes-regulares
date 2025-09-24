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
                   // fin=concatenacion(ER);
                }else{
                    
                    fin.Agregar(ER.charAt(0), 1);
                    //traslaciones.add(fin);
                }
            }
        }
        //parentesis primero

        
        

        
        return fin;
        

    }

    public AFND mapeo(String ER){
        AFND fin=new AFND();
        int i=0;
        while(i<ER.length()){
            if(ER.charAt(i)=='.'){
               
                ArrayList<String> palabra = separador(ER, i, '.');
                String parte1=" ";
                String parte2=" ";
                System.out.println(palabra);
                if(palabra.get(0).indexOf('(')!=-1){
                    parte1=parentesis(palabra.get(0));
                }else{
                    parte1=palabra.get(0);
                }
                if(palabra.get(1).indexOf('(')!=-1){
                    parte2=parentesis(palabra.get(1));
                }else{
                    parte2=palabra.get(1);
                }
                
                fin=concatenacion(parte1,parte2);

                
                //fin=concatenacion(palabra);
            }else{
                if(ER.charAt(i)=='|'){
                    
                }
            }
            i++;
        }
        return fin;
    }

    public ArrayList<String> separador(String ER, int i,char operacion){
        
        if(operacion=='.'){
            char let1=ER.charAt(i+1);
            String izquierdo;
            if (ER.charAt(i - 1) == ')') {
                int balance = 1;
                int j = i - 2;
                while (j >= 0 && balance > 0) {
                    if (ER.charAt(j) == ')') balance++;
                    else if (ER.charAt(j) == '(') balance--;
                    j--;
                }
                izquierdo = ER.substring(j + 1, i); // incluye paréntesis
            } else {
                izquierdo = String.valueOf(ER.charAt(i - 1));
            }

            // --- Buscar operando derecho ---
            String derecho;
            if (ER.charAt(i + 1) == '(') {
                int balance = 1;
                int j = i + 2;
                while (j < ER.length() && balance > 0) {
                    if (ER.charAt(j) == '(') balance++;
                    else if (ER.charAt(j) == ')') balance--;
                    j++;
                }
                derecho = ER.substring(i + 1, j); // incluye paréntesis
            } else {
                derecho = String.valueOf(ER.charAt(i + 1));
            }
            ArrayList<String> palabras = new ArrayList<>();
            palabras.add(izquierdo);
            palabras.add(derecho); 
            return palabras;
            
        }else{
            if(operacion=='('){
                
            }
        }
        return null;
    }

    public String parentesis(String ER){
        int inicio = ER.indexOf('(');
        int balance = 1;
        int i = inicio + 1;

        while (i < ER.length() && balance > 0) {
            if (ER.charAt(i) == '(') balance++;
            else if (ER.charAt(i) == ')') balance--;
            i++;
        }

        if (balance != 0) {
            throw new IllegalArgumentException("Paréntesis desbalanceados en la expresión.");
        }

        // substring desde después de '(' hasta antes de ')'
        return ER.substring(inicio + 1, i - 1); 
    }


    
    public AFND concatenacion(String ER,String ER2){
        AFND fin = new AFND();
        
        Cocate coca=new Cocate();

        AFND parte1;
        AFND parte2;

        
        if(ER.length()==1 && ER2.length()==1){
            
            fin = coca.crear(ER.charAt(0), ER2.charAt(0));
        }else{
            if(ER.length()>=2){
                parte1 = mapeo(ER);
                if(ER2.length()>=2){
                    parte2 = mapeo(ER2);
                    fin=coca.crear(parte1.primer, parte2.primer);
                }else{
                    
                    fin=coca.crear(parte1,ER2.charAt(0));
                }
            }else{
                
                if(ER2.length()>=2){
                    
                    parte2 = mapeo(ER2);

                    fin=coca.crear(ER.charAt(0),parte2);
                }
            }
        }                                                               
        
               
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
