package ivanova.app.testmath;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainFinishActivity extends AppCompatActivity {
    TextView txtResult;
    String numQuestions;
    String true_answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_finish);
        Bundle EXTRA = getIntent().getExtras();

        numQuestions=EXTRA.get("num_questions").toString();
        true_answers=EXTRA.get("true_answers").toString();

        txtResult= findViewById(R.id.txtResult);
        txtResult.setText(true_answers +"/"+numQuestions);


        Runnable runnable = new Runnable() { //New Thread
            @Override
            public void run() {
                Intent intent=new Intent(MainFinishActivity.this, MainActivity_choice.class);
                startActivity(intent);
            }
        };

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, 1500); // delayed by 3 seconds

    }
}