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

            updateQuality(item, updateStrategy);

            updateSellIn(item, updateStrategy);

            if (isItemExpired(item)) {
                expireItem(item, updateStrategy);
            }
        }
    }

    private void updateQuality(Item item, ItemUpdateStrategy updateStrategy) {
        updateStrategy.updateQuality(item);
    }

    private void updateSellIn(Item item, ItemUpdateStrategy updateStrategy) {
        updateStrategy.updateSellIn(item);
    }

    private void expireItem(Item item, ItemUpdateStrategy updateStrategy) {
        updateStrategy.expireItem(item);
    }

    private boolean isItemExpired(Item item) {
        return item.sellIn < 0;
    }
}