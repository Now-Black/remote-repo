import com.sun.security.auth.UnixNumericUserPrincipal;


import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("1");
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
    public int subar12raySum(int[] nums, int k) {
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
    public List<List<Integer>> thr12eeSum(int[] nums) {
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
    public List<Integer> findA12nagrams(String s, String p) {
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
        HashMap<Character,Integer> map = new HashMap<>();
        int left = 0;
        int ans  = 0;
        for(int i = 0; i < s.length();i++){
            map.merge(s.charAt(i),1,Integer::sum);
            while (map.get(s.charAt(i))>1){
                map.merge(s.charAt(left),-1,Integer::sum);
                left++;
            }
            ans = Math.max(ans,i-left);
        }
        return ans;
    }
    public List<Integer> findAnagrams(String s, String p) {
        int[] tar = new int[26];
        int[] now = new int[26];
        List<Integer> ans = new ArrayList<>();
        for(char cur : p.toCharArray())tar[cur-'a']++;
        for(int i = 0;i<s.length();i++){
            now[s.charAt(i)-'a']++;
            if(i<p.length()-1){
                continue;
            }
            if(Arrays.equals(now,tar)){
                ans.add(i-p.length()+1);
            }
            now[s.charAt(i-p.length()+1-'a')]--;
        }
        return ans;
    }
    public int subarraySum(int[] nums, int k) {
        int[] pix = new int[nums.length+1];
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0; i<=nums.length;i++){
            pix[i+1] = pix[i] + nums[i];
        }
        for(int i = 0 ; i < pix.length;i++){
            ans += map.getOrDefault(pix[i]-k,0);
            map.merge(pix[i],1,Integer::sum);
        }
        return ans;
    }
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < nums.length ; i++){
            ans = Math.max(ans,sum);
            sum += nums[i];
            if(sum<0){
                sum = 0;
            }
        }
        return ans;
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals);
        List<int[]> ans = new ArrayList<>();
        for(int i = 0 ; i < intervals.length-1;i++){
            if(intervals[i][1]>intervals[i+1][1]){
                intervals[i+1][0] = intervals[i][0];
            }else if(intervals[i][1]>intervals[i+1][0]){
                intervals[i+1][0] = intervals[i][0];
                intervals[i+1][1] = intervals[i][1];
            }else {
                ans.add(new int[]{intervals[i][0],intervals[i][1]});
            }
        }

        return ans.toArray(new int[ans.size()][]);

    }
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < words.length;i++){
            if(words[i].indexOf(x)!=-1){
                ans.add(i);
            }
        }
        return ans;
    }
    public String triangleType(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        if(a+b<=c && a+c <=b && c+b<=a){return "none";}
        if(a==b && b==c){return "equilateral";}
        if(a==b || b==c || a==c)return "isosceles";
        return "scalene";

    }
    public static int countLargestGroup(int n) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int num = 0;
        int ans = 0;
        for(int i = 1; i <= n ; i++){
            int sum = ds(i);
            map.merge(sum,1,Integer::sum);
            if(map.get(sum) > num){
                num = map.get(sum);
                ans = 1;
            }else {
                ans++;
            }
        }
        return ans;
    }
    public static int ds(int cur){
        int no = 0;
        while (cur>0){
            no += cur%10;
            cur /= 10;
        }
        return no;
    }
    public int countPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for(int s  = 0 ; s < nums.length;s++){
            if(set.contains(nums[s]))continue;
            for(int f = s+1 ; f<nums.length;f++){
                if(set.contains(nums[f]))continue;
                if(nums[f] == nums[s]){
                    set.add(nums[f]);
                    if((s*f)%k==0)ans++;
                }
            }
        }
        return ans;
    }
    public int minimizedStringLength(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for(char c : s.toCharArray()){
            if(!set.contains(c)){
                set.add(c);
                ans++;
            }
        }
        return ans;
    }
    public int[] rowAndMaximumOnes(int[][] mat) {
        int max = 0;
        int idx = 0;
        for(int i = 0 ; i < mat.length ; i++){
            int now = 0;
            for(int cur : mat[i]){
                if(cur == 1){
                    now++;
                }
            }
            if(now > max){
                idx = i;
                max = now;
            }
        }
        return new int[]{idx,max};
    }
    public char[] re_nums(char[] nums){
        char[] ans = new char[nums.length];
        for(int i = 0 ; i<nums.length;i++){
            ans[nums.length-i-1] = nums[i];
        }
        return ans;
    }
    public String answerString(String word, int numFriends) {
        word = word+"!";
        String ans = word.substring(0,word.length()-numFriends+1);
        int s = word.charAt(0) - 'a';
        for(int i = 1 ; i <= word.length()-numFriends;i++){
            if(word.charAt(i)-'a' > s){
                ans = word.substring(i,i+word.length()- numFriends+1);
                s = word.charAt(i)-'a';

            }

        }
        return ans;
    }
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Map<Character,Character> map = new HashMap<>();
        for(int i = 0 ; i < s1.length();i++){
            char c1 = up(s1.charAt(i),map);
            char c2 = up(s2.charAt(i),map);
            if(c1>c2){
                map.put(c1,c2);
            }else if(c1<c2){
                map.put(c2,c1);
            }
        }
        char[] ans = new char[baseStr.length()];
        for(int i = 0 ; i < baseStr.length();i++){
            if(! map.containsKey(baseStr.charAt(i))){
                ans[i] = baseStr.charAt(i);
            }else {
                ans[i] =up(baseStr.charAt(i),map) ;

            }
        }
        return new String(ans);
    }
    public char up(char c , Map<Character,Character> map){
        while (!map.containsKey(c)){
            c = map.get(c);
        }
        return c;
    }
    public static String robot12WithString(String s) {
        int idx_min = compare_char(0,s);
        int r = idx_min+1;
        int l = idx_min-1;
        StringBuilder ans = new StringBuilder();
        ans.append(s.charAt(idx_min));
        Set<Integer> set = new HashSet<>();
        set.add(idx_min);
        while (r < s.length()){
            idx_min = compare_char(r,s);
            if(l>-1 && s.charAt(l) < s.charAt(idx_min)){
                ans.append(s.charAt(l));
                set.add(l);
                l--;
                continue;
            }
            ans.append(s.charAt(idx_min));
            l =idx_min-1;
            while (set.contains(l))l--;
            r= idx_min+1;
            set.add(idx_min);
        }
        for(int i = s.length()-1;i>=0;i--){
            if(set.contains(i))continue;
            ans.append(s.charAt(i));
        }
        return String.valueOf(ans);
    }
    public static int compare_char(int r ,String s){
        int idx_min = r;
        for(int i = r ; i < s.length() ; i++){
            idx_min = s.charAt(idx_min) < s.charAt(i) ? idx_min : i;
        }
        return idx_min;
    }
    public ListNode swap12Pairs(ListNode head) {
        ListNode dummy = new ListNode(0,head);
        if(head==null || head.next==null)return head;
        ListNode pre = dummy;
        ListNode s = head;
        ListNode f = head.next;
        while (f != null ){
            ListNode cur = f.next;

            pre.next = f;
            s.next = f.next;
            f.next = s;

            pre = s;
            s = cur;

            f = cur!=null ? cur.next : null;
        }
        return dummy.next;


    }
    public ListNode sort12List(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur!=null){
            list.add(cur.val);
            cur = cur.next;
        }
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(nums);
        cur = head;
        for (int num : nums) {
            cur.val = num;
            cur = cur.next;
        }
        return head;
    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = new ListNode(0,head);
        while (head != null){
            ListNode cur = head.next;
            head.next.next = head;
            head.next = pre;
            pre = head;
            head = cur;
        }
        return head;
    }
    public ListNode removeNth12FromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode s = dummy;
        ListNode f = dummy;
        for(int i = 0 ; i <=n ; i ++){
            f = f.next;
        }
        while (f!=null){
            f = f.next;
            s = s.next;
        }
        s.next = s.next.next;
        return dummy.next;
    }
    public String clearStars(String s) {
        char[] sufmin = new char[s.length()+1];
        sufmin[0] = Character.MAX_VALUE;
        for(int i = 1 ; i <s.length()+1 ; i++){
            sufmin[i] = (char) Math.min(s.charAt(i),sufmin[i-1]);
        }
        StringBuilder ans = new StringBuilder(s);

        while (ans.indexOf("*")>=0){
            int idx = ans.indexOf("*");
            ans.deleteCharAt(idx);
            ans.deleteCharAt(ans.indexOf(String.valueOf(sufmin[idx])));

        }
        return String.valueOf(ans);

    }
    public ListNode addTwo12Numbers(ListNode l1, ListNode l2) {
        int sumfix = 0;
        ListNode ans = new ListNode();
        ListNode dummy = new ListNode(0,ans);
        while (l1 != null || l2 != null){
            int a = l1!=null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = (a + b + sumfix)%10;
            sumfix = (a + b + sumfix)/10;
            ans.next = new ListNode(sum);
            ans = ans.next;
            if(l1 != null)l1 = l1.next;
            if(l2 != null)l2 = l2.next;
        }
        if(sumfix != 0)ans.next = new ListNode(sumfix);
        return dummy.next;

    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1!=null || list2!=null){
            int a = list1!=null ? list1.val : Integer.MAX_VALUE;
            int b = list2!=null ? list2.val : Integer.MAX_VALUE;
            if(a > b){
                cur.next = new ListNode(b);
                list2 = list2.next;
            }else {
                cur.next = new ListNode(a);
                list1 = list1.next;
            }
        }
        return dummy.next;
    }
    public ListNode detect12Cycle(ListNode head) {
        ListNode f= head;
        ListNode s= head;

        while (f!=null && f.next!=null){
            f = f.next.next;
            s = s.next;
            if(f == s)break;
        }
        if(f==null || f.next==null)return null;
        s = head;
        while (s == f){
            s=s.next;
            f=f.next;
        }
        return s;
    }
    public boolean hasCycle(ListNode head) {
        ListNode f = head;
        ListNode s = head;
        while (f.next!=null && s!=null){
            f=f.next.next;
            s = s.next;
            if(f==s){
                return true;
            }
        }
        return false;



    }
    public ListNode getInter12sectionNode(ListNode headA, ListNode headB) {
        while (headA != null){
            ListNode cur = headB;
            while (cur != null){
                if(headA == cur)return headA;
                cur = cur.next;
            }
            headA = headA.next;
        }
        return null;
    }
    public ListNode removeNt12hFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode f = dummy;
        for(int i = 0 ; i < n ; i++){
            f = f.next;
        }
        ListNode s = dummy;
        while (f != null){
            s = s.next;
            f = f.next;
        }
        s.next = s.next!=null ?  s.next.next : null;
        return head;
    }
    public Node copyRandomList(Node head) {
        Node cur = head;
        HashSet<Node> set = new HashSet<>();
        HashMap<Node,Node> map = new HashMap<>();

        Node dummy = new Node(0);
        Node ans = dummy;
        while (cur!=null){
            ans.next = new Node(cur.val);
            ans = ans.next;
            map.put(cur,ans);
            cur = cur.next;

        }
        cur = head;
        ans = dummy.next;
        while (cur!=null){
            ans.random = map.get(cur.random);
            ans = ans.next;
            cur = cur.next;
        }
        return dummy.next;
    }
    public int lengthOf12LongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int l = 0;
        for(int i = 0 ; i < s.length() ; i++){
            while (set.contains(s.charAt(i))){
                set.remove(s.charAt(l++));
            }
            set.add(s.charAt(i));
            ans = Math.max(ans,i-l+1);
        }
        return ans;
    }
    public List<List<Integer>> three1Sum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ; i++){
            int n = nums[i];
            int l = i+1;
            int r = nums.length-1;
            if(i>0 && nums[i]==nums[i--])continue;
            while (l < r){
                int ln = nums[l];
                int lr = nums[r];
                if(ln + lr + n < 0){
                    r--;
                }else if(ln + lr + n > 0){
                    l++;
                }else if(ln + lr + n == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(ln);
                    list.add(lr);
                    list.add(n);
                    ans.add(list);
                    while (nums[l] == nums[l+1])l++;
                    while (nums[r] == nums[r-1])r--;
                }
            }
        }
        return ans;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != null){
            while (B != null){
                if(A == B)return A;
                B = B.next;
            }
            A = A.next;
        }
        return null;
    }
    public ListNode rever12seList(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;

    }
    public boolean isPalin12drome(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        int r = list.size()-1;
        int l = 0;
        while (l < r){
            if(!list.get(l).equals(list.get(r)))return false;
            l++;
            r--;
        }

        return true;


    }
    public boolean has12Cycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (f!=null && f.next !=null){

            f = f.next.next;
            s = s.next;
            if(f==s)return true;
        }
        return false;
    }
    public ListNode detect121Cycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        ListNode ans = null;
        while (f!=null && f.next !=null){

            f = f.next.next;
            s = s.next;
            if(f==s){ans = f;break;}
        }
        if(ans == null)return ans;
        s=head;
        while (s==ans){
            s = s.next;
            ans =ans.next;
        }
        return s;




    }
    public ListNode sort121List(ListNode head) {
        ListNode cur = head;
        int length = 0;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        int[] nums = new  int[length];
        cur = head;
        int i = 0;
        while (cur != null){nums[i++] = cur.val;cur = cur.next;}
        Arrays.sort(nums);
        cur = head;
        i = 0;
        while (cur!=null){
            cur.val = nums[i++];
            cur = cur.next;
        }
        return head;
    }
    public ListNode sw1apPairs(ListNode head) {
        if(head==null ||head.next==null)return head;
        ListNode dummy = new ListNode(0,head);
        ListNode pre = dummy;
        ListNode s = head;
        ListNode f = head.next;
        while (f!=null && f.next!=null){
            ListNode a = f.next.next;
            ListNode b = f.next;

            s.next = f.next;
            f.next = s;

            pre.next = f;

            pre = f;
            f = a;
            s = b;

        }
        return dummy.next;


    }
    public ListNode remove12NthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        for(int i = 0 ; i < n-1 ; i++){
            cur = cur.next;
        }
        ListNode s = new ListNode(0,head);;
        while (cur!=null){
            s = s.next;
            cur = cur.next;
        }
        s.next = s.next!=null ? s.next.next : null;
        return head;
    }
    public ListNode addT13woNumbers(ListNode l1, ListNode l2) {
        int fix = 0;
        ListNode dummy = new ListNode(0);
        ListNode ans = dummy;
        while (l1 !=null || l2 != null){
            int a = l1!=null ? l1.val : 0;
            int b = l2!=null ? l2.val : 0;
            ans.next = new ListNode((a + b +fix)%10);
            fix = (a + b +fix) /10;

            ans = ans.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if(fix!=0)ans.next = new ListNode(fix);
        return dummy.next;
    }
    public ListNode mergeTwo3Lists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1!=null || list2!=null){
            int a = list1!=null ? list1.val : Integer.MAX_VALUE;
            int b = list2!=null ? list2.val : Integer.MAX_VALUE;
            if(a < b){
                cur.next = new ListNode(a);
                list1 = list1.next;
            }else {
                cur.next = new ListNode(b);
                list2 = list2.next;
            }
            cur = cur.next;
        }
        return dummy.next;


    }
    public Node co1pyRandomList(Node head) {
        Node cur = head;
        Node dummy = new Node(0);
        Node ans = dummy;
        Map<Node,Node> map = new HashMap<>();
        while (cur!=null){
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur!=null){
            ans.next = map.get(cur.next);
            ans.random = map.get(cur.random);
            cur = cur.next;
            ans =ans.next;
        }
        return dummy.next;


    }
    public int maximumDifference(int[] nums) {
        int ans = -1;
        for(int i = 0; i < nums.length;i++){
            for(int j = i+1 ; j < nums.length ; j++){
                if(nums[j] < nums[i])continue;
                ans = Math.max(nums[j]-nums[i],ans);
            }
        }
        return ans;

    }
    public String longestCommonPrefix(String[] strs) {
        String tar = strs[0];
        for(int i = 1 ; i < strs.length;i++){
            int start = 0;
            StringBuilder ans = new StringBuilder();
            while(start<Math.min(tar.length(),strs[i].length()) &&  tar.charAt(start) == strs[i].charAt(start)){
                ans.append(tar.charAt(start));
                start++;
            }
            tar = String.valueOf(ans);
        }
        return tar;

    }
    public int[] two123Sum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if (map.containsKey(target - nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }
    public List<List<String>> groupAn12agrams(String[] strs) {
        Map<int[],List<String> >map = new HashMap<>();
        for(String str : strs){
            int[] nums = new int[26];
            for(char cur : str.toCharArray()){
                nums[cur-'a'] ++;
            }
            if(! map.containsKey(nums)){
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(Arrays.copyOf(nums,26),list);
            }else {
                map.get(nums).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }
    public int lon123gestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for(int num : nums){
            set.add(num);
        }
        for(int num : set){
            if(set.contains(num-1)){
                continue;
            }
            int length = 0;
            while (set.contains(num)){
                num++;
                length++;
            }
            ans = Math.max(length,ans);
        }
        return ans;
    }
    public void m2oveZeroes(int[] nums) {
        int s = 0;
        int f = 0;
        while ( f <= nums.length){
            if(nums[s] != 0){
                s++;
            }else {
                if(nums[f]!=0){
                    nums[s] = nums[f];
                    nums[f] = 0;
                }
            }
            f++;
        }
    }
    public void zero(int[] nums){
        int z = 0;
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[z];
                nums[z] = temp;
                z++;
            }
        }
    }
    public int m2axArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int ans = 0;
        while (l < r){
            int temp = 0;
            if(height[l] < height[r]){
                temp = (r-l) * height[l];
                l++;
            }else {
                temp = (r-l) * height[r];
                r--;
            }
            ans = Math.max(ans,temp);
        }
        return ans;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length ; i++){
            int l = i + 1;
            int r = nums.length-1;
            if(i>0 && nums[i]==nums[i-1])continue;
            while (l < r){
                if(nums[i] + nums[r] + nums[l] < 0 ){
                    l++;
                }else if(nums[i] + nums[r] + nums[l] > 0 ){
                    r--;
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(l);
                    list.add(r);
                    ans.add(list);
                    while(nums[l]==nums[l+1])l++;
                    while (nums[r] == nums[r-1])r--;
                }

            }
        }
        return ans;
    }
    public int l2engthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();
        int l = 0;
        int ans = -1;
        for(int i = 0 ;  i < s.length();i++){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
            }else {
                ans = Math.max(ans,i-l-1);
                while (set.contains(s.charAt(i))){
                    set.remove(s.charAt(l));
                    l++;
                }
            }
        }
        return ans==-1?s.length():ans;
    }
    public List<Integer> f3indAnagrams(String s, String p) {
        List<Integer> ans =new ArrayList<>();
        int length = p.length();
        char[] pc = p.toCharArray();
        Arrays.sort(pc);
        p = new String(pc) ;
        for(int i = 0; i < s.length()-length;i++){
            char[] temp = s.substring(i,i+length).toCharArray();
            Arrays.sort(temp);
            if(p.equals(new String(temp))){
                ans.add(i);
            }
        }
        return ans;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        int j = row-1;
        while (i<col && j>-1){
            if(matrix[i][j] > target){
                j--;
            }else if(matrix[i][j] < target){
                i++;
            }else return true;

        }
        return false;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return Path_Search(preorder,inorder);
    }
    private TreeNode Path_Search(int[] preorder, int[] inorder){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i,j});
        que.remove()
        if(preorder.length == 0)return null;

        TreeNode root = new TreeNode(preorder[0]);
        int i = 0  ;
        for(;i < inorder.length;i++){
            if(inorder[i]==preorder[0]);break;
        }
        int[] preorder_left = Arrays.copyOfRange(preorder,1,i);
        int[] inorder_left = Arrays.copyOfRange(inorder,1,preorder_left.length+1);
        int[] preorder_right = Arrays.copyOfRange(preorder,i+1,preorder.length);
        int[] inorder_right = Arrays.copyOfRange(inorder,preorder_left.length+2,preorder.length);
        root.left = Path_Search(preorder_left,inorder_left);
        root.right = Path_Search(preorder_right,inorder_right);
        return root;

    }


}