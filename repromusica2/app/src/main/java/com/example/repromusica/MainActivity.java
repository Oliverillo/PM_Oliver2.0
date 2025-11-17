package com.example.repromusica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private TextView txtTiempoActual, txtTiempoTotal, txtNombreCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // UI
        txtNombreCancion = findViewById(R.id.txtNombreCancion);
        txtTiempoActual = findViewById(R.id.txtTiempoActual);
        txtTiempoTotal = findViewById(R.id.txtTiempoTotal);
        seekBar = findViewById(R.id.seekBar);
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);

        // MediaPlayer
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.cancion); // <-- Nombre actualizado
            if(mediaPlayer == null){
                Toast.makeText(this, "Error: archivo cancion.mp3 no encontrado en res/raw", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostrar nombre de la canción
        txtNombreCancion.setText("Canción: cancion.mp3");

        // Configurar SeekBar y tiempos
        seekBar.setMax(mediaPlayer.getDuration());
        txtTiempoTotal.setText(formatoTiempo(mediaPlayer.getDuration()));
        txtTiempoActual.setText("00:00");

        // Cuando el usuario mueve la SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
                txtTiempoActual.setText(formatoTiempo(mediaPlayer.getCurrentPosition()));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón Play
        btnPlay.setOnClickListener(v -> {
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                actualizarSeekBar();
            }
        });

        // Botón Pausa
        btnPause.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });
    }

    // Actualizar SeekBar cada 500ms mientras se reproduce
    private void actualizarSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            handler.postDelayed(this::actualizarSeekBar, 500);
        }
    }

    // Convertir milisegundos a mm:ss
    private String formatoTiempo(int ms){
        int minutos = (ms / 1000) / 60;
        int segundos = (ms / 1000) % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

