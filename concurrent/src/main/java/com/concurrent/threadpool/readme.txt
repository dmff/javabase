Executor 
	ExecutorService submit、scheduledExecutorService
	Callable(有返回值结果) = Runnable
Executors 
	ThreadPool
	Future和futuretask的区别

并行处理，线程数等于cpu内核数
fixed cached single scheduled workstealing forkjoin
	工作窃取，当其他线程的任务已经完成时会去获取另一个线程的任务执行
	
forkjoin：需要不断的拆分
	RecursiveTask<Long>:有返回值
	RecursiveAction:没有返回值,需要阻塞的拿到的返回值结果，因为是守护线程，主线程dead的话，守护也会dead

ThreadpoolExecutor：重要的参数需要了解
	  int corePoolSize,
	  int maximumPoolSize,
	  long keepAliveTime,
	  TimeUnit unit,
	  BlockingQueue<Runnable> workQueue,
	  ThreadFactory threadFactory,
	  RejectedExecutionHandler handler
通过集合或者数组获取并行流、串行流
ParallelStreamAPI