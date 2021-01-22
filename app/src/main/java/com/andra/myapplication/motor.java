package com.andra.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andra.myapplication.Model.MMotor;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class motor extends AppCompatActivity {

    private EditText txtmotornama, txtmotoralamat, txtmotormerk, txtmotortglpeminjaman, txtmotortglpengembalian;
    private BaseApi baseApi;
    private int mYear, mMonth, mDay;
    private Calendar c;
    private String id_motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motor);
        baseApi = BaseApi.BaseApiUtama.buat();
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        uiInitial();
    }

    private void uiInitial() {
        txtmotornama = (EditText) findViewById(R.id.txtmotornama);
        txtmotoralamat = (EditText) findViewById(R.id.txtmotoralamat);
        txtmotormerk = (EditText) findViewById(R.id.txtmotormerk);
        txtmotortglpeminjaman = (EditText) findViewById(R.id.txtmotortglpeminjaman);
        txtmotortglpengembalian = (EditText) findViewById(R.id.txtmotortglpengembalian);
    }

    public void konfirmasiDataMotor(View view) {
        id_motor = String.valueOf((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE));
        String nama = txtmotornama.getText().toString();
        String alamat = txtmotoralamat.getText().toString();
        String merk = txtmotormerk.getText().toString();
        String tglpeminjaman = txtmotortglpeminjaman.getText().toString();
        String tglpengembalian = txtmotortglpengembalian.getText().toString();

        simpanDataMotor(id_motor, nama, alamat, merk, tglpeminjaman, tglpengembalian);
    }


    public void tglPeminjamanMotor(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtmotortglpeminjaman.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tglPengembalianMotor(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtmotortglpengembalian.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void simpanDataMotor(final String id, final String nama, final String alamat, final String merk, final String tglpeminjaman, final String tglpengembalian) {
        Call<MMotor> tambahData = baseApi.insertDataMotor(id, nama, alamat, merk, tglpeminjaman, tglpengembalian);
        tambahData.enqueue(new Callback<MMotor>() {
            @Override
            public void onResponse(Call<MMotor> call, Response<MMotor> response) {
                if (response.body().getSukses() == true) {
                    Toast.makeText(motor.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(motor.this, motor1.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nama", nama);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("merk", merk);
                    intent.putExtra("tglpeminjaman", tglpeminjaman);
                    intent.putExtra("tglpengembalian", tglpengembalian);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(motor.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MMotor> call, Throwable t) {
                Toast.makeText(motor.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_motor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_call_motor:
                String no = "082138398329";
                Intent In = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + no));
                startActivity(In);
                break;
            case R.id.nav_lokasi_motor :
                startActivity(new Intent(motor.this, Maps.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}