import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Mytest {
    public static void main(String[] args) {

        for (int i : result(3)) {
            System.out.println(i);
        }
    }

    static public int[] result(int n){
        if(n == 0) return new int[]{0,0};

        int []distant = new int[n];
        distant[0] = 5;
        int sum = distant[0];

        for (int i = 1;i < n;i++){
            distant[i] = distant[i-1] * 2 + i;
            sum += distant[i];
        }

        return new int[]{distant[n-1],sum};
    }
}
