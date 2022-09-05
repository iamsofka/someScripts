package classes;

import Interfaces.IAggregable;
import Interfaces.IDeeplyCloneable;

public class Subject implements IAggregable <Subject, String>, IDeeplyCloneable <Subject>{
    private String name;

    public Subject(){
    }

    public Subject(String name){
        this.name = name;
    }

    public String name(){
        return name;
    }

    @Override
    public String aggregate(String intermediateResult) {
        if(intermediateResult == null){
            return name;
        } else {
            return name + "' " + intermediateResult + " '";
        }
    }

    @Override
    public Subject deepClone() {
        Subject sub = new Subject();
        sub.name = name;
        return sub;
    }

}
