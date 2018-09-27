package training.gildedrose;

class GildedRose {
    ItemWithType[] items;

    public GildedRose(ItemWithType[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for(ItemWithType itemWithType : items)
        {
            itemWithType.updateSellIn();
            itemWithType.updateQuality();
        }
    }
}