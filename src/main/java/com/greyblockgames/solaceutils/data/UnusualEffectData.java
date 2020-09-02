package com.greyblockgames.solaceutils.data;

import net.minecraft.particle.ParticleEffect;

public class UnusualEffectData {
    private int spawnRate;
    private double heightScale;
    private ParticleEffect particleEffect;


    public UnusualEffectData(int spawnRate, double heightScale, ParticleEffect particleEffect){
        this.spawnRate = spawnRate;
        this.heightScale = heightScale;
        this.particleEffect = particleEffect;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public double getHeightScale() {
        return heightScale;
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }


}
