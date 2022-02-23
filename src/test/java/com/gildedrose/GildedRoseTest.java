package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private Item[] items;
    private GildedRose gildedRose;

    @BeforeEach
    void beforeEach() {
        items = new Item[]{
                new Item("foo", 1, 0),
                new Item("item2", 0, 0),
                new Item("item1", 0, 4),
                new Item("Aged Brie", 2, 0),
                new Item("Aged Brie", 1, 50),
                new Item("Sulfuras, Hand of Ragnaros", 2, 1),
                new Item("Backstage passes to a TAFKAL80ETC concert", 50, 1)};
        gildedRose = new GildedRose(items);
    }

    @Test
    void qualityDegradesTwiceAfterSellIn() {
        gildedRose.updateQuality();
        assertEquals(2, items[2].quality);
        gildedRose.updateQuality();
        assertEquals(0, items[2].quality);
    }

    @Test
    void backstagePassesQualityCapped() {
        items[6].quality = 50;
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        assertEquals(50, items[6].quality);
    }

    @Test
    void backstagePasses() {
        gildedRose.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[6].name);
        assertEquals(49, items[6].sellIn);
        assertEquals(2, items[6].quality);
        items[6].sellIn = 10;
        items[6].quality = 0;
        gildedRose.updateQuality();
        assertEquals(9, items[6].sellIn);
        assertEquals(2, items[6].quality);
        items[6].sellIn = 5;
        items[6].quality = 0;
        gildedRose.updateQuality();
        assertEquals(4, items[6].sellIn);
        assertEquals(3, items[6].quality);
    }

    @Test
    void backstagePassesQualityAfterSellIn() {
        items[6].sellIn = 0;
        gildedRose.updateQuality();
        assertEquals(0, items[6].quality);
    }

    @Test
    void sulfarus() {
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        assertEquals(2, items[5].sellIn);
        assertEquals(1, items[5].quality);
    }

    @Test
    void sulfarusQualityCanExceed50() {
        assertEquals("Sulfuras, Hand of Ragnaros", items[5].name);
        items[5].quality = 60;
        gildedRose.updateQuality();
        assertEquals(2, items[5].sellIn);
        assertEquals(60, items[5].quality);
    }

    @Test
    void maximumQualityIsFifty() {
        gildedRose.updateQuality();
        gildedRose.updateQuality();
        assertEquals(50, items[4].quality);
    }

    @Test
    void agedBrieQuality() {
        gildedRose.updateQuality();
        assertEquals(1, items[3].quality);
        gildedRose.updateQuality();
        assertEquals(2, items[3].quality);
    }

    @Test
    void foo() {
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[0].quality);
        assertEquals("foo", gildedRose.items[0].name);
    }

    @Test
    void QualityNeverNegative() {
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[1].quality);
    }

}
