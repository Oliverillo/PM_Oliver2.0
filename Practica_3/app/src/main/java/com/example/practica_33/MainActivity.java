package com.example.practica_33;   // 👈 asegúrate de que coincide con el nombre real de tu paquete

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declaración de los botones
    private Button btnContinuar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carga el layout principal de la actividad
        setContentView(R.layout.activity_main);

        // Vincula los botones con sus IDs del XML
        btnContinuar = findViewById(R.id.btnContinuar);
        btnCancelar  = findViewById(R.id.btnCancelar);

        // (Opcional) Acciones simples para probar los botones
        btnContinuar.setOnClickListener(v ->
                btnContinuar.setText("¡Has pulsado Continuar!"));

        btnCancelar.setOnClickListener(v ->
                btnCancelar.setText("¡Has pulsado Cancelar!"));
    }
}