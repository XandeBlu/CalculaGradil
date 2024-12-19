package Projeto_WEG;

import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        frame.setBounds(100, 100, 547, 527);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Label e campo de texto para o comprimento
        JLabel lblNewLabel = new JLabel("Digite o comprimento da cerca (Metros)");
        lblNewLabel.setBounds(10, 5, 188, 14);
        frame.getContentPane().add(lblNewLabel);

        inter_comprimento = new JTextField();
        inter_comprimento.setBounds(10, 23, 171, 20);
        frame.getContentPane().add(inter_comprimento);
        inter_comprimento.setColumns(10);

        // ComboBox para altura
        JComboBox<String> inter_altura = new JComboBox<>();
        inter_altura.setModel(new DefaultComboBoxModel<>(new String[]{"1,03 M", "1,53 M", "2,03 M"}));
        inter_altura.setBounds(191, 22, 100, 22);
        frame.getContentPane().add(inter_altura);

        JLabel lblNewLabel_1 = new JLabel("Altura da cerca");
        lblNewLabel_1.setBounds(205, 5, 86, 14);
        frame.getContentPane().add(lblNewLabel_1);

        // ComboBox para cor
        JComboBox<String> Inter_cor = new JComboBox<>();
        Inter_cor.setModel(new DefaultComboBoxModel<>(new String[]{"Sem cor", "Preta", "Branca", "Verde"}));
        Inter_cor.setBounds(302, 22, 100, 22);
        frame.getContentPane().add(Inter_cor);

        JLabel lblNewLabel_2 = new JLabel("Cor da cerca");
        lblNewLabel_2.setBounds(313, 5, 66, 14);
        frame.getContentPane().add(lblNewLabel_2);

        // Botões de rádio para confirmação
        JLabel lblNewLabel_3 = new JLabel("Confirmado?");
        lblNewLabel_3.setBounds(412, 5, 75, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JRadioButton radio = new JRadioButton("Sim");
        Inter_confirmado.add(radio);
        radio.setBounds(405, 22, 49, 23);
        frame.getContentPane().add(radio);

        JRadioButton radio1 = new JRadioButton("Não");
        Inter_confirmado.add(radio1);
        radio1.setBounds(456, 22, 49, 23);
        frame.getContentPane().add(radio1);

        // Botão de criar pedido
        JButton btn_cria_pedido = new JButton("Criar Pedido");
        btn_cria_pedido.setBounds(44, 54, 137, 23);
        frame.getContentPane().add(btn_cria_pedido);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 88, 491, 89);
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
        		Integer.class, Double.class, Double.class, Object.class, String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        
        JButton btn_Materiais = new JButton("Ver detalhes do pedido");
        
        //Botão de Ver materiais
        btn_Materiais.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		int linha_selecionada = table.getSelectedRow();
        		
        		//Verifica se tem alguma linha selecionada
        		if (linha_selecionada == -1) {
        		    JOptionPane.showMessageDialog(frame, "Selecione um pedido primeiro!", "Erro", JOptionPane.WARNING_MESSAGE);
        		    return; 
        		}

        				Double comprimento = (double) table.getValueAt(linha_selecionada, 1);
        				Double altura = (double) table.getValueAt(linha_selecionada, 2);
                       
                        
                        Materiais materiais = new Materiais();
                        materiais.calculo_materiais(comprimento,altura);
                        
                        if(materiais.getDiferenca()==0) {
                        	JOptionPane.showMessageDialog(frame, String.format(
                                    "Materiais necessários \n Telas : %d \nPostes: %d \nFixadores: %d\nParafusos: %d\nTamanho total da cerca: %.2f",
                                    materiais.getQuantTelas(), materiais.getQuantPostes(), materiais.getQuantFixadores(), materiais.getQuantParafusos(), materiais.getTamanhoCerca()));
                        }else {
                        	JOptionPane.showMessageDialog(frame, String.format(
                                    "Materiais necessários \n Telas : %d \nPostes: %d \nFixadores: %d\nParafusos: %d\nTamanho total da cerca: %.2f\nA diferença entre o tamanho pedido e o vendido é de %.2f",
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
        	            Double comprimento = (Double) table.getValueAt(linhaSelecionada, 1);
        	            Double altura = (Double) table.getValueAt(linhaSelecionada, 2);
        	            String cor = (String) table.getValueAt(linhaSelecionada, 3);
        	            
        	            // Cria e exibe a janela de desenho
        	            PainelDesenho desenho = new PainelDesenho(comprimento, altura, cor);
        	            desenho.setVisible(true);
        	            
        	        } catch (Exception ex) {
        	            JOptionPane.showMessageDialog(frame, 
        	                "Erro ao criar o desenho: " + ex.getMessage(), 
        	                "Erro", 
        	                JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
        	});
        
        
        btnNewButton.setBounds(191, 188, 126, 23);
        frame.getContentPane().add(btnNewButton);
        
        JPanel cerca_painel = new JPanel();
        cerca_painel.setBackground(new Color(255, 255, 255));
        cerca_painel.setBounds(10, 232, 491, 228);
        frame.getContentPane().add(cerca_painel);

        // Adiciona ação ao botão
        btn_cria_pedido.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validações de entrada
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

                    // Verifica qual rádio foi selecionado
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

                    // Validações adicionais
                    if (comprimento <= 0) {
                        throw new IllegalArgumentException("O comprimento deve ser maior que zero.");
                    }

                    // Mensagem com os dados do pedido
                    JOptionPane.showMessageDialog(frame, String.format(
                            "Pedido criado com sucesso!\nComprimento: %.2f m\nAltura: %.2f m\nCor: %s\nConfirmado: %s",
                            comprimento, altura, cor, confirmado ? "Sim" : "Não"));

                    // Cria o objeto Cerca (se necessário)
                    Cerca cerca = new Cerca(comprimento, altura, cor, confirmado, dataHoraFormatada);

                    // Adiciona pedido no topo da tabela
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.insertRow(0, new Object[]{
                        model.getRowCount() + 1,  // Número do pedido
                        comprimento,              // Comprimento
                        altura,                   // Altura
                        cor,                      // Cor
                        confirmado ? "Sim" : "Não", // Confirmado
                        dataHoraFormatada          // Data e hora formatadas
                    });

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
