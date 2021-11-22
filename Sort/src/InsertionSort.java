import java.util.Arrays;
import java.util.Random;
public class InsertionSort {

    public static int numCompare = 0, numswaps = 0;

    public static void bubblesort(int[] arr) {
        int firstPos = 0;
        int lastPos = arr.length-1;
        int innerLast = lastPos;
        int temp, lastSwapPos;


        while(firstPos < lastPos){
            lastSwapPos = firstPos;
            for (int j = firstPos; j < lastPos; j++) {
                numCompare++;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    numswaps++;
                    lastSwapPos = j;
                }
            }
            lastPos = lastSwapPos;
            //System.out.println("Pass: " + Arrays.toString(arr));
        }
    }

    public static int[] randomArray(int size){
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    public static void selectionSort(int[] arr){
        int firstPos = 0;
        int lastPos = arr.length-1;
        int temp, smallestPos;

        for (int i = firstPos; i < lastPos; i++) {
            smallestPos = i;
            for (int j = i+1; j <= lastPos; j++) {
                if(arr[j] < arr[smallestPos]){
                    smallestPos = j;
                }
            }
            temp = arr[smallestPos];
            arr[smallestPos] = arr [i];
            arr[i] = temp;
        }
    }

    public static void selectionSort_r(int[] arr, int firstPos, int lastPos){
        int temp, smallestPos;


        if(firstPos< lastPos) {
            smallestPos = firstPos;
            for (int j = firstPos+1; j <= lastPos; j++) {
                if(arr[j] < arr[smallestPos]){
                    smallestPos = j;
                }
            }
            temp = arr[smallestPos];
            arr[smallestPos] = arr [firstPos];
            arr[firstPos] = temp;
            selectionSort_r(arr, firstPos+1,lastPos);
        }
    }

    public static void insertionSort(int[] arr){
        int nextToInsert, index;
        for (int i = 1; i< arr.length;i++){
            nextToInsert = arr[i];
            index = i - 1;
            while(index >= 0 && arr[index] > nextToInsert){
            arr[index+1] = arr[index];
            index--;
            }
            arr[index + 1] = nextToInsert;
        }
    }

    public static void insertionSort_r(int[] arr, int firstPos, int lastPos){
        int nextToInsert, index;
        if(firstPos < lastPos){
            insertionSort_r(arr, firstPos,lastPos-1);
            nextToInsert = arr[lastPos];
            index = lastPos - 1;
            while(index >= 0 && arr[index] > nextToInsert){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index + 1] = nextToInsert;
        }
    }


    public static void main(String[] args) {
        int[] arr;
        arr = randomArray(100);
        insertionSort_r(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
