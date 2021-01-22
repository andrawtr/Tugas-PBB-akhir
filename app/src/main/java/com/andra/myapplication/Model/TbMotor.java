
package com.andra.myapplication.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TbMotor {

    @SerializedName("id_motor")
    private String mIdMotor;
    @SerializedName("mt_alamat")
    private String mMtAlamat;
    @SerializedName("mt_merk")
    private String mMtMerk;
    @SerializedName("mt_nama")
    private String mMtNama;
    @SerializedName("mt_tglpeminjaman")
    private String mMtTglpeminjaman;
    @SerializedName("mt_tglpengembalian")
    private String mMtTglpengembalian;

    public String getIdMotor() {
        return mIdMotor;
    }

    public void setIdMotor(String idMotor) {
        mIdMotor = idMotor;
    }

    public String getMtAlamat() {
        return mMtAlamat;
    }

    public void setMtAlamat(String mtAlamat) {
        mMtAlamat = mtAlamat;
    }

    public String getMtMerk() {
        return mMtMerk;
    }

    public void setMtMerk(String mtMerk) {
        mMtMerk = mtMerk;
    }

    public String getMtNama() {
        return mMtNama;
    }

    public void setMtNama(String mtNama) {
        mMtNama = mtNama;
    }

    public String getMtTglpeminjaman() {
        return mMtTglpeminjaman;
    }

    public void setMtTglpeminjaman(String mtTglpeminjaman) {
        mMtTglpeminjaman = mtTglpeminjaman;
    }

    public String getMtTglpengembalian() {
        return mMtTglpengembalian;
    }

    public void setMtTglpengembalian(String mtTglpengembalian) {
        mMtTglpengembalian = mtTglpengembalian;
    }

}
