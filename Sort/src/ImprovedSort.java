import java.util.Arrays;
import java.util.Random;


public class ImprovedSort {

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

    public static void main(String[] args) {
        int[] arr;
        long startTime, endTime;
        int[] arraySizes = {100,200,400,800,1600,3200,6400};
        for (int arraySize : arraySizes) {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                arr = randomArray(arraySize);

                bubblesort(arr);

            }
            endTime = System.currentTimeMillis();
            System.out.println("ArraySize:"+ arraySize + "\tTook:" + (endTime-startTime)/100 + "s");
        }


    }
}
