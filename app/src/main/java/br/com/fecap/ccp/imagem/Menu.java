package br.com.fecap.ccp.imagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Menu extends AppCompatActivity {
    private Button btnClassico;
    private Button btnCorrida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        btnClassico = findViewById(R.id.btnClassico);
        btnCorrida = findViewById(R.id.btnCorrida);

        btnClassico.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Menu.this, Classico.class);
            startActivity(intent);
        }
        });

        btnCorrida.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Corrida.class);
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}