package com.example.reprosonicorto; //

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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

    private SoundPool soundPool;
    private int sonido1, sonido2, sonido3, sonido4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNombreCancion = findViewById(R.id.txtNombreCancion);
        txtTiempoActual = findViewById(R.id.txtTiempoActual);
        txtTiempoTotal = findViewById(R.id.txtTiempoTotal);
        seekBar = findViewById(R.id.seekBar);
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);

        mediaPlayer = MediaPlayer.create(this, R.raw.cancion);
        if(mediaPlayer == null){
            Toast.makeText(this, "Error: cancion.mp3 no encontrado", Toast.LENGTH_LONG).show();
            return;
        }

        txtNombreCancion.setText("Canción: cancion.mp3");
        seekBar.setMax(mediaPlayer.getDuration());
        txtTiempoTotal.setText(formatoTiempo(mediaPlayer.getDuration()));
        txtTiempoActual.setText("00:00");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) mediaPlayer.seekTo(progress);
                txtTiempoActual.setText(formatoTiempo(mediaPlayer.getCurrentPosition()));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnPlay.setOnClickListener(v -> { if(!mediaPlayer.isPlaying()){ mediaPlayer.start(); actualizarSeekBar(); } });
        btnPause.setOnClickListener(v -> { if(mediaPlayer.isPlaying()){ mediaPlayer.pause(); } });

        // SoundPool
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(4)
                    .setAudioAttributes(attrs)
                    .build();
        } else {
            soundPool = new SoundPool(4, android.media.AudioManager.STREAM_MUSIC, 0);
        }

        sonido1 = soundPool.load(this, R.raw.sonido1, 1);
        sonido2 = soundPool.load(this, R.raw.sonido2, 1);
        sonido3 = soundPool.load(this, R.raw.sonido3, 1);
        sonido4 = soundPool.load(this, R.raw.sonido4, 1);

        Button btnS1 = findViewById(R.id.btnSonido1);
        Button btnS2 = findViewById(R.id.btnSonido2);
        Button btnS3 = findViewById(R.id.btnSonido3);
        Button btnS4 = findViewById(R.id.btnSonido4);

        btnS1.setOnClickListener(v -> soundPool.play(sonido1,1,1,0,0,1));
        btnS2.setOnClickListener(v -> soundPool.play(sonido2,1,1,0,0,1));
        btnS3.setOnClickListener(v -> soundPool.play(sonido3,1,1,0,0,1));
        btnS4.setOnClickListener(v -> soundPool.play(sonido4,1,1,0,0,1));
    }

    private void actualizarSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()) handler.postDelayed(this::actualizarSeekBar,500);
    }

    private String formatoTiempo(int ms){
        int minutos = (ms/1000)/60;
        int segundos = (ms/1000)%60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){ mediaPlayer.release(); mediaPlayer = null; }
        if(soundPool != null){ soundPool.release(); soundPool = null; }
    }
}
