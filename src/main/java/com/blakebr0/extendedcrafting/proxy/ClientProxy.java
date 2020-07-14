package com.blakebr0.extendedcrafting.proxy;

import com.blakebr0.extendedcrafting.client.tesr.ModRenders;
import com.blakebr0.extendedcrafting.config.ModConfig;
import com.blakebr0.extendedcrafting.item.ItemSingularity;
import com.blakebr0.extendedcrafting.item.ItemSingularityCustom;
import com.blakebr0.extendedcrafting.item.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ModRenders.init();
		
		if (ModConfig.confSingularityEnabled) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemSingularity.ColorHandler(),
					ModItems.itemSingularity);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemSingularityCustom.ColorHandler(),
					ModItems.itemSingularityCustom);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
