package utar.edu.uccd3223;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private View[] views;
    private int highlightedViewIndex;
    private int viewTouch2;
    private TextView touchPoints;
    int numOfViews = 16;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Create the array
        views = new View[numOfViews];
        // Use a loop to populate the array with views
        for (int i = 0; i < numOfViews; i++) {
            int viewId = getResources().getIdentifier("view" + (i+1), "id", getPackageName());
            views[i] = findViewById(viewId);
        }

        //change to for loop
//        views = new View[]{
//                findViewById(R.id.view1),
//                findViewById(R.id.view2),
//                findViewById(R.id.view3),
//                findViewById(R.id.view4),
//                findViewById(R.id.view5),
//                findViewById(R.id.view6),
//                findViewById(R.id.view7),
//                findViewById(R.id.view8),
//                findViewById(R.id.view9)
//        };

        highlightedViewIndex = -1;
        viewTouch2 = 0;
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
                        viewTouch2++;
                        touchPoints.setText("Points: " + viewTouch2);
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
        builder.setMessage("Do you want to go to the homepage or continue the next level?");

        // add buttons to the dialog and set their click listeners
        builder.setPositiveButton("Homepage", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // navigate to homepage
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this, MainActivity.class);
                intent.putExtra("Level", 3);
                intent.putExtra("score_level3", viewTouch2);
                startActivity(intent);
                finish(); // close current activity
            }
        });

        builder.setNegativeButton("Next Level", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // navigate to next page
                Intent intent = new Intent();
                Intent intent1 = new Intent(MainActivity4.this, Score.class);
                intent.setClass(MainActivity4.this, MainActivity5.class);
                intent1.putExtra("score_level3", viewTouch2);
                startActivity(intent1);
                startActivity(intent);
                finish(); // close current activity
            }
        });

        // create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false); //prevent users dismissed the prompt message
        dialog.show();

    }
}
