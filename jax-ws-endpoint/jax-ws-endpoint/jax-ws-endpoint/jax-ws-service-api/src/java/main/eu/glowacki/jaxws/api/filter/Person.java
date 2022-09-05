package eu.glowacki.jaxws.api.filter;

import java.util.Date;

public class Person {
    public String name;
    public String surname;
    public Date birthdate;

    public Person(){}

    public Person(String n, String s, Date d){
        name = n;
        surname = s;
        birthdate = d;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
