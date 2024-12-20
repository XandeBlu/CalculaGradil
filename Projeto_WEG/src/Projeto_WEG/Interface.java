package Projeto_WEG;

import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Interface {

    private JFrame frame;
    private JTextField inter_comprimento;
    private final ButtonGroup Inter_confirmado = new ButtonGroup();
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Interface window = new Interface();
                window.frame.setVisible(true); // Exibe a janela gráfica
            } catch (Exception e) {
                e.printStackTrace(); // Exibe erros no console, se houver
            }
        });
    }


    /**
     * Create the application.
     */
    public Interface() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 582, 276);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Label e campo de texto para o comprimento
        JLabel lblNewLabel = new JLabel("Digite o comprimento da cerca (Metros)");
        lblNewLabel.setBounds(10, 5, 243, 14);
        frame.getContentPane().add(lblNewLabel);

        inter_comprimento = new JTextField();
        inter_comprimento.setBounds(35, 23, 171, 20);
        frame.getContentPane().add(inter_comprimento);
        inter_comprimento.setColumns(10);

        // ComboBox para altura
        JComboBox<String> inter_altura = new JComboBox<>();
        inter_altura.setModel(new DefaultComboBoxModel<>(new String[]{"1,03 M", "1,53 M", "2,03 M"}));
        inter_altura.setBounds(249, 22, 100, 22);
        frame.getContentPane().add(inter_altura);

        JLabel lblNewLabel_1 = new JLabel("Altura da cerca");
        lblNewLabel_1.setBounds(249, 5, 100, 14);
        frame.getContentPane().add(lblNewLabel_1);

        // ComboBox para cor
        JComboBox<String> Inter_cor = new JComboBox<>();
        Inter_cor.setModel(new DefaultComboBoxModel<>(new String[]{"Sem cor", "Preta", "Branca", "Verde"}));
        Inter_cor.setBounds(359, 22, 100, 22);
        frame.getContentPane().add(Inter_cor);

        JLabel lblNewLabel_2 = new JLabel("Cor da cerca");
        lblNewLabel_2.setBounds(376, 5, 83, 14);
        frame.getContentPane().add(lblNewLabel_2);

        // Botões de rádio para confirmação
        JLabel lblNewLabel_3 = new JLabel("Confirmado?");
        lblNewLabel_3.setBounds(481, 5, 75, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JRadioButton radio = new JRadioButton("Sim");
        Inter_confirmado.add(radio);
        radio.setBounds(460, 22, 49, 23);
        frame.getContentPane().add(radio);

        JRadioButton radio1 = new JRadioButton("Não");
        Inter_confirmado.add(radio1);
        radio1.setBounds(511, 22, 49, 23);
        frame.getContentPane().add(radio1);

        // Botão de criar pedido
        JButton btn_cria_pedido = new JButton("Criar Pedido");
        btn_cria_pedido.setBounds(69, 54, 137, 23);
        frame.getContentPane().add(btn_cria_pedido);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 88, 546, 89);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setBackground(Color.WHITE);
        table.setToolTipText("");
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Comprimento", "Altura", "Cor", "Confirmado?", "Data"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, Double.class, Double.class, String.class, String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        
        JButton btn_Materiais = new JButton("Ver detalhes do pedido");
        
        //Botão de Ver materiais
        btn_Materiais.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		// Essa variável receberá a linha selecionaawadd
        		int linha_selecionada = table.getSelectedRow();
        		
        		//Verifica se tem alguma linha selecionada
        		if (linha_selecionada == -1) {
        		    JOptionPane.showMessageDialog(frame, "Selecione um pedido primeiro!", "Erro", JOptionPane.WARNING_MESSAGE);
        		    return; 
        		}
        				//Pega os valores da tabela
        				Double comprimento = (double) table.getValueAt(linha_selecionada, 1);
        				Double altura = (double) table.getValueAt(linha_selecionada, 2);
                       
                        // Cria um novo objeto com os valores, parea calcular a quantiadde de cada materiall
                        Materiais materiais = new Materiais();
                        materiais.calculo_materiais(comprimento,altura);
                        
                        if(materiais.getDiferenca()==0) {
                        	JOptionPane.showMessageDialog(frame, String.format(
                                    "Materiais necessários \n Telas : %d \nPostes: %d \nFixadores: %d\nParafusos: %d\nTamanho total da cerca: %.2f metros",
                                    materiais.getQuantTelas(), materiais.getQuantPostes(), materiais.getQuantFixadores(), materiais.getQuantParafusos(), materiais.getTamanhoCerca()));
                        }else {
                        	JOptionPane.showMessageDialog(frame, String.format(
                                    "Materiais necessários \n Telas : %d \nPostes: %d \nFixadores: %d\nParafusos: %d\nTamanho total da cerca: %.2f metros\nA diferença entre o tamanho pedido e o vendido é de %.2f Metros",
                                    materiais.getQuantTelas(), materiais.getQuantPostes(), materiais.getQuantFixadores(), materiais.getQuantParafusos(), materiais.getTamanhoCerca(),materiais.getDiferenca()));
                        }
                        
                        
                       
              
        	}
        });
        
        
        btn_Materiais.setBounds(245, 54, 220, 23);
        frame.getContentPane().add(btn_Materiais);
        
        JButton btnNewButton = new JButton("Desenhar Cerca");
        
        btnNewButton.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		  int linhaSelecionada = table.getSelectedRow();
        	        
        	        if (linhaSelecionada == -1) {
        	            JOptionPane.showMessageDialog(frame, 
        	                "Selecione um pedido primeiro!", 
        	                "Erro", 
        	                JOptionPane.WARNING_MESSAGE);
        	            return;
        	        }
        	        
        	        try {
        	        	Materiais materiais = new Materiais();
        	            Double comprimento = (Double) table.getValueAt(linhaSelecionada, 1);
        	            Double altura = (Double) table.getValueAt(linhaSelecionada, 2);
        	            materiais.calculo_materiais(comprimento, altura);
        	            Double tamanho = materiais.getTamanhoCerca();
        	            String cor = (String) table.getValueAt(linhaSelecionada, 3);
        	            
        	            // Cria e exibe a janela de desenho
        	            PainelDesenho desenho = new PainelDesenho(tamanho, altura, cor);
        	            desenho.setVisible(true);
        	            
        	        } catch (Exception ex) {
        	            JOptionPane.showMessageDialog(frame, 
        	                "Erro ao criar o desenho: " + ex.getMessage(), 
        	                "Erro", 
        	                JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
        	});
        
        
        btnNewButton.setBounds(333, 188, 126, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnExcluirPedido = new JButton("Excluir Pedido");
        //botão de excluir
        btnExcluirPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linha_selecionada = table.getSelectedRow();
                
                if (linha_selecionada == -1) {
                    JOptionPane.showMessageDialog(frame, "Por favor, selecione um pedido para excluir.");
                    return;
                }
                
                // Confirma se o usuário realmente quer excluir
                int confirma = JOptionPane.showConfirmDialog(frame, 
                    "Tem certeza que deseja excluir este pedido?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);
                    
                if (confirma == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(linha_selecionada);
                    
                    // Atualiza os números dos pedidos
                    for (int i = 0; i < model.getRowCount(); i++) {
                        model.setValueAt(i + 1, i, 0);
                    }
                    
                    JOptionPane.showMessageDialog(frame, "Pedido excluído com sucesso!");
                }
            }
        });
        
        btnExcluirPedido.setBounds(85, 188, 144, 23);
        frame.getContentPane().add(btnExcluirPedido);

        // Adiciona ação ao botão
        btn_cria_pedido.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    // entrada de dados
                    String comprimentoStr = inter_comprimento.getText().replace(",", ".");
                    Double comprimento = Double.parseDouble(comprimentoStr);

                    String alturaStr = (String) inter_altura.getSelectedItem();
                    String cor = (String) Inter_cor.getSelectedItem();

                    if (alturaStr == null || cor == null) {
                        throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
                    }

                    // Converte altura para número
                    double altura = Double.parseDouble(alturaStr.replace(" M", "").replace(",", "."));

                    // Variáveis para formatação da data e hora
                    boolean confirmado;
                    LocalDateTime dataHoraAtual = null;
                    String dataHoraFormatada = null;

                    // Verifica qual radio foi selecionado
                    if (radio.isSelected()) {
                        confirmado = true;
                        dataHoraAtual = LocalDateTime.now();
                        
                        // Formata a data no formato brasileiro
                        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        dataHoraFormatada = dataHoraAtual.format(formatoBrasileiro);
                    } else if (radio1.isSelected()) {
                        confirmado = false;
                    } else {
                        throw new IllegalArgumentException("Selecione se o pedido está confirmado.");
                    }

                    if (comprimento <= 0) {
                        throw new IllegalArgumentException("O comprimento deve ser maior que zero.");
                    }

                    // Mensagem com os dados do pedido
                    JOptionPane.showMessageDialog(frame, String.format(
                            "Pedido criado com sucesso!\nComprimento: %.2f m\nAltura: %.2f m\nCor: %s\nConfirmado: %s",
                            comprimento, altura, cor, confirmado ? "Sim" : "Não"));

                    

                    // Adiciona pedido no topo da tabela
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.insertRow(0, new Object[]{
                        model.getRowCount() + 1,  
                        comprimento,              
                        altura,                   
                        cor,                      
                        confirmado ? "Sim" : "Não", 
                        dataHoraFormatada          
                    });
                    
                 // Limpa o campo de comprimento
                    inter_comprimento.setText("");

                    // Desmarca os radio buttons
                    Inter_confirmado.clearSelection();
                    
                    
                    //Tratamento de exceçõess
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: Comprimento inválido. Use números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro inesperado: " + ex.getMessage());
                }
            }
        });

        }
}
