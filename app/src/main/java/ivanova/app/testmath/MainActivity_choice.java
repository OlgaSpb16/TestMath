package ivanova.app.testmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity_choice extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    SeekBar seekBar;
    TextView seekBarText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choice);
        seekBarText= findViewById(R.id.seekBarText);
        seekBarText.setText("5");
        seekBar= findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
    }

    public void onClick(View view) {
        Intent intent=new Intent(MainActivity_choice.this, MainActivity.class);
        intent.putExtra("num_questions",seekBarText.getText());
        switch (view.getId()) {
            case R.id.LevelEasy:
                intent.putExtra("level", "easy");
                intent.putExtra("bound", 10);
                break;
            case R.id.LevelMiddle:
                intent.putExtra("level", "middle");
                intent.putExtra("bound", 50);
                break;
            case R.id.LevelHard:
                intent.putExtra("level", "hard");
                intent.putExtra("bound", 200);
                break;
        }
        startActivity(intent);
        }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seekBarText.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}