package xox.labvorty.weaversparadise.renderers.render_helper;

public class ThighHighsRenderingData {
    private int pCLO;
    private int sCLO;
    private int pCRO;
    private int sCRO;
    private int pCLT;
    private int sCLT;
    private int pCRT;
    private int sCRT;
    private String dTLO;
    private String dTRO;
    private String dTLT;
    private String dTRT;
    private String sTL;
    private String sTR;
    private int lVLO;
    private int lVLT;
    private int lVRO;
    private int lVRT;
    private String mat;

    public ThighHighsRenderingData(
            int pCLO,
            int sCLO,
            int pCRO,
            int sCRO,
            int pCLT,
            int sCLT,
            int pCRT,
            int sCRT,
            String dTLO,
            String dTRO,
            String dTLT,
            String dTRT,
            String sTL,
            String sTR,
            int lVLO,
            int lVLT,
            int lVRO,
            int lVRT,
            String mat
    ) {
        this.pCLO = pCLO;
        this.sCLO = sCLO;
        this.pCRO = pCRO;
        this.sCRO = sCRO;
        this.pCLT = pCLT;
        this.sCLT = sCLT;
        this.pCRT = pCRT;
        this.sCRT = sCRT;
        this.dTLO = dTLO;
        this.dTRO = dTRO;
        this.dTLT = dTLT;
        this.dTRT = dTRT;
        this.sTL = sTL;
        this.sTR = sTR;
        this.lVLO = lVLO;
        this.lVLT = lVLT;
        this.lVRO = lVRO;
        this.lVRT = lVRT;
        this.mat = mat;
    }

    public int getPrimaryColorLeftOne() {
        return pCLO;
    }

    public int getSecondaryColorLeftOne() {
        return sCLO;
    }

    public int getPrimaryColorRightOne() {
        return pCRO;
    }

    public int getSecondaryColorRightOne() {
        return sCRO;
    }

    public int getPrimaryColorLeftTwo() {
        return pCLT;
    }

    public int getSecondaryColorLeftTwo() {
        return sCLT;
    }

    public int getPrimaryColorRightTwo() {
        return pCRT;
    }

    public int getSecondaryColorRightTwo() {
        return sCRT;
    }

    public String getDyeTypeLeftOne() {
        return dTLO;
    }

    public String getDyeTypeRightOne() {
        return dTRO;
    }

    public String getDyeTypeLeftTwo() {
        return dTLT;
    }

    public String getDyeTypeRightTwo() {
        return dTRT;
    }

    public String getStencilTypeLeft() {
        return sTL;
    }

    public String getStencilTypeRight() {
        return sTR;
    }

    public int getLightValueLeftOne() {
        return lVLO;
    }

    public int getLightValueRightOne() {
        return lVRO;
    }

    public int getLightValueLeftTwo() {
        return lVLT;
    }

    public int getLightValueRightTwo() {
        return lVRT;
    }

    public String getMaterial() {
        return mat;
    }
}
