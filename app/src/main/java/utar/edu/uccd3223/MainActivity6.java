package utar.edu.uccd3223;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {

    private View[] views;
    private int highlightedViewIndex;
    private int viewTouch4;
    private TextView touchPoints;
    int numOfViews = 36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        // Create the array
        views = new View[numOfViews];
        // Use a loop to populate the array with views
        for (int i = 0; i < numOfViews; i++) {
            int viewId = getResources().getIdentifier("view" + (i+1), "id", getPackageName());
            views[i] = findViewById(viewId);
        }

        highlightedViewIndex = -1;
        viewTouch4 = 0;
        touchPoints = findViewById(R.id.Points);

        new CountDownTimer(5000, 1000) {
            TextView tv = findViewById(R.id.timer);
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText("Time: " + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                tv.setText("Finished!");
                alertDialog();
            }
        }.start();


        randomHighlightView();

        for(View view: views){
            view.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(v == views[highlightedViewIndex]) {
                        viewTouch4++;
                        touchPoints.setText("Points: " + viewTouch4);
                        randomHighlightView();
                    }
                    return true;
                }
            });
        }
    }

    private void randomHighlightView() {
        if(highlightedViewIndex != -1){
            views[highlightedViewIndex].setBackgroundResource(R.color.white);
        }

        int randomIndex = highlightedViewIndex;

        while(randomIndex == highlightedViewIndex){
            randomIndex = (int) (Math.random()*views.length);
        }
        highlightedViewIndex = randomIndex;
        views[highlightedViewIndex].setBackgroundResource(R.color.purple_200);
    }

    private void alertDialog() {
        // create an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set the title and message of the dialog
        builder.setTitle("Next Level");
        builder.setMessage("Do you want to go to the homepage or check the score?");

        // add buttons to the dialog and set their click listeners
        builder.setPositiveButton("Homepage", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // navigate to homepage
                Intent intent = new Intent();
                intent.setClass(MainActivity6.this, MainActivity.class);
                intent.putExtra("Level", 5);
                intent.putExtra("score_level5", viewTouch4);
                startActivity(intent);
                finish(); // close current activity
            }
        });

        builder.setNegativeButton("Score", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // navigate to next page
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity6.this, Score.class);
                intent1.putExtra("score_level5", viewTouch4);
                startActivity(intent1);
                finish(); // close current activity
            }
        });

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false); //prevent users dismissed the prompt message
        dialog.show();

    }
}
