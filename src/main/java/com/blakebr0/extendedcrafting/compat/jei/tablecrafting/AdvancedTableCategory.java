package com.blakebr0.extendedcrafting.compat.jei.tablecrafting;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.extendedcrafting.ExtendedCrafting;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

@MethodsReturnNonnullByDefault
public class AdvancedTableCategory implements IRecipeCategory<IRecipeWrapper> {

	public static final String UID = "extendedcrafting:table_crafting_5x5";
	private static final ResourceLocation TEXTURE = ResourceHelper.getResource(ExtendedCrafting.MOD_ID, "textures/jei/advanced_crafting.png");

	private final IDrawable background;

	public AdvancedTableCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 150, 90);
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return Utils.localize("jei.ec.table_crafting_5x5");
	}

	@Override
	public String getModName() {
		return ExtendedCrafting.NAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout layout, @Nonnull IRecipeWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = layout.getItemStacks();

		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<ItemStack> outputs = ingredients.getOutputs(VanillaTypes.ITEM).get(0);

		stacks.init(0, false, 128, 35);
		stacks.set(0, outputs);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int index = 1 + j + (i * 5);
				stacks.init(index, true, j * 18, i * 18);
			}
		}

		if (wrapper instanceof TableShapedWrapper) {
			TableShapedWrapper shaped = (TableShapedWrapper) wrapper;
			
			int stackIndex = 0;
			for (int i = 0; i < shaped.getHeight(); i++) {
				for (int j = 0; j < shaped.getWidth(); j++) {
					int index = 1 + (i * 5) + j;
					
					stacks.set(index, inputs.get(stackIndex));
					
					stackIndex++;
				}
			}
		} else if (wrapper instanceof TableShapelessWrapper) {
			int i = 1;
			for (List<ItemStack> stack : inputs) {
				stacks.set(i, stack);
				i++;
			}
		}
		
		layout.setRecipeTransferButton(137, 77);
	}
}
