import java.util.Objects;

public class Benchmark {

    public static Results medir(Runnable accion, String nombre, String sample) {
        Objects.requireNonNull(accion, "La operación no puede ser nula.");
        Objects.requireNonNull(nombre, "El nombre del método no puede ser nulo.");
        Objects.requireNonNull(sample, "El sample no puede ser nulo.");

        long tiempoInicial = System.nanoTime();
            accion.run();
        long tiempoFinal = System.nanoTime();

        long duracionNs = tiempoFinal - tiempoInicial;

        Results results = new Results(nombre, duracionNs, sample);
        return results;
    }
}

