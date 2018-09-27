package training.gildedrose;

public class Main
{
    public static void main(String[] args)
    {
        ItemWithType itemOne = new ItemWithType("Aged Brie", 20, 5, TypeOfItem.AGED_BRIE);
        ItemWithType itemTwo = new ItemWithType("Sulfuras", 20, 5, TypeOfItem.SULFURAS);
        ItemWithType itemThree = new ItemWithType("Backstage Pass", 20, 5, TypeOfItem.BACKSTAGE_PASS);
        ItemWithType itemFour = new ItemWithType("Apple", 20, 5);

        ItemWithType[] items = { itemOne, itemTwo, itemThree, itemFour };
        GildedRose gildedRose = new GildedRose(items);

        for(int i = 0; i < 100; i++)
        {
            System.out.print("\n\n");
            gildedRose.updateQuality();
            for(ItemWithType item : gildedRose.items)
            {
                System.out.println(item.toString());
            }
        }
    }
}
