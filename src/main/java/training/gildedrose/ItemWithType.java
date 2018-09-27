package training.gildedrose;

public class ItemWithType extends Item
{
    private String name;
    private int sellIn;
    private int quality;
    private TypeOfItem typeOfItem;
    private int qualityChange;

    private int maxQuality = 50;
    private int minQuality = 0;

    public ItemWithType(String name, int sellIn, int quality, TypeOfItem typeOfItem)
    {
        super(name, sellIn, quality);
        this.name = super.name;
        this.sellIn = super.sellIn;
        this.quality = super.quality;
        this.typeOfItem = typeOfItem;
        this.maxQuality = typeOfItem == TypeOfItem.SULFURAS ? 80 : maxQuality;
        calculateQualityChange();
    }

    public ItemWithType(String name, int sellIn, int quality)
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
        int qualityRate = s > 0 ? 1 : 2;

        switch(this.typeOfItem)
        {
            case AGED_BRIE:
                q += 1 * qualityRate;
                break;
            case BACKSTAGE_PASS:
                if(s <= 0) q -= this.quality;
                else if(s <= 5) q += 3;
                else if(s <= 10) q += 2;
                break;
            case SULFURAS:
                this.quality = 80;
                break;
            case OTHER_TYPE:
                q -= 1 * qualityRate;
                break;
        }
        if (this.getQuality() + q > maxQuality)
        {
            setQuality(maxQuality);
            q = 0;
        }
        else if (this.getQuality() + q < minQuality)
        {
            setQuality(minQuality);
            q = 0;
        }
        setQualityChange(q);
    }

    public void setName(String name)
    {
        this.name = name;
        super.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setSellIn(int sellIn)
    {
        this.sellIn = sellIn;
        super.sellIn = sellIn;
    }

    public int getSellIn()
    {
        return sellIn;
    }

    public void setQuality(int quality)
    {
        this.quality = quality;
        super.quality = quality;
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

    public void updateQuality()
    {
        this.calculateQualityChange();
        int currentQuality = this.getQuality();
        int qualityChange = this.getQualityChange();
        this.setQuality(currentQuality + qualityChange);
    }

    public void updateSellIn()
    {
        if(this.typeOfItem != TypeOfItem.SULFURAS)
        {
            if(this.getSellIn() > 0)
                this.setSellIn(this.getSellIn() - 1);
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
