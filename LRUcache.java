/*
 * TC: O(1) for get(),put()
 * SC: O(n), HashMap of size n where n: capacity.
 * 
 * Approach: Desigining the LRU using Linked List and HashMap. HashMap used so that i could get the node in O(1) Time.
 * i will add node to the head and remove from the tail.So, all the least recently used node would be towards the tail.
 */

class LRUCache {
    int size;
    HashMap<Integer,LRUcache> map = new HashMap<>();;
    LRUcache head = new LRUcache(0,0);
    LRUcache tail = new LRUcache(0,0);

    public LRUCache(int capacity) {
        this.size = capacity;
        head.next = tail;
        tail.prev = head;
        
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            LRUcache temp = map.get(key);
            remove(map.get(key));
            insert(temp);
            return temp.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));
        }

        if(map.size() == size){
            remove(tail.prev);
        }
        insert(new LRUcache(key,value));
    }

    public void insert(LRUcache node){
        map.put(node.key,node);
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

     public void remove(LRUcache node){
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

public class LRUcache{
    int key,value;
    LRUcache next,prev;

    public LRUcache(int key, int value){
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */