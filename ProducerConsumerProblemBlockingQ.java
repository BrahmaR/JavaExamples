package com.examples;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerProblemBlockingQ {
	public static void main(String... args) {
		BlockingQueue<Object> queue = new LinkedBlockingQueue<>(20);
		int numProducers = 4;
		int numConsumers = 3;

		for (int i = 0; i < numProducers; i++) {
			new Thread(new Producer1(queue)).start();
		}

		for (int i = 0; i < numConsumers; i++) {
			new Thread(new Consumer1(queue)).start();
		}
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Success");
		System.exit(0);
	}
}

class Producer1 implements Runnable {
	BlockingQueue<Object> queue;

	public Producer1(BlockingQueue<Object> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (true) {
				Object object = getResource();
				queue.put(object);
				System.out.println("Produced object Q size:" + queue.size());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public Object getResource() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("interrupted in producing resource");
		}
		System.out.println("Producing object:");
		return new Object();
	}
}

class Consumer1 implements Runnable {
	BlockingQueue<Object> queue;

	public Consumer1(BlockingQueue<Object> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (true) {
				Object object = queue.take();
				System.out.println("Consumer consumed object Q size:" + queue.size());
				take(object);
			}
		} catch (Exception e) {
			System.out.println("Interrupted while consuming");
		}
	}

	public void take(Object object) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println("Interrupted while consuming");
		}
		System.out.println("Consuming object:" + object);
	}
}