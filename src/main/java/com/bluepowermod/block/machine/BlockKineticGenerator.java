/*
 * This file is part of Blue Power. Blue Power is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. Blue Power is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along
 * with Blue Power. If not, see <http://www.gnu.org/licenses/>
 */
package com.bluepowermod.block.machine;

import com.bluepowermod.block.BlockContainerBase;
import com.bluepowermod.client.render.RendererBlockBase;
import com.bluepowermod.reference.GuiIDs;
import com.bluepowermod.reference.Refs;
import com.bluepowermod.tile.tier2.TileKineticGenerator;
import net.minecraft.block.material.Material;

/**
 * 
 * @author TheFjong
 * 
 */
public class BlockKineticGenerator extends BlockContainerBase {

    public BlockKineticGenerator() {

        super(Material.iron, TileKineticGenerator.class);
        setBlockName(Refs.KINETICGENERATOR_NAME);
        setGuiId(GuiIDs.KINETICGENERATOR_ID);
    }

    @Override
    protected String getIconName(RendererBlockBase.EnumFaceType faceType, boolean ejecting, boolean powered) {

        String iconName = textureName + "_" + faceType.toString().toLowerCase();
        return iconName;
    }

}
