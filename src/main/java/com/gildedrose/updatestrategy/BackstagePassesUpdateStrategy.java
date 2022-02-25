package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class BackstagePassesUpdateStrategy extends ItemUpdateStrategy {
    protected void updateQuality(Item item) {
        increaseQualityCapped(item);
        if (item.sellIn < 11) {
            increaseQualityCapped(item);
        }
        if (item.sellIn < 6) {
            increaseQualityCapped(item);
        }
    }
    protected void expireItem(Item item) {
        item.quality = 0;
    }
}
