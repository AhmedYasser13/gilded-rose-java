package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private GildedRose gildedRose;

    @BeforeEach
    void beforeEach() {
        Item[] items = new Item[]{
                new Item("foo", 1, 0),
                new Item("item2", 0, 0),
                new Item("item1", 0, 4),
                new Item("Aged Brie", 2, 0),
                new Item("Aged Brie", 1, 50),
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 50, 1),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50)
        };
        gildedRose = new GildedRose(items);
    }

    @Test
    void qualityDegradesTwiceAfterSellIn() {
        gildedRose.simulateDay();
        assertEquals(2, gildedRose.items[2].quality);
        gildedRose.simulateDay();
        assertEquals(0, gildedRose.items[2].quality);
    }

    @Test
    void backstagePassesQualityCappedTo50() {
        gildedRose.simulateDay();
        assertEquals(50, gildedRose.items[10].quality);
        gildedRose.items[9].sellIn=10;
        gildedRose.simulateDay();
        assertEquals(50, gildedRose.items[10].quality);
        gildedRose.items[9].sellIn=5;
        gildedRose.simulateDay();
        assertEquals(50, gildedRose.items[10].quality);
    }

    @Test
    void backstagePasses() {
        gildedRose.simulateDay();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", gildedRose.items[6].name);
        assertEquals(49, gildedRose.items[6].sellIn);
        assertEquals(2, gildedRose.items[6].quality);
    }

    @Test
    void backstagePassesLast3Days(){
        gildedRose.simulateDay();
        assertEquals(4, gildedRose.items[8].sellIn);
        assertEquals(3, gildedRose.items[8].quality);
    }

    @Test
    void backstagePassesLast10Days() {
        gildedRose.simulateDay();
        assertEquals(9, gildedRose.items[7].sellIn);
        assertEquals(2, gildedRose.items[7].quality);
    }

    @Test
    void backstagePassesQualityAfterSellIn() {
        gildedRose.simulateDay();
        assertEquals(0, gildedRose.items[9].quality);
    }

    @Test
    void sulfarus() {
        gildedRose.simulateDay();
        assertEquals(2, gildedRose.items[5].sellIn);
        assertEquals(80, gildedRose.items[5].quality);
        gildedRose.simulateDay();
        assertEquals(2, gildedRose.items[5].sellIn);
        assertEquals(80, gildedRose.items[5].quality);
    }

    @Test
    void maximumQualityIsFifty() {
        gildedRose.simulateDay();
        gildedRose.simulateDay();
        assertEquals(50, gildedRose.items[4].quality);
    }

    @Test
    void agedBrieQuality() {
        gildedRose.simulateDay();
        assertEquals(1, gildedRose.items[3].quality);
        gildedRose.simulateDay();
        assertEquals(2, gildedRose.items[3].quality);
    }

    @Test
    void foo() {
        gildedRose.simulateDay();
        assertEquals(0, gildedRose.items[0].quality);
        assertEquals("foo", gildedRose.items[0].name);
    }

    @Test
    void qualityCanNeverBeNegative() {
        gildedRose.simulateDay();
        assertEquals(0, gildedRose.items[1].quality);

    }
}
