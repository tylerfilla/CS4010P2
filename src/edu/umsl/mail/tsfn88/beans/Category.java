package edu.umsl.mail.tsfn88.beans;

public class Category {

    /** The category ID. */
    private int mCid;

    /** The category name. */
    private String mName;

    public Category() {
        mCid = -1;
        mName = "";
    }

    /**
     * @return The category ID
     */
    public int getCid() {
        return mCid;
    }

    /**
     * @param pCid The category ID
     */
    public void setCid(int pCid) {
        mCid = pCid;
    }

    /**
     * @return The category name
     */
    public String getName() {
        return mName;
    }

    /**
     * @param pName The category name
     */
    public void setName(String pName) {
        mName = pName;
    }

}
