import java.util.Arrays;
import java.util.Random;
public class MergeSort{

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

    public static void shellSort(int[] arr){
        int temp,index;

        for (int gap = arr.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                for (index = i; index >= gap && arr[index - gap] > temp; index -= gap) {
                    arr[index] = arr[index - gap];
                }
                arr[index] = temp;
            }
        }
    }

    public static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSort_r(arr, temp, 0, arr.length-1);
    }

    public static void mergeSort_r(int[] arr, int[]temp, int first, int last){
        if(first < last){
            int mid = (first+last) / 2;
            mergeSort_r(arr, temp,first, mid);
            mergeSort_r(arr, temp, mid+1, last);
            merge(arr, temp, first, mid, last);
        }
    }

    public static void merge(int[] arr, int[] temp, int first, int mid, int last){
       int pos1 = first, pos2 = mid+1, index = first;

       while(pos1 <= mid && pos2 <= last){
           if (arr[pos1] <= arr[pos2]) temp[index++] = arr[pos1++];
           else temp[index++] = arr[pos2++];
       }

       while(pos1 <= mid) temp[index++] = arr[pos1++];
       while(pos2 <= last) temp[index++] = arr[pos2++];

        for (int i = first; i <= last ; i++) {
            arr[i] = temp[i];
        }
    }




    public static void main(String[] args) {
        int[] arr;
        arr = randomArray(10);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
