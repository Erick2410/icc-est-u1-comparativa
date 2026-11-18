public class Benchmark {
    
    public static Results medir(Runnable accion, String nombre, String sample){
        System.out.println("Funcion : " + nombre);
        // Tiempo actual
        long tiempoInicial = System.nanoTime();
        accion.run();
        //TIEMPO FINAL
        long tiempoFinal = System.nanoTime();
        //Retornar el tiempo de duracion

        Results results = new Results(nombre, tiempoInicial - tiempoFinal, sample);
        
        return results;

    }
}
