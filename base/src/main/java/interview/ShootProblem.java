package interview;

import java.util.Stack;

/**
 * 用回溯法求解打靶问题：打靶打10次，打中90环的可能性有多少？
 * 用回溯法实现，控制台输出：递归实现：打10次90环的可能性：92378种，迭代实现：打10次90环的可能性：92378种。
 * 
 * @author dmf
 *
 */
public class ShootProblem {

	public static void main(String[] args) {
		ShootTree shootTree = new ShootTree(10, 90);
		System.out.println("递归实现：打10次90环的可能性：" + shootTree.getSum() + "种");
		System.out.println("迭代实现：打10次90环的可能性：" + shootTree.getSumIteration() + "种");
	}

	static class ShootTree {

		private int count; //需要打靶的次数
		private int circles; //需要打中的总靶数
		private int sum; // 记录打中的总靶数

		public ShootTree(int count, int circles) {
			super();
			this.count = count;
			this.circles = circles;
		}

		private int getSum() {
			solve(0, 0, -1);
			return this.sum;
		}

		private int getSumIteration() {
			solveIteration(0, 0, -1);
			return this.sum;
		}

		class Three {

			private int node; //当前次数打中的靶数
			private int sum;  //在此之前打中的总共的靶数
			private int nodes;  //目前属于第几次打靶

			public Three(int node, int sum, int nodes) {
				super();
				this.node = node;
				this.sum = sum;
				this.nodes = nodes;
			}
		}

		private void solveIteration(int node, int sum, int nodes) {
			Stack<Three> threes = new Stack<>();
			threes.push(new Three(node, sum, nodes));
			while (!threes.isEmpty()) {
				Three three = threes.pop();
				node = three.node;
				sum = three.sum;
				nodes = three.nodes;
				if (sum+node <=this.circles &&sum+node+(this.count-nodes-1)*10>=this.circles) {
					if (nodes+1 < this.count) {
						for(int i=10;i>=0;i--){
							threes.push(new Three(i, sum+node, nodes+1));
						}
					}else {
						if (sum + node == this.circles) {
							this.sum++;
						}
					}
				}	
			}
		}

		private void solve(int node, int sum, int nodes) {
			if (sum+node <=this.circles &&sum+node+(this.count-nodes-1)*10>=90) {
				if (nodes+1 < this.count) {
					for(int i=0;i<11;i++){
						solve(i, sum+node, nodes+1);
					}
				}else {
					if (sum + node == this.circles) {
						this.sum++;
					}
				}
			}
		}
	}

}
