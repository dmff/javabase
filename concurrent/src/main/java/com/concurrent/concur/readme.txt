1.map
 	* 使用hashmap线程不安全，使用put会造成死循环(entry形成环形结构)
 	* 使用hashtable效率低，会把整个map都锁住
    * ConcurrentHashMap使用分段锁技术来提高效率；容器有多把锁，没把锁只锁定一小部分
    * 1.7有segment数组结构(重入锁)和hashentry数组结构组成
    * 1.8使用cas和红黑树构成
ConcurrentHashMap/set
HashMap
TreeMap
LinkedHashMap

Hashtable
Collections.sychronizedXXX

//非阻塞的同步容器
ConcurrentHashMap
ConcurrentSkipListMap 

2.collection
ArrayList
LinkedList
Collections.synchronizedXXX
CopyOnWriteList：写少读多的场景
Queue
	CocurrentLinkedQueue //concurrentArrayQueue
	BlockingQueue
		LinkedBQ
		ArrayBQ
		TransferQueue
		SynchronusQueue
	DelayQueue
		
	