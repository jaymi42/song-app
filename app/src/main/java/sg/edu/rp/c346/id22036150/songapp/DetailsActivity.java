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
    DBHelper dbh = new DBHelper(DetailsActivity.this);

    @Override
    protected void onResume() {
        super.onResume();

        songs.clear();
        songs.addAll(dbh.getSongs());
        aaSongs.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intentSelected = getIntent();
        btnBack = findViewById(R.id.btnBack);
        btnStars = findViewById(R.id.btnStars);
        songs = new ArrayList<Song>();
        lvSongs = findViewById(R.id.lvSongs);

        aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(aaSongs);

        songs.clear();
        songs.addAll(dbh.getSongs());
        aaSongs.notifyDataSetChanged();

        for(int i = 0; i < songs.size(); i++){
            if(!(songs.get(i) == null)){
                songs.get(i).setID(i);
            }
        }


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
                songs.clear();
                songs.addAll(dbh.getSongs("5"));
                aaSongs.notifyDataSetChanged();
            }
        });
    }


}