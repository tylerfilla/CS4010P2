package edu.umsl.mail.tsfn88.beans;

public class CatMapping {

    /** The mapping ID. */
    private int mMid;

    /** The mapped problem ID. */
    private int mPid;

    /** The mapped category ID. */
    private int mCid;

    public CatMapping() {
        mMid = -1;
        mPid = -1;
        mCid = -1;
    }

    /**
     * @return The mapping ID
     */
    public int getMid() {
        return mMid;
    }

    /**
     * @param pMid The mapping ID
     */
    public void setMid(int pMid) {
        mMid = pMid;
    }

    /**
     * @return The mapped problem ID
     */
    public int getPid() {
        return mPid;
    }

    /**
     * @param pPid The mapped problem ID
     */
    public void setPid(int pPid) {
        mPid = pPid;
    }

    /**
     * @return The mapped category ID
     */
    public int getCid() {
        return mCid;
    }

    /**
     * @param pCid The mapped category ID
     */
    public void setCid(int pCid) {
        mCid = pCid;
    }

}
