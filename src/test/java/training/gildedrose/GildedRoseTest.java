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
        ItemWithType item = new ItemWithType("Aged Brie", 10, 10, TypeOfItem.AGED_BRIE);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items).contains(item);
        assertThat(gildedRose.items[0].getName()).isEqualTo("Aged Brie");
        assertThat(gildedRose.items[0].getSellIn()).isEqualTo(10);
        assertThat(gildedRose.items[0].getQuality()).isEqualTo(10);
    }

    @Test
    public void qualityIsNeverNegative()
    {
        ItemWithType item = new ItemWithType("Aged Brie", 10, -10, TypeOfItem.AGED_BRIE);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityCannotFallBelowZero()
    {
        ItemWithType item = new ItemWithType("Brie", 10, 0);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityIsNeverAboveFifty()
    {
        ItemWithType item = new ItemWithType("Brie", 10, 60);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items[0].getQuality()).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityCannotIncreaseAboveFifty()
    {
        ItemWithType item = new ItemWithType("Aged Brie", 10, 50, TypeOfItem.AGED_BRIE);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityDegradesFasterOnceSellInDateHasPassed()
    {
        ItemWithType item = new ItemWithType("Brie", 0, 10);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(8);
    }

    @Test
    public void agedBrieIncreasesInQuality()
    {
        ItemWithType item = new ItemWithType("Aged Brie", 10, 10, TypeOfItem.AGED_BRIE);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThan(10);
    }

    @Test
    public void sulfurasSellInValueDoesNotDecrease()
    {
        ItemWithType item = new ItemWithType("Sulfuras, Hand of Ragnaros", 10, 80, TypeOfItem.SULFURAS);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getSellIn()).isEqualTo(10);
    }

    @Test
    public void sulfurasDoesNotDecreaseInQuality()
    {
        ItemWithType item = new ItemWithType("Sulfuras, Hand of Ragnaros", 10, 80, TypeOfItem.SULFURAS);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(80);
    }

    @Test
    public void sulfurasQualityIsAlwaysEighty()
    {
        ItemWithType item = new ItemWithType("Sulfuras, Hand of Ragnaros", 10, 10, TypeOfItem.SULFURAS);
        ItemWithType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(80);
    }

    @Test
    public void backstagePassesQualityIncreasesCorrectly()
    {
        ItemWithType passOne = new ItemWithType("Backstage passes to a TAFKAL80ETC concert", 10, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemWithType passTwo = new ItemWithType("Backstage passes to a TAFKAL80ETC concert", 5, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemWithType passThree = new ItemWithType("Backstage passes to a TAFKAL80ETC concert", 0, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemWithType[] items = { passOne, passTwo, passThree };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(12);
        assertThat(gildedRose.items[1].getQuality()).isEqualTo(13);
        assertThat(gildedRose.items[2].getQuality()).isEqualTo(0);
    }
}