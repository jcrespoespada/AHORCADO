package juego;

import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

    private static final String[] PALABRAS = {
        "programacion", "java", "ordenador", "teclado", "variable",
        "git", "algoritmo", "servidor", "compilador", "bucle"
    };

    private static final int MAX_FALLOSS = 6;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        boolean seguir = true;

        System.out.println("=== JUEGO DEL AHORCADO ===");

        while (seguir) {
            String palabra = PALABRAS[random.nextInt(PALABRAS.length)];
            jugar(palabra, input);

            System.out.print("¿Quieres jugar otra vez? (s/n): ");
            String respuesta = input.nextLine().trim().toLowerCase();
            seguir = respuesta.equals("s");
        }

        System.out.println("¡Hasta la próxima!");
        input.close();
    }

    private static void jugar(String palabra, Scanner input) {
        StringBuilder oculta = new StringBuilder("_".repeat(palabra.length()));
        StringBuilder usadas = new StringBuilder();
        int falloss = 0;

        while (falloss < MAX_FALLOSS && oculta.indexOf("_") != -1) {
            System.out.println("\n" + dibujar(falloss));
            System.out.println("Palabra: " + oculta.toString().replace("", " ").trim());
            System.out.println("Letras usadas: " + usadas);
            System.out.print("Introduce una letra: ");

            String texto = input.nextLine().trim().toLowerCase();

            if (texto.length() != 1 || !Character.isLetter(texto.charAt(0))) {
                System.out.println("Introduce una sola letra.");
                continue;
            }

            char letra = texto.charAt(0);

            if (usadas.indexOf(String.valueOf(letra)) != -1) {
                System.out.println("Ya has usado esa letra.");
                continue;
            }

            usadas.append(letra).append(" ");

            if (palabra.indexOf(letra) != -1) {
                for (int i = 0; i < palabra.length(); i++) {
                    if (palabra.charAt(i) == letra) {
                        oculta.setCharAt(i, letra);
                    }
                }
                System.out.println("¡Bien! La letra está en la palabra.");
            } else {
                falloss++;
                System.out.println("La letra no está. Fallos: " + falloss + "/" + MAX_FALLOSS);
            }
        }

        System.out.println("\n" + dibujar(falloss));

        if (oculta.indexOf("_") == -1) {
            System.out.println("¡Enhorabuena! Has adivinado: " + palabra);
        } else {
            System.out.println("Has perdido. La palabra era: " + palabra);
        }
    }

    private static String dibujar(int falloss) {
        String[] partes = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };

        return partes[falloss];
    }
}