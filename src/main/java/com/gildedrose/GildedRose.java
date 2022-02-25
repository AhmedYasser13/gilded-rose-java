package com.gildedrose;

import com.gildedrose.updatestrategy.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void simulateDay() {
        ItemUpdateStrategy updateStrategy;
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                updateStrategy = new SulfurasUpdateStrategy();
            } else if (item.name.equals("Aged Brie")) {
                updateStrategy = new AgedBrieUpdateStrategy();
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updateStrategy = new BackstagePassesUpdateStrategy();
            } else {
                updateStrategy = new ItemUpdateStrategy();
            }
            updateStrategy.update(item);
        }
    }
}