public class SumIntegers {
    public static long  sum_A(int n){
        long sum = 0;
        for(int x=1;x <= n;x++){
            sum = sum + x; 
        }
        return sum;
    }

    public static long sum_B(int n){
        long sum = 0;

        for(long k=1;k <= n ;k++){
            for(long j=1;j <=k ;j++){
                sum = sum + 1; 
            } 
        }
        return sum;
    }
    public static long sum_C(int i){
        long sum = 0;
        return i*(i+1) / 2;
    }

    public void mstart(){
    }

    public void mend(){
    }

    public static void main (String args[]){
        int[] nums = {1,10,100,1000,10000,100000,1000000};
        long startTime, endTime;
        String result="Num elements\tAlg A\t\tAlg B\t\t\t\talgC\n";

        for(int i : nums){
            result += i+"\t\t";
            startTime = System.nanoTime();
            sum_A(i);
            endTime = System.nanoTime();
            result += (endTime - startTime) + "\t\t";

            startTime = System.nanoTime();
            sum_B(i);
            endTime = System.nanoTime();
            result += (endTime - startTime) + "\t\t\t\t";

            startTime = System.nanoTime();
            sum_C(i);
            endTime = System.nanoTime();
            result += (endTime - startTime) + "\n";
        }
System.out.println(result);


    }
}
