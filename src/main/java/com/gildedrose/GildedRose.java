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

            if (isItemExpired(item)) {
                expireItem(item);
            }
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackStagePasses(item);
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

    private void updateNormalItem(Item item) {
        decreaseQualityCapped(item);
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

    private void increaseQualityCapped(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}