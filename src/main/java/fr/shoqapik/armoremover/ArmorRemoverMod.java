package fr.shoqapik.armoremover;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@Mod(ArmorRemoverMod.MODID)
public class ArmorRemoverMod {

    public static final String MODID = "armoremover";

    public ArmorRemoverMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ArmorRemoverConfig.SPEC, "armor-remover.toml");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        for(int i  = 0; i < 4; i++){
            ItemStack stack = player.getInventory().armor.get(i);
            if (!stack.isEmpty() && isItemBroken(stack) && isConfigItem(stack)) {
                player.getInventory().armor.set(i, ItemStack.EMPTY);
                int freeSlot = player.getInventory().getFreeSlot();

                if (freeSlot != -1) {
                    player.getInventory().setItem(freeSlot, stack);
                } else if(ArmorRemoverConfig.DROP_ITEM_FULL_INVENTORY.get()){
                    player.drop(stack, true, false);
                }
            }
        }
    }

    public boolean isConfigItem(ItemStack stack) {
        return ArmorRemoverConfig.ARMORS_ITEMS.get().contains(Registry.ITEM.getKey(stack.getItem()).toString());
    }

    public boolean isItemBroken(ItemStack stack) {
        return stack.isDamageableItem() && stack.getMaxDamage() > 1 && stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }

}



