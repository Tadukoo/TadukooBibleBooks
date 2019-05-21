package com.gmail.realtadukoo.TB.Minecraft.Books;

import java.util.LinkedList;
import java.util.List;

/**
 * Adapted from a college lab, https://ycpcs.github.io/cs365-spring2019/labs/lab10.html
 */
public class Queue<E>{
	private final Object lock = new Object();
	private final LinkedList<E> data;
	private final int maxItems;

	public Queue(int maxItems){
		data = new LinkedList<E>();
		this.maxItems = maxItems;
	}

	public void enqueue(E item) throws InterruptedException{
		synchronized(lock){
			while(data.size() == maxItems){
				lock.wait();
			}
			data.addLast(item);
			lock.notifyAll();
		}
	}

	public E dequeue() throws InterruptedException{
		synchronized(lock){
			while(data.size() == 0){
				lock.wait();
			}
			E item = data.removeFirst();
			lock.notifyAll();
			return item;
		}
	}

	public void getContents(List<E> queueContents){
		synchronized(lock){
			queueContents.addAll(data);
		}
	}
}
