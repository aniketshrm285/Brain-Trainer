package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView time ;
    TextView question ;
    TextView ratio ;
    TextView score;
    LinearLayout layout ;
    int timeLeft=0;
    int upperBound;
    int leftOperand;
    int rightOperand;
    int answer;
    int answerIndex;
    int totalChild;
    Random rand=new Random();
    int totalCorrectAns;
    int totalQuestionsAnswered ;
    CountDownTimer myCountDownTimer;

    public void goFunc(View view){
        if(timeLeft!=0){
            myCountDownTimer.cancel();
        }
        Button goButtton = (Button) findViewById(R.id.goButton);
        final Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setText("RESTART!");
        playAgainButton.setVisibility(View.VISIBLE);
        goButtton.setVisibility(View.INVISIBLE);
        score=(TextView) findViewById(R.id.score);
        score.setVisibility(View.INVISIBLE);
        Log.i("Button tapped ","GO" );

        upperBound=50;
        timeLeft=60;


        leftOperand = 1+ rand.nextInt(upperBound);
        rightOperand = 1+ rand.nextInt(upperBound);
        Log.i(Integer.toString(leftOperand),Integer.toString(rightOperand));

        answer = leftOperand + rightOperand ;
        totalChild = 4;
        answerIndex = rand.nextInt(totalChild);


        totalCorrectAns=0;
        totalQuestionsAnswered = 0;
        time = (TextView) findViewById(R.id.timeLeft);
        time.setText(String.valueOf(timeLeft)+"s");

        question = (TextView) findViewById(R.id.question);
        question.setText(String.valueOf(leftOperand)+" + " + String.valueOf(rightOperand));

        ratio = (TextView) findViewById(R.id.ratio);
        ratio.setText(String.valueOf(totalCorrectAns)+"/"+String.valueOf(totalQuestionsAnswered));

        layout = (LinearLayout) findViewById(R.id.linearLayout);
        ArrayList<Integer> intAns = new ArrayList<Integer>();

        for(int i=0;i<4;i++){
            Log.i("indfo",String.valueOf(i));
            if(i==answerIndex){
                intAns.add(answer);
            }
            int wrongAns= rand.nextInt(101);
            while(wrongAns==answer){
                wrongAns= rand.nextInt(101);
            }
            intAns.add(wrongAns);
        }

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button1.setText(String.valueOf(intAns.get(0)));
        button2.setText(String.valueOf(intAns.get(1)));
        button3.setText(String.valueOf(intAns.get(2)));
        button4.setText(String.valueOf(intAns.get(3)));

        layout.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);

        question.setVisibility(View.VISIBLE);

        ratio.setVisibility(View.VISIBLE);

        myCountDownTimer = new CountDownTimer(timeLeft*1000,1000){
                public void onTick(long millisecondsUntilDone){
                    timeLeft= (int) millisecondsUntilDone/1000;
                    time.setText(String.valueOf(timeLeft)+"s");



                }
                public void onFinish(){
                    timeLeft=0;
                    time.setText(String.valueOf(timeLeft)+"s");
                    score.setText(String.valueOf(totalCorrectAns)+"/"+ String.valueOf(totalQuestionsAnswered));
                    score.setVisibility(View.VISIBLE);
                    playAgainButton.setText("PLAY AGAIN!!");
                    playAgainButton.setVisibility(View.VISIBLE);
                }
        }.start();

    }

    public void selectFunc(View view){

        if(timeLeft!=0){
            Button buttonPressed = (Button) view;
            int tag=Integer.parseInt(buttonPressed.getTag().toString());
            Log.i("Button Pressed ", Integer.toString(tag));
            totalQuestionsAnswered++;
            score=(TextView) findViewById(R.id.score);
            if(tag==answerIndex){
                score.setText("Correct :)");
                totalCorrectAns++;
            }
            else{
                score.setText("Incorrect :(");
            }
            score.setVisibility(View.VISIBLE);








            leftOperand = 1+ rand.nextInt(upperBound);
            rightOperand = 1+ rand.nextInt(upperBound);

            answer = leftOperand + rightOperand ;
            answerIndex = rand.nextInt(totalChild);


            question = (TextView) findViewById(R.id.question);
            question.setText(String.valueOf(leftOperand)+" + " + String.valueOf(rightOperand));

            ratio = (TextView) findViewById(R.id.ratio);
            ratio.setText(String.valueOf(totalCorrectAns)+"/"+String.valueOf(totalQuestionsAnswered));

            ArrayList<Integer> intAns = new ArrayList<Integer>();

            for(int i=0;i<4;i++){
                if(i==answerIndex){
                    intAns.add(answer);
                }
                int wrongAns= rand.nextInt(101);
                while(wrongAns==answer){
                    wrongAns= rand.nextInt(101);
                }
                intAns.add(wrongAns);
            }

            Button button1 = (Button) findViewById(R.id.button1);
            Button button2 = (Button) findViewById(R.id.button2);
            Button button3 = (Button) findViewById(R.id.button3);
            Button button4 = (Button) findViewById(R.id.button4);

            button1.setText(String.valueOf(intAns.get(0)));
            button2.setText(String.valueOf(intAns.get(1)));
            button3.setText(String.valueOf(intAns.get(2)));
            button4.setText(String.valueOf(intAns.get(3)));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
