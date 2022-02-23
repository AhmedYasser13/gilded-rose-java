package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void simulateDay() {
        for (Item item : items) {
            updateQuality(item);

            updateSellIn(item);

            expireItem(item);
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQualityCapped(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackStagePasses(item);
        } else {
            updateNormalItem(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void expireItem(Item item) {
        if (item.sellIn < 0) {
            if (item.name.equals("Aged Brie")) {
                increaseQualityCapped(item);
            } else {
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                }
            }
        }
    }

    private void updateBackStagePasses(Item item) {
        increaseQualityCapped(item);
        if (item.sellIn < 11) {
            increaseQualityCapped(item);
        }
        if (item.sellIn < 6) {
            increaseQualityCapped(item);
        }
    }

    private void increaseQualityCapped(Item item) {
        if (isQualityMaxed(item)) {
            item.quality = item.quality + 1;
        }
    }

    private boolean isQualityMaxed(Item item) {
        return item.quality < 50;
    }

    private void updateNormalItem(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality = item.quality - 1;
            }
        }
    }
}