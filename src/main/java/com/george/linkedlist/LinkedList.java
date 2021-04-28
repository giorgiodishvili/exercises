package com.george.linkedlist;

import java.util.Iterator;
import java.util.Optional;

public class LinkedList<K> implements Iterable<K>{
    private LinkedListNode<K> head;
    private LinkedListNode<K> tail;

    public LinkedListNode<K> insertFirst(K key){
        if (head == null){
            head = new LinkedListNode<K>(key);
            tail = head;
        }else {
            LinkedListNode<K> temp = new LinkedListNode<>(key);
            temp.setNext(head);
            this.head.setPrev(temp);
            this.head = temp;
        }
        return head;
    }

    public boolean remove(K key) {
        Optional<LinkedListNode<K>> search = search(key);

        if (search.isEmpty()) {
            return false;
        }
        LinkedListNode<K> next = search.get().getNext();
        LinkedListNode<K> prev = search.get().getPrev();

        if (prev == null) {
            head = next;
        } else {
            prev.setNext(next);
            search.get().getPrev().setKey(null);
        }

        if (next == null) {
            tail = prev;
        } else {
            next.setPrev(prev) ;
            search.get().setNext(null);
        }
        search.get().setKey(null);
        return true;
    }


    public Optional<LinkedListNode<K>> search(K key) {
      if(head == null){
          return Optional.empty();
      }

      LinkedListNode<K> temp = head;

      while (temp.getNext() != null){
            if(temp.getKey().equals(key)){
                break;
            }
            temp = temp.getNext();
      }

      if(!temp.getKey().equals(key)){
          return Optional.empty();
      }

      return Optional.of(temp);
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }
    @Override
    public Iterator<K> iterator() {
        return new LinkedListIterator<>(head);
    }

    private static class LinkedListIterator<T> implements Iterator<T> {

        private LinkedListNode<T> linkedListNode;

        private LinkedListIterator(LinkedListNode<T> linkedListNode) {
            this.linkedListNode = linkedListNode;
        }

        @Override
        public boolean hasNext() {
            return linkedListNode != null;
        }

        @Override
        public T next() {
            T key = linkedListNode.getKey();
            linkedListNode = linkedListNode.getNext();
            return key;
        }
    }
}

