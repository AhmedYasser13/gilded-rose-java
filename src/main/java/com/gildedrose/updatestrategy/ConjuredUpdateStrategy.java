package com.gildedrose.updatestrategy;

import com.gildedrose.Item;

public class ConjuredUpdateStrategy extends ItemUpdateStrategy {
    protected void decreaseQualityCapped(Item item) {
        super.decreaseQualityCapped(item);
        super.decreaseQualityCapped(item);
    }
}
