
package com.andra.myapplication.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MMotor {

    @SerializedName("pesan")
    private String mPesan;
    @SerializedName("sukses")
    private Boolean mSukses;
    @SerializedName("tb_motor")
    private List<TbMotor> mTbMotor;

    public String getPesan() {
        return mPesan;
    }

    public void setPesan(String pesan) {
        mPesan = pesan;
    }

    public Boolean getSukses() {
        return mSukses;
    }

    public void setSukses(Boolean sukses) {
        mSukses = sukses;
    }

    public List<TbMotor> getTbMotor() {
        return mTbMotor;
    }

    public void setTbMotor(List<TbMotor> tbMotor) {
        mTbMotor = tbMotor;
    }

}
