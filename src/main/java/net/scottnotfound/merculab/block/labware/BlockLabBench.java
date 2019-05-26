package net.scottnotfound.merculab.block.labware;

import net.minecraft.block.Block;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockLabBench {

    //todo: make lab bench voxel shapes and have them connect like stairs and fences

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape BENCHTOP = Block.makeCuboidShape(0.0, 15.0, 0.0,
                                                                       16.0, 16.0, 16.0);
    protected static final VoxelShape CABINET_CORE = Block.makeCuboidShape(1.0, 0.0, 1.0,
                                                                           15.0, 15.0, 15.0);
    protected static final VoxelShape CABINET_NORTH = Block.makeCuboidShape();

    protected final VoxelShape[] shapes;

    /**
     * Constructs the lab bench with the default dimensions.
     * @param properties block properties
     */
    public BlockLabBench(Block.Properties properties) {
        this(1.0f, 0.0f, properties);
    }

    /**
     * The floats values are taken as units of a 16x16x16 space. 16.0f is the full length, 8.0f half, 0.0f zero.
     *
     * @param benchV vertical height of the bench top from top of cabinet
     * @param benchH horizontal inset of the bench top from edge of block space
     * @param properties block properties
     */
    public BlockLabBench(float benchV, float benchH, Block.Properties properties) {
        this(benchV, benchH, 16.0f - benchV, 1.0f + benchH, properties);
    }

    /**
     * The floats values are taken as units of a 16x16x16 space. 16.0f is the full length, 8.0f half, 0.0f zero.
     *
     * @param benchV vertical height of the bench top from top of cabinet
     * @param benchH horizontal inset of the bench top from edge of block space
     * @param cabinetH horizontal inset of the cabinet from the edge of the block space
     * @param properties block properties
     */
    public BlockLabBench(float benchV, float benchH, float cabinetH, Block.Properties properties) {
        this(benchV, benchH, 16.0f - benchV, cabinetH, properties);
    }

    /**
     * The floats values are taken as units of a 16x16x16 space. 16.0f is the full length, 8.0f half, 0.0f zero.
     *
     * @param benchV vertical height of the bench top from top of cabinet
     * @param benchH horizontal inset of the bench top from edge of block space
     * @param cabinetV vertical height of the cabinet from the bottom of block space
     * @param cabinetH horizontal inset of the cabinet from the edge of the block space
     * @param properties block properties
     */
    public BlockLabBench(float benchV, float benchH, float cabinetV, float cabinetH, Block.Properties properties) {
        this.shapes = makeShapes(benchV, benchH, cabinetV, cabinetH);
    }

    protected VoxelShape[] makeShapes(float benchV, float benchH, float cabinetV, float cabinetH) {


    }

}
