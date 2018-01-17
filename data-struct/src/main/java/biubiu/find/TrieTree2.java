package biubiu.find;

public class TrieTree2 {

	public class TrieNode {
		public TrieNode[] children;
		public char data;
		public int freq; // 词频数

		public TrieNode() {
			// 因为有26个字母
			children = new TrieNode[26];
			freq = 0;
		}
	}

	private TrieNode root;

	public TrieTree2() {
		super();
		this.root = new TrieNode();
	}

	public void insert(String word) {
		if (word == null || word.equals("")) {
			return;
		}
		insertNode(root, word.toCharArray(), 0);
	}

	private void insertNode(TrieNode rootNode, char[] charArray, int index) {

		int k = charArray[index] - 'a';
		if (k < 0 || k > 25) {
			throw new RuntimeException("charArray[index] is not a alphabet!");
		}
		if (rootNode.children[k] == null) {
			rootNode.children[k] = new TrieNode();
			rootNode.children[k].data = charArray[index];
		}

		if (index == charArray.length - 1) {
			rootNode.children[k].freq++;
			return;
		} else {
			insertNode(rootNode.children[k], charArray, index + 1);
		}
	}

	public void remove(String word) {
		if (word == null || word.equals("")) {
			return;
		}
		remove(root, word.toCharArray(), 0);
	}

	private static void remove(TrieNode rootNode, char[] charArray, int index) {
		int k = charArray[index] - 'a';
		if (k < 0 || k > 25) {
			throw new RuntimeException("charArray[index] is not a alphabet!");
		}
		if (rootNode.children[k] == null) {
			// it means we cannot find the word in this tree
			return;
		}

		if (index == charArray.length - 1 && rootNode.children[k].freq > 0) {
			rootNode.children[k].freq--;
		}
		remove(rootNode.children[k], charArray, index + 1);
	}

	public int getFreq(String word) {
		if (word == null || word.equals("")) {
			return 0;
		}
		return getFreq(root, word.toCharArray(), 0);
	}

	private int getFreq(TrieNode rootNode, char[] charArray, int index) {
		int k = charArray[index] - 'a';
		if (k < 0 || k > 25) {
			throw new RuntimeException("charArray[index] is not a alphabet!");
		}
		// it means the word is not in the tree
		if (rootNode.children[k] == null) {
			return 0;
		}
		if (index == charArray.length - 1) {
			return rootNode.children[k].freq;
		}
		return getFreq(rootNode.children[k], charArray, index + 1);
	}

	public static void test() {
		TrieTree2 trieTree = new TrieTree2();
		String sourceStr = "Democratic presumptive nominee Hillary Clintons campaign posed pounced on Trumps assertion that British term monetary turmoil might benefit his business venture in Scotland";

		// String sourceStr="the that";
		sourceStr = sourceStr.toLowerCase();

		String[] strArray = sourceStr.split(" ");
		for (String str : strArray) {
			trieTree.insert(str);
		}

		String sourceStr2 = "Every president is tested by world events But Donald Trump thinks about how is his golf resort can profit from that";
		sourceStr2 = sourceStr2.toLowerCase();
		String[] strArray2 = sourceStr2.split(" ");
		for (String str : strArray2) {
			trieTree.insert(str);
		}

	}

}
