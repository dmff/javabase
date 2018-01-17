package biubiu.array;

/**
 * 队列加强版
 * 不带数据项技术字段的队列实现
 */
public class QueuePlus {
    private int[] queArray;
    private int rear;
    private int front;
    private int maxSize;

    public QueuePlus(int maxSize){
        this.maxSize = maxSize + 1;
        queArray = new int[this.maxSize];//万分注意，此处一定要加this！！！！
        rear = -1;
        front = 0;
    }

    public void insert(int value){
        if (rear == maxSize - 1){
            rear = -1;
        }
        queArray[++rear] = value;
    }

    public int remove(){
        int temp = queArray[front++];
        if (front == maxSize){
            front = 0;
        }
        return temp;
    }

    public int peekFront(){
        return queArray[front];
    }

    public boolean isEmpty(){
        return rear + 1 == front || front + maxSize - 1 == rear;
    }

    public boolean isFull(){
        return rear + 2 == front || front + maxSize - 2 == rear;
    }

    public int size(){
        if (rear >= front){
            return rear - front + 1;
        }else {
            return maxSize - (front - rear - 1);
        }
    }
}

/**
 * 测试类
 */
class QueuePlusTest{
    public static void main(String[] args) {
        QueuePlus queuePlus = new QueuePlus(5);
        queuePlus.insert(1);
        queuePlus.insert(2);
        queuePlus.insert(3);
        queuePlus.insert(4);
        queuePlus.insert(5);
        queuePlus.remove();
        queuePlus.remove();
        queuePlus.remove();
        queuePlus.remove();
        queuePlus.remove();
        queuePlus.insert(6);
        while (!queuePlus.isEmpty()){
            System.out.println(queuePlus.remove());// TODO
        }
        System.out.println(queuePlus.size());// TODO
    }
}
