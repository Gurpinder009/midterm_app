package com.example.midterm1gsi89859;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Random;

public class GameScreenActivity extends AppCompatActivity {
    EditText editText ;
    Integer numberToGuess;
    LinearLayout list;
    int counter =0;
    Button changeBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);
        editText = findViewById(R.id.userInput);
        list = findViewById(R.id.guessList);
        changeBtn = findViewById(R.id.changeBtn);

        Random rand = new Random();

        numberToGuess = rand.nextInt((10) + 1);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("SetTextI18n")
    public void submitGuess(View v){

        if(counter < 4 ) {

            int randomNumber = Integer.parseInt(editText.getText().toString());
            if (randomNumber >= 0 && randomNumber <= 10) {
                String str = "";
                TextView tv = new TextView(getApplicationContext());

                if(randomNumber -numberToGuess ==0){
                    str =  "You guessed it right! congratulations";
//                        list.removeAllViews();
                    tv.setText(str);
                    counter =6;
                  replaceBtnLogic();



                }
                else if(randomNumber - numberToGuess > 3){
                    str = "too far";
                    tv.setText(Integer.toString(randomNumber));
                }else{
                    str ="too close";
                    tv.setText(Integer.toString(randomNumber));
                }
                list.addView(tv);


               showToast(str);

            } else {


                showToast("Only 0 to 10 input is valid");
            }
            counter++;
        }else{
            if(counter <5){
                TextView tv = new TextView(getApplicationContext());
                tv.setText("User Maximum guess limit");
                list.addView(tv);
                replaceBtnLogic();
                Toast.makeText(getApplicationContext(), "you hit maximum guess limit",
                        Toast.LENGTH_LONG).show();
            }



            counter++;
        }
        editText.setText("");


    }





    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    public void replaceBtnLogic(){
        changeBtn.setText(R.string.resetBtn);
        changeBtn.setOnClickListener(this::resetGame);
    }



    public void resetGame(View view){
        finish();

    }




    public void showRandomNumber(View view){


        Random rand = new Random();
        int showHintNumber  = rand.nextInt((10) + 1);
        showToast(Integer.toString(showHintNumber));
    }
}