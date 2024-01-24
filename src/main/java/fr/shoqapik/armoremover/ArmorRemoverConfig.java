package fr.shoqapik.armoremover;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class ArmorRemoverConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ARMORS_ITEMS;
    public static  final ForgeConfigSpec.BooleanValue DROP_ITEM_FULL_INVENTORY;

    static{
        BUILDER.push("ArmorRemover Configuration");
        ARMORS_ITEMS = BUILDER.comment("List of armor to remove when broken").defineList("armors", Arrays.asList("minecraft:golden_helmet"), item -> true);
        DROP_ITEM_FULL_INVENTORY = BUILDER.comment("Drop armor when inventory is full").define("drop_armors", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
