@SubscribeEvent
public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    // 1. Grab a standard human body blueprint (Head, Arms, Legs)
    MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
    PartDefinition root = mesh.getRoot();

    // 2. Find the "chest" section (called "body" in Minecraft code)
    PartDefinition body = root.getChild("body");

    // 3. Add the custom exaggerated "bazongas" boxes right onto the chest!
    body.addOrReplaceChild("custom_chest_assets", 
        CubeListBuilder.create()
            .texOffs(16, 16) // Where to look on your texture PNG file for the skin colors
            .addBox(-4.0F, 2.0F, -6.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), // Coordinates: (X, Y, Z, Width, Height, Depth)
        PartPose.offset(0.0F, 0.0F, 0.0F)
    );

    // Register our newly shaped model layer
    event.registerLayerDefinition(CustomWaifuMobRenderer.LAYER_LOCATION, () -> LayerDefinition.create(mesh, 64, 64));
}