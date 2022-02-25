package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class AgedBrieUpdateStrategy extends ItemUpdateStrategy {
    public void updateQuality(Item item) {
        increaseQualityCapped(item);
    }
    public void expireItem(Item item) {
    }
}
