import java.util.*;

public class validar {

    // Método que devuelve true si la cadena completa es aceptada
    public boolean validador(String cadena, Trasformador.AFD afd) {
        Set<Nodo> estadoActual = afd.inicial;

        for (char simbolo : cadena.toCharArray()) {
            Map<Character, Set<Nodo>> trans = afd.transiciones.get(estadoActual);

            if (trans == null || !trans.containsKey(simbolo)) {
                return false;
            }

            estadoActual = trans.get(simbolo);
        }

        for (Set<Nodo> estadoFinal : afd.finales) {
            if (estadoFinal.equals(estadoActual)) {
                return true;
            }
        }

        return false;
    }

    // Método que busca todas las coincidencias dentro de una cadena y sus posiciones
    public void buscarCoincidencias(String texto, Trasformador.AFD afd) {
        System.out.println("Coincidencias encontradas:");

        for (int i = 0; i < texto.length(); i++) {
            Set<Nodo> estadoActual = afd.inicial;
            int j = i;

            while (j < texto.length()) {
                char simbolo = texto.charAt(j);
                Map<Character, Set<Nodo>> trans = afd.transiciones.get(estadoActual);

                if (trans == null || !trans.containsKey(simbolo)) break;

                estadoActual = trans.get(simbolo);

                // Si llegamos a un estado final
                for (Set<Nodo> estadoFinal : afd.finales) {
                    if (estadoFinal.equals(estadoActual)) {
                        System.out.println("Posición " + i + ": " + texto.substring(i, j + 1));
                        break;
                    }
                }

                j++;
            }
        }
    }
}
