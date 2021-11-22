import java.util.Arrays;

public class Sort {

    public static int numCompare = 0, numswaps = 0;

    public static void bubblesort(int[] arr) {
        int lastPos = arr.length-1;
        int innerLast = lastPos;
        int temp;


        for (int i = 0; i < lastPos; i++) {
            for (int j = 0; j < innerLast; j++) {
                numCompare++;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    numswaps++;
                }
            }
            innerLast--;
            System.out.println("Pass: " + (i+1) + " " + Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {
        int[] arr = {1,9,3,6,0,10,4,8,1,3};
        System.out.println("Before: " + Arrays.toString(arr));
        bubblesort(arr);
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println("Comp:  " + numCompare);
        System.out.println("Swap:  " + numswaps);
    }
}
