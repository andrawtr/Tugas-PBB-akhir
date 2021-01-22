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

import com.andra.myapplication.Model.MMobil;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class mobil extends AppCompatActivity{

     private EditText txtmobilnama, txtmobilalamat, txtmobilmerk, txtmobiltglpeminjaman, txtmobiltglpengembalian;
     private BaseApi baseApi;
     private int mYear, mMonth, mDay;
     private Calendar c;
     private String id_mobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobil);
        baseApi = BaseApi.BaseApiUtama.buat();
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        uiInitial();
    }

     private void uiInitial() {
         txtmobilnama = (EditText) findViewById(R.id.txtmobilnama);
         txtmobilalamat = (EditText) findViewById(R.id.txtmobilalamat);
         txtmobilmerk = (EditText) findViewById(R.id.txtmobilmerk);
         txtmobiltglpeminjaman = (EditText) findViewById(R.id.txtmobiltglpeminjaman);
         txtmobiltglpengembalian = (EditText) findViewById(R.id.txtmobiltglpengembalian);
     }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mobil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_call_mobil :
                String no = "082138398329";
                Intent In=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+no));
                startActivity(In);
                break;
            case R.id.nav_lokasi_mobil :
                startActivity(new Intent(mobil.this, Maps.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

     public void tglPengembalianMobil(View view) {
         DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                 new DatePickerDialog.OnDateSetListener() {

                     @Override
                     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                         txtmobiltglpengembalian.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                     }
                 }, mYear, mMonth, mDay);
         datePickerDialog.show();
     }

     public void tglPeminjamanMotor(View view) {
         DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                 new DatePickerDialog.OnDateSetListener() {

                     @Override
                     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                         txtmobiltglpeminjaman.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                     }
                 }, mYear, mMonth, mDay);
         datePickerDialog.show();
     }

     public void konfirmasiDataMobil(View view) {
         id_mobil = String.valueOf((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE));
         String nama = txtmobilnama.getText().toString();
         String alamat = txtmobilalamat.getText().toString();
         String merk = txtmobilmerk.getText().toString();
         String tglpeminjaman = txtmobiltglpeminjaman.getText().toString();
         String tglpengembalian = txtmobiltglpengembalian.getText().toString();

         simpanDataMobil(id_mobil, nama, alamat, merk, tglpeminjaman, tglpengembalian);
     }

     private void simpanDataMobil(final String id_mobil, final String nama, final String alamat, final String merk, final String tglpeminjaman, final String tglpengembalian) {
         Call<MMobil> tambahData = baseApi.insertDataMobil(id_mobil, nama, alamat, merk, tglpeminjaman, tglpengembalian);
         tambahData.enqueue(new Callback<MMobil>() {
             @Override
             public void onResponse(Call<MMobil> call, Response<MMobil> response) {
                 if (response.body().getSukses() == true) {
                     Toast.makeText(mobil.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                     Intent intent = new Intent(mobil.this, mobil1.class);
                     intent.putExtra("id", id_mobil);
                     intent.putExtra("nama", nama);
                     intent.putExtra("alamat", alamat);
                     intent.putExtra("merk", merk);
                     intent.putExtra("tglpeminjaman", tglpeminjaman);
                     intent.putExtra("tglpengembalian", tglpengembalian);
                     startActivity(intent);
                     finish();
                 } else {
                     Toast.makeText(mobil.this, response.body().getPesan(), Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<MMobil> call, Throwable t) {
                 Toast.makeText(mobil.this, t.getMessage(), Toast.LENGTH_LONG).show();
             }
         });
     }
 }
