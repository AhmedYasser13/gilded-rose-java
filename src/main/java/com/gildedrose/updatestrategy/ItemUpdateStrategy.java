package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class ItemUpdateStrategy {
    public void updateQuality(Item item) {
        decreaseQualityCapped(item);
    }

    public void updateSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public void expireItem(Item item) {
        decreaseQualityCapped(item);
    }

    private void decreaseQualityCapped(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void increaseQualityCapped(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
