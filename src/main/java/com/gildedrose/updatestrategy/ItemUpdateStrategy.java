package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class ItemUpdateStrategy {
    public void update(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (isItemExpired(item)) {
            expireItem(item);
        }
    }

    protected void updateQuality(Item item) {
        decreaseQualityCapped(item);
    }

    protected void updateSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    protected void expireItem(Item item) {
        decreaseQualityCapped(item);
    }

    protected void increaseQualityCapped(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQualityCapped(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private boolean isItemExpired(Item item) {
        return item.sellIn < 0;
    }
}
