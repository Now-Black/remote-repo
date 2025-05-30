import com.sun.security.auth.UnixNumericUserPrincipal;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 1 ; i<n;i++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            slow= slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)return ans;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()){
            List<TreeNode> list_next = new ArrayList<>();
            List<Integer> te = new ArrayList<>();
            for(TreeNode temp : list){
                te.add(temp.val);
                if(temp.left!=null)list_next.add(temp.left);
                if(temp.right!=null)list_next.add(temp.right);
            }
            ans.add(te);
            list = list_next;
        }
        return ans;
    }
    public static class LRUCache {
        public int capacity;
        private Map<Integer,Node> map;
        private Node dummy;
        static class Node{
            int val;
            int key;
            Node next;
            Node pre;
            Node(int val,int key){
                this.val = val;
                this.key = key;
            }
        }
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.dummy = new Node(0,0);
            dummy.next = dummy;
            dummy.pre = dummy;
        }
        public int get(int key) {
            if(!map.containsKey(key))return -1;
            delete(map.get(key));
            use_recent(map.get(key));
            return map.get(key).val;
        }
        public void put(int key, int value) {
            if(map.containsKey(key)){
                map.get(key).val = value;
                delete(map.get(key));
                use_recent(map.get(key));
            }else {
                Node temp = new Node(value,key);
                map.put(key,temp);
                use_recent(temp);
            }
            if(map.size() > capacity){
                map.remove(dummy.pre.key);
                delete(dummy.pre);
            }
        }
        private void delete(Node node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        private void use_recent(Node node){
            node.next = dummy.next;
            node.pre = dummy;
            dummy.next.pre = node;
            dummy.next = node;
        }
    }
    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(0,head);
        ListNode cur = head;
        int length = 0;
        while (cur != null){
            cur = cur.next;
            length ++;
        }
        int[] nums = new int[length];
        cur = head;
        int idx = 0;
        while (cur!=null){
            nums[idx++] = cur.val;
            cur = cur.next;
        }
        Arrays.sort(nums);
        idx = 0;
        cur = head;
        while (cur != null){
            cur.val = nums[idx++];
            cur = cur.next;
        }
        return dummy.next;
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = Arrays.asList(new Integer[nums.length]);

        boolean[] onpath = new boolean[nums.length];
        dfspermute(nums,0,ans,list,onpath);
        return ans;
    }
    public void dfspermute(int[] nums,int i,List<List<Integer>> ans,List<Integer> list,boolean[] onpath){
        if(i == nums.length){
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int j = 0 ;j < nums.length;j++){
            if(!onpath[j] ){
                list.set(i,nums[j]);
                onpath[j] = true;
                dfspermute(nums,i+1,ans,list,onpath);
                onpath[j] = false;
            }

        }

    }
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfssubsets(0,nums,list,ans);
        return ans;
    }
    public void dfssubsets(int i,int[] nums,List<Integer> list,List<List<Integer>> ans){
        ans.add(new ArrayList<>(list));
        for(int j = i ; j < nums.length ; j++){
            list.add(nums[j]);
            dfssubsets(i+1,nums,list,ans);
            list.removeLast();
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int fix = 0;
        while (l1 != null || l2 != null){
            int a = l1!=null?l1.val : 0;
            int b = l2!=null? l2.val : 0;
            int sum = fix + (a + b) % 10;
            fix = (a+b)/10;
            cur.next = new ListNode(sum);
            if(l1!=null)l1 = l1.next;
            if(l2!=null)l2 = l2.next;
            cur = cur.next;
        }
        if(fix!=0)cur.next = new ListNode(fix);
        return dummy.next;
    }
    public int subarraySum(int[] nums, int k) {
        int[] sum  = new int[nums.length];
        int suma = 0;
        int ans = 0;
        for(int i = 0;i<nums.length;i++){
            suma += nums[i];
            sum[i] = suma;
        }
        Map<Integer,Integer> map =new HashMap<>();
        for(int num:sum){
            ans += map.getOrDefault(num-k,0);
            map.merge(num,1,Integer::sum);
        }
        return ans;
    }
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0,head);
        ListNode P0 = dummy;
        ListNode P1 = head;
        while (P0 != null && P1 != null){
            ListNode node1 = P1.next;
            ListNode node2 = node1.next;
            P0.next = node1;
            P1.next = node2;
            node1.next = P0;

            P0 = node1;
            P1 = node2;
        }
        return dummy.next;
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean cycle = false;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                cycle = true;
                break;
            }
        }
        if(cycle){
            slow = head;
            while (slow == fast){
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
        return null;
    }
    public int max12Area(int[] height) {
        int l = 0;
        int r = height.length-1;
        int ans = Math.min(height[l],height[r]) * r-l;
        while (l<r){
            if(height[l]<height[r]){
                l++;
            }else r--;
            ans = Math.max(Math.min(height[l],height[r]) * r-l,ans);
        }
        return ans;
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(p,q)->p[0]-q[0]);
        List<int[]> ans = new ArrayList<>();
        for(int[] cur : intervals){
            if(!ans.isEmpty() && ans.getLast()[1]>=cur[0]){
                ans.getLast()[1] = Math.max(ans.getLast()[1],cur[1]);
            }else {
                ans.add(cur);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
    public boolean canJump(int[] nums) {
        int reach = 0;
        for(int i = 0; i < nums.length;i++){
            reach = Math.max(reach,i+nums[i]);
            if(i>reach)return false;
            if(reach>nums.length)return true;
        }
        return false;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0,head);
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null){
            for(int i = 0;i<k&&end!=null;i++){end = end.next;}
            if(end == null)break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next =  next;
            pre = start;
            end = pre;
        }
        return dummy.next;

    }
    public ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre= null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> que = new PriorityQueue<>();
        return pre;
    }
    public int[] two12Sum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }
    public List<List<String>> group12Anagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String cur : strs) {
            char[] no = cur.toCharArray();
            Arrays.sort(no);
            if (map.containsKey(new String(no))) {
                map.get(new String(no)).add(cur);
            } else {
                map.put(new String(no), new ArrayList<>() {{
                    add(cur);
                }});
            }
        }
        return new ArrayList<>(map.values());
    }
    public int longestCo1nsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int length = 0;
        for(int num : nums)set.add(num);
        for(int cur : set){
            if (set.contains(cur-1)){
                continue;
            }
            int no = cur+1;
            while (set.contains(no)){no++;}
            length = Math.max(length,no-cur);
        }
        return length;
    }
    public void move1Zeroes(int[] nums) {
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==0) {
                int idx = i;
                while (nums[idx]==0){
                    idx++;
                }
                nums[i] = nums[idx];
                nums[idx] = 0;
            }
        }
    }
    public int max2Area(int[] height) {
        int r = height.length-1;
        int l = 0;
        int ans = 0;
        while (l < r){
            if(height[l] > height[r]){
                ans = Math.max(ans , height[r] * (r-l));
                r--;
            }else {
                ans = Math.max(ans , height[l] * (r-l));
                l++;
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i<nums.length;i++){
            int l = i+1;
            int r = nums.length-1;
            int cur = nums[i];
            while (l<r){
                List<Integer> list = new ArrayList<>();
                if(nums[l] + nums[r] +cur == 0){
                    list.add(l);
                    list.add(r);
                    list.add(i);
                    ans.add(list);
                    l++;
                    r--;
                }else if(nums[l] + nums[r] < -cur){l++;}
                else if(nums[l] + nums[r] > -cur){r--;}
            }
        }
        return ans;
    }
    public int lengthOfLonges12tSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int l = 0;
        int ans = 0;
        for(int i = 0 ; i< s.length();i++){
            map.merge(s.charAt(i),1,Integer::sum);
            while (map.get(s.charAt(l))>1){
                map.merge(s.charAt(l),-1,Integer::sum);
                l++;
            }
            ans = Math.max(ans,i-l+1);
        }
        return ans;
    }
    public List<Integer> findAnagrams(String s, String p) {
        char[] pc = p.toCharArray();
        Arrays.sort(pc);
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < s.length()-p.length();i++){
            char[] temp = s.substring(i,i+p.length()).toCharArray();
            Arrays.sort(temp);
            if(Arrays.equals(temp, pc)){
                ans.add(i);
            }
        }
        
        return ans;
    }
    public void moveZeroes(int[] nums) {
        int b = 0;
        for (int i = 0 ; i <  nums.length;i++){
            if(nums[i] != 0){
                nums[b] = nums[i];
                nums[i] = 0;
                b++;
            }
        }
    }
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for(int cur : nums)set.add(cur);
        for (int num : nums) {
            if (set.contains(num - 1)) continue;
            int temp = 0;
            int no = num + 1;
            while (set.contains(no)) {
                temp++;
                no = no + 1;
            }
            ans = Math.max(no, ans);
        }
        return ans;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String cur : strs){
            char[] c = cur.toCharArray();
            Arrays.sort(c);
            if(!map.containsKey(Arrays.toString(c))){
                List<String> list = new ArrayList<>();
                list.add(cur);
                map.put(Arrays.toString(c),list);
            }else {
                map.get(Arrays.toString(c)).add(cur);
            }
        }
        List<List<String>> ans = new ArrayList<>(map.values());
        return ans;
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> set = new HashMap();
        for(int i = 0;i < nums.length;i++){
            if(! set.containsKey(target-nums[i])){
                set.put(nums[i],i);
            }else{
                return new int[]{i,set.get(target-nums[i])};
            }
        }
        return null;
    }
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int ans = 0;
        while (left < right){
            if(height[left] < height[right]){
                ans = Math.max(ans,(right-left) * height[left]);
                left ++;
            }else {
                ans = Math.max(ans, (right - left) * height[right]);
                right--;
            }
        }
        return ans;
    }
    public int lengthOfLongestSubstring(String s) {

    }
}