package com.concurrent.concur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.synchronizedList同步方法
 * @author dmf
 *
 */
public class T03_SynchronizedList {
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		List<String> list = Collections.synchronizedList(strs);
	}
}
