package sg.edu.rp.c346.id22036150.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    DBHelper db = new DBHelper(MainActivity.this);



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




        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int star = 0;

                int selectedStar = rgStars.getCheckedRadioButtonId();
                if (selectedStar == R.id.rb1){
                    star = 1;
                } else if (selectedStar == R.id.rb2){
                    star = 2;
                } else if (selectedStar == R.id.rb3){
                    star = 3;
                } else if (selectedStar == R.id.rb4){
                    star = 4;
                } else if (selectedStar == R.id.rb5){
                    star = 5;
                }

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertSong(title, singer, year, star);
                etTitle.getText().clear();
                etSingers.getText().clear();
                etYear.getText().clear();
                rgStars.clearCheck();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

    }


}