package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinLock implements Lock{

	private final Sync sync = new Sync(2);
	private static class Sync extends AbstractQueuedSynchronizer{
		
		public Sync(int state) {
			if (state<=0) {
				throw new IllegalArgumentException("count must large 0");
			}
			setState(state);
		}
		private static final long serialVersionUID = 1L;

		@Override
		protected int tryAcquireShared(int reduce) {
			for(;;){
				int current = getState();
				int newCount = current - reduce;
				if (newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int returnCount) {
			for(;;){
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
			
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
		
		Condition newCondition(){
			return new ConditionObject();
		}
		
	}
	
	
	
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryReleaseShared(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {	
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
