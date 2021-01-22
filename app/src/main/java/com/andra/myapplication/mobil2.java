package com.andra.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andra.myapplication.Model.MMobil;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mobil2 extends AppCompatActivity {

    private EditText txtedmobilnama, txtedmobilalamat, txtedmobilmerk, txtedmobiltglpeminjaman, txtedmobiltglpengembalian;
    private String id, nama, alamat, merk, tglpeminjaman, tglpengembalian;
    private BaseApi baseApi;
    private int mYear, mMonth, mDay;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobil2);
        baseApi = BaseApi.BaseApiUtama.buat();
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        uiInitial();
        showDataInForm();
    }

    private void showDataInForm() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        merk = intent.getStringExtra("merk");
        tglpeminjaman = intent.getStringExtra("tglpeminjaman");
        tglpengembalian = intent.getStringExtra("tglpengembalian");

        txtedmobilnama.setText(nama);
        txtedmobilalamat.setText(alamat);
        txtedmobilmerk.setText(merk);
        txtedmobiltglpeminjaman.setText(tglpeminjaman);
        txtedmobiltglpengembalian.setText(tglpengembalian);
    }

    private void uiInitial() {
        txtedmobilnama = (EditText) findViewById(R.id.txtedmobilnama);
        txtedmobilalamat = (EditText) findViewById(R.id.txtedmobilalamat);
        txtedmobilmerk = (EditText) findViewById(R.id.txtedmobilmerk);
        txtedmobiltglpeminjaman = (EditText) findViewById(R.id.txtedmobiltglpeminjaman);
        txtedmobiltglpengembalian = (EditText) findViewById(R.id.txtedmobiltglpengembalian);
    }

    public void tglPeminjamanMobil(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtedmobiltglpeminjaman.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tglPengembalianMobil(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtedmobiltglpengembalian.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void perbaruiDataMobil(View view) {
        nama = txtedmobilnama.getText().toString();
        alamat = txtedmobilalamat.getText().toString();
        merk = txtedmobilmerk.getText().toString();
        tglpeminjaman = txtedmobiltglpeminjaman.getText().toString();
        tglpengembalian = txtedmobiltglpengembalian.getText().toString();

        simpanPembaruan(id, nama, alamat, merk, tglpeminjaman, tglpengembalian);
    }

    private void simpanPembaruan(final String id, final String nama, final String alamat, final String merk, final String tglpeminjaman, final String tglpengembalian) {
        Call<MMobil> perbaruiData = baseApi.updateDataMobil(id, nama, alamat, merk, tglpeminjaman, tglpengembalian);
        perbaruiData.enqueue(new Callback<MMobil>() {
            @Override
            public void onResponse(Call<MMobil> call, Response<MMobil> response) {
                if (response.body().getSukses() == true){
                    Toast.makeText(mobil2.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(mobil2.this, mobil1.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nama", nama);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("merk", merk);
                    intent.putExtra("tglpeminjaman", tglpeminjaman);
                    intent.putExtra("tglpengembalian", tglpengembalian);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mobil2.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MMobil> call, Throwable t) {
                Toast.makeText(mobil2.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
