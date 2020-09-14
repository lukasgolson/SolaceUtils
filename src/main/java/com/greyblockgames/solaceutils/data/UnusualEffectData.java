package com.greyblockgames.solaceutils.data;

import com.mojang.math.Vector3d;
import net.minecraft.core.particles.ParticleOptions;

public class UnusualEffectData {
    private final int spawnRate;
    private final double heightScale;
    private final double radius;
    private final Vector3d vel;
    private final ParticleOptions particleEffect;


    public UnusualEffectData(int spawnRate, double heightScale, double radius, Vector3d velocity, ParticleOptions particleEffect) {
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

    public ParticleOptions getParticleEffect() {
        return particleEffect;
    }


}
