package roma.wallevader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class topScore extends AppCompatActivity {

    private Button back;
    private EditText firstPlace;
    private EditText secPlace;
    private EditText thrdPlace;
    private EditText fourthPlace;
    private EditText fifthPlace;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String[] scores1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);
        firstPlace = findViewById(R.id.editText1);
        secPlace = findViewById(R.id.editText2);
        thrdPlace = findViewById(R.id.editText3);
        fourthPlace = findViewById(R.id.editText4);
        fifthPlace = findViewById(R.id.editText5);
        back = findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(topScore.this, MainActivity.class));
            }
        });
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Top 5 places");

        //writeToDatabase();
        readFromDatabase();
        //updateScoreBoard();
        //checkNewScore(85,scores1);

    }

    public boolean checkNewScore(int newScore,String[] scores) {

        if (newScore > Integer.valueOf(scores[4])) {
            if (newScore > Integer.valueOf(scores[3])) {
                if (newScore > Integer.valueOf(scores[2])) {
                    if (newScore > Integer.valueOf(scores[1])) {
                        if (newScore > Integer.valueOf(scores[0])) {
                            scores[4] = scores[3];
                            scores[3] = scores[2];
                            scores[2] = scores[1];
                            scores[1] = scores[0];
                            scores[0] = String.valueOf(newScore);
                        } else {
                            scores[4] = scores[3];
                            scores[3] = scores[2];
                            scores[2] = scores[1];
                            scores[1] = String.valueOf(newScore);
                        }
                    } else {
                        scores[4] = scores[3];
                        scores[3] = scores[2];
                        scores[2] = String.valueOf(newScore);
                    }
                } else {
                    scores[4] = scores[3];
                    scores[3] = String.valueOf(newScore);
                }
            } else {
                scores[4] = String.valueOf(newScore);
            }
            //writeToDatabase();
            return true;
        } else return false;
    }

    public void updateScoreBoard() {
        firstPlace.setText(scores1[0]);
        secPlace.setText(scores1[1]);
        thrdPlace.setText(scores1[2]);
        fourthPlace.setText(scores1[3]);
        fifthPlace.setText(scores1[4]);
    }

    public void writeToDatabase(String[] scores) {
        // Write a message to the database
        String tmp = String.join(":", scores);
        myRef.setValue(tmp);
        //myRef.setValue("100:90:80:70:60");
    }

    public void readFromDatabase() {

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("logger", "Value is: " + value);
                String[] scores = value.split(":");
                scores1 = scores;
                updateScoreBoard();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("logger", "Failed to read value.", error.toException());
            }
        });

    }
}
