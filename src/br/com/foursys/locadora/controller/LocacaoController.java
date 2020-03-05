/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.VendedorDAO;
import br.com.foursys.locadora.model.Cliente;
import br.com.foursys.locadora.model.Filme;
import br.com.foursys.locadora.model.Locacao;
import br.com.foursys.locadora.model.Vendedor;
import br.com.foursys.locadora.util.ConnectionFactory;
import br.com.foursys.locadora.view.LocacaoView;
import com.mysql.jdbc.Connection;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Cell;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author mpoda
 */
public class LocacaoController {
    private static final String fileName = "C:/Teste/novo.xls";
    private LocacaoView viewLocacao;
    private Locacao locacao = new Locacao();

    private List<Cliente> listaClientes;
    private List<Vendedor> listaVendedores;
    private List<Filme> listaFilmes;
    private Filme filme = new Filme();

    private Cliente cliente = new Cliente();
    private Vendedor vendedor = new Vendedor();
    private List<Filme> listaFilmesLocacao = new ArrayList<Filme>();

    private Double valorTotal = 0.0;
    private Double valorPago = 0.0;
    private Double troco = 0.0;

    private String formaPagamento = "";

    public LocacaoController(LocacaoView viewLocacao) {
        this.viewLocacao = viewLocacao;
    }

    public void carregarComboCliente() {
        ClienteController controller = new ClienteController();
        listaClientes = controller.buscarTodos();
        this.viewLocacao.getJcbCliente().removeAllItems();
        this.viewLocacao.getJcbCliente().addItem("-Selecione Cliente-");
        for (Cliente cliente : listaClientes) {
            this.viewLocacao.getJcbCliente().addItem(cliente.getNome());

        }
    }

    public void carregarComboVendedor() {
        VendedorController controller = new VendedorController();
        listaVendedores = controller.buscarTudo();
        this.viewLocacao.getJcbVendedor().removeAllItems();
        this.viewLocacao.getJcbVendedor().addItem("-Selecione Vendedor-");
        for (Vendedor vendedor : listaVendedores) {
            this.viewLocacao.getJcbVendedor().addItem(vendedor.getNome());

        }

    }

    public void carregarComboFilme() {
        FilmeController controller = new FilmeController();
        listaFilmes = controller.buscarTudo();
        this.viewLocacao.getJcbFilme().removeAllItems();
        this.viewLocacao.getJcbFilme().addItem("-Selecione Filme-");
        for (Filme filme : listaFilmes) {
            this.viewLocacao.getJcbFilme().addItem(filme.getNome());

        }
    }

    public void incluirFilme() {
        if (this.viewLocacao.getJcbFilme().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecionar um filme");
        } else {
            DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
            filme = listaFilmes.get(this.viewLocacao.getJcbFilme().getSelectedIndex() - 1);
            if (this.filme.isPromocao()) {
                valorTotal += this.filme.getValor_promocional();
            } else {
                valorTotal += this.filme.getValor();
            }
            DecimalFormat formatador = new DecimalFormat(".###");
            this.viewLocacao.getJtfValorTotal().setText("R$" + formatador.format(valorTotal));
            listaFilmesLocacao.add(filme);
            modelo.addRow(new String[]{filme.getCodigo() + "", filme.getNome(), "R$" + filme.getValor(), (filme.isPromocao()) ? "SIM" : "NÃO", "R$ " + filme.getValor_promocional()});

        }
    }

    public void excluirFilme() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
        if (this.viewLocacao.getTabelaLocacao().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmesLocacao.get(this.viewLocacao.getTabelaLocacao().getSelectedRow());

            if (this.filme.isPromocao()) {
                valorTotal -= this.filme.getValor_promocional();
            } else {

                valorTotal -= this.filme.getValor();
            }
            if (valorTotal == -0) {
                valorTotal = 0.0;
            }
            DecimalFormat formatador = new DecimalFormat("#.###");
            this.viewLocacao.getJtfValorTotal().setText("R$ " + formatador.format(valorTotal));
            listaFilmesLocacao.remove(this.viewLocacao.getTabelaLocacao().getSelectedRow());
            modelo.removeRow(this.viewLocacao.getTabelaLocacao().getSelectedRow());
        }
    }

    public void troco() {
        DecimalFormat formatador = new DecimalFormat("#.###");
        try {
            this.viewLocacao.getJtfTroco().setText((formatador.format(Double.parseDouble(this.viewLocacao.getJtfValorPago().getText().trim().replace(",", ".")) - valorTotal)));
        } catch (NumberFormatException e) {
            this.viewLocacao.getJbtCancelar().grabFocus();
            this.viewLocacao.getJtfTroco().setText("");
            this.viewLocacao.getJtfValorPago().setText("");
            this.viewLocacao.getJtfValorPago().grabFocus();

            //JOptionPane.showMessageDialog(null, "Apenas números são permitidos");
        }
    }

    public void acaoBotaoCancelar() {
        limparCampos();
    }

    public void limparCampos() {
        this.viewLocacao.getJcbCliente().setSelectedIndex(0);
        this.viewLocacao.getJcbVendedor().setSelectedIndex(0);
        this.viewLocacao.getJcbFilme().setSelectedIndex(0);
        this.viewLocacao.getJtfTroco().setText(null);
        this.viewLocacao.getJtfValorPago().setText(null);
        this.viewLocacao.getJtfValorTotal().setText(null);
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
        modelo.setRowCount(0);
        this.viewLocacao.getButtonGroup1().clearSelection();
        excluirFilme();
    }

    public void acaoBotaoSalvar() {
        salvarDados();
        gerarExcel();
    }

    public void salvarDados() {
        validarCampos();
        locacao.setCliente(listaClientes.get(this.viewLocacao.getJcbCliente().getSelectedIndex()-1));
        locacao.setVendedor(listaVendedores.get(this.viewLocacao.getJcbCliente().getSelectedIndex()-1));
        locacao.setFilme(listaFilmesLocacao.get(this.viewLocacao.getJcbCliente().getSelectedIndex()-1));
        locacao.setFormaPagamento(this.viewLocacao.getButtonGroup1().getSelection().toString());
    }

    public void validarCampos() {
        if (this.viewLocacao.getJcbCliente().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o Cliente, campo obrigatório.");
        }
        if (this.viewLocacao.getJcbVendedor().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o Vendedor, campo obrigatório.");
        }
        if (this.viewLocacao.getJcbFilme().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o Filme, campo obrigatório.");
        }
    }

    public void acaoBotaoSair() {
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do cadastro ?", "Atenção",
                JOptionPane.YES_OPTION,
                JOptionPane.CANCEL_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            viewLocacao.dispose();
        }

    }
    public void gerarExcel(){
        PrintStream gravador;
		try {
			gravador = new PrintStream("saida.txt");
			gravador.println(locacao.getCliente().toString() + ";");
			gravador.println(locacao.getVendedor().toString() + ";");
			gravador.println(locacao.getFilme().toString() + ";");
			gravador.println(locacao.getFormaPagamento().toString() +";");
			gravador.close();
                        JOptionPane.showMessageDialog(null, "Arquivo gerado!");
                        limparCampos();
		} catch (FileNotFoundException e) {
                    JOptionPane.showConfirmDialog(null, "Não foi possível gravar o arquivo.");
			e.printStackTrace();
		}
//        HSSFWorkbook workbook = new HSSFWorkbook();
//	HSSFSheet sheetLocacoes = workbook.createSheet("Locação");
//        List<Locacao> listaLocacaos = new ArrayList<Locacao>();
//        listaLocacaos.add(locacao);
//        int rownum = 0;
//        Row rowAux = sheetLocacoes.createRow();
//        Cell cellNome = (Cell) row.createCell(1);
//        for (Locacao locacao : listaLocacaos) {
//            Row row = sheetLocacoes.createRow(rownum++);
//            int cellnum = 0;
//            Cell cellNome = (Cell) row.createCell(1, 1);
//            cell
//            
//            
//            
//        }
    

    }
}
