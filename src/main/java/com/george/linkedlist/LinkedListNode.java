package com.george.linkedlist;

public class LinkedListNode<K> {

    private LinkedListNode<K> prev;
    private LinkedListNode<K> next;
    private K key;

    public LinkedListNode() {
    }

    public LinkedListNode(K key) {
        setKey(key);
    }

    public LinkedListNode<K> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<K> prev) {
        this.prev = prev;
    }

    public LinkedListNode<K> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<K> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LinkedListNode{" +
                ", key=" + key +
                '}';
    }
}
