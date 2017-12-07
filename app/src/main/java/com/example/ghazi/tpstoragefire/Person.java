package com.example.ghazi.tpstoragefire;

/**
 * Created by ghazi on 29-Nov-17.
 */

public class Person {
    private String last,first,born,id;

    public Person() {
    }


    public Person(String last, String first, String born, String id) {
        this.last = last;
        this.first = first;
        this.born = born;
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "last='" + last + '\'' +
                ", first='" + first + '\'' +
                ", born='" + born + '\'' +
                '}';
    }


}
