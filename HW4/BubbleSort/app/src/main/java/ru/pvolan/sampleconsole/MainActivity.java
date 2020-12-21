package ru.pvolan.sampleconsole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editInput;
    private TextView textOutput;
    private CanvasView imageOutput;
    private View buttonRun;
    private View progressRun;

    private View buttonCanvasOutput;
    private View buttonTextOutput;
    private View layoutCanvasOutput;
    private View layoutTextOutput;

    private Processor processor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processor = new Processor();

        editInput = findViewById(R.id.editInput);
        textOutput = findViewById(R.id.textOutput);
        imageOutput = findViewById(R.id.imageOutput);
        buttonRun = findViewById(R.id.buttonRun);
        progressRun = findViewById(R.id.progressRun);

        buttonCanvasOutput = findViewById(R.id.buttonCanvasOutput);
        buttonTextOutput = findViewById(R.id.buttonTextOutput);
        layoutCanvasOutput = findViewById(R.id.imageOutput);
        layoutTextOutput = findViewById(R.id.layoutTextOutput);

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRunClick();
            }
        });
        buttonCanvasOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCanvas(true);
            }
        });
        buttonTextOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCanvas(false);
            }
        });
    }


    private void buttonRunClick() {
        final Handler handler = new Handler();

        buttonRun.setVisibility(View.INVISIBLE);
        progressRun.setVisibility(View.VISIBLE);

        hideKeyboard(editInput);


        final int bitmapW = 1000;
        final int bitmapH = 1000;

        new Thread(new Runnable() {
            @Override
            public void run() {
                final RunOutput output = processor.doTheMagic(editInput.getText().toString(), bitmapW, bitmapH);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDone(output);
                    }
                });
            }
        }).start();
    }


    private void onDone(RunOutput output) {
        buttonRun.setVisibility(View.VISIBLE);
        progressRun.setVisibility(View.INVISIBLE);

        textOutput.setText(output.getOutputStr());
        imageOutput.setBitmap(output.getOutputBitmap());
    }



    private void showCanvas(boolean show) {
        buttonCanvasOutput.setVisibility(!show ? View.VISIBLE : View.GONE);
        buttonTextOutput.setVisibility(show ? View.VISIBLE : View.GONE);
        layoutCanvasOutput.setVisibility(show ? View.VISIBLE : View.GONE);
        layoutTextOutput.setVisibility(!show ? View.VISIBLE : View.GONE);
    }


    private static void hideKeyboard(final View editText) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }

        }, 100);
    }
}