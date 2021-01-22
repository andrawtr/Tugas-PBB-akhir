package com.andra.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class motor1 extends AppCompatActivity{
    /*Deklarasi variable*/

    TextView txtdmotornama, txtdmotoralamat, txtdmotormerk, txtdmotortglpeminjaman, txtdmotortglpengembalian;
    private String id, nama, alamat, merk, tglpeminjaman, tglpengembalian;

    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;
    String ksp_mapan = "-6.8946396,110.6352056"; // koordinat Masjid Agung Demak
    /*Deklarasi variable*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motor1);
        uiInitial();
        showDetailData();
    }

    private void showDetailData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        merk = intent.getStringExtra("merk");
        tglpeminjaman = intent.getStringExtra("tglpeminjaman");
        tglpengembalian = intent.getStringExtra("tglpengembalian");

        txtdmotornama.setText(nama);
        txtdmotoralamat.setText(alamat);
        txtdmotormerk.setText(merk);
        txtdmotortglpeminjaman.setText(tglpeminjaman);
        txtdmotortglpengembalian.setText(tglpengembalian);
    }

    private void uiInitial() {
        txtdmotornama = (TextView) findViewById(R.id.txtdmotornama);
        txtdmotoralamat = (TextView) findViewById(R.id.txtdmotoralamat);
        txtdmotormerk = (TextView) findViewById(R.id.txtdmotormerk);
        txtdmotortglpeminjaman = (TextView) findViewById(R.id.txtdmotortglpeminjaman);
        txtdmotortglpengembalian = (TextView) findViewById(R.id.txtdmotortglpengembalian);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_motor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_direction_motor :
                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                gmmIntentUri = Uri.parse("google.navigation:q=" + ksp_mapan);

                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                mapIntent.setPackage(goolgeMap);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(motor1.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.nav_perbarui_motor:
                Intent intent = new Intent(motor1.this, motor2.class);
                intent.putExtra("id", id);
                intent.putExtra("nama", nama);
                intent.putExtra("alamat", alamat);
                intent.putExtra("merk", merk);
                intent.putExtra("tglpeminjaman", tglpeminjaman);
                intent.putExtra("tglpengembalian", tglpengembalian);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}