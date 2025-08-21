package Design;
import java.util.*;

class SnapshotArray {
    private final Map<Integer , List<int[]>> map = new HashMap<>();
    private int batch_id = 0;
    public SnapshotArray(int length) {

    }

    public void set(int index, int val) {
        map.computeIfAbsent(index,k->new ArrayList<>()).add(new int[]{batch_id,val});
    }

    public int snap() {
        return batch_id++;
    }

    public int get(int index, int snap_id) {
        if(! map.containsKey(index)){
            return 0;
        }
        List<int[]> list = map.get(index);
        int tar = search(list,snap_id);
        return tar != -1 ? list.get(tar)[1] : 0;
    }
    private int search(List<int[]> list, int snap_id){
        int l = 0;
        int r = list.size();
        while (l < r){
            int mid = (l+r)/2;
            if(list.get(mid)[0] < snap_id){
                l = mid + 1;
            }else {
                r = mid -1;
            }
        }
        return l+1;
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
