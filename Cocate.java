public class Cocate {
    
    public AFND crear(char a, char b){
        AFND coca = new AFND();
        coca.Agregar(a,1);
        coca.Agregar('-',1);
        coca.Agregar(b,1);
        coca.agregar_fin(1);
        return coca;
    }
     public AFND crear(char b){
        AFND coca = new AFND();
        coca.Agregar('-',1);
        coca.Agregar(b,1);
        coca.agregar_fin(1);
        return coca;
    }
    public AFND crear(Nodo nodo1, Nodo nodo2){
        AFND fin = new AFND();
        fin.Agregar_nodo(nodo1);
        fin.Agregar('-', 1);
        fin.Agregar_nodo(nodo2);

        return fin;
    }
    public AFND crear(AFND afnd,char a){

        AFND fin = new AFND();
        fin.Agregar_nodo(afnd.primer);
        fin.Agregar('-', 1);
        fin.Agregar(a, 1);

        return fin; 

    }
    public AFND crear(char a,AFND afnd){

        AFND fin = new AFND();
        fin.Agregar(a, 1);
        fin.Agregar('-', 1);
        fin.Agregar_nodo(afnd.primer);
        

        return fin; 

    }
    public AFND crear(AFND afnd1, AFND afnd2){

        AFND fin = new AFND();
        
        fin.Agregar_nodo(afnd1.primer);
        fin.Agregar('-', 1);
        fin.Agregar_nodo(afnd2.primer);
        

        return fin; 

    }
}
