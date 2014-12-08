package com.projects.ofirbarzilay.funtime.math;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.projects.ofirbarzilay.funtime.R;
import com.projects.ofirbarzilay.funtime.math.helper.Exercise;
import com.projects.ofirbarzilay.funtime.utils.AppConstant;
import com.projects.ofirbarzilay.funtime.utils.Utils;

import java.util.ArrayList;

public class MathExerciseActivity extends Activity {

    private int mMaxNumber;
    private String mSignAsString;
    private int mSignAsInt;
    private int mLevel;
    private int mCategory;
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_exercise);
        Intent intent = getIntent();
        try {
            mLevel = intent.getIntExtra(AppConstant.DIFFICULTY_LEVEL, 0);
            mCategory = intent.getIntExtra(AppConstant.MATH_CATEGORY, 0);
            init();

            drawExercises();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawExercises() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.math_exercise_view);
        for (int i = 0; i < 10; i++) {

            Exercise exercise = generateExercise();
            exercise.setId(i);
            exercises.add(exercise);

            TextView textView = new TextView(this);

            textView.setText(exercise.getEquation());
            EditText editText = new EditText(this);
            editText.setText(" ");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setId(i);

            GridLayout.Spec rowSpec = GridLayout.spec(i);
            GridLayout.Spec columnSpec = GridLayout.spec(0);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            textView.setLayoutParams(layoutParams);

            columnSpec = GridLayout.spec(1);
            layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            editText.setLayoutParams(layoutParams);

            gridLayout.addView(textView);
            gridLayout.addView(editText);
        }

    }


    private void init() {

        switch (mLevel) {
            case AppConstant.EASY:
                mMaxNumber = AppConstant.EASY_MAX_NUMBER;
                break;
            case AppConstant.MEDIUM:
                mMaxNumber = AppConstant.MEDIUM_MAX_NUMBER;;
                break;
            case AppConstant.HARD:
                mMaxNumber = AppConstant.HARD_MAX_NUMBER;;
                break;
            default:
                mMaxNumber = 5;
        }

        switch (mCategory) {
            case AppConstant.ADDITION_CATEGORY:
                mSignAsString = "+";
                break;
            case AppConstant.SUBTRACTION_CATEGORY:
                mSignAsString = "-";
                break;
            case AppConstant.MULTIPLICATION_CATEGORY:
                mSignAsString = "x";
                break;
            case AppConstant.DIVISION_CATEGORY:
                mSignAsString = ":";
                break;
            default:
                mSignAsString = "+";
        }
    }

    private Exercise generateExercise() {
        String equation;
        int result;
        Exercise exercise;
        switch (mCategory) {
            case AppConstant.ADDITION_CATEGORY:
                exercise = generateEquation(mMaxNumber, mMaxNumber);
                result = exercise.getNum1() + exercise.getNum2();
                break;
            case AppConstant.SUBTRACTION_CATEGORY:
                exercise = generateEquation(mMaxNumber, mMaxNumber);
                result = exercise.getNum1() - exercise.getNum2();
                break;
            case AppConstant.MULTIPLICATION_CATEGORY:
                exercise = generateEquation(mMaxNumber, AppConstant.MULTIPLIER_MAX_NUMBER);
                result = exercise.getNum1() * exercise.getNum2();
                break;
            case AppConstant.DIVISION_CATEGORY:
                exercise = generateDivisionEquation();
                result = exercise.getNum1() / exercise.getNum2();
                break;
            default:
                result = -1;
                equation = "";
                exercise = new Exercise(equation);
        }
        exercise.setResult(result);

        return exercise;
    }

    private Exercise generateDivisionEquation() {
        int num1 = Utils.randInt(0, mMaxNumber);
        int num2 = Utils.randInt(0, mMaxNumber);
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        if ((num2 > 0) && (num1 % num2 == 0)){
            String equation = String.valueOf(num1) + " " + mSignAsString + " " +  String.valueOf(num2) + " = ";
            Exercise exercise = new Exercise(equation);
            exercise.setNum1(num1);
            exercise.setNum2(num2);
            return exercise;
        }
        else{
            return generateDivisionEquation();
        }
    }

    private Exercise generateEquation(int maxNumber1, int maxNumber2){
        int num1 = Utils.randInt(0, maxNumber1);
        int num2 = Utils.randInt(0, maxNumber2);
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        String equation = String.valueOf(num1) + " " + mSignAsString + " " +  String.valueOf(num2) + " = ";
        Exercise exercise = new Exercise(equation);
        exercise.setNum1(num1);
        exercise.setNum2(num2);
        return exercise;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_division, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
