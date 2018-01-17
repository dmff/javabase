package biubiu.find;

public class BSTree2 {  
	
    Node root=null; 
    
    private class Node{  
        Node parent=null;  
        Node leftChild=null;  
        Node rightChild=null;  
        int key;  
        public Node(int data) {  
            this.key=data;  
        }  
    }  
    
    public BSTree2(int[] datas) {  
        buildTree(datas);  
    }  
    private void buildTree(int[] datas) {  
        for (int i = 0; i < datas.length; i++) {  
            Node node=new Node(datas[i]);  
            insertNode(node);  
        }  
    }  
    private void insertNode(Node node) {    //插入结点  
        Node next=this.root;      
        Node cur=null;  //用来保存当前结点  
        
        while(next!=null){  //当到达叶子结点时，确认位置！  
            cur=next;  
            if(node.key>=cur.key){  
                next=next.rightChild;  
            }else{  
                next=next.leftChild;  
            }  
        }  
        
        node.parent=cur;    //插入该结点！  
        if(cur==null){  
            this.root=node;  //该树为空树，所以这个是根节点  
        }else if(node.key>=cur.key){  
            cur.rightChild=node;  
        }else{  
            cur.leftChild=node;  
        }  
    }  
      
    public void insert(int data){   //供外部测试调用  
        Node node=new Node(data);  
        System.out.println("插入结点："+data);  
        insertNode(node);  
        this.midOrderTraverse();  
    }  
      
      
    private void preOrderTraverse(Node node){   //先序遍历  
        if(node!=null){  
            System.out.print("-"+node.key+"-");  
            preOrderTraverse(node.leftChild);  
            preOrderTraverse(node.rightChild);  
        }  
    }  
      
    public void preOrderTraverse(){   
        System.out.println("先序遍历：");  
        preOrderTraverse(root);  
        System.out.println();  
    }  
      
    private void midOrderTraverse(Node node){   //中序遍历  
        if(node!=null){  
            midOrderTraverse(node.leftChild);  
            System.out.print("-"+node.key+"-");  
            midOrderTraverse(node.rightChild);  
        }  
          
    }  
      
    public void midOrderTraverse(){   
        System.out.println("中序遍历：");  
        midOrderTraverse(root);  
        System.out.println();  
    }  
      
    private void postOrderTraverse(Node node){  //后序遍历  
        if(node!=null){  
            System.out.print("-"+node.key+"-");  
            postOrderTraverse(node.leftChild);  
            postOrderTraverse(node.rightChild);  
        }  
    }  
    public void postOrderTraverse(){  
        System.out.println("后序遍历：");  
        postOrderTraverse(root);  
        System.out.println();  
    }  
      
    private Node searchNode(Node node){ //private供内部调用，搜索结点  
        if(node==null){  
            System.out.println("输入为空，查找失败！");  
        }else{  
            if(root==null){  
                System.out.println("该树为空树！");  
            }else{                      //开始查找  
                boolean isFound=false;    
                Node x=root;  
                Node y=null;  
                while(!isFound&&x!=null){   //当查到或者到了叶子节点还没查到时，终结！  
                    y=x;  
                    if(node.key==x.key){      
                        isFound=true;  
                    }else{                  //通过比较大小往下面查找  
                        if(node.key>x.key){    
                            x=x.rightChild;  
                        }else{  
                            x=x.leftChild;  
                        }  
                    }  
                }  
                if(isFound){    //没找到的话，在最后返回null  
                    return y;  
                }  
            }  
        }  
        return null;  
    }  
      
    public void search(int data){   //public供外部调用，搜索结点  
        System.out.println("您要查找的是："+data);  
        Node node;  
        if((node=searchNode(new Node(data)))==null){  
            System.out.println("树中没有该结点！");  
        }else{  
            System.out.println("查找"+node.key+"成功！");  
        }  
    }  
      
    private Node getMaxNode(Node node){ //获取最大值  
        if(node!=null){  
            Node x=node;  
            Node y=null;  
            while(x!=null){ //一直往右遍历直到底就是最大值了！  
                y=x;  
                x=x.rightChild;  
            }  
            return y;  
        }  
        return null;  
    }  
      
    public void  getMax(){  //获取最大值，供外部调用  
        Node node;  
        if((node=getMaxNode(root))==null){  
            System.out.println("该树为空！");  
        }else{  
            System.out.println("最大的结点是："+node.key);  
        }  
          
    }  
      
    private Node getMinNode(Node node){ //获取最大值  
        if(node!=null){  
            Node x=node;  
            Node y=null;  
            while(x!=null){ //一直往左遍历直到底就是最小值了！  
                y=x;  
                x=x.leftChild;  
            }  
            return y;  
        }  
        return null;  
    }  
      
    public void getMin(){   //获取最小值，供外部调用  
        Node node;  
        if((node=getMinNode(root))==null){  
            System.out.println("该树为空！");  
        }else{  
            System.out.println("最小的结点是："+node.key);  
        }  
    }  
      
      
    private Node getPreNode(Node node){ //获取前驱结点  
        if(node==null){  
            return null;  
        }  
        if(node.leftChild!=null){   //当有左孩子时，前驱结点就是左子树的最大值  
            return getMaxNode(node.leftChild);  
        }else{//当不存在左孩子时，前驱结点就是——它的祖先，而且，它在这个祖先的右子树中。这句话自己画图就能理解了  
            Node x=node;  
            Node y=node.parent;  
            while(y!=null&&x==y.leftChild){  
                x=y;  
                y=y.parent;  
            }  
            return y;  
        }  
    }  
      
    public void getPre(int data){   //public供外部调用，获取前驱结点  
        Node node=null;  
        System.out.println(data+"的前驱结点：");  
        if((node=getPreNode(searchNode(new Node(data))))==null){  
            System.out.println("该结点不存在或无前驱结点！");  
        }else{  
            System.out.println(data+"的前驱结点为："+node.key);  
        }  
    }  
      
    private Node getPostNode(Node node){    //获取后继结点  
        if(node==null){  
            return null;  
        }  
        if(node.rightChild!=null){  //当有右孩子时，后继结点就是右子树的最小值  
            return getMinNode(node.rightChild);  
        }else{//当不存在右孩子时，后继结点就是——它的祖先，而且，它在这个祖先的左子树中。这句话自己画图就能理解了  
            Node x=node;  
            Node y=node.parent;  
            while(y!=null&&x==y.rightChild){  
                x=y;  
                y=y.parent;  
            }  
            return y;  
        }  
    }  
      
    public void getPost(int data){  //public供外部调用，获取后继结点  
        Node node=null;  
        System.out.println(data+"的后继结点：");  
        if((node=getPostNode(searchNode(new Node(data))))==null){  
            System.out.println("该结点不存在或无后继结点！");  
        }else{  
            System.out.println(data+"的后继结点为："+node.key);  
        }  
    }  
      
      
      
    private void deleteNode(Node node){  
        if(node==null){  
            System.out.println("删除结点不能为空！");  
            return;  
        }  
        replacedNode(node);  
    }  
      
    private void replacedNode(Node node) {  //替换结点  
        if(node.leftChild!=null&&node.rightChild!=null){   //当有左右孩子时，用后继结点替换  
            replacedNodeOfPost(node);  
        }  
        else  
        {  
            if(node.leftChild!=null){   //当只有左孩子时，直接用左子树替换  
                node=node.leftChild;  
            }else if(node.rightChild!=null){    //只有右孩子时，直接有子树替换  
                node=node.rightChild;  
            }else{          //当没有左右孩子时，就直接释放了这个结点  
                freeNode(node);  
            }  
        }  
    }  
      
      
    private void freeNode(Node node) {  //释放该结点，断掉其与父结点的链接  
        if(node==node.parent.leftChild){  
            node.parent.leftChild=null;  
        }else{  
            node.parent.rightChild=null;  
        }  
    }  
      
    private void replacedNodeOfPost(Node node) {      
        Node y=this.getPostNode(node);  //找后继结点  
        node.key=y.key;  
        replacedNode(y);    //替换了key之后，再一次递归把现在这个结点给替换了！  
    }  
      
    public void delete(int data){   //public供外部调用，删除结点  
        Node node;  
        if((node=searchNode(new Node(data)))==null){//注意！这里不能new结点！你必须从树中找该结点！new就是初始化了  
            System.out.println("二叉树中不存在此结点！");  
            return;  
        }  
        deleteNode(node);  
        System.out.println("删除结点"+data+"后：");  
        this.midOrderTraverse();  
    }  
    
    
    public static void main(String[] args) {
    	int[] datas={12,4,5,7,4,8,3,2,6,9};  
    	BSTree2 tree=new BSTree2(datas);  
        tree.preOrderTraverse();//先序遍历  
        tree.midOrderTraverse();//中序遍历  
        tree.postOrderTraverse();//后序遍历  
        tree.insert(15);    //插入结点  
        tree.search(7);     //查询结点  
        tree.search(100);   //查询一个不存在的结点  
        tree.getMax();      //获取最大值  
        tree.getMin();      //获取最小值  
        tree.getPre(7);     //前驱结点  
        tree.getPre(2);     //最前的前驱结点  
        tree.getPost(7);    //后继结点  
        tree.getPost(15);   //最后的后继结点  
        tree.delete(5);     //删除结点  
        tree.delete(0);     //删除一个不存在的结点  
	}
}  