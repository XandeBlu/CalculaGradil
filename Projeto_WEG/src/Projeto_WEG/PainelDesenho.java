package Projeto_WEG;

import javax.swing.*;
import java.awt.*;

public class PainelDesenho extends JFrame {
    private JPanel painelDesenho;
    private double comprimento;
    private double altura;
    private String cor;

    public PainelDesenho(double comprimento, double altura, String cor) {
        this.comprimento = comprimento;
        this.altura = altura;
        this.cor = cor;
        
        // Configurações básicas da janela
        setTitle("Desenho da Cerca");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Cria o painel de desenho
        painelDesenho = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharCerca(g);
            }
        };
        painelDesenho.setBackground(Color.WHITE);
        add(painelDesenho);
    }

    private void desenharCerca(Graphics g) {
        // Configurações iniciais
        int margemX = 50;
        int margemY = 50;
        int larguraMaxima = painelDesenho.getWidth() - (2 * margemX);
        int alturaMaxima = painelDesenho.getHeight() - (2 * margemY);

        // Calcula escala para o desenho
        double escalaX = larguraMaxima / comprimento;
        double escalaY = alturaMaxima / altura;
        double escala = Math.min(escalaX, escalaY) * 0.8; // 80% do tamanho para margem

        // Dimensões do desenho
        int larguraDesenho = (int)(comprimento * escala);
        int alturaDesenho = (int)(altura * escala);

        // Posição inicial centralizada
        int x = (painelDesenho.getWidth() - larguraDesenho) / 2;
        int y = (painelDesenho.getHeight() - alturaDesenho) / 2;

        // Define a cor da cerca
        Color corCerca;
        switch (cor.toLowerCase()) {
            case "preta":
                corCerca = Color.BLACK;
                break;
            case "branca":
                corCerca = Color.LIGHT_GRAY;
                break;
            case "verde":
                corCerca = Color.GREEN;
                break;
            default:
                corCerca = Color.GRAY;
        }
        g.setColor(corCerca);

        // Desenha a base da cerca
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g.drawLine(x, y + alturaDesenho, x + larguraDesenho, y + alturaDesenho);

        // Desenha os postes
        double espacoEntrePostes = 2.5; // 2.5 metros
        int espacoEscalado = (int)(espacoEntrePostes * escala);
        for (int i = x; i <= x + larguraDesenho; i += espacoEscalado) {
            g.fillRect(i - 3, y, 6, alturaDesenho);
        }

        // Desenha a tela
        g2d.setStroke(new BasicStroke(1));
        int espacoTela = 10;
        for (int i = x; i <= x + larguraDesenho; i += espacoTela) {
            g.drawLine(i, y, i, y + alturaDesenho);
        }

        // Desenha as travessas horizontais
        g2d.setStroke(new BasicStroke(2));
        g.drawLine(x, y + alturaDesenho/3, x + larguraDesenho, y + alturaDesenho/3);
        g.drawLine(x, y + 2*alturaDesenho/3, x + larguraDesenho, y + 2*alturaDesenho/3);

        // Adiciona informações da cerca
        g.setColor(Color.BLACK);
        Font fonte = new Font("Arial", Font.BOLD, 14);
        g.setFont(fonte);
        String info = String.format("Dimensões da cerca: %.2f metros x %.2f metros | Cor: %s", 
                                  comprimento, altura, cor);
        g.drawString(info, margemX, 30);
    }
}