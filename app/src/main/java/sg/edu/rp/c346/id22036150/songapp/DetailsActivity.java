package sg.edu.rp.c346.id22036150.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnBack;
    ArrayList<Song> songs;
    ArrayList<String> alSongs;
    ArrayAdapter<String> aaSongs;
    DBHelper db = new DBHelper(DetailsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intentSelected = getIntent();
        btnBack = findViewById(R.id.btnBack);
        alSongs = intentSelected.getStringArrayListExtra("songs");
        lvSongs = findViewById(R.id.lvSongs);

        aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alSongs);
        lvSongs.setAdapter(aaSongs);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}