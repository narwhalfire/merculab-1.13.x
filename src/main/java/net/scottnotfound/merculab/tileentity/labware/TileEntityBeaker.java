package net.scottnotfound.merculab.tileentity.labware;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.scottnotfound.merculab.init.MercuLab;

public class TileEntityBeaker extends TileEntity {


    public TileEntityBeaker(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityBeaker() {
        this(MercuLab.TileEntityTypes.BEAKER);
    }
}
