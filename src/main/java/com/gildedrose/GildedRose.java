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
            updateStrategy = createStrategy(item);
            updateStrategy.update(item);
        }
    }

    private ItemUpdateStrategy createStrategy(Item item) {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasUpdateStrategy();
            case "Aged Brie":
                return new AgedBrieUpdateStrategy();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassesUpdateStrategy();
            default:
                return new ItemUpdateStrategy();
        }
    }
}