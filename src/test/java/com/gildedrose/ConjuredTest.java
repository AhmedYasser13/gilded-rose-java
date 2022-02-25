package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredTest {
    GildedRose gildedRose;

    @BeforeEach
    void beforeAll() {
        Item[] Conjured = new Item[]{
                new Item("Conjured Mana Cake", 3, 10),
                new Item("Conjured Mana Cake", 0, 10),
                new Item("Conjured Mana Cake", 0, 5),
        };
        gildedRose = new GildedRose(Conjured);
    }

    @Test
    void conjuredItem() {
        assertEquals("Conjured Mana Cake", gildedRose.items[0].name);
        assertEquals(3, gildedRose.items[0].sellIn);
        assertEquals(10, gildedRose.items[0].quality);
    }

    @Test
    void conjuredPriorSellInQuality() {
        gildedRose.simulateDay();
        assertEquals(8, gildedRose.items[0].quality);
        gildedRose.simulateDay();
        assertEquals(6, gildedRose.items[0].quality);
    }

    @Test
    void conjuredPostSellInQuality() {
        gildedRose.simulateDay();
        assertEquals(6, gildedRose.items[1].quality);
        gildedRose.simulateDay();
        assertEquals(2, gildedRose.items[1].quality);
    }

    @Test
    void conjuredQualityCanNeverBeNegative() {
        gildedRose.simulateDay();
        assertEquals(1, gildedRose.items[2].quality);
        gildedRose.simulateDay();
        assertEquals(0, gildedRose.items[2].quality);
    }
}
