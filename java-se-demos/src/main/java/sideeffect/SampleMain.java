package sideeffect;

import annotations.BadPractice;

import java.util.ArrayList;
import java.util.List;

public class SampleMain {

    @BadPractice("Side effect")
    public void populateList(List<String> names) {
        names.add("John Doe");
    }

    public List<String> addJohnDoe(List<String> names) {
        List<String> copy = new ArrayList<>(names);
        copy.add("John Doe");
        return copy;
    }

    public static void main(String[] args) {
        new SampleMain().populateList(List.of("Jack Doe"));
    }


}
