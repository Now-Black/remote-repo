package Design;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
public class Main {
    static class  c {


    }
    public static void main(String[] args) {
        Main main = new Main();
        List<? super Integer> list = new ArrayList<>();

        c a = new c();

    }
    public  void  a(){
        c a = new c();
    }
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int index = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            if(sum > target)return i-index+1;
            if(sum < 0){
                sum = 0;
                index = i+1;
            }
        }
        return 0;
    }

}






