import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //no se puede sacar fecha con este
       // long nanoTime = System.nanoTime();
        //Se puede sacar, pero es inpresisa
       //long currentTimeMillis = System.currentTimeMillis();

       //System.out.println("Nano time: " + nanoTime);
       //System.out.println("Current time: " + currentTimeMillis);

       //Runnable funcion = () -> System.out.println("Ejecutando tarea");
       //System.out.println("Ejecucion de la funcion runnable");
       //funcion.run();

        Generador g = new Generador();
        Benchmark b = new Benchmark();
        Metodos m = new Metodos();

        //Tres arreglos base
        //10000
        int[] list1 = Generador.generate(10000);
        //50000
        int[] list2 = Generador.generate(50000);
        //100000
        int[] list3 = Generador.generate(100000);

        int[] list1c1 = list1.clone();
        int[] list1c2 = list1.clone();
        int[] list1c3 = list1.clone();
        int[] list1c4 = list1.clone();

        int[] list2c1 = list2.clone();
        int[] list2c2 = list2.clone();
        int[] list2c3 = list2.clone();
        int[] list2c4 = list2.clone();

        int[] list3c1 = list3.clone();
        int[] list3c2 = list3.clone();
        int[] list3c3 = list3.clone();
        int[] list3c4 = list3.clone();

        List<Results> resultados = new ArrayList<Results>();


        Results resultaM1S1 = Benchmark.medir(
                () -> m.sortBubble(list1c1), "Burbuja","10.000");
        Validador.verificarOrdenYConservacion(list1, list1c1, "Burbuja/10.000");
        resultados.add(resultaM1S1);

        Results resultaM1S2 = Benchmark.medir(
                () -> m.sortBubble(list2c1), "Burbuja","50.000");
        Validador.verificarOrdenYConservacion(list2, list2c1, "Burbuja/50.000");
        resultados.add(resultaM1S2);       

        Results resultaM1S3 = Benchmark.medir(
                () -> m.sortBubble(list3c1), "Burbuja","100.000");
        Validador.verificarOrdenYConservacion(list3, list3c1, "Burbuja/100.000");
        resultados.add(resultaM1S3);


        Results resultaM2S1 = Benchmark.medir(
                () -> m.metodo2(list1c2), "Merge sort","10.000");
        Validador.verificarOrdenYConservacion(list1, list1c2, "Merge sort/10.000");
        resultados.add(resultaM2S1);

        Results resultaM2S2 = Benchmark.medir(
                () -> m.metodo2(list2c2), "Merge sort","50.000");
        Validador.verificarOrdenYConservacion(list2, list2c2, "Merge sort/50.000");
        resultados.add(resultaM2S2);       

        Results resultaM2S3 = Benchmark.medir(
                () -> m.metodo2(list3c2), "Merge sort","100.000");
        Validador.verificarOrdenYConservacion(list3, list3c2, "Merge sort/100.000");
        resultados.add(resultaM2S3);


        Results resultaM3S1 = Benchmark.medir(
                () -> m.metodo3(list1c3), "ArraySort","10.000");
        Validador.verificarOrdenYConservacion(list1, list1c3, "ArraySort/10.000");
        resultados.add(resultaM3S1);

        Results resultaM3S2 = Benchmark.medir(
                () -> m.metodo3(list2c3), "ArraySort","50.000");
        Validador.verificarOrdenYConservacion(list2, list2c3, "ArraySort/50.000");
        resultados.add(resultaM3S2);       

        Results resultaM3S3 = Benchmark.medir(
                () -> m.metodo3(list3c3), "ArraySort","100.000");
        Validador.verificarOrdenYConservacion(list3, list3c3, "ArraySort/100.000");
        resultados.add(resultaM3S3);


        Results resultaM4S1 = Benchmark.medir(
                () -> m.metodo4(list1c4), "ArraySortv2","10.000");
        Validador.verificarOrdenYConservacion(list1, list1c4, "ArraySortv2/10.000");
        resultados.add(resultaM4S1);

        Results resultaM4S2 = Benchmark.medir(
                () -> m.metodo4(list2c4), "ArraySortv2","50.000");
        Validador.verificarOrdenYConservacion(list2, list2c4, "ArraySortv2/50.000");
        resultados.add(resultaM4S2);       

        Results resultaM4S3 = Benchmark.medir(
                () -> m.metodo4(list3c4), "ArraySortv2 ","100.000");
        Validador.verificarOrdenYConservacion(list3, list3c4, "ArraySortv2/100.000");
        resultados.add(resultaM4S3);
        
        System.out.println("=== LISTA COMPLETA DE RESULTADOS (12 MEDICIONES) ===");
        for (Results r : resultados) {
            System.out.println(r);
        }
    }
}