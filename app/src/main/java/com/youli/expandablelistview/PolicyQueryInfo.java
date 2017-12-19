package com.youli.expandablelistview;

/**
 * 作者: zhengbin on 2017/12/19.
 * <p>
 * 邮箱:2381447237@qq.com
 * <p>
 * github:2381447237
 */

public class PolicyQueryInfo {

    private int ID;
    private String POLICY_TYPE;
    private String QUESTIONS;//问题
    private String ANSWERS;//答案

    public String getANSWERS() {
        return ANSWERS;
    }

    public void setANSWERS(String ANSWERS) {
        this.ANSWERS = ANSWERS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPOLICY_TYPE() {
        return POLICY_TYPE;
    }

    public void setPOLICY_TYPE(String POLICY_TYPE) {
        this.POLICY_TYPE = POLICY_TYPE;
    }

    public String getQUESTIONS() {
        return QUESTIONS;
    }

    public void setQUESTIONS(String QUESTIONS) {
        this.QUESTIONS = QUESTIONS;
    }

    @Override
    public String toString() {
        return "PolicyQueryInfo{" +
                "QUESTIONS='" + QUESTIONS + '\'' +
                '}';
    }
}
