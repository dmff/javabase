package leetcode.po;

public class ListNode {

	int val;
	public ListNode next,random;
	public ListNode(int val) {
		this.val = val;
		next = null;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public ListNode getNext() {
		return next;
	}
	public void setNext(ListNode next) {
		this.next = next;
	}

	public ListNode getRandom() {
		return random;
	}
	public void setRandom(ListNode random) {
		this.random = random;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + ", random=" + random + "]";
	}
	
	/*@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}*/

	
	
}
