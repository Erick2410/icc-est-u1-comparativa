import java.util.Arrays;
import java.util.Comparator;

public class Metodos {

    public void sortBubble(int[] list) {
        if (list == null || list.length <= 1) return;
        int n = list.length;
        for (int i = 0; i < n - 1; i++) {
            boolean intercambiado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    intercambiado = true;
                }
            }
            if (!intercambiado) break;
        }

    }

    public void metodo2(int[] list){
        if (list == null || list.length <= 1) return;
        int[] auxiliar = new int[list.length];
        mergeSort(list, auxiliar, 0, list.length - 1);
    }

    private void mergeSort(int[] arr, int[] auxiliar, int izquierda, int derecha) {
        if (izquierda >= derecha) return;
        int medio = izquierda + (derecha - izquierda) / 2;
        mergeSort(arr, auxiliar, izquierda, medio);
        mergeSort(arr, auxiliar, medio + 1, derecha);
        merge(arr, auxiliar, izquierda, medio, derecha);
    }

    private void merge(int[] arr, int[] auxiliar, int izquierda, int medio, int derecha) {
        for (int k = izquierda; k <= derecha; k++) {
            auxiliar[k] = arr[k];
        }
        int i = izquierda;
        int j = medio + 1;
        int k = izquierda;

        while (i <= medio && j <= derecha) {
            if (auxiliar[i] <= auxiliar[j]) {
                arr[k++] = auxiliar[i++];
            } else {
                arr[k++] = auxiliar[j++];
            }
        }
        while (i <= medio) {
            arr[k++] = auxiliar[i++];
        }

    }

    public void metodo3(int[] list){
        Arrays.sort(list);

    }
    
    public void metodo4(int[] list){
        Integer[] copiaInteger = new Integer[list.length];
        for (int i = 0; i < list.length; i++) {
            copiaInteger[i] = list[i];
        }
        Comparator<Integer> comparador = Integer::compare;
        Arrays.sort(copiaInteger, comparador);

        for (int i = 0; i < list.length; i++) {
            list[i] = copiaInteger[i];
        }

    }
    
}