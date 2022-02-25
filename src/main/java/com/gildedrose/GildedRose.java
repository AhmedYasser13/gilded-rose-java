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

            updateSellIn(item);

            if (isItemExpired(item)) {
                expireItem(item);
            }
        }
    }

    private void updateQuality(Item item, ItemUpdateStrategy updateStrategy) {
        updateStrategy.updateQuality(item);
    }

    private void updateSellIn(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn = item.sellIn - 1;
    }

    private void expireItem(Item item) {
        if (item.name.equals("Aged Brie") || item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            expireBackStagePasses(item);
        } else {
            expireNormalItem(item);
        }
    }

    private boolean isItemExpired(Item item) {
        return item.sellIn < 0;
    }

    private void expireBackStagePasses(Item item) {
        item.quality = 0;
    }

    private void expireNormalItem(Item item) {
        decreaseQualityCapped(item);
    }

    private void decreaseQualityCapped(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}