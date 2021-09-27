package localhost.bag;

public class Printer {

    public static void main(String[] args) {
        Integer[] intArray = {1,2,3};
        String[] strArray = {"one","two","three"};
        //printArray(intArray);
        //printArray(strArray);
    }

    public static <T> void printArray(T[] collection){
        String result = "[";
        for (int i = 0; i < collection.length; i++) {
            if(collection[i] != null)result += collection[i] + " , ";
        }
        result = result.substring(0,result.length()-3);
        result += "]";
        System.out.print(result);
    }
}
}
