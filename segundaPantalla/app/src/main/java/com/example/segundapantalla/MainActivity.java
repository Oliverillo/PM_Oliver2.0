package com.example.segundapantalla;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText tCorreo;
    private EditText tContraseña;
    private Button bContinuar;
    private Switch sRecordar;
    private TextView tvMensaje;
    private TextView tvMensaje2;

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
        tCorreo = findViewById(R.id.etCorreo);
        tContraseña = findViewById(R.id.etContraseña);
        bContinuar = findViewById(R.id.bContinuar);
        sRecordar = findViewById(R.id.sRecordar);
        tvMensaje = findViewById(R.id.tvMensaje);
        tvMensaje2 = findViewById(R.id.tvMensaje2);

        bContinuar.setOnClickListener(V ->{

            String correo = tCorreo.getText().toString();
            String contraseña = tContraseña.getText().toString();
            boolean recordar = sRecordar.isChecked();

            if(correo.equals("correo@correo.com")  && contraseña.equals("1234")){
                tvMensaje.setTextColor(Color.GREEN);
                tvMensaje2.setTextColor(Color.GREEN);
                Intent intent = new Intent(MainActivity.this, MainActivityLogin.class);
                intent.putExtra("correo", tCorreo.getText().toString());
                startActivity(intent);
                if(recordar){
                    tvMensaje.setText("Usuario y contraseña correctos");
                    tvMensaje2.setText("Almacenados para siguientes accesos");

                }else{
                    tvMensaje.setText("Usuario y contraseña correctos");
                }

            }else{
                tvMensaje.setTextColor(Color.RED);
                tvMensaje.setText("Usuario y/o contraseña incorrectos");

            }

        });
    }
}