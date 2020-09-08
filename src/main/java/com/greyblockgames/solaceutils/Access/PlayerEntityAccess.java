package com.greyblockgames.solaceutils.Access;

import com.greyblockgames.solaceutils.data.UnusualEffectData;

public interface PlayerEntityAccess {
    boolean GBG_hasUnusualEffect();

    UnusualEffectData GBG_getUnusualEffect();

    boolean GBG_hasMouseEars();
}
