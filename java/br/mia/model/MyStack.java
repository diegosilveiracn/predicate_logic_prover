package br.mia.model;

import java.util.Collection;
import java.util.LinkedList;

public class MyStack<T> extends LinkedList<T> {

	private static final long serialVersionUID = -8226850202791198641L;

	public MyStack() {
		super();
	}
	
	public MyStack(Collection<? extends T> c) {
		super(c);
	}
	
	public boolean empty() {
		return super.size() == 0;
	}
	
	public T peek() {
		return super.peek();
	}
	
	public T pop() {
		return super.removeFirst();
	}
	
	public T push(T item) {
		super.addFirst(item);
		return item;
	}
	
	public int search(T item) {
		int index = 0;
		for (T currItem : this) {
			if (currItem.equals(item))
				break;
			index++;
		}
		if (index == super.size())
			return -1;
		return index;
	}
	
}
