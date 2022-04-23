package com.example.newapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.tools.MySolution;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final EditText in1 = (EditText) findViewById(R.id.input1);
        final EditText in2= (EditText) findViewById(R.id.input2);
        final TextView output = (TextView) findViewById(R.id.out);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = in1.getText().toString();
                String input2 = in2.getText().toString();

                // Check Input1 is empty or not
                if (input1.length() == 0) {
                    output.setText(getString(R.string.empty_input_error, "Input1"));
                    return;
                }

                // Check Input2 is empty or not
                if (input2.length() == 0) {
                    output.setText(getString(R.string.empty_input_error, "Character index"));
                    return;
                }

                // Check Input2 is String or not
                int intInput2 = 0;
                try {
                    intInput2 = Integer.parseInt(input2);
                }
                catch (NumberFormatException ex) {
                    output.setText(R.string.input2_error);
                    return;
                }

                MySolution mySol = new MySolution(input1, intInput2);

                try {
                    output.setText(mySol.findSuffix());
                }
                catch (Exception ex) {
                    output.setText(ex.getMessage());
                }

            }
        });
    }
}