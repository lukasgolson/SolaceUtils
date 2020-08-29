package com.greyblockgames.solaceutils.data;

public class CapeData {
    private final String url;
    private final int version;

    public CapeData(String URL, int Version){
        url = URL;
        version = Version;
    }

    public String getUrl() {
        return url;
    }

    public int getVersion() {
        return version;
    }
}
