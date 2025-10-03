package Design;

class MyCircularQueue {
    int[] nums;
    int k;
    int ta;
    int he;
    public MyCircularQueue(int k) {
        this.k = k;
        nums = new int[k];
    }

    public boolean enQueue(int value) {
        if(isFull())return false;
        nums[ta%k]=value;
        ta++;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty())return false;
        he++;
        return true;
    }

    public int Front() {
        if(isEmpty())return -1;
        return nums[he%k];
    }

    public int Rear() {
        if(isEmpty())return -1;
        return nums[ta%k];
    }

    public boolean isEmpty() {
        return ta == he;
    }

    public boolean isFull() {
        return ta - he == k;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
