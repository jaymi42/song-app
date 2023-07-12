package sg.edu.rp.c346.id22036150.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnBack, btnStars;
    ArrayList<Song> songs;
    ArrayAdapter<Song> aaSongs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intentSelected = getIntent();
        btnBack = findViewById(R.id.btnBack);
        btnStars = findViewById(R.id.btnStars);
        lvSongs = findViewById(R.id.lvSongs);

        songs = new ArrayList<Song>();
        aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(aaSongs);


        DBHelper dbh = new DBHelper(DetailsActivity.this);
        songs.clear();
        songs.addAll(dbh.getSong());
        aaSongs.notifyDataSetChanged();

        ArrayList<String> songData = dbh.getSongContent();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = songs.get(position);
                Intent intent = new Intent(DetailsActivity.this, UpdateActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(DetailsActivity.this);


                ArrayList<Song> songsFive = new ArrayList<>();

                for(Song song : songs){
                    if(song.getStar() == 5){
                        songsFive.add(song);
                    }

                    ArrayAdapter<Song> adapterFive = new ArrayAdapter<>(DetailsActivity.this, android.R.layout.simple_list_item_1, songsFive);
                    lvSongs.setAdapter(adapterFive);
                }
            }
        });
    }


}