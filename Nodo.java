// clase nodo
public class Nodo {
    //atributos


    //Nodos siguientes
    Nodo nodo_sig1;
    Nodo nodo_sig2;

    //Mensaje que transmite
    char mensaje;
    char mensaje2;
    int numero;


    //Constructor 
    public Nodo(){
        nodo_sig1=null;
        nodo_sig2=null;
        mensaje=' ';
        mensaje2=' ';
        numero=-1;
        
    }

    public void agregar1(char pal){
        mensaje=pal;
    }
    public void agregar2(char pal){
        mensaje2=pal;
    }
    

}
