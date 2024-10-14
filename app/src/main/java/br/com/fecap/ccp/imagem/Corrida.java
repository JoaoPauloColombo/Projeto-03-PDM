package br.com.fecap.ccp.imagem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Corrida extends AppCompatActivity {

    private TextView pontuacaoPlayer;
    private int pontosPlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_corrida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pontuacaoPlayer = findViewById(R.id.pontuacaoPlayer); // Inicializando a referência ao TextView
    }

    public void selectPapel(View view) {
        this.opcaoSelecionada("papel");
    }

    public void selectPedra(View view) {
        this.opcaoSelecionada("pedra");
    }

    public void selectTesoura(View view) {
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {
        ImageView imagemResultado = findViewById(R.id.imagemPadrao);
        TextView textoResultado = findViewById(R.id.textResultado);

        imagemResultado.setImageResource(R.drawable.padrao); // Define uma imagem padrão

        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        // Definindo a imagem correspondente à jogada do app
        switch (opcaoApp) {
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        // Lógica para determinar o vencedor
        if (
                (opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                        (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                        (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))
        ) {
            textoResultado.setText(R.string.appJogoGameOver); // App vence
            resetJogo();

        } else if (
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                        (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                        (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))
        ) {
            pontosPlayer += 1; // Jogador vence e pontuação aumenta
            textoResultado.setText(R.string.appJogoWin); // Exibe "Você ganhou!"
            pontuacaoPlayer.setText(String.valueOf(pontosPlayer)); // Atualiza a pontuação no TextView
        }else if (
                (opcaoSelecionada.equals("tesoura") == opcaoApp.equals("tesoura")) ||
                (opcaoSelecionada.equals("papel") == opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("pedra") == opcaoApp.equals("pedra"))
        ) {
            textoResultado.setText(R.string.appJogoEmpate);
        }
    }

    // Método para resetar o jogo
    private void resetJogo() {
        pontosPlayer = 0;
        pontuacaoPlayer.setText(String.valueOf(pontosPlayer));
        ImageView imagemResultado = findViewById(R.id.imagemPadrao);
        imagemResultado.setImageResource(R.drawable.padrao);
    }
}
