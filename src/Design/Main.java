package Design;
import com.sun.jmx.remote.internal.ArrayQueue;
import sun.misc.ConditionLock;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static class  c {


    }
    public static void main(String[] args) {
        Main main = new Main();
        List<? super Integer> list = new ArrayList<>();
        int[] neum = new int[11];
         int D =  neum.length;

        Set<Character> set = new HashSet<>();
        StringBuilder s = null;
        Random ran = new Random();
        Map<Integer , Integer> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
//        map.merge(a,1,Integer::sum);
        PriorityQueue<int[]> priorityQueue  = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        StringBuilder str = new StringBuilder();
        str = s;
        String ssss;

        ReentrantLock lock = new ReentrantLock();
        Semaphore semaphore = new Semaphore(1);

        priorityQueue.poll();
        System.out.println((int)Math.sqrt(12));
    }
    public  void  a(){
        c a = new c();
    }
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int index = 0;
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            while (sum >= target){
                ans = Math.min(ans , i-index + 1);
                sum -= nums[index];
                index++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


}






