import java.util.Arrays;

public class Validador {

    public static void verificarOrdenYConservacion(int[] original, int[] ordenado, String etiqueta) {
        if (original.length != ordenado.length) {
            throw new AssertionError(etiqueta + ": tamaño distinto al original.");
        }
        for (int i = 0; i < ordenado.length - 1; i++) {
            if (ordenado[i] > ordenado[i + 1]) {
                throw new AssertionError(etiqueta + ": desorden en índice " + i);
            }
        }
        int[] copiaEsperada = original.clone();
        Arrays.sort(copiaEsperada);
        if (!Arrays.equals(copiaEsperada, ordenado)) {
            throw new AssertionError(etiqueta + ": no conserva exactamente los mismos elementos.");
        }
    }

    public static void ejecutarCasosDeAceptacion() {
        Metodos m = new Metodos();

        // CA01
        for (int n : new int[]{10000, 50000, 100000}) {
            int[] muestra = Generador.generate(n);
            if (muestra.length != n) throw new AssertionError("CA01: longitud incorrecta");
            for (int v : muestra) {
                if (v < 0 || v > n) throw new AssertionError("CA01: valor fuera de [0, n]");
            }
        }

        // CA02
        if (Generador.generate(0).length != 0) throw new AssertionError("CA02: n=0 debe ser vacío");
        boolean lanzoExcepcion = false;
        try {
            Generador.generate(-1);
        } catch (IllegalArgumentException e) {
            lanzoExcepcion = true;
        }
        if (!lanzoExcepcion) throw new AssertionError("CA02: n negativo no rechazado");

        // CA03 y CA04
        int[] base = {7, 2, 7, 0, 4, 2};
        int[] esperado = {0, 2, 2, 4, 7, 7};
        int[] c1 = base.clone();
        int[] c2 = base.clone();
        int[] c3 = base.clone();
        int[] c4 = base.clone();
        m.sortBubble(c1);
        m.metodo2(c2);
        m.metodo3(c3);
        m.metodo4(c4);
        if (!Arrays.equals(c1, esperado) || !Arrays.equals(c2, esperado)
                || !Arrays.equals(c3, esperado) || !Arrays.equals(c4, esperado)) {
            throw new AssertionError("CA04: salida distinta a [0, 2, 2, 4, 7, 7]");
        }

        // CA05
        for (int[] caso : new int[][]{{}, {5}, {3, 3, 3}}) {
            int[] b = caso.clone();
            int[] ms = caso.clone();
            m.sortBubble(b);
            m.metodo2(ms);
            verificarOrdenYConservacion(caso, b, "CA05-Burbuja");
            verificarOrdenYConservacion(caso, ms, "CA05-MergeSort");
        }

        // CA06
        int[] asc = {0, 1, 2, 3};
        int[] desc = {3, 2, 1, 0};
        m.metodo2(asc);
        m.metodo2(desc);
        if (!Arrays.equals(asc, new int[]{0, 1, 2, 3}) || !Arrays.equals(desc, new int[]{0, 1, 2, 3})) {
            throw new AssertionError("CA06: fallo en arreglo ordenado o inverso");
        }

        // CA08 y CA09
        int[] muestraPrueba = Generador.generate(25);
        Results rOk = Benchmark.medir(() -> m.metodo2(muestraPrueba), "Merge sort", "25");
        if (rOk.getDuracionNs() < 0) throw new AssertionError("CA08: duración inválida");

        boolean falloCapturado = false;
        try {
            Benchmark.medir(() -> { throw new RuntimeException("Fallo intencional"); }, "TestError", "10");
        } catch (RuntimeException ex) {
            falloCapturado = true;
        }
        if (!falloCapturado) throw new AssertionError("CA09: no propagó la excepción");

        System.out.println("[OK] CA01 - CA09 verificados.\n");
    }
}