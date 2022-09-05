package classes;

import Interfaces.IAggregable;
import Interfaces.IDeeplyCloneable;

public class Mark implements IAggregable<Mark, Integer>, IDeeplyCloneable<Mark> {
    private int points;

    public Mark(){
    }

    public Mark(int points){
        this.points = points;
    }

    public int points(){
        return points;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if(intermediateResult == null){
            return points;
        } else {
            return points + intermediateResult;
        }
    }

    @Override
    public Mark deepClone() {
        Mark clone = new Mark();
        clone.points = points;
        return clone;
    }
}
