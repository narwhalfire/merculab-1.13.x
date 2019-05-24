package net.scottnotfound.merculab.tileentity.labware;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.scottnotfound.merculab.init.MercuLab;

public class TileEntityVial extends TileEntity {


    public TileEntityVial(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityVial() {
        this(MercuLab.TileEntityTypes.VIAL);
    }

}
