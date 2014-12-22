package com.projects.ofirbarzilay.funtime.math;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.ofirbarzilay.funtime.R;
import com.projects.ofirbarzilay.funtime.math.helper.Exercise;
import com.projects.ofirbarzilay.funtime.utils.AppConstant;
import com.projects.ofirbarzilay.funtime.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MathExerciseActivity extends Activity {

    private int mMaxNumber;
    private String mSignAsString;
    private int mDifficultyLevel;
    private int mCategory;
    private ArrayList<Exercise> mExercises = new ArrayList<Exercise>();
    private ArrayList<ImageView> mCheckViewsList = new ArrayList<ImageView>();
    private Set<String> mEquationSet = new HashSet<String>();
    public enum GreetingTypes {WELL_DONE, FIX_MISTAKES};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_exercise);
        Intent intent = getIntent();
        try {
            mDifficultyLevel = intent.getIntExtra(AppConstant.DIFFICULTY_LEVEL, 0);
            mCategory = intent.getIntExtra(AppConstant.MATH_CATEGORY, 0);
            init();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void draw() {
        final GridLayout gridLayout = (GridLayout) findViewById(R.id.math_exercise_view);

        final LinearLayout linearLayout = new LinearLayout(this);

        ImageView checkResultsImgView = new ImageView(this);
        checkResultsImgView.setImageResource(R.drawable.check_results96);
        checkResultsImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkResults();
            }
        });

        linearLayout.addView(checkResultsImgView);

        ImageView mathHomePageImgView = new ImageView(this);
        mathHomePageImgView.setImageResource(R.drawable.home96);
        mathHomePageImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMathHomePage();
            }
        });
        linearLayout.addView(mathHomePageImgView);

        ImageView nextImgView = new ImageView(this);
        nextImgView.setImageResource(R.drawable.next96);
        nextImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPage();
            }
        });
        linearLayout.addView(nextImgView);
        GridLayout.Spec rowSpec = GridLayout.spec(0);
        GridLayout.Spec columnSpec = GridLayout.spec(0, 2);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);

        linearLayout.setLayoutParams(layoutParams);
        gridLayout.addView(linearLayout);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                gridLayout.addView(linearLayout);
//            }
//        });


        drawExercises();
    }

    private void drawExercises() {
        final GridLayout gridLayout = (GridLayout) findViewById(R.id.math_exercise_view);
        for (int i = 1; i < AppConstant.NUM_OF_EXERCISES; i++) {

            Exercise exercise = generateExercise();

            mExercises.add(exercise);

            final TextView textView = new TextView(this);
            textView.setText(exercise.getEquation());
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(28);
            textView.setTypeface(null, Typeface.BOLD);

            final EditText editText = new EditText(this);
            editText.setTextSize(28);
            editText.setSelectAllOnFocus(true);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTextColor(Color.WHITE);
            editText.setTypeface(null, Typeface.BOLD);
            int viewId = editText.generateViewId();
            editText.setId(viewId);
            exercise.setId(viewId);


            GridLayout.Spec rowSpec = GridLayout.spec(i);
            GridLayout.Spec columnSpec = GridLayout.spec(0);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);

            textView.setLayoutParams(layoutParams);

            columnSpec = GridLayout.spec(1);
            layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            editText.setLayoutParams(layoutParams);


            gridLayout.addView(textView);
            gridLayout.addView(editText);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });

        }

    }


    private void init() {

        switch (mDifficultyLevel) {
            case AppConstant.EASY:
                mMaxNumber = AppConstant.EASY_MAX_NUMBER;
                break;
            case AppConstant.MEDIUM:
                mMaxNumber = AppConstant.MEDIUM_MAX_NUMBER;
                ;
                break;
            case AppConstant.HARD:
                mMaxNumber = AppConstant.HARD_MAX_NUMBER;
                ;
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

        draw();
/*
        final ProgressDialog progressDialog = new ProgressDialog(MathExerciseActivity.this);
        progressDialog.setTitle("Loading...");
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(true);
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                progressDialog.dismiss();
                super.handleMessage(msg);
            }

        };

        progressDialog.show();

        Thread t = new Thread() {
            public void run() {

                draw();

                handler.sendEmptyMessage(0);
            }

        };
        t.start();
        */
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
                exercise = generateDivisionEquation(mMaxNumber, AppConstant.MULTIPLIER_MAX_NUMBER);
                result = exercise.getNum1() / exercise.getNum2();
                break;
            default:
                result = -1;
                equation = "";
                exercise = new Exercise(equation);
        }
        exercise.setResult(result);
        mEquationSet.add(exercise.getEquation());
        return exercise;
    }

    private Exercise generateDivisionEquation(int maxNumber1, int maxNumber2) {
        int num1 = Utils.randInt(0, maxNumber1);
        int num2 = Utils.randInt(0, maxNumber2);
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        String equation = String.valueOf(num1) + " " + mSignAsString + " " + String.valueOf(num2) + " = ";
        if ((num2 > 0) && (num1 % num2 == 0) && (!mEquationSet.contains(equation))) {
            Exercise exercise = new Exercise(equation);
            exercise.setNum1(num1);
            exercise.setNum2(num2);
            return exercise;
        } else {
            return generateDivisionEquation(maxNumber1, maxNumber2);
        }
    }

    private Exercise generateEquation(int maxNumber1, int maxNumber2) {
        int num1 = Utils.randInt(0, maxNumber1);
        int num2 = Utils.randInt(0, maxNumber2);
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        String equation = String.valueOf(num1) + " " + mSignAsString + " " + String.valueOf(num2) + " = ";
        if (!mEquationSet.contains(equation)){
            Exercise exercise = new Exercise(equation);
            exercise.setNum1(num1);
            exercise.setNum2(num2);
            return exercise;
        }
        else {
            return generateEquation(maxNumber1, maxNumber2);
        }
    }

    public void checkResults() {
        boolean allResultsAreTrue = false;
        int counter = 0;
        boolean wrongAnswer = false;
        try {
            GridLayout gridLayout = (GridLayout) findViewById(R.id.math_exercise_view);

            // remove old views that displays the x or check marks
            Iterator<ImageView> iterator = mCheckViewsList.iterator();
            while (iterator.hasNext()) {
                ImageView imageView = iterator.next();
                gridLayout.removeView(imageView);
            }
            // add new views
            int size = mExercises.size();
            for (int i = 0; i < size; i++) {
                Exercise exercise = mExercises.get(i);
                int result = exercise.getResult();
                int id = exercise.getId();
                EditText editText = (EditText) gridLayout.findViewById(id);
                String text = editText.getText().toString().trim();
                if (text.length() > 0) {
                    ImageView imgView = new ImageView(this);
                    mCheckViewsList.add(imgView);
                    imgView.setPadding(0, 40, 0, 0);
                    int userResult = Integer.parseInt(text);
                    if (result == userResult) {
                        imgView.setImageResource(R.drawable.checkmark48);
                        counter++;
                    } else {
                        imgView.setImageResource(R.drawable.x_icon48);
                        wrongAnswer = true;
                    }
                    GridLayout.Spec rowSpec = GridLayout.spec(i + 1);//first row is for the buttons
                    GridLayout.Spec columnSpec = GridLayout.spec(2);
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
                    imgView.setLayoutParams(layoutParams);
                    gridLayout.addView(imgView);
                }

            }
            if (counter == size) {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.applause);
                mediaPlayer.start();
                showGreetingToast(GreetingTypes.WELL_DONE);

            }else if (wrongAnswer){
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.error);
                mediaPlayer.start();
                showGreetingToast(GreetingTypes.FIX_MISTAKES);
            }
        } catch (Exception e) {
            //TODO handle
        }
    }

    private void showGreetingToast(GreetingTypes types) {
       String prefix = "well_done_";
        switch (types){
            case WELL_DONE:
                prefix = "well_done_";
                break;
            case FIX_MISTAKES:
                prefix = "fix_mistakes_";
                break;
        }
        int randInt = Utils.randInt(1, 4);
        int stringIdentifier = getResources().getIdentifier(prefix + randInt, "string", "com.projects.ofirbarzilay.funtime");
        Toast.makeText(MathExerciseActivity.this, getString(stringIdentifier),Toast.LENGTH_SHORT).show();
    }

    public void openMathHomePage() {
        Intent intent = new Intent(getApplicationContext(), MathMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra(AppConstant.DIFFICULTY_LEVEL, mDifficultyLevel);
//        startActivity(intent);
        this.setResult(RESULT_OK, intent);
        finish();
    }

    public void resetPage() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.math_exercise_view);
        gridLayout.removeAllViews();
        mExercises.clear();
        mEquationSet.clear();
        mCheckViewsList.clear();
        draw();
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.recycle);
        mediaPlayer.start();

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
