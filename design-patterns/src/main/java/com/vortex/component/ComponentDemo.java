package com.vortex.component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dmf
 * 二叉树示例：根节点下有左节点和右节点
 * 			    节点下面有叶子节点，其中叶子节点下面没有子节点
 * 			    有一个方法使得当前节点和子节点都会执行
 */
public class ComponentDemo {

	public abstract class Component {
		String name;
		public abstract void add(Component c);
		public abstract void remove(Component c);
		public abstract void eachChild();
	}
	
	public class Composite extends Component{
		
		public Composite(String name) {
			this.name = name;
		}
		
		// 用来保存节点的子节点
		List<Component> lists = new ArrayList<Component>();
		
		@Override
		public void add(Component c) {
			lists.add(c);	
		}
		
		@Override
		public void remove(Component c) {
			lists.remove(c);	
		}
		@Override
		public void eachChild() {
			System.out.println(name+"执行了...");
			for (Component component : lists) {
				component.eachChild();
			}
		}	
	}
	
	//叶子节点
	public class Leaf extends Component{
		
		public Leaf(String name) {
			this.name = name;
		}
		
		@Override
		public void add(Component c) {}
		
		@Override
		public void remove(Component c) {}

		@Override
		public void eachChild() {
			System.out.println(name+"执行了...");		
		}	
	}
	
	public static void main(String[] args) {
		ComponentDemo demo = new ComponentDemo();
		
		Component root = demo.new Composite("root");
		
		Component left = demo.new Composite("left");
		Component right = demo.new Composite("right");
		
		Component leaf1 = demo.new Leaf("leaf1");
		Component leaf2 = demo.new Leaf("leaf2");
		
		left.add(leaf1);
		left.add(leaf2);
		
		root.add(left);
		root.add(right);
		
		//root.eachChild();
		//right.eachChild();
		left.eachChild();
	}
}
