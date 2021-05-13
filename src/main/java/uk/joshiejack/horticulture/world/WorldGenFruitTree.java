package uk.joshiejack.horticulture.world;

//public class WorldGenFruitTree extends BirchTree {
//    //private static final Random rotation = new Random();
//    private final BlockState original;
//    private final AbstractSaplingBlock<?> sapling;
//
//    public WorldGenFruitTree(HSaplingBlock.Sapling sapling)  {
//        super(false);
//        this.original = HorticultureBlocks.SAPLING.getStateFromEnum(sapling);
//        this.sapling = HorticultureBlocks.SAPLING;
//    }
//
//    @Override
//    public boolean generate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos position) {
//        BlockState soil = worldIn.getBlockState(position.down());
//        if (soil.getBlock() == Blocks.GRASS && worldIn.getBlockState(position).getMaterial() != Material.WATER) {
//            worldIn.setBlockState(position, original);
//            sapling.generateTree(worldIn, position, original, new Random());
//            return true;
//        }
//
//        return false;
//    }
//}