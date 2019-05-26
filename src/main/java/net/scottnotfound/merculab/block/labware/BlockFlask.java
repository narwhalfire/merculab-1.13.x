package net.scottnotfound.merculab.block.labware;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.scottnotfound.merculab.init.MercuLab;

import javax.annotation.Nullable;

public class BlockFlask extends Block implements IForgeBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0, 0.0, 4.0,
                                                                    12.0, 10.0, 12.0);

    public BlockFlask(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return MercuLab.TileEntityTypes.FLASK.create();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }


}
