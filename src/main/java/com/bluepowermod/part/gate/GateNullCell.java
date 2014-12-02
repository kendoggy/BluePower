package com.bluepowermod.part.gate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import uk.co.qmunity.lib.client.render.RenderHelper;
import uk.co.qmunity.lib.misc.Pair;
import uk.co.qmunity.lib.raytrace.QMovingObjectPosition;
import uk.co.qmunity.lib.vec.Vec3d;
import uk.co.qmunity.lib.vec.Vec3dCube;
import uk.co.qmunity.lib.vec.Vec3i;

import com.bluepowermod.api.redstone.IRedstoneConductor;
import com.bluepowermod.api.redstone.IRedstoneDevice;
import com.bluepowermod.api.redstone.RedstoneColor;
import com.bluepowermod.client.renderers.IconSupplier;

public class GateNullCell extends GateBase implements IRedstoneConductor {

    private boolean analog = false;

    private byte powerA = 0, powerB = 0;
    private IRedstoneDevice[] devices = new IRedstoneDevice[6];

    public GateNullCell(Boolean analog) {

        this.analog = analog;
    }

    @Override
    public void initializeConnections() {

    }

    @Override
    public String getId() {

        return "nullcell" + (analog ? ".analog" : "");
    }

    @Override
    protected void renderTop(float frame) {

    }

    @Override
    public boolean renderStatic(Vec3i translation, RenderHelper renderer, RenderBlocks renderBlocks, int pass) {

        super.renderStatic(translation, renderer, renderBlocks, pass);

        double height = 2 / 16D;

        IIcon planks = Blocks.planks.getIcon(0, 0);
        IIcon wire = IconSupplier.wire;

        renderer.renderBox(new Vec3dCube(2 / 16D, 2 / 16D, 2 / 16D, 3 / 16D, 10 / 16D, 3 / 16D), planks);
        renderer.renderBox(new Vec3dCube(2 / 16D, 2 / 16D, 13 / 16D, 3 / 16D, 10 / 16D, 14 / 16D), planks);
        renderer.renderBox(new Vec3dCube(2 / 16D, 9 / 16D, 3 / 16D, 3 / 16D, 10 / 16D, 13 / 16D), planks);

        renderer.renderBox(new Vec3dCube(13 / 16D, 2 / 16D, 2 / 16D, 14 / 16D, 10 / 16D, 3 / 16D), planks);
        renderer.renderBox(new Vec3dCube(13 / 16D, 2 / 16D, 13 / 16D, 14 / 16D, 10 / 16D, 14 / 16D), planks);
        renderer.renderBox(new Vec3dCube(13 / 16D, 9 / 16D, 3 / 16D, 14 / 16D, 10 / 16D, 13 / 16D), planks);

        renderer.setColor(0xDD0000);

        renderer.renderBox(new Vec3dCube(7 / 16D, 2 / 16D, 1 / 16D, 9 / 16D, 2 / 16D + height, 15 / 16D), wire);
        renderer.renderBox(new Vec3dCube(7 / 16D, 2 / 16D, 0 / 16D, 9 / 16D, 2 / 16D + (height / 2), 1 / 16D), wire);
        renderer.renderBox(new Vec3dCube(7 / 16D, 2 / 16D, 15 / 16D, 9 / 16D, 2 / 16D + (height / 2), 16 / 16D), wire);

        renderer.setColor(0x0000CC);

        renderer.renderBox(new Vec3dCube(0 / 16D, 2 / 16D, 7 / 16D, 2 / 16D, 12 / 16D, 9 / 16D), wire);
        renderer.renderBox(new Vec3dCube(14 / 16D, 2 / 16D, 7 / 16D, 16 / 16D, 12 / 16D, 9 / 16D), wire);
        renderer.renderBox(new Vec3dCube(2 / 16D, 10 / 16D, 7 / 16D, 14 / 16D, 12 / 16D, 9 / 16D), wire);

        renderer.setColor(0xFFFFFF);

        return true;
    }

    @Override
    public void doLogic() {

    }

    @Override
    public void onUpdate() {

        super.onUpdate();
    }

    @Override
    public void addSelectionBoxes(List<Vec3dCube> boxes) {

        super.addSelectionBoxes(boxes);
        addBoxes(boxes);
    }

    @Override
    public void addCollisionBoxes(List<Vec3dCube> boxes, Entity entity) {

        super.addCollisionBoxes(boxes, entity);
        addBoxes(boxes);
    }

    private void addBoxes(List<Vec3dCube> boxes) {

        double height = 2 / 16D;

        boxes.add(new Vec3dCube(2 / 16D, 2 / 16D, 2 / 16D, 3 / 16D, 10 / 16D, 3 / 16D));
        boxes.add(new Vec3dCube(2 / 16D, 2 / 16D, 13 / 16D, 3 / 16D, 10 / 16D, 14 / 16D));
        boxes.add(new Vec3dCube(2 / 16D, 9 / 16D, 3 / 16D, 3 / 16D, 10 / 16D, 13 / 16D));

        boxes.add(new Vec3dCube(13 / 16D, 2 / 16D, 2 / 16D, 14 / 16D, 10 / 16D, 3 / 16D));
        boxes.add(new Vec3dCube(13 / 16D, 2 / 16D, 13 / 16D, 14 / 16D, 10 / 16D, 14 / 16D));
        boxes.add(new Vec3dCube(13 / 16D, 9 / 16D, 3 / 16D, 14 / 16D, 10 / 16D, 13 / 16D));

        boxes.add(new Vec3dCube(7 / 16D, 2 / 16D, 1 / 16D, 9 / 16D, 2 / 16D + height, 15 / 16D));
        boxes.add(new Vec3dCube(7 / 16D, 2 / 16D, 0 / 16D, 9 / 16D, 2 / 16D + (height / 2), 1 / 16D));
        boxes.add(new Vec3dCube(7 / 16D, 2 / 16D, 15 / 16D, 9 / 16D, 2 / 16D + (height / 2), 16 / 16D));

        boxes.add(new Vec3dCube(0 / 16D, 2 / 16D, 7 / 16D, 2 / 16D, 12 / 16D, 9 / 16D));
        boxes.add(new Vec3dCube(14 / 16D, 2 / 16D, 7 / 16D, 16 / 16D, 12 / 16D, 9 / 16D));
        boxes.add(new Vec3dCube(2 / 16D, 10 / 16D, 7 / 16D, 14 / 16D, 12 / 16D, 9 / 16D));
    }

    @Override
    public QMovingObjectPosition rayTrace(Vec3d start, Vec3d end) {

        QMovingObjectPosition mop = super.rayTrace(start, end);

        if (mop != null)
            mop = new QMovingObjectPosition(mop, mop.getPart(), Vec3dCube.merge(getSelectionBoxes()));

        return mop;
    }

    @Override
    public boolean canConnectStraight(IRedstoneDevice device) {

        return true;
    }

    @Override
    public boolean canConnectOpenCorner(IRedstoneDevice device) {

        return true;
    }

    @Override
    public void onConnect(ForgeDirection side, IRedstoneDevice device) {

        devices[side.ordinal()] = device;
    }

    @Override
    public void onDisconnect(ForgeDirection side) {

        devices[side.ordinal()] = null;
    }

    @Override
    public IRedstoneDevice getDeviceOnSide(ForgeDirection side) {

        return devices[side.ordinal()];
    }

    @Override
    public byte getRedstonePower(ForgeDirection side) {

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {
            if (d == getFace() || d == getFace().getOpposite())
                continue;
            if (d == side || d == side.getOpposite())
                return powerA;
            return powerB;
        }

        return 0;
    }

    @Override
    public void setRedstonePower(ForgeDirection side, byte power) {

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {
            if (d == getFace() || d == getFace().getOpposite())
                continue;
            if (d == side || d == side.getOpposite()) {
                powerA = power;
                return;
            }
            powerB = power;
            return;
        }
    }

    @Override
    public void onRedstoneUpdate() {

        sendUpdatePacket();
    }

    @Override
    public RedstoneColor getInsulationColor(ForgeDirection side) {

        return null;
    }

    @Override
    public boolean isNormalBlock() {

        return false;
    }

    @Override
    public boolean hasLoss() {

        return false;
    }

    @Override
    public boolean isAnalog() {

        return analog;
    }

    @Override
    public Collection<Pair<IRedstoneDevice, ForgeDirection>> propagate(ForgeDirection fromSide) {

        IRedstoneDevice dev = getDeviceOnSide(fromSide.getOpposite());
        if (dev == null)
            return Arrays.asList();

        return Arrays.asList(new Pair<IRedstoneDevice, ForgeDirection>(dev, fromSide.getOpposite()));
    }

}