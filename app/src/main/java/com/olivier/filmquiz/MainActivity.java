package com.olivier.filmquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    public static final String KEY_QUESTION = "question";
    private final String TAG = "QuizActivity";

    Button btnTrue = null;
    Button btnFalse = null;
    TextView tvQuestion = null;
    TextView tvScore = null;
    List<Question> questions = new ArrayList<>();
    Question question = null;
    Integer score = 0;
    int indexQuestion = 0;
    String toastMessage = null;
    Context context =null;

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() call now");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() call now");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() call now");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() call now");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() call now");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onDestroy() call now");

        outState.putInt(KEY_INDEX, indexQuestion);
        outState.putInt(KEY_SCORE, score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cheat:
                Intent intent = new Intent(context, CheatActivity.class);

                intent.putExtra(KEY_QUESTION, question);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "on create() call now");

        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        tvQuestion = findViewById(R.id.twQuestion);
        tvScore = findViewById(R.id.twScore);

        questions.add(new Question(getString(R.string.question_ai).toString(), true));
        questions.add(new Question(getString(R.string.question_taxi_driver).toString(), true));
        questions.add(new Question(getString(R.string.question_2001).toString(), false));
        questions.add(new Question(getString(R.string.question_reservoir_dogs).toString(), true));
        questions.add(new Question(getString(R.string.question_citizen_kane).toString(), false));

        if(savedInstanceState != null){
            indexQuestion = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        question = questions.get(indexQuestion);
        tvQuestion.setText(question.getText());
        tvScore.setText(String.format("Score : %d", score));

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage = question.isAnswer() ? "Good" : "Baad";
                if(question.isAnswer()){
                    score ++;
                }

                context = getApplicationContext();
                CharSequence text = toastMessage;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                indexQuestion ++;

                if (indexQuestion >= questions.size() - 1){
                    indexQuestion = 0;
                    score = 0;
                } else {
                    question = questions.get(indexQuestion);
                }
                tvScore.setText(String.format("Score : %d", score));

            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage = !question.isAnswer() ? "Good" : "Baad";
                if(!question.isAnswer()){
                    score ++;
                }

                context = getApplicationContext();
                CharSequence text = toastMessage;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                indexQuestion ++;

                if (indexQuestion >= questions.size() - 1){
                    indexQuestion = 0;
                    score = 0;
                } else {
                    question = questions.get(indexQuestion);
                }
                tvScore.setText(String.format("Score : %d", score));

            }
        });
    }
}