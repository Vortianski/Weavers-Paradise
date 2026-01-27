package xox.labvorty.weaversparadise.renderers.render_helper;

public class ShirtRenderingData {
    private int pCO;
    private int sCO;
    private int pCT;
    private int sCT;
    private String dTO;
    private String dTT;
    private String sT;
    private int lVO;
    private int lVT;
    private String mat;

    public ShirtRenderingData(
            int pCO,
            int sCO,
            int pCT,
            int sCT,
            String dTO,
            String dTT,
            String sT,
            int lVO,
            int lVT,
            String mat
    ) {
        this.pCO = pCO;
        this.sCO = sCO;
        this.pCT = pCT;
        this.sCT = sCT;
        this.dTO = dTO;
        this.dTT = dTT;
        this.sT = sT;
        this.lVO = lVO;
        this.lVT = lVT;
        this.mat = mat;
    }

    public int getPrimaryColorOne() {
        return pCO;
    }

    public int getSecondaryColorOne() {
        return sCO;
    }

    public int getPrimaryColorTwo() {
        return pCT;
    }

    public int getSecondaryColorTwo() {
        return sCT;
    }

    public String getDyeTypeOne() {
        return dTO;
    }

    public String getDyeTypeTwo() {
        return dTT;
    }

    public String getStencilType() {
        return sT;
    }

    public int getLightValueOne() {
        return lVO;
    }

    public int getLightValueTwo() {
        return lVT;
    }

    public String getMaterial() {
        return mat;
    }
}
