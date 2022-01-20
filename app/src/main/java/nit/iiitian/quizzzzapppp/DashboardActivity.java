package nit.iiitian.quizzzzapppp;

import static nit.iiitian.quizzzzapppp.MainActivity.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;


    ProgressBar mprogressBar;
    List<ModelClass> allQuestionList;
    ModelClass modelclass;
    int index = 0;
    TextView card_ques, card_option_A, card_option_B, card_option_C, card_option_D;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        mprogressBar = findViewById(R.id.quiz_timer);

        allQuestionList = listOfQ;
        Collections.shuffle(allQuestionList);
        modelclass = listOfQ.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

        nextBtn.setClickable(false);

        countDownTimer = new CountDownTimer(20000, 1000) {
            private Object windowManager;

            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                mprogressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);

//                dialog.getWindow().addFlags(windowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        }.start();

        setAllData();
    }

    private void setAllData() {
        card_ques.setText(modelclass.getQuestion());
        card_option_A.setText(modelclass.getoA());
        card_option_B.setText(modelclass.getoB());
        card_option_C.setText(modelclass.getoC());
        card_option_D.setText(modelclass.getoD());
        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Hooks() {
        mprogressBar = findViewById(R.id.quiz_timer);
        card_ques = findViewById(R.id.card_ques);
        card_option_A = findViewById(R.id.card_option_A);
        card_option_B = findViewById(R.id.card_option_B);
        card_option_C = findViewById(R.id.card_option_C);
        card_option_D = findViewById(R.id.card_option_D);

        cardOA = findViewById(R.id.card_A);
        cardOB = findViewById(R.id.card_B);
        cardOC = findViewById(R.id.card_C);
        cardOD = findViewById(R.id.card_D);

        nextBtn = findViewById(R.id.nextBtn);
    }

    public void Correct(CardView cardView) {

        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelclass = listOfQ.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });
    }

    public void Wrong(CardView cardOA) {

        cardOA.setBackgroundColor(getResources().getColor(R.color.red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if (index < listOfQ.size() - 1) {
                    index++;
                    modelclass = listOfQ.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                } else {
                    GameWon();
                }
            }
        });


    }

    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this, WonActivity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);
    }

    public void enableButton() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor() {
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoA().equals(modelclass.getAns())) {
//            cardOA.setBackgroundColor(getResources().getColor(R.color.green));
            if (index < listOfQ.size() - 1) {
                Correct(cardOA);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardOA);
        }
    }

    public void optionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoB().equals(modelclass.getAns())) {
//            cardOB.setBackgroundColor(getResources().getColor(R.color.green));
            if (index < listOfQ.size() - 1) {
                Correct(cardOB);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardOB);
        }
    }

    public void optionCClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoC().equals(modelclass.getAns())) {
//            cardOC.setBackgroundColor(getResources().getColor(R.color.green));
            if (index < listOfQ.size() - 1) {

                Correct(cardOC);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardOC);
        }
    }

    public void optionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoD().equals(modelclass.getAns())) {
//            cardOD.setBackgroundColor(getResources().getColor(R.color.green));
            if (index < listOfQ.size() - 1) {

                Correct(cardOD);
            } else {
                GameWon();
            }
        } else {
            Wrong(cardOD);
        }
    }



    public void back_click(View view){
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void exit_click(View view){
//        android.os.Process.killProcess(android.os.Process.myPid());

        System.exit(0);
    }

}