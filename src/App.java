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


       List<Integer> list = g.generate(100);
       System.out.println(list);
       
       Results beenGenList = b.medir(
               () -> g.generate(100000),
                "Generacion de listado","100");

       System.out.println(beenGenList);

    }
}