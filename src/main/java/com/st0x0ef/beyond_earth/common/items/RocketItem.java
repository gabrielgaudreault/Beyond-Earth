package com.st0x0ef.beyond_earth.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.MinecraftForge;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.blocks.RocketLaunchPad;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import com.st0x0ef.beyond_earth.common.entities.RocketEntity;
import com.st0x0ef.beyond_earth.common.events.forge.PlaceRocketEvent;
import com.st0x0ef.beyond_earth.common.registries.EntityRegistry;
import com.st0x0ef.beyond_earth.client.registries.ItemRendererRegistry;
import com.st0x0ef.beyond_earth.common.util.FluidUtils;

import java.util.List;
import java.util.function.Consumer;

public class RocketItem extends VehicleItem {
    public static final String FUEL_TAG = BeyondEarth.MODID + ":fuel";
    public static final String BUCKET_TAG = BeyondEarth.MODID + ":buckets";

    public int fuelCapacityModifier = 0;
    public int fuelUsageModifier = 0;
    public final String rocketSkinTextureBasePath = "textures/vehicle/rocket_skin/";
    public String rocketSkinTextureName = "standard";
    public String rocketModelName = "tiny";

    public RocketItem(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockEntityWithoutLevelRenderer getRenderer() {
        return switch (rocketModelName) {
            case "small" -> ItemRendererRegistry.SMALL_ROCKET_ITEM_RENDERER;
            case "normal" -> ItemRendererRegistry.NORMAL_ROCKET_ITEM_RENDERER;
            case "big" -> ItemRendererRegistry.BIG_ROCKET_ITEM_RENDERER;
            default -> ItemRendererRegistry.TINY_ROCKET_ITEM_RENDERER;
        };
    }

    public EntityType<? extends RocketEntity> getEntityType() {
        return switch (rocketModelName) {
            case "small" -> EntityRegistry.SMALL_ROCKET.get();
            case "normal" -> EntityRegistry.NORMAL_ROCKET.get();
            case "big" -> EntityRegistry.BIG_ROCKET.get();
            default -> EntityRegistry.TINY_ROCKET.get();
        };
    }

    public RocketEntity getRocket(Level level) {
        return new RocketEntity(getEntityType(), level);
    }

    public String getRocketSkinTexturePath() {
        return rocketSkinTextureBasePath + rocketModelName + "/" + rocketSkinTextureName + ".png";
    }

    public int getFuelBuckets() {
        return RocketEntity.DEFAULT_FUEL_BUCKETS + fuelCapacityModifier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        InteractionHand hand = context.getHand();
        ItemStack itemStack = context.getItemInHand();

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        if (state.getBlock() instanceof RocketLaunchPad && state.getValue(RocketLaunchPad.STAGE)) {
            BlockPlaceContext blockplacecontext = new BlockPlaceContext(context);
            BlockPos blockpos = blockplacecontext.getClickedPos();
            Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos, this.getRocketPlaceHigh());
            AABB aabb = this.getEntityType().getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());

            if (level.noCollision(aabb)) {
                /** POS */
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                /** CHECK IF NO ENTITY ON THE LAUNCH PAD */
                AABB scanAbove = new AABB(x, y, z, x + 1, y + 1, z + 1);
                List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

                if (entities.isEmpty()) {
                    RocketEntity rocket = this.getRocket(context.getLevel());

                    /** SET PRE POS */
                    rocket.setPos(pos.getX() + 0.5D,  pos.getY() + 1, pos.getZ() + 0.5D);

                    double d0 = RocketItem.getYOffset(level, pos, true, rocket.getBoundingBox());
                    float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 45.0F) / 90.0F) * 90.0F;

                    /** SET FINAL POS */
                    rocket.moveTo(pos.getX() + 0.5D, pos.getY() + d0, pos.getZ() + 0.5D, f, 0.0F);

                    rocket.yRotO = rocket.getYRot();

                    level.addFreshEntity(rocket);

                    /** SET TAGS */
                    rocket.getEntityData().set(RocketEntity.FUEL, itemStack.getOrCreateTag().getInt(FUEL_TAG));
                    rocket.getEntityData().set(RocketEntity.FUEL_BUCKET_NEEDED, RocketEntity.DEFAULT_FUEL_BUCKETS + itemStack.getOrCreateTag().getInt("fuelCapacityModifier"));
                    rocket.getEntityData().set(RocketEntity.FUEL_USAGE, RocketEntity.DEFAULT_FUEL_USAGE + itemStack.getOrCreateTag().getInt("fuelUsageModifier"));
                    rocket.getEntityData().set(RocketEntity.SKIN_TEXTURE_PATH, itemStack.getOrCreateTag().getString("rocketSkinTexturePath"));
                    rocket.setSkinTexture(getRocketSkinTexturePath());

                    /** CALL PLACE ROCKET EVENT */
                    MinecraftForge.EVENT_BUS.post(new PlaceRocketEvent(rocket, context));

                    /** ITEM REMOVE */
                    if (!player.getAbilities().instabuild) {
                        player.setItemInHand(hand, ItemStack.EMPTY);
                    }

                    /** PLACE SOUND */
                    this.rocketPlaceSound(pos, level);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);

        int fuel = itemstack.getOrCreateTag().getInt(FUEL_TAG);
        int capacity = this.getFuelBuckets() * FluidUtils.BUCKET_SIZE;
        list.add(GaugeTextHelper.buildFuelStorageTooltip(GaugeValueHelper.getFuel(fuel, capacity), ChatFormatting.GRAY));

        list.add(Component.literal("Fuel Capacity Modifier : " + this.fuelCapacityModifier));
        list.add(Component.literal("Fuel Usage Modifier : " + this.fuelUsageModifier));
        list.add(Component.literal("Model : " + this.rocketModelName + " rocket"));
        list.add(Component.literal("Skin : " + this.rocketSkinTextureName));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return RocketItem.this.getRenderer();
            }
        });
    }

    public float getRocketPlaceHigh() {
        return -0.6F;
    }

    public void rocketPlaceSound(BlockPos pos, Level world) {
        world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1,1);
    }

    public void setUpgrade(VehicleUpgradeItem upgradeItem) {
        if (!upgradeItem.getRocketModelName().isEmpty()) {
            this.rocketModelName = upgradeItem.getRocketModelName();
        }

        if (!upgradeItem.getRocketSkinTexture().isEmpty()) {
            this.rocketSkinTextureName = upgradeItem.getRocketSkinTexture();
        }

        if (upgradeItem.getFuelCapacityModifier() > 0) {
            fuelCapacityModifier = upgradeItem.getFuelCapacityModifier();
        }

        if (upgradeItem.getFuelUsageModifier() > 0) {
            fuelUsageModifier = upgradeItem.getFuelUsageModifier();
        }

        this.getDefaultInstance().getOrCreateTag().putString("rocketSkinTexturePath", getRocketSkinTexturePath());
        this.getDefaultInstance().getOrCreateTag().putInt("fuelCapacityModifier", this.fuelCapacityModifier);
        this.getDefaultInstance().getOrCreateTag().putInt("fuelUsageModifier", this.fuelUsageModifier);
    }
}