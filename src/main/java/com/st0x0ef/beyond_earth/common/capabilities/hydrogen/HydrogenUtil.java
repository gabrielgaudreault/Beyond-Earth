package com.st0x0ef.beyond_earth.common.capabilities.hydrogen;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.items.IItemHandlerModifiable;

public class HydrogenUtil {

    public static <T> LazyOptional<T> getHydrogenCapability(Capability<T> capability, NonNullSupplier<IHydrogenStorage> hydrogenStorage) {
        if (capability == null) {
            return LazyOptional.empty();
        } else if (capability == HydrogenProvider.HYDROGEN) {
            return LazyOptional.of(hydrogenStorage).cast();
        }

        return LazyOptional.empty();
    }

    public static IHydrogenStorage getItemStackHydrogenStorage(ItemStack itemStack) {
        return itemStack.getCapability(HydrogenProvider.HYDROGEN).orElse(null);
    }

    /**
     * test receive hydrogen to itemstack
     * 
     * @param itemStack
     * @return
     */
    public static boolean canReceive(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }

        IHydrogenStorage storageInItemStack = getItemStackHydrogenStorage(itemStack);
        return storageInItemStack != null && storageInItemStack.receiveHydrogen(1, true) > 0;
    }

    /**
     * test extract oxygen from itemstack
     * 
     * @param itemStack
     * @return
     */
    public static boolean canExtract(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }

        IHydrogenStorage storageInItemStack = getItemStackHydrogenStorage(itemStack);
        return storageInItemStack != null && storageInItemStack.extractHydrogen(1, true) > 0;
    }

    public static ItemStack makeFull(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return itemStack;
        }

        IHydrogenStorage storageInItemStack = getItemStackHydrogenStorage(itemStack);

        if (storageInItemStack != null) {
            storageInItemStack.receiveHydrogen(storageInItemStack.getMaxCapacity(), false);
        }

        return itemStack;
    }

    public static boolean fillSink(IItemHandlerModifiable itemHandler, int sinkItemSlot, IHydrogenStorage source,
            int transfer) {
        ItemStack sinkItemStack = itemHandler.getStackInSlot(sinkItemSlot);

        return fillSinkCapability(source, sinkItemStack, transfer) > 0;
    }

    public static int fillSinkCapability(IHydrogenStorage source, ItemStack sinkItemStack, int transfer) {
        IHydrogenStorage sink = getItemStackHydrogenStorage(sinkItemStack);
        return tryTransfer(sink, source, transfer);
    }

    public static boolean drainSource(IItemHandlerModifiable itemHandler, int sourceItemSlot, IHydrogenStorage sink, int transfer) {
        ItemStack sourceItemStack = itemHandler.getStackInSlot(sourceItemSlot);
        return drainSourceCapability(sink, sourceItemStack, transfer) > 0;
    }

    public static int drainSourceCapability(IHydrogenStorage sink, ItemStack sourceItemStack, int transfer) {
        IHydrogenStorage source = getItemStackHydrogenStorage(sourceItemStack);
        return tryTransfer(sink, source, transfer);
    }

    public static int tryTransfer(IHydrogenStorage sink, IHydrogenStorage source, int transfer) {
        int received = 0;

        if (sink != null && source != null && transfer > 0) {
            int extractableAmount = source.extractHydrogen(transfer, true);
            if (extractableAmount > 0) {
                int receivableAmount = sink.receiveHydrogen(extractableAmount, true);
                if (receivableAmount > 0) {
                    int extracted = source.extractHydrogen(receivableAmount, false);
                    if (extracted > 0) {
                        received = sink.receiveHydrogen(extracted, false);
                    }
                }
            }
        }

        return received;
    }

    private HydrogenUtil() {

    }
}
