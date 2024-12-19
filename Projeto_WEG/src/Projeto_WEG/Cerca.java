package Projeto_WEG;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Cerca {
	
	private Double comprimento;
	private Double altura;
	private String cor;
	private Boolean confirmado;
	private LocalDateTime dataHoraAtual;
	
	
	public Cerca(Double comprimento, Double altura, String cor, Boolean confirmado, String dataHoraFormatada) {
		this.comprimento = comprimento;
		this.altura = altura;
		this.cor = cor;
		this.confirmado = confirmado;
		this.dataHoraAtual = dataHoraAtual;
	}
	
	public Cerca(){
		
	}
	
	
	
}
