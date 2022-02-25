package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredTest {
    @Test
    void conjuredItem(){
        Item[] Conjured = new Item[]{ new Item("Conjured Mana Cake" , 3 ,10) };
        GildedRose gildedRose= new GildedRose(Conjured);
        assertEquals("Conjured Mana Cake", gildedRose.items[0].name);
        assertEquals(3, gildedRose.items[0].sellIn);
        assertEquals(10, gildedRose.items[0].quality);
    }

    @Test
    void conjuredPriorSellInQuality(){
        Item[] Conjured = new Item[]{ new Item("Conjured Mana Cake" , 3 ,10) };
        GildedRose gildedRose= new GildedRose(Conjured);
        gildedRose.simulateDay();
        assertEquals(8, gildedRose.items[0].quality);
        gildedRose.simulateDay();
        assertEquals(6, gildedRose.items[0].quality);
    }

    @Test
    void conjuredPostSellInQuality(){
        Item[] Conjured = new Item[]{ new Item("Conjured Mana Cake" , 0 ,10) };
        GildedRose gildedRose= new GildedRose(Conjured);
        gildedRose.simulateDay();
        assertEquals("Conjured Mana Cake", gildedRose.items[0].name);
        assertEquals(6, gildedRose.items[0].quality);
    }


}
