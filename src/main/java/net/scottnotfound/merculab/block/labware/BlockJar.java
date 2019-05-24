package net.scottnotfound.merculab.block.labware;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.scottnotfound.merculab.init.MercuLab;

import javax.annotation.Nullable;

public class BlockJar extends Block implements IForgeBlock {

    public BlockJar(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return MercuLab.TileEntityTypes.JAR.create();
    }

}
