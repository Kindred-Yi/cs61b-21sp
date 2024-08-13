package deque;
import java.util.Comparator;
import java.util.Iterator;
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public void checkIfDoubleSize(){
        if(size == items.length - 1){
            T[] mewItems = (T[]) new Object[2 * items.length];
            int index = 0;
            for(int i = (nextFirst + 1) % items.length; i != nextLast; i = (i + 1) % items.length){
                mewItems[index++] = items[i];
            }
            items = mewItems;
            nextFirst = items.length - 1;
            nextLast = index;

        }
    }

    public void addFirst(T item) {
        checkIfDoubleSize();
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size = size + 1;
    }

    public void addLast(T item) {
        checkIfDoubleSize();
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }

    public void checkIfHalfSize(){
        if(size == items.length / 4){
            T[] mewItems = (T[]) new Object[items.length / 2];
            int index = 0;
            for(int i = (nextFirst + 1) % items.length; i != nextLast; i = (i + 1) % items.length){
                mewItems[index++] = items[i];
            }
            items = mewItems;
            nextFirst = items.length - 1;
            nextLast = index;
        }
    }

    public T removeFirst() {
        if(isEmpty()){
            return null;
        }

        checkIfHalfSize();
        T returnItem = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        size = size - 1;
        nextFirst = (nextFirst + 1) % items.length;
        return returnItem;
    }

    public T removeLast() {
        if(isEmpty()){
            return null;
        }

        checkIfHalfSize();
        T returnItem = items[(nextLast - 1) % items.length];
        items[(nextLast - 1) % items.length] = null;
        size = size - 1;
        nextLast = (nextLast - 1) % items.length;
        return returnItem;
    }

    public T get(int index){
        if(!isEmpty()){
            if(index < 0 || index >= size){
                return null;
            }else{
                return items[(nextFirst + index + 1) % items.length];
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void printDeque(){
        for(int i = (nextFirst + 1) % items.length; i != nextLast; i = (i + 1) % items.length){
            System.out.print(items[i] + " ");
        }
    }


    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T>{
        private int i = 0;
        public boolean hasNext(){
            return i < size;
        }
        public T next(){
            T returnItem = get(i);
            i = i + 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (size != other.size) return false;
        for(int i = 0; i < other.size(); i += 1){
            T itemFromObj =  other.get(i);
            T itemFromThis = this.get(i);
            if (!itemFromObj.equals(itemFromThis)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> list =  new ArrayDeque<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

    }

}
