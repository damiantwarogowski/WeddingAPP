package com.sda.weddingApp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class WeddingAPPTests {

    @Test
    void contextLoads() {
        System.out.println("common_prefixDB".hashCode());
        System.out.println("common_prefixCa".hashCode());

        Set<String> str = new HashSet<>();
        str.add("common_prefixDB");
        str.add("common_prefixCa");

        System.out.println(str.size()); //

        Set<Person> personSet = new HashSet<>();
        personSet.add(new Person("common_prefixDB", 13));
        personSet.add(new Person("common_prefixCa", 13));
        personSet.add(new Person("Pawel",  13));
        personSet.add(new Person("Pawel",  14));

        System.out.println(personSet.size());
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    private static class Person {
        private String imie;
        private int numerButa;
    }

}
