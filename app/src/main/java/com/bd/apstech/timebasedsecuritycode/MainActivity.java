package com.bd.apstech.timebasedsecuritycode;

import static com.bd.apstech.timebasedsecuritycode.SecurityCodeUtils.generateSecurityCode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static {
       System.loadLibrary("timebasedsecuritycode");
    }

    Button button;
    TextView textView;

    private static native String getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String secretKey = getKey(); //here used native c++ for security, it don't even show after app decoded!!
                    String code = generateSecurityCode(secretKey, 8);
                    System.out.println(code);
                    textView.setText(code);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}