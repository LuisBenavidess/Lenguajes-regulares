import java.util.ArrayList;
// Clase que creará las disyunciones atraves de 2 AFND
public class Disyuncion {
 
    public AFND crear(AFND afnd1, AFND afnd2) {
        AFND resultado = new AFND();

        Nodo inicio = new Nodo();
        inicio.mensaje = '-';
        inicio.mensaje2 = '-';
        inicio.nodo_sig1 = (afnd1 != null) ? afnd1.primer : null;
        inicio.nodo_sig2 = (afnd2 != null) ? afnd2.primer : null;

        Nodo finalComun = new Nodo();
        finalComun.mensaje = 'f';
        finalComun.mensaje2 = 'f';

        if (afnd1 != null) {
            ArrayList<Nodo> finales1 = afnd1.getFinales();
            for (Nodo f : finales1) {
                if (f.mensaje == 'f') f.mensaje = '-';
                if (f.mensaje2 == 'f') f.mensaje2 = '-';
                f.nodo_sig1 = finalComun;
            }
        }

        if (afnd2 != null) {
            ArrayList<Nodo> finales2 = afnd2.getFinales();
            for (Nodo f : finales2) {
                if (f.mensaje == 'f') f.mensaje = '-';
                if (f.mensaje2 == 'f') f.mensaje2 = '-';
                f.nodo_sig1 = finalComun;
            }
        }

        resultado.primer = inicio;
        return resultado;
    }
}
