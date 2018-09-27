package training.gildedrose;

public class ItemType extends Item
{
    private String name;
    private int sellIn;
    private int quality;
    private TypeOfItem typeOfItem;
    private int qualityChange;

    public ItemType(String name, int sellIn, int quality, TypeOfItem typeOfItem)
    {
        super(name, sellIn, quality);
        this.name = super.name;
        this.sellIn = super.sellIn;
        this.quality = super.quality;
        this.typeOfItem = typeOfItem;
        calculateQualityChange();
    }

    public ItemType(String name, int sellIn, int quality)
    {
        super(name, sellIn, quality);
        this.name = super.name;
        this.sellIn = super.sellIn;
        this.quality = super.quality;
        this.typeOfItem = TypeOfItem.OTHER_TYPE;
        calculateQualityChange();
    }

    private void calculateQualityChange()
    {
        int q = 0;
        int s = this.sellIn;
        switch(this.typeOfItem)
        {
            case AGED_BRIE:
                q++;
                break;
            case BACKSTAGE_PASS:
                if(s < 5) q += 3;
                else if(s < 10) q += 2;
                else q -= this.quality;
                break;
            case SULFURAS:
                this.quality = 80;
                break;
            case OTHER_TYPE:
                q--;
                break;
        }
        setQualityChange(q);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setSellIn(int sellIn)
    {
        this.sellIn = sellIn;
    }

    public int getSellIn()
    {
        return sellIn;
    }

    public void setQuality(int quality)
    {
        this.quality = quality;
    }

    public int getQuality()
    {
        return quality;
    }

    public void setTypeOfItem(TypeOfItem typeOfItem)
    {
        this.typeOfItem = typeOfItem;
    }

    public TypeOfItem getTypeOfItem()
    {
        return typeOfItem;
    }

    public void setQualityChange(int qualityChange)
    {
        this.qualityChange = qualityChange;
    }

    public int getQualityChange()
    {
        return qualityChange;
    }
}
