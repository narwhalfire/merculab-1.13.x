package net.scottnotfound.merculab.block.labware;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.scottnotfound.merculab.state.properties.BlockStateProperties;
import net.scottnotfound.merculab.state.properties.LabBenchSide;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockLabBench extends Block {

    public static final EnumProperty<LabBenchSide> NORTH = BlockStateProperties.NORTH_BENCH;
    public static final EnumProperty<LabBenchSide> SOUTH = BlockStateProperties.SOUTH_BENCH;
    public static final EnumProperty<LabBenchSide> EAST = BlockStateProperties.EAST_BENCH;
    public static final EnumProperty<LabBenchSide> WEST = BlockStateProperties.WEST_BENCH;
    protected static final Map<EnumFacing, EnumProperty<LabBenchSide>> FACING_TO_PROPERTY_MAP =
            Util.make(Maps.newEnumMap(EnumFacing.class), (map) -> {
                map.put(EnumFacing.NORTH, NORTH);
                map.put(EnumFacing.SOUTH, SOUTH);
                map.put(EnumFacing.EAST, EAST);
                map.put(EnumFacing.WEST, WEST);
    });
    protected final VoxelShape[] shapeParts;

    public BlockLabBench(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState()
                                 .with(NORTH, LabBenchSide.NULL)
                                 .with(SOUTH, LabBenchSide.NULL)
                                 .with(EAST, LabBenchSide.NULL)
                                 .with(WEST, LabBenchSide.NULL));
        this.shapeParts = makeShapes(1.0, 0.0, 15.0, 2.0);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return super.onBlockActivated(state, worldIn, pos, player, hand, side, hitX, hitY, hitZ);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader blockReader = context.getWorld();
        BlockPos blockPos = context.getPos();
        return super.getStateForPlacement(context)
                    .with(NORTH, getAttachmentPref(blockReader, blockPos, EnumFacing.NORTH))
                    .with(SOUTH, getAttachmentPref(blockReader, blockPos, EnumFacing.SOUTH))
                    .with(EAST, getAttachmentPref(blockReader, blockPos, EnumFacing.EAST))
                    .with(WEST, getAttachmentPref(blockReader, blockPos, EnumFacing.WEST));
    }

    @Override
    public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing.getAxis().isHorizontal()) {
            return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), getAttachmentPref(worldIn, currentPos, facing));
        } else {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return super.getBlockFaceShape(worldIn, state, pos, face);
    }

    @Override
    public boolean canBeConnectedTo(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing) {
        return false;
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        VoxelShape voxelShape = VoxelShapes.or(shapeParts[0], shapeParts[1]);
        for (VoxelShape vs : getSideShapes(state)) {
            voxelShape = VoxelShapes.or(voxelShape, vs);
        }
        for (VoxelShape vs : getCornerShapes(state)) {
            voxelShape = VoxelShapes.or(voxelShape, vs);
        }
        return voxelShape;
    }

    private VoxelShape[] getSideShapes(IBlockState blockState) {
        List<VoxelShape> shapes = new ArrayList<>();
        if (blockState.get(NORTH) != LabBenchSide.NULL) shapes.add(shapeParts[2]);
        if (blockState.get(SOUTH) != LabBenchSide.NULL) shapes.add(shapeParts[3]);
        if (blockState.get(EAST) != LabBenchSide.NULL) shapes.add(shapeParts[4]);
        if (blockState.get(WEST) != LabBenchSide.NULL) shapes.add(shapeParts[5]);
        return shapes.toArray(new VoxelShape[]{});
    }

    private VoxelShape[] getCornerShapes(IBlockState blockState) {
        List<VoxelShape> shapes = new ArrayList<>();
        if (shouldPlaceCorner(blockState, EnumFacing.NORTH, EnumFacing.WEST)) shapes.add(shapeParts[6]);
        if (shouldPlaceCorner(blockState, EnumFacing.NORTH, EnumFacing.EAST)) shapes.add(shapeParts[7]);
        if (shouldPlaceCorner(blockState, EnumFacing.SOUTH, EnumFacing.WEST)) shapes.add(shapeParts[8]);
        if (shouldPlaceCorner(blockState, EnumFacing.SOUTH, EnumFacing.EAST)) shapes.add(shapeParts[9]);
        return shapes.toArray(new VoxelShape[]{});
    }

    private boolean shouldPlaceCorner(IBlockState blockState, EnumFacing side1, EnumFacing side2) {
        return !side1.getAxis().isVertical() &&
               !side2.getAxis().isVertical() &&
               side1.getOpposite() != side2 &&
               side1 != side2 &&
               blockState.get(FACING_TO_PROPERTY_MAP.get(side1)).getMeta() +
               blockState.get(FACING_TO_PROPERTY_MAP.get(side2)).getMeta() > 2;
    }

    private VoxelShape[] makeShapes(double benchV, double benchH, double cabinetV, double cabinetH) {
        double cabinetH2 = 16.0 - cabinetH;
        double benchH2 = 16.0 - benchH;
        benchV = benchV + cabinetV;
        VoxelShape benchtop = Block.makeCuboidShape(benchH, cabinetV, benchH, benchH2, benchV, benchH2);
        VoxelShape cabinetC = Block.makeCuboidShape(cabinetH, 0.0, cabinetH, cabinetH2, cabinetV, cabinetH2);
        VoxelShape cabinetN = Block.makeCuboidShape(cabinetH, 0.0, 0.0, cabinetH2, cabinetV, cabinetH);
        VoxelShape cabinetS = Block.makeCuboidShape(cabinetH, 0.0, cabinetH2, cabinetH2, cabinetV, 16.0);
        VoxelShape cabinetE = Block.makeCuboidShape(cabinetH2, 0.0, cabinetH, 16.0, cabinetV, cabinetH2);
        VoxelShape cabinetW = Block.makeCuboidShape(0.0, 0.0, cabinetH, cabinetH, cabinetV, cabinetH2);
        VoxelShape cabinetNW = Block.makeCuboidShape(0.0, 0.0, 0.0, cabinetH, cabinetV, cabinetH);
        VoxelShape cabinetNE = Block.makeCuboidShape(cabinetH2, 0.0, 0.0, 16.0, cabinetV, cabinetH);
        VoxelShape cabinetSW = Block.makeCuboidShape(0.0, 0.0, cabinetH2, cabinetH, cabinetV, 16.0);
        VoxelShape cabinetSE = Block.makeCuboidShape(cabinetH2, 0.0, cabinetH2, 16.0, cabinetV, 16.0);

        return new VoxelShape[]{
                benchtop, cabinetC,
                cabinetN, cabinetS, cabinetE, cabinetW,
                cabinetNW, cabinetNE, cabinetSW, cabinetSE
        };

    }

    private LabBenchSide getAttachmentPref(IBlockReader blockReader, BlockPos blockPos, EnumFacing facing) {
        IBlockState adjacent = blockReader.getBlockState(blockPos.offset(facing));
        if (adjacent.getBlock() instanceof BlockLabBench) {
            return LabBenchSide.BENCH;
        }
        if (adjacent.isFullCube()) {
            return LabBenchSide.FULL;
        }

        BlockFaceShape faceShape = adjacent.getBlockFaceShape(blockReader, blockPos, facing);
        if (faceShape == BlockFaceShape.SOLID) {
            return LabBenchSide.FULL;
        } else if (faceShape == BlockFaceShape.CENTER_BIG) {
            return LabBenchSide.BENCH;
        }

        return LabBenchSide.NULL;
    }
}
