package biubiu.array;

public class ArrayTest {

	/**
	 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
	 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
	 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 * 
	 * @param target
	 * @param array
	 * @return
	 */
	 public boolean Find(int target, int [][] array) {
	        boolean flag = false;
			for(int i=0;i<array.length;i++){  
	            for(int j=0;j<array[i].length;j++){
	                if(target == array[i][j]){
	                    flag = true;
	                    break;
	                }
	            }
	        }
	        return flag;     
	}
	 
	 
	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
	 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 
	 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
	 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
	 */
	 
	 public int minNumber(int[] arr){
		 
		 int result = 0;
		 
		 /**
		  * 进到里面，找出最小元素，result,旋转后得到一个递增的数组
		  * 解题思路：找到第一个数组元素比后面大的元素，后面的则为最小元素,并且旋转
		  */ 
		 //int flag = 0; //记住元素的位置，用来旋转
		 if (arr.length >0) {
			 for(int i=0;i<arr.length-1;i++){
				 if (arr[i]>arr[i+1]) {
					result = arr[i+1];
					//flag = i;
				}
			 }
			 
			 //调整元素的位置	 	 
		 } 	 
		 return result;
	 }
	 
	 public static void main(String[] args) {
		ArrayTest test = new ArrayTest();
		int number = test.minNumber(new int[]{3,4,5,1,2});
		System.out.println(number);
	}
}
