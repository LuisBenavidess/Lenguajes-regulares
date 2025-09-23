// clase nodo
public class Nodo {
    //atributos

    //Nodos anteriores
    Nodo nodo_ant1;
    Nodo nodo_ant2;

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
        
    }

    public void agregar1(char pal){
        mensaje=pal;
    }
    public void agregar2(char pal){
        mensaje2=pal;
    }
    

}
