package com.example.electricbillcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtOutput;
    EditText editKwh;
    EditText editRebate;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtOutput=findViewById(R.id.txtOutput);
        editKwh=findViewById(R.id.editKwh);
        editRebate=findViewById(R.id.editRebate);
        btnCalculate=findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //taking value from editText
                String inputKwh=editKwh.getText().toString();
                String inputRebate=editRebate.getText().toString();

                // Check if the input is empty
                if (inputKwh.isEmpty() && inputRebate.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                //convert to doule
                double Kwh=Double.parseDouble(inputKwh);
                double Rebate=Double.parseDouble(inputRebate);

                //calculation

                double totalCharges=0;

                if(Kwh<=200){
                    totalCharges+=Kwh*0.218;
                }
                else if(Kwh>200 && Kwh<=300){
                    totalCharges=43.6+((Kwh-200)*0.334);
                }
                else if(Kwh>300 && Kwh<=600){
                    totalCharges=43.6+33.4+((Kwh-300)*0.516);
                }
                else if(Kwh>600){
                    totalCharges=43.6+33.4+154.8+((Kwh-600)*0.546);
                }

                //display
                txtOutput.setText(Double.toString(totalCharges));

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}