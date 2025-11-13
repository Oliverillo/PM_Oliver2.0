package com.example.listasejer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Films> filmsList = new ArrayList<>();
        filmsList.add(new Films("Dokkaebi", "doka", "Atacante"));
        filmsList.add(new Films("Ace", "ace", "Atacante"));
        filmsList.add(new Films("Solis", "solis", "Defensor"));
        filmsList.add(new Films("Frost", "frost", "Defensor"));

        FilmsAdapter adapter = new FilmsAdapter(this, filmsList);
        recyclerView.setAdapter(adapter);
    }
}
