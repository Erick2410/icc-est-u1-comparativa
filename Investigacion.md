Merge Sort:

Merge Sort (ordenamiento por mezcla) es un algoritmo de ordenamiento eficiente y estable que sigue el paradigma de divide y venceras (divide and conquer). Su implementacion consiste en dividir recursivamente una lista en mitades hasta tener sublistas de un solo elemento, para luego fusionarlas de manera ordenada.

En una comparativa de metodos de ordenamiento y benchmarking (evaluacion comparativa de rendimiento), Merge Sort actua como el estandar de referencia (baseline) de eficiencia(Onlog n) y predictibilidad. Su funcion principal en estas pruebas es demostrar la brecha de rendimiento frente a algoritmos cuadraticos O(n²) (como Burbuja o Insercion) y medir el costo de memoria frente a otros algoritmos rapidos (como Quick Sort o Heap Sort).


1. Metricas clave a medir

En un benchmark academico o tecnico, no basta con medir el tiempo; debes registrar tres dimensiones:
- Tiempo de ejecucion (Wall-clock time): Medido en milisegundos o microsegundos usando contadores de alta precision (ej. time.perf_counter() en Python o System.nanoTime() en Java).
- Consumo de memoria auxiliar: Mide cuanta RAM extra necesita el algoritmo. Aqui es donde Merge Sort muestra su mayor debilidad O(n) frente a algoritmos in-place como Quick Sort (Olog n) o Heap Sort O(1).
- Conteo de operaciones internas: Numero de comparaciones e intercambios/asignaciones. Esto permite validar la teoria matematica independientemente de la velocidad del procesador.


2. Escenarios de prueba (Datasets)

Para evaluar el comportamiento real de Merge Sort frente a sus competidores, debes someterlos a 4 tipos de arreglos con distintos tamanios (ej. N = 100, 1 000, 10 000, 100 000):
1. Datos aleatorios (Caso promedio): Evalua la velocidad general. Quick Sort suele ganar ligeramente aqui por mejor aprovechamiento de la memoria cache del procesador, aunque ambos son (On log n).
2. Datos ya ordenados (Mejor caso para algunos): Insertion Sort y Timsort ganan en O(n), mientras que Merge Sort sigue tardando (On log n) porque siempre divide y mezcla todo el arreglo.
3. Datos ordenados en orden inverso (Peor caso): Merge Sort mantiene su tiempo (On log n) sin degradarse, aplastando a Bubble Sort y a versiones basicas de Quick Sort que caen a O(n²).
4. Datos con muchos elementos repetidos: Permite verificar la estabilidad del algoritmo (si dos registros tienen la misma clave, Merge Sort conserva su orden original).



3. Reglas de oro para que el benchmark sea valido

Si vas a programar esta prueba para un informe o laboratorio, asegurate de cumplir estos tres puntos:
- Usar copias exactas de los datos: Todos los algoritmos deben recibir una copia identica del arreglo desordenado (lista.copy()). Si ordenas la misma lista en memoria, el segundo algoritmo recibira los datos ya ordenados y falseara los resultados.
- Promediar multiples ejecuciones: Ejecuta cada prueba entre 5 y 10 veces para cada tamanio de N y calcula el promedio (o la mediana) para eliminar picos causados por procesos en segundo plano del sistema operativo.
- Descartar el calentamiento (Warm-up): En lenguajes compilados en tiempo de ejecución (como Java o C#), ejecuta el algoritmo un par de veces antes de empezar a cronometrar para que el compilador JIT optimice el codigo.
