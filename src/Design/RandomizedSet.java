package Design;

import java.util.*;

class RandomizedSet {
    private final Map<Integer , Integer> map = new HashMap<>();
    private final Random random = new Random();
    private final int length = 200010;
    private final int[] nums = new int[length];
    private int idx = -1;

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else {
            map.put(val,++idx);
            nums[idx] = val;
            return true;
        }
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }else {
            int loc = map.remove(val);
            if(loc != idx){map.put(nums[idx],loc);}
            nums[loc] = nums[idx--];
            return true;
        }
    }

    public int getRandom() {
        return nums[random.nextInt(idx+1)];
    }
}
