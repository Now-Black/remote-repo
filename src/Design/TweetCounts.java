package Design;

import java.lang.reflect.Array;
import java.util.*;

class TweetCounts {
    private Map<String , Map<Integer , Integer>> map;
    private TreeMap<Integer,Integer> treeMap;

    public TweetCounts() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();

    }

    public void recordTweet(String tweetName, int time) {


        if(map.containsKey(tweetName)){
            map.get(tweetName).merge(time,1,Integer::sum);
        }else {
            Map<Integer, Integer> temp = new HashMap<>();
            temp.put(time,1);
            map.put(tweetName,temp);
        }
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int f = 0;
        if(freq.equals("minute")){
            f = 60;
        }else if(freq.equals("hour")){
            f = 60 * 60;
        }else {
            f = 60 * 60 * 24;
        }
        List<Integer> list = new ArrayList<>();
        int temp = startTime;
        while (temp < endTime){
            int count = 0;
            for(Integer key :map.get(tweetName).keySet()){
                if(key < temp){
                    count+=map.get(tweetName).get(key);
                }
            }
            list.add(count);
            temp+=f;

        }
        return list;
    }
}
