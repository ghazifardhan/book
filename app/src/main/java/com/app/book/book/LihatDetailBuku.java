package com.app.book.book;

/**
 * Created by Ghazi on 09/01/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LihatDetailBuku extends AppCompatActivity {
    // mendefinisikan
    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewAuthor;
    private TextView textViewYear;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail);

        // inisialisasi
        textViewId = (TextView) findViewById(R.id.txtIdBuku);
        textViewName = (TextView) findViewById(R.id.txtNamaBuku);
        textViewAuthor = (TextView) findViewById(R.id.txtAuthorBuku);
        textViewYear = (TextView) findViewById(R.id.txtYearBuku);

        // mengambil intent
        Intent intent = getIntent();

        // menampilkan hasil parsing dari intent
        textViewId.setText("ID: " + String.valueOf(intent.getIntExtra(MainActivity.ID_BUKU,0)));
        textViewName.setText("Nama: " + intent.getStringExtra(MainActivity.NAME));
        textViewAuthor.setText("Author: " + intent.getStringExtra(MainActivity.AUTHOR));
        textViewYear.setText("Year: " + intent.getStringExtra(MainActivity.YEAR));
    }
}
