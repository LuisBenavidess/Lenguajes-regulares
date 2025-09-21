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

    int numero;


    //Constructor 
    public Nodo(char pal){
        nodo_sig1=null;
        nodo_sig2=null;
        mensaje=pal;
    }

    //Set
    public void agregar_numero(int numero){
        this.numero=numero;
    }


}
