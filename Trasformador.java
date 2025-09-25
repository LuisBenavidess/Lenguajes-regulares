import java.util.*;

public class Trasformador {

    //Clase interna para representar el AFD
    public static class AFD {
        Set<Set<Nodo>> estados = new HashSet<>();
        List<Character> alfabeto = new ArrayList<>();
        Map<Set<Nodo>, Map<Character, Set<Nodo>>> transiciones = new HashMap<>();
        Set<Nodo> inicial;
        Set<Set<Nodo>> finales = new HashSet<>();

        // Imprimir en formato formal
        public void imprimirFormal() {
            // Asignar nombres q0, q1, q2...
            List<Set<Nodo>> listaEstados = new ArrayList<>(estados);
            List<String> nombres = new ArrayList<>();
            for (int i = 0; i < listaEstados.size(); i++) {
                nombres.add("q" + i);
            }

            // Construir Delta
            List<String> delta = new ArrayList<>();
            for (int i = 0; i < listaEstados.size(); i++) {
                Set<Nodo> estadoActual = listaEstados.get(i);
                String nombreActual = nombres.get(i);

                Map<Character, Set<Nodo>> trans = transiciones.get(estadoActual);
                if (trans != null) {
                    for (Character simbolo : alfabeto) {
                        Set<Nodo> destino = trans.get(simbolo);
                        if (destino != null) {
                            int idxDestino = listaEstados.indexOf(destino);
                            if (idxDestino != -1) {
                                delta.add("(" + nombreActual + "," + simbolo + "," + nombres.get(idxDestino) + ")");
                            }
                        }
                    }
                }
            }

            // K
            String K = "{" + String.join(",", nombres) + "}";
            // Sigma
            String Sigma = "{" + alfabeto.stream()
                                         .map(String::valueOf)
                                         .reduce((a,b)->a+","+b)
                                         .orElse("") + "}";
            // Estado inicial
            int idxInicial = listaEstados.indexOf(inicial);
            String s = idxInicial != -1 ? nombres.get(idxInicial) : "?";
            // Estados finales
            List<String> finalesNombre = new ArrayList<>();
            for (Set<Nodo> estadoFinal : finales) {
                int idx = listaEstados.indexOf(estadoFinal);
                if (idx != -1) finalesNombre.add(nombres.get(idx));
            }
            String F = "{" + String.join(",", finalesNombre) + "}";

            // Imprimir todo
            System.out.println("AFD M:");
            System.out.println("K=" + K);
            System.out.println("Sigma=" + Sigma);
            System.out.println("Delta={" + String.join(",", delta) + "}");
            System.out.println("s=" + s);
            System.out.println("F=" + F);
        }
    }

    //Metodos que buscan un conjunto de estados
    private Set<Nodo> epsilonClausura(Set<Nodo> estados) {
        Stack<Nodo> stack = new Stack<>();
        Set<Nodo> clausura = new HashSet<>(estados);

        stack.addAll(estados);
        while (!stack.isEmpty()) {
            Nodo actual = stack.pop();

            if (actual.nodo_sig1 != null && actual.mensaje == '-') {
                if (!clausura.contains(actual.nodo_sig1)) {
                    clausura.add(actual.nodo_sig1);
                    stack.push(actual.nodo_sig1);
                }
            }
            if (actual.nodo_sig2 != null && actual.mensaje2 == '-') {
                if (!clausura.contains(actual.nodo_sig2)) {
                    clausura.add(actual.nodo_sig2);
                    stack.push(actual.nodo_sig2);
                }
            }
        }
        return clausura;
    }

    //Metodo de movimiento
    private Set<Nodo> mover(Set<Nodo> estados, char simbolo) {
        Set<Nodo> resultado = new HashSet<>();
        for (Nodo n : estados) {
            if (n.nodo_sig1 != null && n.mensaje == simbolo) {
                resultado.add(n.nodo_sig1);
            }
            if (n.nodo_sig2 != null && n.mensaje2 == simbolo) {
                resultado.add(n.nodo_sig2);
            }
        }
        return resultado;
    }

    //Metodo que convierte un AFND a AFD
    public AFD convertir(AFND afnd, List<Character> sigma) {
        AFD afd = new AFD();
        afd.alfabeto = sigma;

        // Estado inicial = ε-clausura del primer nodo
        Set<Nodo> inicial = new HashSet<>();
        inicial.add(afnd.primer);
        inicial = epsilonClausura(inicial);
        afd.inicial = inicial;
        afd.estados.add(inicial);

        Queue<Set<Nodo>> cola = new LinkedList<>();
        cola.add(inicial);

        while (!cola.isEmpty()) {
            Set<Nodo> actual = cola.poll();
            afd.transiciones.putIfAbsent(actual, new HashMap<>());

            for (char simbolo : sigma) {
                Set<Nodo> moverSet = mover(actual, simbolo);
                Set<Nodo> destino = epsilonClausura(moverSet);

                if (!destino.isEmpty()) {
                    afd.transiciones.get(actual).put(simbolo, destino);

                    if (!afd.estados.contains(destino)) {
                        afd.estados.add(destino);
                        cola.add(destino);
                    }
                }
            }
        }

        // Estados finales = aquellos que contienen algún nodo final
        for (Set<Nodo> estado : afd.estados) {
            for (Nodo n : estado) {
                if (n.mensaje == 'f' || n.mensaje2 == 'f') {
                    afd.finales.add(estado);
                    break;
                }
            }
        }

        return afd;
    }

    //Metodo que impre un AFD
    public void imprimirAFD(AFD afd) {
    // Asignar nombres q0, q1, q2... a cada conjunto de nodos
    List<Set<Nodo>> listaEstados = new ArrayList<>(afd.estados);
    List<String> nombres = new ArrayList<>();
    for (int i = 0; i < listaEstados.size(); i++) {
        nombres.add("q" + i);
    }

    // Construir Delta
    List<String> delta = new ArrayList<>();
    for (int i = 0; i < listaEstados.size(); i++) {
        Set<Nodo> estadoActual = listaEstados.get(i);
        String nombreActual = nombres.get(i);

        Map<Character, Set<Nodo>> trans = afd.transiciones.get(estadoActual);
        if (trans != null) {
            for (Character simbolo : afd.alfabeto) {
                Set<Nodo> destino = trans.get(simbolo);
                if (destino != null) {
                    int idxDestino = listaEstados.indexOf(destino);
                    if (idxDestino != -1) {
                        delta.add("(" + nombreActual + "," + simbolo + "," + nombres.get(idxDestino) + ")");
                    }
                }
            }
        }
    }

    // K
    String K = "{" + String.join(",", nombres) + "}";
    // Sigma
    String Sigma = "{" + String.join(",", afd.alfabeto.stream()
                                                    .map(String::valueOf)
                                                    .toList()) + "}";
    // Estado inicial
    int idxInicial = listaEstados.indexOf(afd.inicial);
    String s = idxInicial != -1 ? nombres.get(idxInicial) : "?";

    // Estados finales
    List<String> finalesNombre = new ArrayList<>();
    for (Set<Nodo> estadoFinal : afd.finales) {
        int idx = listaEstados.indexOf(estadoFinal);
        if (idx != -1) finalesNombre.add(nombres.get(idx));
    }
    String F = "{" + String.join(",", finalesNombre) + "}";

    // Imprimir todo
    System.out.println("AFD M:");
    System.out.println("K=" + K);
    System.out.println("Sigma=" + Sigma);
    System.out.println("Delta={" + String.join(",", delta) + "}");
    System.out.println("s=" + s);
    System.out.println("F=" + F);
}

}

