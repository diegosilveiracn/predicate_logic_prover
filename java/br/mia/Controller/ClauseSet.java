package br.mia.Controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ClauseSet implements Set<Clause> {

	protected Node head = new Node(null);

	protected Node tail = new Node(null);

	protected int size;

	public ClauseSet() {
		clear();
	}
	
	public boolean add(Clause clause) {
		if (contains(clause))
			return false;
		Node node = new Node(clause);
		node.next = tail;
		node.prior = tail.prior;
		tail.prior.next = node;
		tail.prior = node;
		size++;
		return true;
	}

	public boolean addAll(Collection<? extends Clause> c) {
		Iterator<? extends Clause> iter = c.iterator();
		boolean changed = false;
		while (iter.hasNext())
			changed |= add(iter.next());
		return changed;
	}

	public void clear() {
		head.prior = null;
		head.next = tail;
		tail.prior = head;
		tail.next = null;
		size = 0;
	}

	public boolean contains(Object o) {
		if (!(o instanceof Clause))
			return false;
		Clause clause = (Clause) o;
		Iterator<Clause> iter = iterator();
		while (iter.hasNext())
			if (iter.next().equals(clause))
				return true;
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		Iterator<?> iter = c.iterator();
		boolean flag = true;
		while (iter.hasNext())
			flag &= contains(iter.next());
		return flag;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<Clause> iterator() {
		return new ClauseIterator(this);
	}

	public ClauseIterator clauseIterator() {
		return new ClauseIterator(this);
	}

	public boolean remove(Object o) {
		if (!(o instanceof Clause))
			return false;
		Clause clause = (Clause) o;
		Iterator<Clause> iter = iterator();
		while (iter.hasNext())
			if (iter.next().equals(clause)) {
				iter.remove();
				return true;
			}
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		boolean flag = true;
		Iterator<?> iter = c.iterator();
		while (iter.hasNext())
			flag &= remove(iter.next());
		return flag;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		if (size() == 0)
			return "[]";
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Node currentNode = head.next;
		while (currentNode.next != tail) {
			buffer.append(currentNode.clause + ", ");
			currentNode = currentNode.next;
		}
		buffer.append(currentNode.clause + "]");
		return buffer.toString();
	}

	public ClauseSet removeUnitaryClauses() {
		ClauseSet unitaryClauses = new ClauseSet();
		
		Iterator<Clause> iterator = this.iterator();
		while (iterator.hasNext()) {
			Clause clause = iterator.next();
			if (clause.numLiterals() == 1) {
				unitaryClauses.add(clause);
				iterator.remove();
			}
		}
		
		return unitaryClauses;
	}
	
}

class Node {

	Node prior;

	Node next;

	Clause clause;

	public Node(Clause clause) {
		this.clause = clause;
	}

}

class ClauseIterator implements Iterator<Clause> {

	ClauseSet set;
	
	Node head;

	Node tail;

	Node currentNode;

	public ClauseIterator(ClauseSet set) {
		this.set = set;
		this.head = set.head;
		this.tail = set.tail;
		currentNode = head;
	}

	public boolean hasNext() {
		return currentNode.next != tail;
	}

	public Clause next() {
		currentNode = currentNode.next;
		return currentNode.clause;
	}

	public ClauseIterator nextIterator() {
		ClauseIterator iter = new ClauseIterator(set);
		iter.currentNode = this.currentNode.next;
		return iter;
	}

	public void remove() {
		Node tempNode = currentNode.prior;
		currentNode.prior.next = currentNode.next;
		currentNode.next.prior = currentNode.prior;
		currentNode.prior = null;
		currentNode.next = null;
		currentNode.clause = null;
		currentNode = tempNode;
		set.size--;
	}

}