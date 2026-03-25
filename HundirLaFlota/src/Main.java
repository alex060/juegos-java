import java.util.Scanner;

public class Main {
    // Escáner para pedir datos por consola
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO A HUNDIR LA FLOTA ===");

        // Pedimos los nombres para que no sea genérico
        System.out.print("Nombre del Jugador 1: ");
        Jugador j1 = new Jugador(entrada.nextLine());
        System.out.print("Nombre del Jugador 2: ");
        Jugador j2 = new Jugador(entrada.nextLine());

        // Barcos
        int[] misBarcos = {3, 2, 1};

        // Fase de poner barcos
        montarTablero(j1, misBarcos);
        montarTablero(j2, misBarcos);

        // Variables para el bucle de pelea
        Jugador atacante = j1;
        Jugador victima = j2;
        boolean hayGanador = false;

        while (!hayGanador) {
            limpiarPantalla();
            System.out.println(">>> TURNO DE: " + atacante.getApodo());
            System.out.println("Puntos: " + atacante.getPuntosActuales());

            // Pintamos el radar para ver dónde hemos disparado ya
            pintaTablero(atacante.getMiRadar());

            System.out.print("Fila (0-9): ");
            int f_tiro = entrada.nextInt();
            System.out.print("Columna (0-9): ");
            int c_tiro = entrada.nextInt();

            // Comprobación de tiro
            if (f_tiro < 0 || f_tiro > 9 || c_tiro < 0 || c_tiro > 9) {
                System.out.println("¡Te has salido del mapa! Pierdes el turno por despiste.");
            } else if (victima.getMiMapa()[f_tiro][c_tiro] == 'B') {
                System.out.println("¡BUM! Le has dado a algo.");
                atacante.getMiRadar()[f_tiro][c_tiro] = 'X';
                victima.getMiMapa()[f_tiro][c_tiro] = 'X';

                // Actualizar puntuación
                atacante.setPuntosActuales(atacante.getPuntosActuales() + 1);
            } else {
                System.out.println("Agua... solo has mojado el mapa.");
                atacante.getMiRadar()[f_tiro][c_tiro] = 'O';
            }

            // Ganar con 6 puntos
            if (atacante.getPuntosActuales() == 6) {
                System.out.println("¡ENHORABUENA " + atacante.getApodo() + "! Has hundido toda su flota.");
                hayGanador = true;
            } else {
                System.out.println("Pulsa Enter para que el otro jugador no te vea el tablero...");
                entrada.nextLine(); entrada.nextLine(); // Truco para limpiar el buffer

                // Intercambio de papeles
                Jugador aux = atacante;
                atacante = victima;
                victima = aux;
            }
        }
    }

    // Método para rellenar los barcos
    public static void montarTablero(Jugador p, int[] listaBarcos) {
        limpiarPantalla();
        System.out.println("PREPARACIÓN: " + p.getApodo());
        for (int tam : listaBarcos) {
            pintaTablero(p.getMiMapa());
            System.out.println("Colocando pieza de tamaño " + tam);
            System.out.print("Fila: ");
            int f = entrada.nextInt();
            System.out.print("Col: ");
            int c = entrada.nextInt();

            char o = 'H';
            if (tam > 1) {
                System.out.print("Orientación (h/v): ");
                o = entrada.next().toUpperCase().charAt(0);
            }
            p.posicionarPieza(f, c, tam, o);
            limpiarPantalla();
        }
        System.out.println("¡Listo! Llama al siguiente...");
        entrada.nextLine(); entrada.nextLine();
    }

    // Método optimizado para dibujar matrices
    public static void pintaTablero(char[][] mat) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 45; i++) System.out.println();
    }
}