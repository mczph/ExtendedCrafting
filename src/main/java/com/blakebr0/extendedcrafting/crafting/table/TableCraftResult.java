package com.blakebr0.extendedcrafting.crafting.table;

import com.blakebr0.extendedcrafting.lib.IExtendedTable;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class TableCraftResult extends InventoryCraftResult {

	private final IExtendedTable tile;

	public TableCraftResult(IExtendedTable tile) {
		this.tile = tile;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot == 0 ? this.tile.getResult() : ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int slot, int decrement) {
		ItemStack stack = this.tile.getResult();
		if (!stack.isEmpty()) {
			this.tile.setResult(ItemStack.EMPTY);
			return stack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, @Nonnull ItemStack stack) {
		this.tile.setResult(stack);
	}
}
