package com.greyblockgames.solaceutils.data;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CosmeticsData {
    public HashMap<String, CapeData> capeList = new HashMap<>();
    public HashMap<String, String> capeOwners = new HashMap<>();
    public ArrayList<String> EarOwners = new ArrayList<String>();
    public HashMap<String, String> particleOwners = new HashMap<>();
}
