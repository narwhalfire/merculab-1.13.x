package net.scottnotfound.merculab.item.chemical;

import net.minecraft.item.Item;
import net.scottnotfound.merculab.chemical.Chemical;

public class ItemChemical extends Item {

    private final Chemical chemical;

    public ItemChemical(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
