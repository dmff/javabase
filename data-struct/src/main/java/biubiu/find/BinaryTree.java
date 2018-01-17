package biubiu.find;



public interface BinaryTree<K, V> {

	/**
	 * 二叉搜索树的长度
	 * @return
	 */
	int size();

	/**
	 * 二叉搜索树是否为null
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 插入二叉搜索树中
	 * @param key
	 * @param value
	 */
	void insert(K key, V value);

	/**
	 * 是否包含key
	 * @param key
	 * @return
	 */
	boolean containkey(K key);

	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	V search(K key);
	
	/**
	 * 先序遍历
	 */
	void preOrder();
	
	/**
	 * 中序遍历
	 */
	void inOrder();
	
	/**
	 * 后续遍历
	 */
	void postOrder();
	
	/**
	 * 层序遍历
	 */
	void levelOrder();

	/**
	 * 最小的key
	 * @return
	 */
	K minimun();
	
	/**
	 * 最大的key
	 * @return
	 */
	K maxinum();
	/**
	 * 从二叉树中删除最小值所在节点
	 */
	void removeMin();
	
	/**
	 * 从二叉树中删除最大值所在节点
	 */
	void removeMax();
	
	/**
	 * 从二叉树中删除key所在节点
	 * @param key
	 */
	void remove(K key);
}
