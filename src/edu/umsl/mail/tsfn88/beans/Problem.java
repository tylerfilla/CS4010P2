package edu.umsl.mail.tsfn88.beans;

public class Problem {

    /** The problem ID. */
    private int mPid;

    /** The problem content. */
    private String mContent;

    /** The ID of the problem which this problem follows. */
    private int mFollows;

    public Problem() {
        mPid = -1;
        mContent = "";
        mFollows = 0;
    }

    /**
     * @return The problem ID
     */
    public int getPid() {
        return mPid;
    }

    /**
     * @param pPid The problem ID
     */
    public void setPid(int pPid) {
        mPid = pPid;
    }

    /**
     * @return The problem content
     */
    public String getContent() {
        return mContent;
    }

    /**
     * @param pContent The problem content
     */
    public void setContent(String pContent) {
        mContent = pContent;
    }

    /**
     * @return The ID of the problem which this problem follows
     */
    public int getFollows() {
        return mFollows;
    }

    /**
     * @param pFollows The ID of the problem which this problem follows
     */
    public void setFollows(int pFollows) {
        mFollows = pFollows;
    }

}
