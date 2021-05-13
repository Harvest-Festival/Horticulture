package uk.joshiejack.horticulture.client.renderer.block.statemap;

//public class StateMapperStump extends DefaultStateMapper {
//    @Override
//    @Nonnull
//    protected ModelResourceLocation getModelResourceLocation(@Nonnull BlockState state) {
//        Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
//        String s = (Block.REGISTRY.getNameForObject(state.getBlock())).toString();
//        return new ModelResourceLocation(s, getPropertyString(map));
//    }
//
//    @SuppressWarnings("ConstantConditions")
//    @Nonnull
//    @Override
//    public Map<BlockState, ModelResourceLocation> putStateModelLocations(@Nonnull Block block) {
//        for (int i = 0; i < 4; i++) {
//            mapStateModelLocations.put(new BlockStateContainer(block).getBaseState(), new ModelResourceLocation(block.getRegistryName(), "mushroom_" + i));
//        }
//
//        return super.putStateModelLocations(block);
//    }
//
//    @SuppressWarnings("unused")
//    @SubscribeEvent
//    public void onBaking(ModelBakeEvent event) {
//        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
//        IBakedModel[] mushroom_models = new IBakedModel[4];
//        for (int i = 0; i < mushroom_models.length; i++) {
//            mushroom_models[i] = registry.getObject(new ModelResourceLocation("horticulture:stump", "mushroom_" + i));
//        }
//
//        for (BlockState state : HorticultureBlocks.STUMP.getBlockState().getValidStates()) {
//            ModelResourceLocation resource = getModelResourceLocation(state);
//            registry.putObject(resource, new StumpBakedModel(registry.getObject(resource), mushroom_models));
//        }
//    }
//}
