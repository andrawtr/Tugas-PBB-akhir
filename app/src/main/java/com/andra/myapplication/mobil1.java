package com.andra.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by USER on 03/11/2017.
 */

public class mobil1 extends AppCompatActivity {
    /*Deklarasi variable*/
    TextView txtdmobilnama, txtdmobilalamat, txtdmobilmerk, txtdmobiltglpeminjaman, txtdmobiltglpengembalian;
    private String id, nama, alamat, merk, tglpeminjaman, tglpengembalian;

    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;
    String ksp_agheng = "-6.884297, 110.658321"; // koordinat Jln.satrio wibowo 1 no 50
    /*Deklarasi variable*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobil1);
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

        txtdmobilnama.setText(nama);
        txtdmobilalamat.setText(alamat);
        txtdmobilmerk.setText(merk);
        txtdmobiltglpeminjaman.setText(tglpeminjaman);
        txtdmobiltglpengembalian.setText(tglpengembalian);
    }

    private void uiInitial() {
        txtdmobilnama = (TextView) findViewById(R.id.txtdmobilnama);
        txtdmobilalamat = (TextView) findViewById(R.id.txtdmobilalamat);
        txtdmobilmerk = (TextView) findViewById(R.id.txtdmobilmerk);
        txtdmobiltglpeminjaman = (TextView) findViewById(R.id.txtdmobiltglpeminjaman);
        txtdmobiltglpengembalian = (TextView) findViewById(R.id.txtdmobiltglpengembalian);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_mobil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_direction_mobil :
                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                gmmIntentUri = Uri.parse("google.navigation:q=" + ksp_agheng);

                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                mapIntent.setPackage(goolgeMap);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(mobil1.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.nav_perbarui_mobil:
                Intent intent = new Intent(mobil1.this, mobil2.class);
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
