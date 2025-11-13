package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText tvCorreo;
    private EditText tvContraseña;
    private Button bContinuar;
    private Switch sRecordar;
    private TextView tvMensaje;

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

        tvCorreo = findViewById(R.id.etCorreo);
        tvContraseña = findViewById(R.id.etContraseña);
        bContinuar = findViewById(R.id.bContinuar);
        sRecordar = findViewById(R.id.sRecordar);
        tvMensaje= findViewById(R.id.tvMensaje);

        bContinuar.setOnClickListener(v -> {
            String correo = tvCorreo.getText().toString();
            String contraseña = tvContraseña.getText().toString();

            if (correo.equals("correo@correo.com") && contraseña.equals("1234")){
                tvMensaje.setText("Bienvenido");
            }else {
                tvMensaje.setText("Usuario y/o contraseña incorrectos");
            }
        });

    }
}