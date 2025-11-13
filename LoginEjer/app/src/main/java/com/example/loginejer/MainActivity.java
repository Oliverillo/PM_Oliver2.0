package com.example.loginejer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText tCorreo;
    private EditText tContrasena;
    private Button bContinuar;
    private Switch sRecordar;
    private TextView tvMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tCorreo = findViewById(R.id.etCorreo);
        tContrasena = findViewById(R.id.etContrasena);
        bContinuar = findViewById(R.id.bContinuar);
        sRecordar = findViewById(R.id.sRecordar);
        tvMensaje = findViewById(R.id.tvMensaje);

        bContinuar.setOnClickListener(v -> {
            String correo = tCorreo.getText().toString();
            String contrasena = tContrasena.getText().toString();

            if (correo.equals("correo@correo.com") && contrasena.equals("1234")) {
                tvMensaje.setText("Bienvenido");
            } else {
                tvMensaje.setText("Usuario y/o contraseña incorrecta");
            }
        });
    }
}
