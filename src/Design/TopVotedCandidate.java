package Design;
import java.util.*;
class TopVotedCandidate {
    private final List<int[]> list = new ArrayList<>();
    public TopVotedCandidate(){

    }
    public TopVotedCandidate(int[] persons, int[] times) {
        int val = 0;
        Map<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < persons.length ; i++){
            map.put(persons[i] , map.getOrDefault(persons[i],0)+1);
            if(map.get(persons[i]) > val){
                list.add(new int[]{times[i],map.get(persons[i])});
            }
        }
    }

    public int q(int t) {
        int l = 0 ;
        int r = list.size()-1;
        while (l < r){
            int mid = (l + r)/2;
            if(list.get(mid)[0] < t){
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return list.get(l)[1];
    }
}
