package com.projects.ofirbarzilay.funtime.math.helper;

/**
 * Created by Ofir.Barzilay on 07/12/2014.
 */
public class Exercise {
    private int mId;
    private String mEquation;
    private int mNum1;
    private int mNum2;
    private int mResult;

    public Exercise(String equation) {
        mEquation = equation;
    }

    public int getResult() {
        return mResult;
    }

    public boolean checkResult(int result) {
        return mResult == result;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getEquation() {
        return mEquation;
    }

    public void setNum1(int num1) {
        this.mNum1 = num1;
    }

    public void setNum2(int num2) {
        this.mNum2 = num2;
    }

    public int getNum1() {
        return mNum1;
    }

    public int getNum2() {
        return mNum2;
    }

    public void setResult(int mResult) {
        this.mResult = mResult;
    }
}