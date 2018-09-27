package training.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseTest {

    @Test
    public void failingTest() {
        assertThat(1).isEqualTo(2);
    }

    @Test
    public void itemsArePresent()
    {
        Item item = new Item("Aged Brie", 10, 10);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items).contains(item);
        assertThat(gildedRose.items[0].name).isEqualTo("Aged Brie");
        assertThat(gildedRose.items[0].sellIn).isEqualTo(10);
        assertThat(gildedRose.items[0].quality).isEqualTo(10);
    }

    @Test
    public void qualityIsNeverNegative()
    {
        Item item = new Item("Aged Brie", 10, -10);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityCannotFallBelowZero()
    {
        Item item = new Item("Brie", 10, 0);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityIsNeverAboveFifty()
    {
        Item item = new Item("Brie", 10, 60);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items[0].quality).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityCannotIncreaseAboveFifty()
    {
        Item item = new Item("Aged Brie", 10, 50);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityDegradesFasterOnceSellInDateHasPassed()
    {
        Item item = new Item("Brie", 0, 10);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isEqualTo(8);
    }

    @Test
    public void agedBrieIncreasesInQuality()
    {
        Item item = new Item("Aged Brie", 10, 10);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isGreaterThan(10);
    }

    @Test
    public void sulfurasSellInValueDoesNotDecrease()
    {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].sellIn).isEqualTo(10);
    }

    @Test
    public void sulfurasDoesNotDecreaseInQuality()
    {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isEqualTo(80);
    }

    @Test
    public void sulfurasQualityIsAlwaysEighty()
    {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 10);
        Item[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isEqualTo(80);
    }

    @Test
    public void backstagePassesQualityIncreasesCorrectly()
    {
        Item passOne = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        Item passTwo = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        Item passThree = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        Item[] items = { passOne, passTwo, passThree };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].quality).isEqualTo(12);
        assertThat(gildedRose.items[1].quality).isEqualTo(13);
        assertThat(gildedRose.items[2].quality).isEqualTo(0);
    }
}