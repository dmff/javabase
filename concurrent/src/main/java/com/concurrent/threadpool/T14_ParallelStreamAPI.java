package com.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * ParallelStreamAPI的用法
 * @author dmf
 *
 */
public class T14_ParallelStreamAPI {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i=2; i<=10000000; i++) nums.add(i);
		
		//System.out.println(nums);
		/*
		long start = System.currentTimeMillis();
		nums.forEach(v->isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println(end - start);*/
		
		//使用parallel stream api
		long start = System.currentTimeMillis();
		nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		//parallelStream();
	}
	
	static boolean isPrime(int num){
		for(int i=2;i<=num/2;i++){
			if (num%i ==0) {
				return false;
			}	
		}
		return true;
	}
	
	public static void parallelStream(){
		List<Integer> list = new ArrayList<>();
		for(int i=1;i<=5;i++){
			list.add(i);
		}
		
		Optional<Integer> reduce = list.parallelStream().map(e->{
			int res = 1;
			while(e>=1){
				res *=e;
			}
			return res;
		}).reduce((a,b)->a+b);
		reduce.ifPresent(s->System.out.println(s));
	}
}
