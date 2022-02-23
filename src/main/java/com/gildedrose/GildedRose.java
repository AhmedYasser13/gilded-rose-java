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

            if (item.sellIn < 0) {
                expireItem(item);
            }
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackStagePasses(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else {
            updateNormalItem(item);
        }
    }

    private void updateSellIn(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn = item.sellIn - 1;
    }

    private void expireItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            return;
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else {
            expireNormalItem(item);
        }
    }

    private void expireNormalItem(Item item) {
        decreaseQualityCapped(item);
    }

    private void decreaseQualityCapped(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
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

    private void updateAgedBrie(Item item) {
        increaseQualityCapped(item);
    }

    private void increaseQualityCapped(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQualityCapped(item);
    }
}