
package com.andra.myapplication.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MMobil {

    @SerializedName("pesan")
    private String mPesan;
    @SerializedName("sukses")
    private Boolean mSukses;
    @SerializedName("tb_mobil")
    private List<TbMobil> mTbMobil;

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

    public List<TbMobil> getTbMobil() {
        return mTbMobil;
    }

    public void setTbMobil(List<TbMobil> tbMobil) {
        mTbMobil = tbMobil;
    }

}
