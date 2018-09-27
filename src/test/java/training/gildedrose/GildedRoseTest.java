package training.gildedrose;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseTest {

    @Test
    public void failingTest() {
        assertThat(1).isEqualTo(2);
    }

    @Test
    public void itemsArePresent()
    {
        ItemType item = new ItemType("Aged Brie", 10, 10, TypeOfItem.AGED_BRIE);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items).contains(item);
        assertThat(gildedRose.items[0].getName()).isEqualTo("Aged Brie");
        assertThat(gildedRose.items[0].getSellIn()).isEqualTo(10);
        assertThat(gildedRose.items[0].getQuality()).isEqualTo(10);
    }

    @Test
    public void qualityIsNeverNegative()
    {
        ItemType item = new ItemType("Aged Brie", 10, -10, TypeOfItem.AGED_BRIE);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityCannotFallBelowZero()
    {
        ItemType item = new ItemType("Brie", 10, 0);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void qualityIsNeverAboveFifty()
    {
        ItemType item = new ItemType("Brie", 10, 60);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        assertThat(gildedRose.items[0].getQuality()).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityCannotIncreaseAboveFifty()
    {
        ItemType item = new ItemType("Aged Brie", 10, 50, TypeOfItem.AGED_BRIE);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isLessThanOrEqualTo(50);
    }

    @Test
    public void qualityDegradesFasterOnceSellInDateHasPassed()
    {
        ItemType item = new ItemType("Brie", 0, 10);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(8);
    }

    @Test
    public void agedBrieIncreasesInQuality()
    {
        ItemType item = new ItemType("Aged Brie", 10, 10, TypeOfItem.AGED_BRIE);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isGreaterThan(10);
    }

    @Test
    public void sulfurasSellInValueDoesNotDecrease()
    {
        ItemType item = new ItemType("Sulfuras, Hand of Ragnaros", 10, 80, TypeOfItem.SULFURAS);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getSellIn()).isEqualTo(10);
    }

    @Test
    public void sulfurasDoesNotDecreaseInQuality()
    {
        ItemType item = new ItemType("Sulfuras, Hand of Ragnaros", 10, 80, TypeOfItem.SULFURAS);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(80);
    }

    @Test
    public void sulfurasQualityIsAlwaysEighty()
    {
        ItemType item = new ItemType("Sulfuras, Hand of Ragnaros", 10, 10, TypeOfItem.SULFURAS);
        ItemType[] items = { item };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(80);
    }

    @Test
    public void backstagePassesQualityIncreasesCorrectly()
    {
        ItemType passOne = new ItemType("Backstage passes to a TAFKAL80ETC concert", 10, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemType passTwo = new ItemType("Backstage passes to a TAFKAL80ETC concert", 5, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemType passThree = new ItemType("Backstage passes to a TAFKAL80ETC concert", 0, 10, TypeOfItem.BACKSTAGE_PASS);
        ItemType[] items = { passOne, passTwo, passThree };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(gildedRose.items[0].getQuality()).isEqualTo(12);
        assertThat(gildedRose.items[1].getQuality()).isEqualTo(13);
        assertThat(gildedRose.items[2].getQuality()).isEqualTo(0);
    }
}