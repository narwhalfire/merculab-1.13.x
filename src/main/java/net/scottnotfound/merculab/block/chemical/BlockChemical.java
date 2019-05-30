package net.scottnotfound.merculab.block.chemical;

import net.minecraft.block.Block;
import net.scottnotfound.merculab.chemical.Chemical;

public class BlockChemical extends Block {

    private final Chemical chemical;

    public BlockChemical(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
