package utar.edu.uccd3223;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // get the total score
        Intent intent1 = getIntent();
        int viewTouch = intent1.getIntExtra("score_level1", 0);
        int viewTouch1 = intent1.getIntExtra("score_level2", 0);
        int viewTouch2 = intent1.getIntExtra("score_level3", 0);
        int viewTouch3 = intent1.getIntExtra("score_level4", 0);
        int viewTouch4 = intent1.getIntExtra("score_level5", 0);

        int totalScore = viewTouch + viewTouch1 + viewTouch2 + viewTouch3 + viewTouch4;

        TextView score = findViewById(R.id.score);
        score.setText("Total Score: " + totalScore);
    }
}