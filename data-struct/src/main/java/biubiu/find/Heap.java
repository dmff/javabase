package biubiu.find;

import java.util.ArrayList;

public class Heap {  
    private ArrayList<Object> list = new ArrayList<Object>();  
  
    public Heap() {  
  
    }  
  
    public Heap(Object[] objects) {  
        for (int i = 0; i < objects.length; i++) {  
            add(objects[i]);  
        }  
    }  
  
    @SuppressWarnings("unchecked")
	public void add(Object newObject) {  
        list.add(newObject);  
        //the index of the last node  
        int currentIndex = list.size() - 1;  
  
        while (currentIndex > 0) {  
            //计算父节点的index  
            int parentIndex = (currentIndex - 1) / 2;  
            //如果当前节点大于他的父节点就交换  
            if (((Comparable<Object>) (list.get(currentIndex))).compareTo(list  
                    .get(parentIndex)) > 0) {  
                Object temp = list.get(currentIndex);  
                list.set(currentIndex, list.get(parentIndex));  
                list.set(parentIndex, temp);  
            } else {  
                break;  
            }  
            currentIndex = parentIndex;  
        }  
    }  
  
    /** 
     * remove the root from the heap 
     *  
     * @return 
     */  
    @SuppressWarnings("unchecked")
	public Object remove() {  
        if (list.size() == 0) {  
            return null;  
        }  
        //被删除的节点---根节点  
        Object removedObject = list.get(0);  
          
        //将最后一个移动到根节点  
        list.set(0,  list.get(list.size() - 1));  
        list.remove(list.size() - 1);  
          
        int currentIndex = 0;  
        while (currentIndex < list.size()) {  
            //计算当前节点的左节点和右节点  
            int leftChildIndex = 2 * currentIndex + 1;  
            int rightChileIndex = 2 * currentIndex + 2;  
              
            //找到两个子节点中最大的节点  
            if (leftChildIndex >= list.size()) {  
                break;  
            }  
            int maxIndex = leftChildIndex;  
            if (rightChileIndex < list.size()) {  
                if (((Comparable<Object>) (list.get(maxIndex))).compareTo(list  
                        .get(rightChileIndex)) < 0) {  
                    maxIndex = rightChileIndex;  
                }  
            }  
  
            //如果当前节点小于子节点的最大值就交换  
            if (((Comparable<Object>) (list.get(currentIndex))).compareTo(list  
                    .get(maxIndex)) < 0) {  
                Object temp = list.get(maxIndex);  
                list.set(maxIndex, list.get(currentIndex));  
                list.set(currentIndex, temp);  
                currentIndex = maxIndex;  
            } else {  
                break;  
            }  
        }  
        return removedObject;  
    }  
  
    public int getSize() {  
        return list.size();  
    }  
  
    public static void main(String[] args) {
    	Heap heap = new Heap(new Integer[] { 8, 9, 2, 4, 5, 6, 7, 5, 3, 0 });  
        while (heap.getSize() > 0) {  
            System.out.println(heap.remove() + " ");  
        } 
	}
}  
