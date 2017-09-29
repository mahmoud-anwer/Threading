package com.example.root.threading;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Activity myActivity;
    TextView text1;
    TextView text2;
    myThread mythread;
    myThread1 mythread1;
    boolean flag = true;
    int counter = 0;
    int counter1 = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity = this;
        text1 = (TextView) findViewById(R.id.textViewText1);
        text2 = (TextView) findViewById(R.id.textViewText2);
    }




    public void buStop(View view) {
        flag = false;
    }

    public void buMessage(View view) {
        Toast.makeText(this, "work while threading", Toast.LENGTH_LONG).show();
    }


    public void buRun(View view) {
        flag = true;
        mythread = new myThread();
        mythread.start();
        try {
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        text2.setText(String.valueOf(counter));
        //mythread1 = new myThread1();
        //mythread1.start();


    }

    class myThread extends Thread{
        public void run(){
            while (counter < 5) {

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(myActivity, String.valueOf(counter), Toast.LENGTH_LONG).show();
                        text1.setText(String.valueOf(counter));
                    }
                });


                counter++;
                //text1.setText(String.valueOf(counter));
                //Toast.makeText(myActivity, String.valueOf(counter), Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class myThread1 extends Thread{
        public void run(){
            while (flag) {

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(myActivity, String.valueOf(counter), Toast.LENGTH_LONG).show();
                        text2.setText(String.valueOf(counter1));
                    }
                });
                counter1--;
                //text1.setText(String.valueOf(counter));
                //Toast.makeText(myActivity, String.valueOf(counter), Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

