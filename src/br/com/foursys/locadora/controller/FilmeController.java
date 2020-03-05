/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.FilmeDAO;
import br.com.foursys.locadora.model.Filme;
import br.com.foursys.locadora.util.ConnectionFactory;
import br.com.foursys.locadora.view.FilmeView;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Responsável pelo Filme Controller
 *
 * @author mpoda
 */
public class FilmeController {

    private FilmeView viewFilme;
    private Filme filme = new Filme();
    private List<Filme> listaFilmes;
    private boolean alterar;

    public FilmeController(FilmeView viewFilme) {
        this.viewFilme = viewFilme;
    }
    
    public FilmeController(){
        
    }

    public void salvarFilme() {
        if (alterar == false) {
            //incluir registro
            if (validarSalvar()) {
                try {
                    Filme filme = new Filme();
                    filme.setCodigo(Integer.parseInt(this.viewFilme.getJtfCodigo().getText()));
                    filme.setNome(this.viewFilme.getJtfNome().getText());
                    filme.setValor(Double.parseDouble(this.viewFilme.getJtfValor().getText()));
                    filme.setValor_promocional(Double.parseDouble(this.viewFilme.getJtfValorPromocao().getText()));
                    filme.setDisponivel((this.viewFilme.getJcbDisponivel().getSelectedItem().toString() == "SIM") ? true : false);
                    filme.setPromocao((this.viewFilme.getJcbPromocao().getSelectedItem().toString() == "SIM") ? true : false);
                    String genero = verificaGenero();
                    filme.setGenero(genero);
                    Connection bd = ConnectionFactory.getConnection();
                    FilmeDAO dao = new FilmeDAO(bd);

                    dao.inserirFilme(filme);
                    JOptionPane.showMessageDialog(null, "Filme inserido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o Filme.");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor Inválido");
                }
                limparCampos();
                bloqueioInicial();
                listarFilmes();

            }
        } else {
            //alterar registro
            if (validarSalvar()) {
                filme.setValor(Double.parseDouble(this.viewFilme.getJtfValor().getText()));
                filme.setValor_promocional(Double.parseDouble(this.viewFilme.getJtfValorPromocao().getText()));
                filme.setDisponivel((this.viewFilme.getJcbDisponivel().getSelectedItem().toString() == "SIM") ? true : false);
                filme.setPromocao((this.viewFilme.getJcbPromocao().getSelectedItem().toString() == "SIM") ? true : false);
                String genero = verificaGenero();
                filme.setGenero(genero);
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.alterarFilme(filme);
                    JOptionPane.showMessageDialog(null, "Filme alterado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar o Filme.");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFilmes();

            }
        }
    }

    public void alterarFilme() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        if (this.viewFilme.getTabelaFilme().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmes.get(this.viewFilme.getTabelaFilme().getSelectedRow());
            this.viewFilme.getJtfCodigo().setText(filme.getCodigo() + "");
            this.viewFilme.getJtfNome().setText(filme.getNome());
            this.viewFilme.getJtfValor().setText(filme.getValor() + "");
            this.viewFilme.getJtfValorPromocao().setText(filme.getValor_promocional() + "");
            this.viewFilme.getJcbDisponivel().setSelectedItem((filme.isDisponivel()) ? "SIM" : "NÃO");
            this.viewFilme.getJcbPromocao().setSelectedItem((filme.isPromocao()) ? "SIM" : "NÃO");
            carregarCheckBoxGenero();
            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void excluirFilme() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        if (this.viewFilme.getTabelaFilme().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmes.get(this.viewFilme.getTabelaFilme().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?", "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.excluirFilme(filme);
                    JOptionPane.showMessageDialog(null, "Filme excluido com sucesso!");
                    listarFilmes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o filme!");
                    Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public List<Filme> buscarTudo() {
        Connection bd = ConnectionFactory.getConnection();
        FilmeDAO dao = new FilmeDAO(bd);
        try {
            listaFilmes = dao.buscarTodosFilmes();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFilmes;
    }
    
    public void listarFilmes() {
        Connection bd = ConnectionFactory.getConnection();
        FilmeDAO dao = new FilmeDAO(bd);
        try {
            listaFilmes = dao.buscarTodosFilmes();
            carregarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarSalvar() {
        if (this.viewFilme.getJtfCodigo().getText().trim().equals("") || this.viewFilme.getJtfCodigo().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe código, campo obrigatório");
            return false;
        }
        if (this.viewFilme.getJtfNome().getText().trim().equals("") || this.viewFilme.getJtfNome().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe nome, campo obrigatório");
            return false;
        }
        if (this.viewFilme.getJtfValor().getText().trim().equals("") || this.viewFilme.getJtfValor().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe valor, campo obrigatório");
            return false;
        }
        if (this.viewFilme.getJtfValorPromocao().getText().trim().equals("") || this.viewFilme.getJtfValorPromocao().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe valor promocional, campo obrigatório");
            return false;
        }
        if (this.viewFilme.getJcbDisponivel().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe a disponibilidade, campo obrigatório.");
            return false;
        }
        if (this.viewFilme.getJcbPromocao().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe a promocao, campo obrigatório.");
            return false;
        }
        if (!this.viewFilme.getJchAcao().isSelected() && !this.viewFilme.getJchFiccao().isSelected() && !this.viewFilme.getJchTerror().isSelected()
                && !this.viewFilme.getJchComedia().isSelected() && !this.viewFilme.getJchInfantil().isSelected() && !this.viewFilme.getJchAnimacao().isSelected()
                && !this.viewFilme.getJchAventura().isSelected() && !this.viewFilme.getJchOutro().isSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione o genero, compo obrigatório");
            return false;
        }

        return true;
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        modelo.setRowCount(0);
        for (Filme listaFilme : listaFilmes) {

            String genero = carregarGeneroTabela(listaFilme.getGenero());
            modelo.addRow(new String[]{listaFilme.getNome(), genero, "R$ " + listaFilme.getValor(),
                (listaFilme.isDisponivel()) ? "SIM" : "NÃO", (listaFilme.isPromocao()) ? "SIM" : "NÃO", "R$ " + listaFilme.getValor_promocional()});
        }
    }

    public void bloqueioInicial() {
        this.viewFilme.getJbtNovo().setEnabled(true);
        this.viewFilme.getJbtAlterar().setEnabled(true);
        this.viewFilme.getJbtExcluir().setEnabled(true);
        this.viewFilme.getJbtSair().setEnabled(true);
        this.viewFilme.getJbtSalvar().setEnabled(false);
        this.viewFilme.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewFilme.getJtfCodigo().setEditable(false);
        this.viewFilme.getJtfNome().setEditable(false);
        this.viewFilme.getJtfValor().setEditable(false);
        this.viewFilme.getJtfValorPromocao().setEditable(false);
        this.viewFilme.getJcbDisponivel().setEnabled(false);
        this.viewFilme.getJcbPromocao().setEnabled(false);
        this.viewFilme.getJchAcao().setEnabled(false);
        this.viewFilme.getJchFiccao().setEnabled(false);
        this.viewFilme.getJchTerror().setEnabled(false);
        this.viewFilme.getJchComedia().setEnabled(false);
        this.viewFilme.getJchInfantil().setEnabled(false);
        this.viewFilme.getJchAnimacao().setEnabled(false);
        this.viewFilme.getJchAventura().setEnabled(false);
        this.viewFilme.getJchOutro().setEnabled(false);
        this.viewFilme.getJtfPesquisarNome().setEnabled(true);
        this.viewFilme.getJtfPesquisarNome().grabFocus();
    }

    public void acaoBotaoNovo() {
        this.viewFilme.getJbtNovo().setEnabled(false);
        this.viewFilme.getJbtAlterar().setEnabled(false);
        this.viewFilme.getJbtExcluir().setEnabled(false);
        this.viewFilme.getJbtSair().setEnabled(false);
        this.viewFilme.getJbtSalvar().setEnabled(true);
        this.viewFilme.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.alterar = false;
    }

    public void liberarCampos() {
        this.viewFilme.getJtfCodigo().setEditable(true);
        this.viewFilme.getJtfCodigo().grabFocus();
        this.viewFilme.getJtfNome().setEditable(true);
        this.viewFilme.getJtfValor().setEditable(true);
        this.viewFilme.getJtfValorPromocao().setEditable(true);
        this.viewFilme.getJcbDisponivel().setEnabled(true);
        this.viewFilme.getJcbPromocao().setEnabled(true);
        this.viewFilme.getJchAcao().setEnabled(true);
        this.viewFilme.getJchFiccao().setEnabled(true);
        this.viewFilme.getJchTerror().setEnabled(true);
        this.viewFilme.getJchComedia().setEnabled(true);
        this.viewFilme.getJchInfantil().setEnabled(true);
        this.viewFilme.getJchAnimacao().setEnabled(true);
        this.viewFilme.getJchAventura().setEnabled(true);
        this.viewFilme.getJchOutro().setEnabled(true);
        this.viewFilme.getJtfPesquisarNome().setEnabled(false);
    }

    public void acaoBotaoCancelar() {
        this.viewFilme.getJbtNovo().setEnabled(true);
        this.viewFilme.getJbtAlterar().setEnabled(true);
        this.viewFilme.getJbtExcluir().setEnabled(true);
        this.viewFilme.getJbtSair().setEnabled(true);
        this.viewFilme.getJbtSalvar().setEnabled(false);
        this.viewFilme.getJbtCancelar().setEnabled(false);
        limparCampos();
        bloquearCampos();
    }

    public void acaoBotaoAlterar() {
        this.viewFilme.getJbtNovo().setEnabled(false);
        this.viewFilme.getJbtAlterar().setEnabled(false);
        this.viewFilme.getJbtExcluir().setEnabled(false);
        this.viewFilme.getJbtSair().setEnabled(false);
        this.viewFilme.getJbtSalvar().setEnabled(true);
        this.viewFilme.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.viewFilme.getJtfCodigo().setEditable(false);
        this.viewFilme.getJtfNome().setEditable(false);

    }

    public void limparCampos() {
        this.viewFilme.getJtfCodigo().setText(null);
        this.viewFilme.getJtfNome().setText(null);
        this.viewFilme.getJtfValor().setText(null);
        this.viewFilme.getJtfValorPromocao().setText(null);
        this.viewFilme.getJcbDisponivel().setSelectedIndex(0);
        this.viewFilme.getJcbPromocao().setSelectedIndex(0);
        this.viewFilme.getJchAcao().setSelected(false);
        this.viewFilme.getJchFiccao().setSelected(false);
        this.viewFilme.getJchTerror().setSelected(false);
        this.viewFilme.getJchComedia().setSelected(false);
        this.viewFilme.getJchInfantil().setSelected(false);
        this.viewFilme.getJchAnimacao().setSelected(false);
        this.viewFilme.getJchAventura().setSelected(false);
        this.viewFilme.getJchOutro().setSelected(false);

    }

    public String verificaGenero() {
        String retorno = "";
        if (this.viewFilme.getJchAcao().isSelected()) {
            retorno += "Ação;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchFiccao().isSelected()) {
            retorno += "Ficção;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchTerror().isSelected()) {
            retorno += "Terror;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchComedia().isSelected()) {
            retorno += "Comédia;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchInfantil().isSelected()) {
            retorno += "Infantil;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchAnimacao().isSelected()) {
            retorno += "Animação;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchAventura().isSelected()) {
            retorno += "Aventura;";
        } else {
            retorno += " ;";

        }
        if (this.viewFilme.getJchOutro().isSelected()) {
            retorno += "Outro";
        } else {
            retorno += " ;";

        }

        return retorno;
    }

    public void carregarCheckBoxGenero() {
        String genero[] = filme.getGenero().split(";");
        if (!genero[0].equals(" ")) {
            this.viewFilme.getJchAcao().setSelected(true);
        }
        if (!genero[1].equals(" ")) {
            this.viewFilme.getJchFiccao().setSelected(true);
        }
        if (!genero[2].equals(" ")) {
            this.viewFilme.getJchTerror().setSelected(true);
        }
        if (!genero[3].equals(" ")) {
            this.viewFilme.getJchComedia().setSelected(true);
        }
        if (!genero[4].equals(" ")) {
            this.viewFilme.getJchInfantil().setSelected(true);
        }
        if (!genero[5].equals(" ")) {
            this.viewFilme.getJchAnimacao().setSelected(true);
        }
        if (!genero[6].equals(" ")) {
            this.viewFilme.getJchAventura().setSelected(true);
        }
        if (!genero[7].equals(" ")) {
            this.viewFilme.getJchOutro().setSelected(true);
        }
    }

    public String carregarGeneroTabela(String aux) {
        String genero[] = aux.split(";");
        String retorno = "";
        if (!genero[0].equals(" ")) {
            retorno += "Ação";
        }
        if (!genero[1].equals(" ")) {
            retorno += " Ficção";
        }
        if (!genero[2].equals(" ")) {

            retorno += " Terror";
        }
        if (!genero[3].equals(" ")) {
            retorno += " Comédia";
        }
        if (!genero[4].equals(" ")) {
            retorno += " Infantil";
        }
        if (!genero[5].equals(" ")) {
            retorno += " Animação";
        }
        if (!genero[6].equals(" ")) {
            retorno += " Aventura";
        }
        if (!genero[7].equals(" ")) {
            retorno += " Outro";
        }
        return retorno;
    }

    public void acaoBotaoSair() {
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do cadastro ?", "Atenção",
                JOptionPane.YES_OPTION,
                JOptionPane.CANCEL_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            viewFilme.dispose();
        }

    }
}
