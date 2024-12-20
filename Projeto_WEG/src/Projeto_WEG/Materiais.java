package Projeto_WEG;

public class Materiais {
    private int quantTelas;
    private int quantPostes;
    private int quantFixadores;
    private int quantParafusos;
	private double tamanho_cerca;
	private double diferenca;

    public Materiais(int quantTelas, int quantPostes, int quantFixadores, int quantParafusos, double tamanho_cerca, double diferenca) {
        this.quantTelas = quantTelas;
        this.quantPostes = quantPostes;
        this.quantFixadores = quantFixadores;
        this.quantParafusos = quantParafusos;
        this.tamanho_cerca = tamanho_cerca;
    }

    public Materiais() {
        
    }
    
    public double getDiferenca() {
		return diferenca;
	}

    public int getQuantTelas() {
        return quantTelas;
    }

    public int getQuantPostes() {
        return quantPostes;
    }

    public int getQuantFixadores() {
        return quantFixadores;
    }

    public int getQuantParafusos() {
        return quantParafusos;
    }
    
    public double getTamanhoCerca(){
    	return tamanho_cerca;
    }

    public void calculo_materiais(Double comprimento, Double altura) {
        // Quantidade de telas
        
        this.quantTelas = (int) Math.ceil(comprimento / 2.5);
        this.tamanho_cerca = quantTelas * 2.5;

        // Quantidade de postes
        this.quantPostes = this.quantTelas + 1;

        // Quantidade de fixadores
        if (altura == 1.03) {
            this.quantFixadores = 3 * this.quantPostes;
        } else if (altura == 1.53) {
            this.quantFixadores = 4 * this.quantPostes;
        } else {
            this.quantFixadores = 6 * this.quantPostes;
        }

        // Quantidade de parafusos
        this.quantParafusos = 4 * this.quantPostes;
        
        this.diferenca = tamanho_cerca - comprimento;
        
    }

	

	
}
