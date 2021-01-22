package com.andra.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andra.myapplication.Model.MMotor;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class motor2 extends AppCompatActivity {

    private EditText txtedmotornama, txtedmotoralamat, txtedmotormerk, txtedmotortglpeminjaman, txtedmotortglpengembalian;
    private String id, nama, alamat, merk, tglpeminjaman, tglpengembalian;
    private BaseApi baseApi;
    private int mYear, mMonth, mDay;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motor2);
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

        txtedmotornama.setText(nama);
        txtedmotoralamat.setText(alamat);
        txtedmotormerk.setText(merk);
        txtedmotortglpeminjaman.setText(tglpeminjaman);
        txtedmotortglpengembalian.setText(tglpengembalian);
    }

    private void uiInitial() {
        txtedmotornama = (EditText) findViewById(R.id.txtedmotornama);
        txtedmotoralamat = (EditText) findViewById(R.id.txtedmotoralamat);
        txtedmotormerk = (EditText) findViewById(R.id.txtedmotormerk);
        txtedmotortglpeminjaman = (EditText) findViewById(R.id.txtedmotortglpeminjaman);
        txtedmotortglpengembalian = (EditText) findViewById(R.id.txtedmotortglpengembalian);
    }

    public void perbaruiDataMotor(View view) {
        nama = txtedmotornama.getText().toString();
        alamat = txtedmotoralamat.getText().toString();
        merk = txtedmotormerk.getText().toString();
        tglpeminjaman = txtedmotortglpeminjaman.getText().toString();
        tglpengembalian = txtedmotortglpengembalian.getText().toString();

        simpanPembaruan(id, nama, alamat, merk, tglpeminjaman, tglpengembalian);
    }

    private void simpanPembaruan(final String id, final String nama, final String alamat, final String merk, final String tglpeminjaman, final String tglpengembalian) {
        Call<MMotor> perbaruiData = baseApi.updateDataMotor(id, nama, alamat, merk, tglpeminjaman, tglpengembalian);
        perbaruiData.enqueue(new Callback<MMotor>() {
            @Override
            public void onResponse(Call<MMotor> call, Response<MMotor> response) {
                if (response.body().getSukses() == true){
                    Toast.makeText(motor2.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(motor2.this, motor1.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nama", nama);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("merk", merk);
                    intent.putExtra("tglpeminjaman", tglpeminjaman);
                    intent.putExtra("tglpengembalian", tglpengembalian);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(motor2.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MMotor> call, Throwable t) {
                Toast.makeText(motor2.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void tglPengembalianMotor(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtedmotortglpengembalian.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tglPeminjamanMotor(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtedmotortglpeminjaman.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
