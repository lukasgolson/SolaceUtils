package com.greyblockgames.solaceutils.data;

import net.minecraft.client.util.math.Vector3d;
import net.minecraft.particle.ParticleEffect;

public class UnusualEffectData {
    private final int spawnRate;
    private final double heightScale;
    private final double radius;
    private final Vector3d vel;
    private final ParticleEffect particleEffect;


    public UnusualEffectData(int spawnRate, double heightScale, double radius, Vector3d velocity, ParticleEffect particleEffect) {
        this.spawnRate = spawnRate;
        this.heightScale = heightScale;
        this.radius = radius;
        this.vel = velocity;
        this.particleEffect = particleEffect;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public double getHeightScale() {
        return heightScale;
    }

    public double getRadius() {
        return radius;
    }

    public Vector3d getVelocity() {
        return vel;
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }


}
