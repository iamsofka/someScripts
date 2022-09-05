package classes;

import Interfaces.IAggregable;
import Interfaces.IContainer;
import Interfaces.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.List;

public class Cont<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult> implements IContainer<TElement, TAggregateResult> {
    private List<TElement> list;

    public Cont(){
        list = new ArrayList<>();
    }

    public Cont(List<TElement> list) {
        this.list = list;
    }

    @Override
    public List<TElement> elements() {
        return list;
    }

    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult res = null;
        for(TElement t : list){
            res = t.aggregate(res);
        }
        return res;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        if (index > list.size() || index < 0){
            return null;
        } else{
            return list.get(index).deepClone();
        }
    }
}
