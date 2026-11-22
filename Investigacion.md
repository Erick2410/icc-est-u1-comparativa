Merge Sort:

Merge Sort (ordenamiento por mezcla) es un algoritmo de ordenamiento eficiente y estable que sigue el paradigma de divide y venceras (divide and conquer). Su implementacion consiste en dividir recursivamente una lista en mitades hasta tener sublistas de un solo elemento, para luego fusionarlas de manera ordenada.

En una comparativa de metodos de ordenamiento y benchmarking (evaluacion comparativa de rendimiento), Merge Sort actua como el estandar de referencia (baseline) de eficiencia(Onlog n) y predictibilidad. Su funcion principal en estas pruebas es demostrar la brecha de rendimiento frente a algoritmos cuadraticos O(n²) (como Burbuja o Insercion) y medir el costo de memoria frente a otros algoritmos rapidos (como Quick Sort o Heap Sort).


1. Metricas clave a medir

En un benchmark academico o tecnico, no basta con medir el tiempo; debes registrar tres dimensiones:
- Tiempo de ejecucion (Wall-clock time): Medido en milisegundos o microsegundos usando contadores de alta precision (ej. time.perf_counter() en Python o System.nanoTime() en Java).
- Consumo de memoria auxiliar: Mide cuanta RAM extra necesita el algoritmo. Aqui es donde Merge Sort muestra su mayor debilidad O(n) frente a algoritmos in-place como Quick Sort (Olog n) o Heap Sort O(1).
- Conteo de operaciones internas: Numero de comparaciones e intercambios/asignaciones. Esto permite validar la teoria matematica independientemente de la velocidad del procesador.


2. Escenarios de prueba (Datasets)

Para evaluar el comportamiento real de Merge Sort frente a sus competidores, debes someterlos a 4 tipos de list
s con distintos tamanios (ej. N = 100, 1 000, 10 000, 100 000):
1. Datos aleatorios (Caso promedio): Evalua la velocidad general. Quick Sort suele ganar ligeramente aqui por mejor aprovechamiento de la memoria cache del procesador, aunque ambos son (On log n).
2. Datos ya ordenados (Mejor caso para algunos): Insertion Sort y Timsort ganan en O(n), mientras que Merge Sort sigue tardando (On log n) porque siempre divide y mezcla todo el list
.
3. Datos ordenados en orden inverso (Peor caso): Merge Sort mantiene su tiempo (On log n) sin degradarse, aplastando a Bubble Sort y a versiones basicas de Quick Sort que caen a O(n²).
4. Datos con muchos elementos repetidos: Permite verificar la estabilidad del algoritmo (si dos registros tienen la misma clave, Merge Sort conserva su orden original).



3. Reglas de oro para que el benchmark sea valido

Si vas a programar esta prueba para un informe o laboratorio, asegurate de cumplir estos tres puntos:
- Usar copias exactas de los datos: Todos los algoritmos deben recibir una copia identica del list
 desordenado (lista.copy()). Si ordenas la misma lista en memoria, el segundo algoritmo recibira los datos ya ordenados y falseara los resultados.
- Promediar multiples ejecuciones: Ejecuta cada prueba entre 5 y 10 veces para cada tamanio de N y calcula el promedio (o la mediana) para eliminar picos causados por procesos en segundo plano del sistema operativo.
- Descartar el calentamiento (Warm-up): En lenguajes compilados en tiempo de ejecución (como Java o C#), ejecuta el algoritmo un par de veces antes de empezar a cronometrar para que el compilador JIT optimice el codigo.
## Ejemplo:
import java.util.listays;
import java.util.Random;

public class MergeSortBenchmark {

    // Clase auxiliar para almacenar las métricas internas de cada ejecución
    static class Metricas {
        long comparaciones = 0;
        long movimientos = 0;
    }

    // ==========================================================================
    // BLOQUE 1: IMPLEMENTACIÓN DE MERGE SORT INSTRUMENTADO
    // ==========================================================================
    public static void mergeSort(int[] list, Metricas metricas) {
        // Caso base: list
        s de 0 o 1 elemento ya están ordenados
        if (list.length <= 1) {
            return;
        }

        // 1. DIVISIÓN: Se crean dos sublist
        s copiando la mitad izquierda y derecha
        int medio = list.length / 2;
        int[] izquierda = listays.copyOfRange(list, 0, medio);
        int[] derecha = listays.copyOfRange(list, medio, list.length);

        // Llamadas recursivas para ordenar cada mitad
        mergeSort(izquierda, metricas);
        mergeSort(derecha, metricas);

        // 2. MEZCLA (MERGE): Fusión ordenada directamente sobre el list
         original
        merge(list, izquierda, derecha, metricas);
    }

    private static void merge(int[] destino, int[] izquierda, int[] derecha, Metricas metricas) {
        int i = 0, j = 0, k = 0;

        // Comparamos elementos de ambas mitades y colocamos el menor en 'destino'
        while (i < izquierda.length && j < derecha.length) {
            metricas.comparaciones++; // Contamos cada comparación entre elementos
            if (izquierda[i] <= derecha[j]) {
                destino[k++] = izquierda[i++];
            } else {
                destino[k++] = derecha[j++];
            }
            metricas.movimientos++;   // Contamos cada asignación al list
             destino
        }

        // Copiamos los elementos restantes de la mitad izquierda (si quedaron)
        while (i < izquierda.length) {
            destino[k++] = izquierda[i++];
            metricas.movimientos++;
        }

        // Copiamos los elementos restantes de la mitad derecha (si quedaron)
        while (j < derecha.length) {
            destino[k++] = derecha[j++];
            metricas.movimientos++;
        }
    }

    // ==========================================================================
    // BLOQUE 2: MOTOR DE MEDICIÓN (TIEMPO, MEMORIA Y WARM-UP)
    // ==========================================================================
    public static void ejecutarPrueba(int[] datasetOriginal, String escenario) {
        // Clonamos el list
         para no alterar el dataset original
        int[] copia = listays.copyOf(datasetOriginal, datasetOriginal.length);
        Metricas metricas = new Metricas();

        // Sugerimos al Garbage Collector limpiar memoria antes de medir
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

        // Medición de tiempo de alta precisión en nanosegundos
        long inicio = System.nanoTime();
        mergeSort(copia, metricas);
        long fin = System.nanoTime();

        long memoriaDespues = runtime.totalMemory() - runtime.freeMemory();

        // Cálculos de conversión
        double tiempoMs = (fin - inicio) / 1_000_000.0;
        double memoriaKb = Math.max(0, (memoriaDespues - memoriaAntes) / 1024.0);

        System.out.printf("%-8d | %-12s | %-12.3f | %-12.2f | %-14d | %-14d%n",
                datasetOriginal.length, escenario, tiempoMs, memoriaKb,
                metricas.comparaciones, metricas.movimientos);
    }

    // ==========================================================================
    // BLOQUE 3: GENERACIÓN DE ESCENARIOS Y EJECUCIÓN PRINCIPAL
    // ==========================================================================
    public static void main(String[] args) {
        // Fase de calentamiento (Warm-up) para que el compilador JIT de Java optimice el código
        int[] warmup = new Random().ints(5000, 1, 10000).tolistay();
        mergeSort(warmup, new Metricas());

        int[] tamanos = {1000, 10000, 50000, 100000};

        System.out.printf("%-8s | %-12s | %-12s | %-12s | %-14s | %-14s%n",
                "N", "Escenario", "Tiempo (ms)", "RAM (KB)", "Comparaciones", "Movimientos");
        System.out.println("--------------------------------------------------------------------------------------");

        for (int n : tamanos) {
            // 1. Escenario Aleatorio
            int[] aleatorio = new Random().ints(n, 1, 100000).tolistay();

            // 2. Escenario Ordenado ascendentemente
            int[] ordenado = new int[n];
            for (int i = 0; i < n; i++) ordenado[i] = i;

            // 3. Escenario Ordenado inversamente
            int[] inverso = new int[n];
            for (int i = 0; i < n; i++) inverso[i] = n - i;

            ejecutarPrueba(aleatorio, "Aleatorio");
            ejecutarPrueba(ordenado, "Ordenado");
            ejecutarPrueba(inverso, "Inverso");
            System.out.println("--------------------------------------------------------------------------------------");
        }
    }
}

# Investigación Técnica del Algoritmo Implementado: Merge Sort

## 1. Identificación del Algoritmo, Variante y Código Fuente
* **Algoritmo investigado:** Merge Sort (Ordenamiento por Mezcla).
* **Variante implementada:** Recursiva *Top-Down* (de listiba hacia abajo) con list
 auxiliar único preasignado.
* **Ubicación en el repositorio:**
  * Archivo: [`src/Metodos.java`](src/Metodos.java)
  * Método público (V2): `Metodos.metodo2(int[] list)`
  * Métodos auxiliares privados: `Metodos.mergeSort(int[] list, int[] auxiliar, int izquierda, int derecha)` y `Metodos.merge(int[] list, int[] auxiliar, int izquierda, int medio, int derecha)`.
  * Invocación en experimentos: Ejecutado desde [`src/App.java`](src/App.java) como variante `"Merge sort"` sobre `list1c2` (`10.000`), `list2c2` (`50.000`) y `list3c2` (`100.000`).

---

## 2. Proceso Paso a Paso, Parámetros, Caso Base y Recursión
1. **Inicialización (`metodo2`):** Verifica si `list == null` o `list.length <= 1`. Reserva una sola vez `int[] auxiliar = new int[list.length]` (costo incluido dentro del intervalo medido por `Benchmark.medir`) e inicia la recursión con `izquierda = 0` y `derecha = list.length - 1`.
2. **Parámetros y caso base (`mergeSort`):** Recibe `list`, `auxiliar` y los índices inclusivos `izquierda` y `derecha`. El **caso base** se cumple cuando `izquierda >= derecha` (segmento de 0 o 1 elemento, ya ordenado por definición).
3. **Progreso de la recursión:** Calcula el punto medio mediante `int medio = izquierda + (derecha - izquierda) / 2` (expresión segura que evita desbordamiento de `int` al sumar índices grandes) y divide el tramo en dos mitades estrictamente menores: `[izquierda, medio]` y `[medio + 1, derecha]`. Al reducirse el tamaño a la mitad en cada nivel, la recursión alcanza el caso base en \lceil \log_2 n \rceil niveles.
4. **Propiedad invariante conservada:** Al retornar de ambas llamadas recursivas, los sublist
s `list[izquierda..medio]` y `list[medio + 1..derecha]` cumplen la invariante de estar ordenados ascendentemente. La llamada posterior a `merge` fusiona ambos tramos conservando esa propiedad en todo el rango `list[izquierda..derecha]`.

---

## 3. División, Mezcla, list
 Auxiliar y Tratamiento de Empates
* **División:** Es puramente lógica mediante aritmética de índices (`izquierda`, `medio`, `derecha`), sin instanciar sublist
s en cada nivel recursivo.
* **list
 auxiliar:** `auxiliar` se crea una única vez en `metodo2` con longitud n. Dentro de `merge`, se copia únicamente el subrango `[izquierda..derecha]` hacia `auxiliar` antes de realizar la fusión sobre `list`.
* **Mezcla y tratamiento de empates:** Se emplean dos punteros de lectura (`i = izquierda` para la mitad izquierda y `j = medio + 1` para la mitad derecha) y un puntero de escritura (`k = izquierda`):
  ```java
  if (auxiliar[i] <= auxiliar[j]) {
      list[k++] = auxiliar[i++];
  } else {
      list[k++] = auxiliar[j++];
  }

  El operador <= determina el tratamiento de empates: cuando auxiliar[i] == auxiliar[j], se copia primero el elemento proveniente de la mitad izquierda, preservando el orden relativo de claves iguales. Al finalizar el ciclo principal, while (i <= medio) copia los elementos restantes de la mitad izquierda; si quedan elementos en la mitad derecha, ya ocupan su posición final en list.

## Complejidad Temporal, Memoria y EstabilidadMejor caso:
 O(n \log n). Aunque el arreglo ya esté ordenado, realiza \lceil \log_2 n \rceil niveles de división y n/2 comparaciones por nivel en merge.   
 Caso promedio: O(n \log n). Con permutaciones aleatorias uniformes en [0, n], cada nivel efectúa entre n/2 y n-1 comparaciones a través de \lceil \log_2 n \rceil niveles.   
 Peor caso: O(n \log n). Ocurre cuando los elementos de ambas mitades se intercalan alternadamente, exigiendo n - 1 comparaciones por nivel; por el Teorema Maestro, T(n) = 2T(n/2) + O(n) = O(n \log n).   Memoria auxiliar: O(n) adicional por el arreglo auxiliar creado en metodo2.  
  Pila recursiva: O(\log n) marcos de pila (profundidad máxima \lceil \log_2 n \rceil + 1 \approx 18 llamadas anidada para n = 100,000).   
 Estabilidad: Estable, verificado en la traza (2_a antes que 2_b, 7_a antes que 7_b) mediante la condición auxiliar[i] <= auxiliar[j].

## Caso Desfavorable y Prueba RealCaso desfavorable:
  Secuencias con valores intercalados entre mitades (ej. [0, 2, 4, 6, 1, 3, 5, 7]), donde merge ejecuta el máximo de comparaciones posibles en cada nivel. Dado que el punto medio se calcula con izquierda + (derecha - izquierda) / 2, el árbol mantiene altura  log_2 n  + 1$, por lo que no ocurre desbordamiento de pila ni de índices.   Prueba verificada: Ejecutado en Validador.ejecutarCasosDeAceptacion() (CA06) sobre [0, 1, 2, 3] y [3, 2, 1, 0], produciendo [0, 1, 2, 3]

## Relacion con Benchmarking
Escalabilidad frente a Burbuja (V1):
  Merge sort pasa de [4063500ns] (10.000) a [51074400ns] (100.000), mientras Burbuja escala cuadráticamente de [103159900ns] a [22115130300ns], evidenciando la diferencia práctica entre O(n \log n) y O(n^2).   
  Comparación frente a ArraySort (V3):
   Arrays.sort(int[]) registra menor tiempo ([12512900ns] en 100.000) que Merge sort ([51074400ns]) porque ordena in-place sin reservar ni copiar el arreglo auxiliar de 
   100,000 enteros dentro del intervalo cronometrado.
     
## Fuentes Técnicas:
T. H. Cormen, C. E. Leiserson, R. L. Rivest y C. Stein, Introduction to Algorithms, 4.ª ed. Cambridge, MA, USA: MIT Press, 2022, cap. 2, pp. 34–44. Consultado: 21 nov. 2026.  
 R. Sedgewick y K. Wayne, Algorithms, 4.ª ed. Boston, MA, USA: Addison-Wesley, 2011, sec. 2.2 ("Mergesort"). En línea: https://algs4.cs.princeton.edu/22mergesort/ — Consultado: 21 nov. 2026.   
 Oracle Corporation, Java SE 17 API Specification: java.util.Arrays y System.nanoTime(). En línea: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html — Consultado: 21 nov. 2026.

