/*
 * This file is part of Blue Power.
 *
 *     Blue Power is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Blue Power is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Blue Power.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bluepowermod.block.machine;

import com.bluepowermod.block.BlockContainerBase;
import com.bluepowermod.reference.Refs;
import com.bluepowermod.tile.IRotatable;
import com.bluepowermod.tile.tier1.TileIgniter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockIgniter extends BlockContainerBase {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool ACTIVE = PropertyBool.create("active");
    
    public BlockIgniter() {
    
        super(Material.ROCK, TileIgniter.class);
        setUnlocalizedName(Refs.BLOCKIGNITER_NAME);
        setRegistryName(Refs.MODID, Refs.BLOCKIGNITER_NAME);
        setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ACTIVE);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        //Meta 0-5 off direction - Meta 5-11 on direction
        return getDefaultState()
                .withProperty(FACING, EnumFacing.VALUES[meta > 5 ? meta - 6 : meta])
                .withProperty(ACTIVE, meta > 5);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, iblockstate.withProperty(ACTIVE, active), 3);
        if (tileentity != null){
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack iStack) {
        super.onBlockPlacedBy(world, pos, state, placer, iStack);
        world.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        //Meta 0-5 off direction - Meta 5-11 on direction
        return state.getValue(FACING).getIndex() + (state.getValue(ACTIVE) ? 6 : 0);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        TileIgniter tile = (TileIgniter) world.getTileEntity(pos);
        boolean orientation = world.getBlockState(pos).getValue(FACING) == EnumFacing.UP;
        return orientation && tile.getIsRedstonePowered();
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        return !(world.getBlockState(pos) instanceof BlockLampRGB) && super.canConnectRedstone(state, world, pos, side);
    }

    @Override
    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing orDir) {
            EnumFacing dir = worldObj.getBlockState(pos).getValue(FACING);
            Block target = worldObj.getBlockState(pos.offset(dir)).getBlock();
            if (target == Blocks.FIRE || target == Blocks.PORTAL) {
                worldObj.setBlockToAir(pos.offset(dir));
            }
            dir = orDir;
            if (dir != EnumFacing.UP && dir != EnumFacing.DOWN || canRotateVertical()) {
                worldObj.setBlockState(pos,  worldObj.getBlockState(pos).withProperty(FACING, dir), 3);
                return true;
            }
        return false;
    }
}
