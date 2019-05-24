package net.scottnotfound.merculab.tileentity.labware;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.scottnotfound.merculab.init.MercuLab;

public class TileEntityJar extends TileEntity {


    public TileEntityJar(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityJar() {
        this(MercuLab.TileEntityTypes.JAR);
    }
}
