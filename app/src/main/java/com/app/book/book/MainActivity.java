package com.app.book.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.book.book.models.Listbuku;
import com.app.book.book.models.Model;
import com.app.book.book.rest.RestApi;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    // root url dari website
    public static final String ROOT_URL = "http://128.199.226.96:8080/";

    // deklarasi variable untuk mengirim ke activity lain
    public static final String ID_BUKU = "book_id";
    public static final String NAME = "nama";
    public static final String AUTHOR = "author";
    public static final String YEAR = "year";

    // listview untuk menampilkan data
    private ListView listView;

    // variable books bertipe List dan List tersebut berdasarkan objek List
    private List<Listbuku> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inisialisasi Listview
        listView = (ListView) findViewById(R.id.listViewBuku);

        // memanggil method untuk mengambil data buku
        getBuku();

        // setting onItemClickListener untuk listview
        listView.setOnItemClickListener(this);
    }

    private void getBuku(){
        // Ketika Aplikas mengambil data kita akan melihat progres dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching data","Please Wait",false,false);
        // Loggin interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set Level Log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        RestApi service = retrofit.create(RestApi.class);

        Call<Model> call = service.loadListBook();
        call.enqueue(new Callback<Model>(){
            @Override
            public void onResponse(Call<Model> call, Response<Model> response){
                loading.dismiss();
                List<Listbuku> buku = response.body().getListbuku();

                books = buku;

                showList();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t){

            }
        });
    }

    private void showList() {
        // String array untuk menyimpan nama semua nama buku
        String[] items = new String[books.size()];

        for(int i = 0;i < books.size(); i++){
            items[i] = books.get(i).getName();
        }

        // Membuat Array Adapter for listview
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, items);

        // setting adapter untuk listview
        listView.setAdapter(adapter);
    }

    // method akan dieksekusi ketika listitem diklik
    @Override
    public void onItemClick(AdapterView<?> paret, View view, int position, long id){
        //membuat intent
        Intent intent = new Intent(this, LihatDetailBuku.class);
        // mengambil buku dari list
        Listbuku listbuku = books.get(position);
        //menambah detail buku pada intent
        intent.putExtra(ID_BUKU, listbuku.getId());
        intent.putExtra(NAME, listbuku.getName());
        intent.putExtra(AUTHOR, listbuku.getAuthor());
        intent.putExtra(YEAR, listbuku.getYear());

        // memulai activity lain untuk menampilkan detail buku
        startActivity(intent);
    }
}
