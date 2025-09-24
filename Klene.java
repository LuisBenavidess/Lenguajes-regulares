import java.util.ArrayList;

public class Klene {

    public AFND crear(AFND afnd) {
        AFND resultado = new AFND();

        Nodo inicio = new Nodo();
        inicio.mensaje = '-';
        inicio.mensaje2 = '-';

        Nodo fin = new Nodo();
        fin.mensaje = 'f';
        fin.mensaje2 = 'f';

        // conectar inicio a la subexpresion y al fin (0 repeticiones)
        inicio.nodo_sig1 = afnd.primer;
        inicio.nodo_sig2 = fin;

        // conectar TODOS los finales de la subexpresion al inicio (repetir)
        // y también al final común
        ArrayList<Nodo> finales = afnd.getFinales();
        for (Nodo f : finales) {
            if (f.mensaje == 'f') f.mensaje = '-';
            if (f.mensaje2 == 'f') f.mensaje2 = '-';
            f.nodo_sig1 = afnd.primer;  // volver a la subexpresion
            f.nodo_sig2 = fin;          // y también al final común
        }

        resultado.primer = inicio;
        return resultado;
    }
}
