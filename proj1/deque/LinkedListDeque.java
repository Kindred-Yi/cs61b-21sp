package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private final TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public class TNode{
        public TNode next;
        public T item;
        public TNode prev;

        public TNode(T x, TNode n){
            item = x;
            next = n;
        }
    }

    public void addFirst(T item){
        TNode firstNode = sentinel.next;
        firstNode.prev = new TNode(item, firstNode);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
        size = size + 1;

    }

    public void addLast(T item){
        TNode lastNode = sentinel.prev;
        lastNode.next = new TNode(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size = size + 1;
    }


    public int size(){
        return size;
    }

    public void printDeque(){
        TNode currentNode = sentinel.next;
        while(currentNode.item != null){
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }

        TNode firstNode = sentinel.next;
        TNode secondNode = firstNode.next;
        sentinel.next = secondNode;
        secondNode.prev = sentinel;
        firstNode.next = null;
        firstNode.prev = null;
        size = size - 1;
        return firstNode.item;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }

        TNode lastNode = sentinel.prev;
        TNode secondLastNode = lastNode.prev;
        secondLastNode.next = sentinel;
        sentinel.prev = secondLastNode;
        lastNode.next = null;
        lastNode.prev = null;
        size = size - 1;
        return lastNode.item;
    }

    public T get(int index){
        if (size == 0) {
            return null;
        }
        if (index < 0 || index >= size){
            return null;
        }else{
            TNode currentNode = sentinel;
            for(int i = 0; i <= index; i++){
                currentNode = currentNode.next;
            }
            return currentNode.item;
        }
    }

    public T getRecursive(int index){
        if (size == 0) {
            return null;
        }
        if (index < 0 || index >= size){
            return null;
        }else{
            return getRecursive(index, sentinel.next);
        }
    }

    public T getRecursive(int index, TNode currentNode){
        if (index == 0){
            return currentNode.item;
        }else{
            return getRecursive(index - 1, currentNode.next);
        }
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.removeFirst();
        int c = list.getRecursive(2);
        list.printDeque();
        System.out.println(c);
        System.out.println(list.size());
    }

}
