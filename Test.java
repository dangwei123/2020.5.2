给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] windows=new int[128];
        int res=0;
        int left=0;
        int right=0;
        while(right<s.length()){
            char c=s.charAt(right);
            windows[c]++;
            right++;
            while(windows[c]>1){
                char t=s.charAt(left);
                windows[t]--;
                left++;
            }
            res=Math.max(res,right-left);
        }
        return res;
    }
}

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
class MinStack {
    private static class Node{
        private int val;
        private int min;
        public Node(int val,int min){
            this.val=val;
            this.min=min;
        }
    }
    /** initialize your data structure here. */
    private Stack<Node> stack;
    private int size;
    public MinStack() {
        stack=new Stack<>();
        size=0;
    }
    
    public void push(int x) {
        Node node=new Node(x,x);
        if(size==0){
            stack.push(node);
        }else{
            if(stack.peek().min<x){
                node.min=stack.peek().min;
            }
            stack.push(node);
        }
        size++;
    }
    
    public void pop() {
        stack.pop();
        size--;
    }
    
    public int top() {
        return stack.peek().val;
    }
    
    public int getMin() {
        return stack.peek().min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
class LRUCache {
    private static class Node{
        private int key;
        private int val;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    private Map<Integer,Node> map;
    private LinkedList<Node> list;
    private int size;
    private int capacity;
    public LRUCache(int capacity) {
        map=new HashMap();
        list=new LinkedList();
        this.capacity=capacity;
        this.size=0;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            Node node=map.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.val=value;
            list.remove(node);
            list.addFirst(node);
        }else{
            if(size==capacity){
                Node delete=list.removeLast();
                map.remove(delete.key);
                size--;
            }
            Node node=new Node(key,value);
            map.put(key,node);
            list.addFirst(node);
            size++;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
