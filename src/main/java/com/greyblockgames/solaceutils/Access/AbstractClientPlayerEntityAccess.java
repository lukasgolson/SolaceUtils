package com.greyblockgames.solaceutils.Access;

import com.greyblockgames.solaceutils.data.UnusualEffectData;

public interface AbstractClientPlayerEntityAccess {
    boolean GBG_hasUnusualEffect();

    UnusualEffectData GBG_getUnusualEffect();
}
