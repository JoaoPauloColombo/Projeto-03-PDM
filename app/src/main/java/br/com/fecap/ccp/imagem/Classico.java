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

public class Classico extends AppCompatActivity {

    private TextView pontuacaoVitorias;
    private int pontosVitorias = 0;
    private TextView pontuacaoDerrotas;
    private int pontosDerrotas = 0;
    private TextView textoResultado;
    private boolean jogoEncerrado = false; // Flag para controlar se o jogo terminou

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classico);

        pontuacaoVitorias = findViewById(R.id.pontuacaoVitorias);
        pontuacaoDerrotas = findViewById(R.id.pontuacaoDerrotas);
        textoResultado = findViewById(R.id.textResultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectPapel(View view) {
        if (jogoEncerrado) {
            resetJogo(); // Se o jogo acabou, reseta ao clicar
        }
        this.opcaoSelecionada("papel");
    }

    public void selectPedra(View view) {
        if (jogoEncerrado) {
            resetJogo(); // Se o jogo acabou, reseta ao clicar
        }
        this.opcaoSelecionada("pedra");
    }

    public void selectTesoura(View view) {
        if (jogoEncerrado) {
            resetJogo(); // Se o jogo acabou, reseta ao clicar
        }
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {
        if (jogoEncerrado) {
            return; // Não processa novas jogadas enquanto o jogo estiver encerrado
        }

        ImageView imagemResultado = findViewById(R.id.imagemPadrao);
        imagemResultado.setImageResource(R.drawable.padrao); // Define uma imagem padrão

        // Geração da jogada aleatória do app
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        // Define a imagem correspondente à jogada do app
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
            pontosDerrotas += 1; // Oponente vence
            textoResultado.setText(R.string.appJogoGameOver); // Exibe "Game Over"
            pontuacaoDerrotas.setText(String.valueOf(pontosDerrotas)); // Atualiza a pontuação de derrotas
        } else if (
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                        (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                        (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))
        ) {
            pontosVitorias += 1; // Jogador vence e pontuação aumenta
            textoResultado.setText(R.string.appJogoWin); // Exibe "Você ganhou!"
            pontuacaoVitorias.setText(String.valueOf(pontosVitorias)); // Atualiza a pontuação de vitórias
        }else if (
                (opcaoSelecionada.equals("tesoura") == opcaoApp.equals("tesoura")) ||
                (opcaoSelecionada.equals("papel") == opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("pedra") == opcaoApp.equals("pedra"))
        ) {
            textoResultado.setText(R.string.appJogoEmpate);
        }

        // Verifica se algum jogador atingiu 3 pontos
        if (pontosVitorias == 3) {
            textoResultado.setText("Player ganhou o game!");
            jogoEncerrado = true; // Marca que o jogo terminou
        } else if (pontosDerrotas == 3) {
            textoResultado.setText("Máquina ganhou o game!");
            jogoEncerrado = true; // Marca que o jogo terminou
        }
    }

    // Método para resetar o jogo
    private void resetJogo() {
        pontosVitorias = 0;
        pontosDerrotas = 0;
        pontuacaoVitorias.setText(String.valueOf(pontosVitorias));
        pontuacaoDerrotas.setText(String.valueOf(pontosDerrotas));
        textoResultado.setText(""); // Limpa o texto do resultado
        ImageView imagemResultado = findViewById(R.id.imagemPadrao);
        imagemResultado.setImageResource(R.drawable.padrao); // Reseta a imagem
        jogoEncerrado = false; // Permite o reinício do jogo
    }
}
