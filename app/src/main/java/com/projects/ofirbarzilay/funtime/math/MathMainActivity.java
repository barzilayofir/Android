package com.projects.ofirbarzilay.funtime.math;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.projects.ofirbarzilay.funtime.R;
import com.projects.ofirbarzilay.funtime.utils.AppConstant;

import static com.projects.ofirbarzilay.funtime.utils.AppConstant.EASY;
import static com.projects.ofirbarzilay.funtime.utils.AppConstant.HARD;
import static com.projects.ofirbarzilay.funtime.utils.AppConstant.MEDIUM;

public class MathMainActivity extends Activity {

    private int mDifficultyLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_main);
    }

    public void openMathExercisePage(View view){
        int category;
        switch(view.getId()) {
            case R.id.addition_view:
                category = AppConstant.ADDITION_CATEGORY;
                break;
            case R.id.subtraction_view:
                category = AppConstant.SUBTRACTION_CATEGORY;
                break;
            case R.id.multiplication_view:
                category = AppConstant.MULTIPLICATION_CATEGORY;
                break;
            case R.id.division_view:
                category = AppConstant.DIVISION_CATEGORY;
                break;
            default:
                category = AppConstant.ADDITION_CATEGORY;
        }

        Intent i = new Intent(getApplicationContext(), MathExerciseActivity.class);
        //put extra data such as level
        i.putExtra(AppConstant.DIFFICULTY_LEVEL, mDifficultyLevel);
        i.putExtra(AppConstant.MATH_CATEGORY, category);
        startActivity(i);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_easy:
                if (checked)
                    mDifficultyLevel = EASY;
                    break;
            case R.id.radio_medium:
                if (checked)
                    mDifficultyLevel = MEDIUM;
                    break;
            case R.id.radio_hard:
                if (checked)
                    mDifficultyLevel = HARD;
                    break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_math_main, menu);
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
