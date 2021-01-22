
package com.andra.myapplication.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TbMobil {

    @SerializedName("id_mobil")
    private String mIdMobil;
    @SerializedName("mb_alamat")
    private String mMbAlamat;
    @SerializedName("mb_merk")
    private String mMbMerk;
    @SerializedName("mb_nama")
    private String mMbNama;
    @SerializedName("mb_tglpeminjaman")
    private String mMbTglpeminjaman;
    @SerializedName("mb_tglpengembalian")
    private String mMbTglpengembalian;

    public String getIdMobil() {
        return mIdMobil;
    }

    public void setIdMobil(String idMobil) {
        mIdMobil = idMobil;
    }

    public String getMbAlamat() {
        return mMbAlamat;
    }

    public void setMbAlamat(String mbAlamat) {
        mMbAlamat = mbAlamat;
    }

    public String getMbMerk() {
        return mMbMerk;
    }

    public void setMbMerk(String mbMerk) {
        mMbMerk = mbMerk;
    }

    public String getMbNama() {
        return mMbNama;
    }

    public void setMbNama(String mbNama) {
        mMbNama = mbNama;
    }

    public String getMbTglpeminjaman() {
        return mMbTglpeminjaman;
    }

    public void setMbTglpeminjaman(String mbTglpeminjaman) {
        mMbTglpeminjaman = mbTglpeminjaman;
    }

    public String getMbTglpengembalian() {
        return mMbTglpengembalian;
    }

    public void setMbTglpengembalian(String mbTglpengembalian) {
        mMbTglpengembalian = mbTglpengembalian;
    }

}
