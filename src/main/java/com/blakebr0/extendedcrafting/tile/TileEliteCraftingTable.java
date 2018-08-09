package com.blakebr0.extendedcrafting.tile;

import com.blakebr0.extendedcrafting.crafting.table.TableStackHandler;
import com.blakebr0.extendedcrafting.lib.IExtendedTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileEliteCraftingTable extends TileEntity implements IExtendedTable {

	private TableStackHandler matrix = new TableStackHandler(49, this, this.getWorld());
	private ItemStack result = ItemStack.EMPTY;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag = super.writeToNBT(tag);
		
		if (!this.result.isEmpty()) {
			tag.setTag("Result", this.result.serializeNBT());
		} else {
			tag.removeTag("Result");
		}
		
		tag.merge(this.matrix.serializeNBT());
		
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		this.result = new ItemStack(tag.getCompoundTag("Result"));
		this.matrix.deserializeNBT(tag);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.getWorld().getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().add(0.5, 0.5, 0.5)) <= 64;
	}
	
	@Override
	public ItemStack getResult() {
		return this.result;
	}

	@Override
	public void setResult(ItemStack result) {
		this.result = result;
		this.markDirty();
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.matrix.setStackInSlot(slot, stack);
	}
	
	@Override
	public ItemStackHandler getMatrix() {
		return this.matrix;
	}

	@Override
	public int getLineSize() {
		return 7;
	}
}
