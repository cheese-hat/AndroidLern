package ru.pvolan.sim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.pvolan.sim2.program.Program;

public class MainActivity extends AppCompatActivity {

    private View buttonUp;
    private View buttonDown;
    private View buttonLeft;
    private View buttonRight;
    private View buttonA;
    private View buttonB;

    private EditText editCommand;
    private View buttonRun;

    private CanvasView imageOutput;
    private ProgramBase program;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        program = new Program();
        program.doInit(programCallback);

        imageOutput = findViewById(R.id.imageOutput);
        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        editCommand = findViewById(R.id.editCommand);
        buttonRun = findViewById(R.id.buttonRun);

        imageOutput.setCallback(canvasCallback);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonUpClick();
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonDownClick();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonLeftClick();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonRightClick();
            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonAClick();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.onButtonBClick();
            }
        });

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRunClick();
            }
        });
    }


    private void buttonRunClick() {
        String command = editCommand.getText().toString();
        if(command.length() == 0){
            Toast.makeText(this, "No command entered", Toast.LENGTH_SHORT).show();
            return;
        }
        hideKeyboard(editCommand);

        try {
            program.onCommandRun(command);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        editCommand.setText("");
    }


    private ProgramBase.Callback programCallback = new ProgramBase.Callback() {
        @Override
        public void onRedraw() {
            imageOutput.invalidate();
        }

        @Override
        public void showToast(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    private CanvasView.Callback canvasCallback = new CanvasView.Callback() {
        @Override
        public void onDraw(Canvas canvas) {
            program.onDraw(canvas);
        }
    };


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