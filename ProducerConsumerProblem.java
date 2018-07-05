package com.examples;


public class ProducerConsumerProblem {
	
	public static void main(String...args) {
		Items items = new Items();
		new Producer(items);
		new Consumer(items);
	}

}


class Producer implements Runnable{
	Items items;
	public Producer(Items items) {
		this.items = items;
		new Thread(this, "Producer").start();
	}

	@Override
	public void run() {
		int x = 0;
		while(x < 10) {
			items.put(x++);
		}
		
	}
}

class Consumer implements Runnable{
	Items items;
	public Consumer(Items items) {
		this.items = items;
		new Thread(this, "Consumer").start();
	}
	@Override
	public void run() {
		int x = 0;
		while(x < 10) {
			items.get();
			x++;
		}
	}
	
}

 class Items{
	int num;
	boolean waitFlag = false;
	
	public synchronized int get() {
		if(!waitFlag) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("get:"+num);
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println(e);
		}
		waitFlag = false;
		notify();
		return num;
	}
	
	public synchronized void put(int num) {
		
		if(waitFlag) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		this.num = num;
		System.out.println("put:"+num);
		waitFlag = true;
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println(e);
		}
		notify();
	}
}
	
