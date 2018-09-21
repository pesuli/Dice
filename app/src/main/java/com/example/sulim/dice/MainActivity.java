package com.example.sulim.dice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mDiceView;
    private TextView mPointsView;
    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private EditText mEditBet;
    private EditText mEditRez;
    private int mCountRez=0;
    private int mBet=0;
    private int mPoints=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Button rollDicesButton = findViewById(R.id.button_roll);
        mLeftImageView = findViewById(R.id.imageview_left);
        mRightImageView = findViewById(R.id.imageview_right);
        mDiceView= findViewById(R.id.textView_Dice);
        mPointsView= findViewById(R.id.textViewPoints);
        mEditBet= findViewById(R.id.editTextBet);
        mEditRez= findViewById(R.id.editTextRez);

        rollDicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Обработчик щелчка кнопки
             ShowEditAreas();
            }
        });
    }

    private int randomDiceValue() {
        //Великий рандом
        Random random = new Random();
        return random.nextInt(6) + 1;
    }


    private void ShowEditAreas() {

        //Обработка ошибок и вывод EditView и Тосты

        if (mEditBet.getText().length() == 0 | mEditRez.getText().length() == 0) {

            Toast toast = Toast.makeText(getApplicationContext(),"Заполните пустое поле!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }

        else {

            mBet=Integer.parseInt(mEditBet.getText().toString());
            mCountRez=Integer.parseInt(mEditRez.getText().toString());

            ThrowDices();

        }

        if (mBet<10) {

            Toast toast = Toast.makeText(getApplicationContext(),"Ставка не может быть меньше 10 очков!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }

        if (mCountRez==0 | mCountRez>13 | mCountRez==1) {

            Toast toast = Toast.makeText(getApplicationContext(),"Счастливое число, от 2 до 12!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }

    }




    @SuppressLint("SetTextI18n")
    private void ThrowDices(){



        // Получим случайные числа для двух костей
        int value1 = randomDiceValue();
        int value2 = randomDiceValue();

        int mCount = value1 + value2;


        // Находим ресурс с этими числами
        int res1 = getResources().getIdentifier("dice_" + value1,
                "drawable", "com.example.sulim.dice");
        int res2 = getResources().getIdentifier("dice_" + value2,
                "drawable", "com.example.sulim.dice");

        mLeftImageView.setImageResource(res1);
        mRightImageView.setImageResource(res2);

        //Обработка условий игры
        if ( mCountRez == mCount) {
            mPoints+=mBet*4;
            mDiceView.setText("Выпало " + mCount);
            Toast toast = Toast.makeText(getApplicationContext(),"Поздравляю, вы выиграли " + mBet + " очков.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            mPointsView.setText("У вас " + mPoints + " очков");

        }

        if ( mCountRez<7 & mCount <7 ) {
            mPoints+=mBet;
            mDiceView.setText("Выпало " + mCount);
            Toast toast = Toast.makeText(getApplicationContext(),"Поздравляю, вы выиграли " + mBet + " очков.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            mPointsView.setText("У вас " + mPoints + " очков");

        }

        if ( mCountRez>7 & mCount >7 ) {
            mPoints+=mBet;
            mDiceView.setText("Выпало " + mCount);
            Toast toast = Toast.makeText(getApplicationContext(),"Поздравляю, вы выиграли " + mBet + " очков.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            mPointsView.setText("У вас " + mPoints + " очков");

        }

        else {

            mPoints-=mBet;
            Toast toast = Toast.makeText(getApplicationContext(),"Вы проиграли " + mBet + " очков", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            mDiceView.setText("Выпало " + mCount);
            mPointsView.setText("У вас " + mPoints + " очков");
        }

        if (mPoints>4999) {

            Toast toast = Toast.makeText(getApplicationContext(),"Поздравляю с победой, можете попробовать еще раз", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        }

        if (mPoints<10) {

            Toast toast = Toast.makeText(getApplicationContext(),"Вы проиграли в эту игру, я верну вас в главное меню", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        }




    }


     }

