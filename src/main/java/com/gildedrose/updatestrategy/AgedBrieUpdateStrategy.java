package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class AgedBrieUpdateStrategy extends ItemUpdateStrategy {
    protected void updateQuality(Item item) {
        increaseQualityCapped(item);
    }
    protected void expireItem(Item item) {
    }
}
