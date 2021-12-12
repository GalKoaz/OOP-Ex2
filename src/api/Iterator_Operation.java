package api;

import java.util.Iterator;

public class Iterator_Operation<T> implements Iterator {
    private Iterator<T> graphComponent;
    private int mc, mc_2;


    public Iterator_Operation(Iterator<T> iterator, int mc) {
        this.graphComponent = iterator;
        this.mc = mc;
        this.mc_2 = mc;
    }


    @Override
    public boolean hasNext() {
        try {
            if (mc != mc_2) {
                throw new RuntimeException("Graph was changed since the iterator was constructed!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return graphComponent.hasNext();
    }

    @Override
    public Object next() {
        try {
            if (mc != mc_2) {
                throw new RuntimeException("Graph was changed since the iterator was constructed!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return graphComponent.next();
    }

    @Override
    public void remove(){
        try {
            if (mc != mc_2) {
                throw new RuntimeException("Graph was changed since the iterator was constructed!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        graphComponent.remove();
    }

}

