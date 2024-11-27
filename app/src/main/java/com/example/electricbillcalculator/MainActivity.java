package com.example.electricbillcalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtOutput;
    EditText editKwh;
    EditText editRebate;
    Button btnCalculate;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtOutput=findViewById(R.id.txtOutput);
        editKwh=findViewById(R.id.editKwh);
        editRebate=findViewById(R.id.editRebate);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOutput.setText("0.0");
                editKwh.setText("");
                editRebate.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected=item.getItemId();

        if(selected==R.id.menuAbout){
            Toast.makeText(this,"about clicked",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(selected==R.id.menuSettings){
            Toast.makeText(this,"settings clicked",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}