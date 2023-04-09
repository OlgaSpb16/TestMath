package ivanova.app.testmath;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import java.util.Random;
import java.util.stream.IntStream;

import android.util.Log;

public class MainActivity extends AppCompatActivity {
    TextView txtquestion;
    String [] math_action  = {"+","-","*","/"}; //Математические действия
    int[] math_number   = IntStream.range(0,200).toArray(); //Массив целых чисел для вычисления

    Integer questionNow;  //текущий ответ на вопрос
    String level; // уровень сложности
    Integer bound_array; // граница массива
    Integer numQuestions; // кол=во вопросов
    Integer true_answers=0; // кол-во правильных ответов по умолчанию
    Integer all_Questions; // неизменяемая переменная хранящая в себе кол-во вопросов
    Integer i=1; //номер вопроса
    @Override

    //действия происходящие при создании активности
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//создаётся автоматически

        txtquestion = findViewById(R.id.txtquestion);
        txtquestion.setText("Вопрос "+i);//первыый вопрос=1 , последующие вопросы +1
        Bundle EXTRA = getIntent().getExtras();//Bundle вытаскивает extra

        level = EXTRA.get("level").toString();//присваеваем уровень сложности

        bound_array = Integer.parseInt(EXTRA.get("bound").toString());///установка границы массива

        all_Questions=Integer.parseInt(EXTRA.get("num_questions").toString());//присваивание кол во вопрос в зависимости от движка
        numQuestions=all_Questions-1;//от выбраного кол ва вопрос , вычитать 1 после каждого вопроса



        int random_action = new Random().nextInt(4);//генерация мат. действия
        Integer random_firstNum = new Random().nextInt(bound_array);
        Integer random_secondNum = new Random().nextInt(bound_array);
        TextView text = (TextView) findViewById(R.id.textViewQuestion);

//Перевод пример в строку чтобы машина не считала его
        text.setText(random_firstNum.toString()+math_action[random_action].toString()+random_secondNum.toString());

        if (math_action[random_action]=="+") {
            questionNow = math_number[random_firstNum] + math_number[random_secondNum];
        } else if (math_action[random_action]=="-") {
            if (random_firstNum>=random_secondNum) {
                questionNow = math_number[random_firstNum] - math_number[random_secondNum];
            } else {
                questionNow = math_number[random_secondNum] - math_number[random_firstNum];
                text.setText(random_secondNum.toString()+math_action[random_action].toString()+random_firstNum.toString());

            }
        } else if (math_action[random_action]=="*") {
            if (level=="hard" && (random_firstNum>30 || random_secondNum>30)){
                random_firstNum=new Random().nextInt(10);

            }
            questionNow = math_number[random_firstNum] * math_number[random_secondNum];
        }
        else {
            if (level=="hard" && (random_firstNum>30 || random_secondNum>30)){
                random_secondNum=new Random().nextInt(10);
            }
            questionNow=math_number[random_firstNum];
            random_firstNum=random_firstNum*random_secondNum;
            text.setText(random_firstNum.toString()+math_action[random_action].toString()+random_secondNum.toString());

        }

    }

    public void onClick(View view) {
        TextView textAnswer = (TextView) findViewById(R.id.editTextNumber);


        if (view.getId() == R.id.button1) {
            textAnswer.setText(textAnswer.getText() + "1");
        } else if (view.getId() == R.id.button2) {
            textAnswer.setText(textAnswer.getText() + "2");
        } else if (view.getId() == R.id.button3) {
            textAnswer.setText(textAnswer.getText() + "3");
        } else if (view.getId() == R.id.button4) {
            textAnswer.setText(textAnswer.getText() + "4");
        } else if (view.getId() == R.id.button5) {
            textAnswer.setText(textAnswer.getText() + "5");
        } else if (view.getId() == R.id.button6) {
            textAnswer.setText(textAnswer.getText() + "6");
        } else if (view.getId() == R.id.button7) {
            textAnswer.setText(textAnswer.getText() + "7");
        } else if (view.getId() == R.id.button8) {
            textAnswer.setText(textAnswer.getText() + "8");
        } else if (view.getId() == R.id.button9) {
            textAnswer.setText(textAnswer.getText() + "9");
        } else if (view.getId() == R.id.button0) {
            textAnswer.setText(textAnswer.getText() + "0");
        } else if (view.getId() == R.id.buttonDelete) {
            if (textAnswer.getText().length()!=0) {
                textAnswer.setText(textAnswer.getText().toString().subSequence(0, textAnswer.getText().toString().length()-1));
            } //уменьшаем длину текста на -1 , возвращает последовательность символов из строки, начиная и заканчивая указанными индексами.
        }


    }

    public void onClickAnswer(View view) throws InterruptedException {
        Runnable runnable = new Runnable() { //New Thread
            @Override
            public void run() {
                TextView textAnswer = (TextView) findViewById(R.id.editTextNumber);
                TextView text = (TextView) findViewById(R.id.textViewQuestion);
                // Сбрасываем задний фон надписи
                textAnswer.setBackgroundColor(0);
                //Сбрасываем ответ в текстбоксе
                textAnswer.setText("");
                i=i+1;
                txtquestion.setText("Вопрос "+i);
                numQuestions=numQuestions-1;
                int random_action = new Random().nextInt(4);
                Integer random_firstNum = new Random().nextInt(bound_array);
                Integer random_secondNum = new Random().nextInt(bound_array);

                text.setText(random_firstNum.toString()+math_action[random_action].toString()+random_secondNum.toString());

                if (math_action[random_action]=="+") {
                    questionNow = math_number[random_firstNum] + math_number[random_secondNum];
                } else if (math_action[random_action]=="-") {
                    if (random_firstNum>=random_secondNum) {
                        questionNow = math_number[random_firstNum] - math_number[random_secondNum];
                    } else {
                        questionNow = math_number[random_secondNum] - math_number[random_firstNum];
                        text.setText(random_secondNum.toString()+math_action[random_action].toString()+random_firstNum.toString());
                    }
                }
                else if (math_action[random_action]=="*") {
                    if (level=="hard" && (random_firstNum>30 || random_secondNum>30)){
                        random_firstNum=new Random().nextInt(10);

                    }
                    questionNow = math_number[random_firstNum] * math_number[random_secondNum];
                }
                else {
                    if (level=="hard" && (random_firstNum>30 || random_secondNum>30)){
                        random_secondNum=new Random().nextInt(10);
                    }
                    questionNow=math_number[random_firstNum];
                    random_firstNum=random_firstNum*random_secondNum;
                    text.setText(random_firstNum.toString()+math_action[random_action].toString()+random_secondNum.toString());

                }


            }
        };

        TextView textAnswer = (TextView) findViewById(R.id.editTextNumber);
        Integer intAnswer=new Integer(textAnswer.getText().toString());

        if (questionNow-intAnswer==0) {//проверка корректности ответа
            true_answers=true_answers+1; //увеличиваем счётчик правильных ответов
            textAnswer.setText("ВЕРНО!!!");
            textAnswer.setBackgroundColor(Color.GREEN);
        }
        else {
            textAnswer.setText("ОШИБКА!!!");
            textAnswer.setBackgroundColor(Color.RED);
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, 750); // delayed by 3 seconds

        if (numQuestions==0) {
            Intent intent=new Intent(MainActivity.this, MainFinishActivity.class);
            intent.putExtra("num_questions",all_Questions);
            intent.putExtra("true_answers",true_answers);
            startActivity(intent);
        }

    }

}