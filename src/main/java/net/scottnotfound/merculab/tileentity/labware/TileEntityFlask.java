package net.scottnotfound.merculab.tileentity.labware;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.scottnotfound.merculab.init.MercuLab;

public class TileEntityFlask extends TileEntity {


    public TileEntityFlask(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityFlask() {
        this(MercuLab.TileEntityTypes.FLASK);
    }
}
