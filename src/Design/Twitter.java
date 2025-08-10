package Design;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Twitter {
    /**
     * 内部类，描述推文结构
     */
    static class Tweet{
        private int timestate;
        private Tweet next;
        private int tweetId;
        Tweet(int timestate,int tweetId){
            this.timestate = timestate;
            this.tweetId = tweetId;
        }
    }
    private final static Integer number_output = 10;
    /**
     * 全局时间戳
     */
    volatile private AtomicInteger timestate;

    /**
     * 用于维护用户id和推文id的map
     */
    private Map<Integer,Tweet> TweetMap;
    /**
     * 用于维护关注列表
     */
    private Map<Integer,Set<Integer>> FollowMap;

    /**
     * 维护时间戳优先输出队列
     */
    private PriorityQueue<Tweet> priorityQueue;
    public Twitter() {
        TweetMap = new ConcurrentHashMap<>();
        priorityQueue = new PriorityQueue<>((p1,p2)->-p1.timestate+p2.timestate);
        FollowMap = new ConcurrentHashMap<>();
        timestate = new AtomicInteger(0);
    }

    /**
     * 实现发送推文
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {
        int time = timestate.getAndIncrement();
        if(TweetMap.containsKey(userId)){
            Tweet oldtweet = TweetMap.get(userId);
            Tweet newtweet = new Tweet(time,tweetId);
            newtweet.next = oldtweet;
            TweetMap.put(userId,newtweet);
        }else {
            TweetMap.put(userId,new Tweet(time,tweetId));
        }
    }

    /**
     * 输出时间戳最大的10条Tweet对象
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {
        priorityQueue.clear();
        List<Integer> list = new ArrayList<>();
        if(TweetMap.containsKey(userId)){
            priorityQueue.offer(TweetMap.get(userId));
        }
        if (FollowMap.containsKey(userId)){
            for(Integer follid : FollowMap.get(userId)){
                if(TweetMap.containsKey(follid)){
                    priorityQueue.offer(TweetMap.get(follid));
                }
            }
        }
        for(int i = 0 ; i < number_output && priorityQueue.size()>0;i++){
            Tweet now = priorityQueue.poll();
            list.add(now.tweetId);
            if(now.next!=null){
                priorityQueue.offer(now.next);
            }
        }
        return list;
    }

    /**
     * 关注操作
     * @param followerId
     * @param followeeId
     */
    public void follow(int followerId, int followeeId) throws Exception {
        if(followeeId == followerId){
            throw new Exception("不允许关注人与被关注人相同");
        }
        if(FollowMap.containsKey(followerId)){
            if(FollowMap.get(followerId).contains(followeeId)){
                throw new Exception("不允许重复关注");
            }else {
                FollowMap.get(followerId).add(followeeId);
            }
        }else {
            Set<Integer> set = new HashSet<>();
            set.add(followeeId);
            FollowMap.put(followerId,set);
        }
    }

    public void unfollow(int followerId, int followeeId) throws Exception{
        if(followeeId == followerId){
            throw new Exception("不允许取关人与被取关人相同");
        }
        if(FollowMap.containsKey(followerId)){
            if(!FollowMap.get(followerId).contains(followeeId)){
                throw new Exception("取关人不存在");
            }else {
                FollowMap.get(followerId).remove(followeeId);
            }
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */