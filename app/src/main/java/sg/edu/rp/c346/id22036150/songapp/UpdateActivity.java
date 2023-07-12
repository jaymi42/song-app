package sg.edu.rp.c346.id22036150.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStar;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    ArrayList<String> songs;
    ArrayList<Song> alsong;

    DBHelper db = new DBHelper(UpdateActivity.this);
    Song data;
    int rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitles);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYears);
        rgStar = findViewById(R.id.rgStar);
        rb1 = findViewById(R.id.rbtn1);
        rb2 = findViewById(R.id.rbtn2);
        rb3 = findViewById(R.id.rbtn3);
        rb4 = findViewById(R.id.rbtn4);
        rb5 = findViewById(R.id.rbtn5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intentSelected = getIntent();
        songs = intentSelected.getStringArrayListExtra("songs");
        data = (Song) intentSelected.getSerializableExtra("data");


        etID.setText("" + data.getId());
        etID.setEnabled(false);
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText("" + data.getYear());
        rating = data.getStar();
        rgStar.check(data.getStar());

        /**if(rating == 1){
            rb1.setChecked(true);
        } else if(rating == 2){
            rb2.setChecked(true);
        }else if(rating == 3){
            rb3.setChecked(true);
        }else if(rating == 4){
            rb4.setChecked(true);
        }else if(rating == 5){
            rb5.setChecked(true);
        }**/


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateActivity.this);

                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                data.setTitle(title);
                data.setSinger(singer);
                data.setYear(year);

                int rating = 0;

                int checked = rgStar.getCheckedRadioButtonId();
                if (checked == R.id.rbtn1){
                    rating = 1;
                } else if (checked == R.id.rbtn2){
                    rating = 2;
                }else if (checked == R.id.rbtn3){
                    rating = 3;
                }else if (checked == R.id.rbtn4){
                    rating = 4;
                }else if (checked == R.id.rbtn5){
                    rating = 5;
                }


                data.setStars(rating);

                dbh.updateSong(data);
                dbh.close();

                Toast.makeText(UpdateActivity.this, "Song updated successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });/**btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateActivity.this);

                int stars = 0;
                if (rb1.isChecked()){
                    stars = 1;
                } else if (rb2.isChecked()){
                    stars = 2;
                }else if (rb3.isChecked()){
                    stars = 3;
                }else if (rb4.isChecked()){
                    stars = 4;
                }else if (rb5.isChecked()){
                    stars = 5;
                }

                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                data.setTitle(title);
                data.setSinger(singer);
                data.setYear(year);
                data.setStars(stars);

                dbh.updateSong(data);
                dbh.close();

                Intent intent = new Intent(UpdateActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });**/

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateActivity.this);
                dbh.deleteSong(data.getId());
                Intent intent = new Intent(UpdateActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });




    }

}