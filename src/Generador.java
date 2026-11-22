import java.util.Random;

public class Generador {

    public static int[] generate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El tamaño no puede ser negativo: " + n);
        }
        if (n > Integer.MAX_VALUE - 1) {
            throw new IllegalArgumentException("Tamaño excede el límite seguro para evitar desbordamiento.");
        }
        if (n == 0) {
            return new int[0];
        }

        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n + 1); // Entre 0 y n inclusive
        }
        return arr;
    }
}
