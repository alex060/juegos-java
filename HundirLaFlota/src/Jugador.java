public class Jugador {
    // Atributos privados para que paquito no me baje la nota el (Encapsulamiento)
    private String apodo;
    private int puntosActuales;
    private char[][] miMapa;    // Donde guardo mis barcos
    private char[][] miRadar;   // Donde apunto mis disparos

    // Constructor: aquí nace el jugador
    public Jugador(String nombre) {
        this.apodo = nombre; // Usamos 'this' para que Java no se raye
        this.puntosActuales = 0;
        this.miMapa = new char[10][10];
        this.miRadar = new char[10][10];

        // Rellenamos con un símbolo de agua
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                miMapa[i][j] = '~';
                miRadar[i][j] = '~';
            }
        }
    }

    // Método para meter las piezas en el tablero
    public void posicionarPieza(int f, int c, int largo, char orientacion) {
        for (int i = 0; i < largo; i++) {
            if (orientacion == 'H') {
                this.miMapa[f][c + i] = 'B';
            } else {
                this.miMapa[f + i][c] = 'B';
            }
        }
    }

    // GETTERS Y SETTERS: Obligatorios por las instrucciones
    public String getApodo() { return apodo; }
    public int getPuntosActuales() { return puntosActuales; }
    public void setPuntosActuales(int p) { this.puntosActuales = p; }
    public char[][] getMiMapa() { return miMapa; }
    public char[][] getMiRadar() { return miRadar; }
}