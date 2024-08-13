package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }
    public T max(Comparator<T> c){
        if(isEmpty()) return null;
        T max = this.get(0);
        for(T item : this){
            if(item != null && c.compare(item, max) > 0) max = item;
        }
        return max;
    }

    public T max(){
        return max(comparator);
    }

}
