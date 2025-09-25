import java.util.ArrayList;

//Clase que realizara las concatenaciones
public class Cocate {

    //Metodo que crea una concatenacion apartir de 2 afnd
    public AFND crear(AFND afnd1, AFND afnd2) {
        AFND resultado = new AFND();

        if (afnd1 == null || afnd1.primer == null) return afnd2;
        if (afnd2 == null || afnd2.primer == null) return afnd1;

        ArrayList<Nodo> finales = afnd1.getFinales();
        for (Nodo f : finales) {
            // Desmarcar final y poner epsilon hacia afnd2.primer
            if (f.mensaje == 'f') f.mensaje = '-';
            if (f.mensaje2 == 'f') f.mensaje2 = '-';

            f.nodo_sig1 = afnd2.primer;
        }

        resultado.primer = afnd1.primer;
        return resultado;
    }
}
