public class Cocate {
    
    public AFND crear(char a, char b){
        AFND coca = new AFND();
        coca.Agregar(a,1);
        coca.Agregar('-',1);
        coca.Agregar(b,1);
        return coca;
    }
     public AFND crear(char b){
        AFND coca = new AFND();
        coca.Agregar('-',1);
        coca.Agregar(b,1);
        return coca;
    }
}
