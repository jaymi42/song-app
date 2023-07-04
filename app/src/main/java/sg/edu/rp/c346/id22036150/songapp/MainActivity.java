package sg.edu.rp.c346.id22036150.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    ArrayList<String> alSongs;
    ArrayList<Song> songs;
    DBHelper db = new DBHelper(MainActivity.this);
    int stars;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);

        alSongs = new ArrayList<>();

        int btnSelected = rgStars.getCheckedRadioButtonId();
        if(btnSelected == R.id.rb1){
            stars = 1;
        } else if(btnSelected == R.id.rb2){
            stars = 2;
        }else if(btnSelected == R.id.rb3){
            stars = 3;
        }else if(btnSelected == R.id.rb4){
            stars = 4;
        }else if(btnSelected == R.id.rb5){
            stars = 5;
        }


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                // Insert a task
                db.insertSong(etTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()), stars);

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> data = db.getSongContent();
                songs = db.getSongs();
                db.close();

                for(int i = 0; i< data.size(); i++){
                    String lvTxt = songs.get(i).toString();
                    alSongs.add(lvTxt);
                }
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putStringArrayListExtra("songs",alSongs);
                startActivity(intent);
            }
        });

    }
}