import java.net.Socket;
import java.util.ArrayList;
// Clase que hara todo
public class operadores {

    //Atributos
    public ArrayList<Character> letras;
    public ArrayList<AFND> traslaciones; //cambiar a clase nodo
    public int can_estados;

    //Constructos 
    public operadores(){
        letras= new ArrayList<>();
        traslaciones= new ArrayList<>(); // cambiar clase nodo
    
    }

    //Metodos
    public void get_letras(){
        System.out.println(letras);
    }



    public AFND mapeo(String ER) {
        ER = ER.trim();
        
        if (ER.isEmpty()) return new AFND();

        int balance = 0;

        // 1. Buscar disyunciones '|' fuera de paréntesis
        for (int i = 0; i < ER.length(); i++) {
            
            char c = ER.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            else if (c == '|' && balance == 0) {
                String izquierda = ER.substring(0, i);
                String derecha = ER.substring(i + 1);
                AFND afndIzq = mapeo(izquierda);
                AFND afndDer = mapeo(derecha);
                Disyuncion dis = new Disyuncion();
                return dis.crear(afndIzq, afndDer);
            }
        }

        // 2. Eliminar paréntesis externos si cubren toda la expresión
        if (ER.startsWith("(") && ER.endsWith(")")) {

            int balanceCheck = 0;
            boolean externo = true;
            for (int i = 0; i < ER.length(); i++) {
                
                if (ER.charAt(i) == '(') balanceCheck++;
                else if (ER.charAt(i) == ')') balanceCheck--;
                if (balanceCheck == 0 && i < ER.length() - 1) {
                    externo = false;
                    break;
                }
            }
            if (externo) return mapeo(ER.substring(1, ER.length() - 1));
        }

        // 3. Buscar concatenaciones '.' fuera de paréntesis
        balance = 0;
        for (int i = 0; i < ER.length(); i++) {
            char c = ER.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            else if (c == '.' && balance == 0) {
                String izquierda = ER.substring(0, i);
                String derecha = ER.substring(i + 1);
                AFND afndIzq = mapeo(izquierda);
                AFND afndDer = mapeo(derecha);
                Cocate coc = new Cocate();
                return coc.crear(afndIzq, afndDer);
            }
        }

        // 4. Aplicar Kleene '*' al final
        if (ER.endsWith("*")) {
            
            String sub = ER.substring(0, ER.length() - 1);
            
            AFND afndSub = mapeo(sub);
            
            Klene kle = new Klene();

            return kle.crear(afndSub);
        }

        // 5. Caso base: un solo carácter
        AFND resultado = new AFND();
        Nodo n = resultado.Agregar(ER.charAt(0), 1);
        resultado.agregar_fin(1);
        return resultado;
    }



    public ArrayList<String> separador(String ER, int i,char operacion){
        
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


    
    public AFND concatenacion(AFND ER,AFND ER2){
        AFND fin= new AFND();
        Cocate concatenacion= new Cocate();
        fin=concatenacion.crear(ER, ER2);
               
        return fin;
    }
            

   

    public AFND disyun(AFND ER,AFND ER2){
        // falta un metodo para asegurarse de que una disyuncion que no tenga paretesis :)tu puedes luis del futuro y martin del futuro
        // hacer un mapeo de todo y buscar parentesis y todo lo que este dentro no se pesca :)
        AFND fin = new AFND();
    
        Disyuncion disyu = new Disyuncion();                                                                 
              
        /*AFND mitad1=mapeo(ER);
                
        AFND mitad2=mapeo(ER2);*/
        
        fin = disyu.crear(ER, ER2);
                
                

            
        return fin;
       
    }
  


    
}
