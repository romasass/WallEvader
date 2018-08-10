package roma.wallevader;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class topScore extends AppCompatActivity {

    private Button back;
    private EditText firstPlace;
    private EditText secPlace;
    private EditText thrdPlace;
    private EditText fourthPlace;
    private EditText fifthPlace;

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
    }
}
