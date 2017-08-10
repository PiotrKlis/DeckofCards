package com.example.pk.deckofcards;

import junit.framework.Assert;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by PK on 10.08.2017.
 */

public class SchemasTest {

    @Test
    public void testStringToInts() {

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<>();

        Schemas schemas = new Schemas();

        for (int i = 0; i < 5; i++) {

            strings.add(String.valueOf(i));

        }

        ints = schemas.changeValuesToInts(strings);
        Assert.assertEquals(1, ints.get(1), 0.1);

    }

    @Test
    public void testFigures() {

        ArrayList<String> strings = new ArrayList<>();
        Schemas schemas = new Schemas();

        for (int i = 0; i < 5; i++) {

            strings.add("J");
            String result = schemas.figures(strings);

            if (strings.size() < 3) {
                Assert.assertEquals("", result);
            } else {
                Assert.assertEquals("Figury", result);
            }
        }
    }

    @Test
    public void testColor() {

        ArrayList<String> strings = new ArrayList<>();
        Schemas schemas = new Schemas();

        for (int i = 0; i < 5; i++) {

            strings.add("S");
            String result = schemas.color(strings);

            if (strings.size() < 3) {
                Assert.assertEquals("", result);
            } else {
                Assert.assertEquals("Kolor - Pik", result);
            }

        }
    }

    @Test
    public void testStairs() {

        ArrayList<Integer> ints = new ArrayList<>();
        Schemas schemas = new Schemas();

        for (int i = 0; i < 5; i++) {

            ints.add(i);
            String result = schemas.stairs(ints);

            if (ints.size() < 3) {
                Assert.assertEquals("", result);
            } else {
                Assert.assertEquals("Schodki", result);
            }
        }
    }

    @Test
    public void testTwins() {

        ArrayList<Integer> ints = new ArrayList<>();
        Schemas schemas = new Schemas();

        for (int i = 0; i < 5; i++) {

            ints.add(5);
            String result = schemas.twins(ints);

            if (ints.size() < 3) {
                Assert.assertEquals("", result);
            } else {
                Assert.assertEquals("Bliźniaki", result);
            }
        }
    }


}