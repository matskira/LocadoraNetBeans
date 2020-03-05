package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.ClienteDAO;
import br.com.foursys.locadora.dao.VendedorDAO;
import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.model.Cliente;
import br.com.foursys.locadora.model.Estado;
import br.com.foursys.locadora.model.Vendedor;
import br.com.foursys.locadora.util.ConnectionFactory;
import br.com.foursys.locadora.view.ClienteView;
import br.com.foursys.locadora.view.VendedorView;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dmunhoz
 */
public class VendedorController {

    private VendedorView viewVendedor;
    private Vendedor vendedor = new Vendedor();
    private List<Vendedor> listaVendedor;
    private List<Cidade> cidades;
    private List<Estado> estados;
    private boolean alterar;

    public VendedorController(VendedorView viewVendedor) {
        this.viewVendedor = viewVendedor;
    }

    public VendedorController() {

    }

    public void alterarVendedor() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendedor.getTabelaVendedor().getModel();
        if (this.viewVendedor.getTabelaVendedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um vendedor");
        } else {
            vendedor = listaVendedor.get(this.viewVendedor.getTabelaVendedor().getSelectedRow());
            this.viewVendedor.getCbSexo().setSelectedItem(vendedor.getSexo() + "");
            this.viewVendedor.getJtfIdade().setText(vendedor.getIdade() + "");
            this.viewVendedor.getJtfNome().setText(vendedor.getNome());
            this.viewVendedor.getCbCidade().setSelectedItem(vendedor.getCidade().toString());
            this.viewVendedor.getCbEstado().setSelectedItem(vendedor.getEstado().toString());
            this.viewVendedor.getJtfAreaDeVendas().setText(vendedor.getArea_venda());
            this.viewVendedor.getJtfSalario().setText(vendedor.getSalario() + "");
            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void excluirVendedor() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendedor.getTabelaVendedor().getModel();
        if (this.viewVendedor.getTabelaVendedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um vendedor");
        } else {
            vendedor = listaVendedor.get(this.viewVendedor.getTabelaVendedor().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?", "Atenção",
                    JOptionPane.YES_OPTION,
                    JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                Connection bd = ConnectionFactory.getConnection();
                VendedorDAO dao = new VendedorDAO(bd);
                try {
                    dao.excluirVendedor(vendedor);
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                    listarVendedores();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!");
                    Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void salvarVendedor() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(this.viewVendedor.getJtfNome().getText());
                Cidade cidade = new Cidade(this.viewVendedor.getCbCidade().getSelectedItem().toString());
                vendedor.setCidade(cidade);
                Estado estado = new Estado(this.viewVendedor.getCbEstado().getSelectedItem().toString(), "");
                vendedor.setEstado(estado);
                vendedor.setSexo(this.viewVendedor.getCbSexo().getSelectedItem().toString().charAt(0));
                vendedor.setIdade(Integer.parseInt(this.viewVendedor.getJtfIdade().getText()));
                vendedor.setArea_venda(this.viewVendedor.getJtfAreaDeVendas().getText());
                vendedor.setSalario(Double.parseDouble(this.viewVendedor.getJtfSalario().getText()));
                Connection bd = ConnectionFactory.getConnection();
                VendedorDAO dao = new VendedorDAO(bd);
                try {
                    dao.inserirVendedor(vendedor);
                    JOptionPane.showMessageDialog(null, "Vendedor inserido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o vendedor.");
                    Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor Inválido!");
                }
                limparCampos();
                bloqueioInicial();
                listarVendedores();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {
                Cidade cidade = new Cidade(this.viewVendedor.getCbCidade().getSelectedItem().toString());
                vendedor.setCidade(cidade);
                Estado estado = new Estado(this.viewVendedor.getCbEstado().getSelectedItem().toString(), "");
                vendedor.setEstado(estado);
                vendedor.setArea_venda(this.viewVendedor.getJtfAreaDeVendas().getText());
                vendedor.setSalario(Double.parseDouble(this.viewVendedor.getJtfSalario().getText()));
                Connection bd = ConnectionFactory.getConnection();
                VendedorDAO dao = new VendedorDAO(bd);
                try {
                    dao.alterarVendedor(vendedor);
                    JOptionPane.showMessageDialog(null, "Vendedor alterado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterado o vendedor.");
                    Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarVendedores();
            }
        }
    }

    public boolean validarSalvar() {
        if (this.viewVendedor.getJtfNome().getText().trim().equals("") || this.viewVendedor.getJtfNome().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe o Nome, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getJtfIdade().getText().trim().equals("") || this.viewVendedor.getJtfIdade().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe a Idade, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getJtfAreaDeVendas().getText().trim().equals("") || this.viewVendedor.getJtfAreaDeVendas().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe a Area de Vendas, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getJtfSalario().getText().trim().equals("") || this.viewVendedor.getJtfSalario().getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe o Salario, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getCbSexo().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o sexo, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getCbCidade().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o sexo, campo obrigatório.");
            return false;
        }

        if (this.viewVendedor.getCbEstado().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o sexo, campo obrigatório.");
            return false;
        }

        return true;
    }

    public List<Vendedor> buscarTudo() {

        try {
            Connection bd = ConnectionFactory.getConnection();
            VendedorDAO dao = new VendedorDAO(bd);
            listaVendedor = dao.buscarTodosVendedores();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaVendedor;
    }

    public void listarVendedores() {
        Connection bd = ConnectionFactory.getConnection();
        VendedorDAO dao = new VendedorDAO(bd);
        try {
            listaVendedor = dao.buscarTodosVendedores();
            carregarTabela();
            bd.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendedor.getTabelaVendedor().getModel();
        modelo.setRowCount(0);
        for (Vendedor listaVendedores : listaVendedor) {
            modelo.addRow(new String[]{listaVendedores.getNome(), listaVendedores.getCidade().getNome().toString(), listaVendedores.getArea_venda()});
        }
    }

    public void carregarComboCidade() {
        CidadeController controller = new CidadeController();
        cidades = controller.buscarCidade();
        this.viewVendedor.getCbCidade().removeAllItems();
        this.viewVendedor.getCbCidade().addItem("-Escolha Cidade-");
        for (Cidade listaCidade : cidades) {
            this.viewVendedor.getCbCidade().addItem(listaCidade.getNome());
        }
    }

    public void carregarComboEstado() {
        EstadoController controller = new EstadoController();
        estados = controller.buscarEstados();
        this.viewVendedor.getCbEstado().removeAllItems();
        this.viewVendedor.getCbEstado().addItem("-Escolha UF-");
        for (Estado listaEstado : estados) {
            this.viewVendedor.getCbEstado().addItem(listaEstado.getNome());
        }
    }

    public void bloqueioInicial() {
        this.viewVendedor.getJbtNovo().setEnabled(true);
        this.viewVendedor.getJbtAlterar().setEnabled(true);
        this.viewVendedor.getJbtExcluir().setEnabled(true);
        this.viewVendedor.getJbtSair().setEnabled(true);
        this.viewVendedor.getJbtSalvar().setEnabled(false);
        this.viewVendedor.getJbtCancelar().setEnabled(false);

        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewVendedor.getJtfPesquisarNome().setEditable(true);
        this.viewVendedor.getJtfPesquisarNome().grabFocus();
        this.viewVendedor.getJtfIdade().setEditable(false);
        this.viewVendedor.getCbSexo().setEnabled(false);
        this.viewVendedor.getJtfNome().setEditable(false);
        this.viewVendedor.getCbCidade().setEnabled(false);
        this.viewVendedor.getCbEstado().setEnabled(false);
        this.viewVendedor.getJtfAreaDeVendas().setEditable(false);
        this.viewVendedor.getJtfSalario().setEditable(false);
        limparCampos();
    }

    public void liberarCampos() {
        this.viewVendedor.getJtfPesquisarNome().setEditable(false);
        //this.viewCliente.getJtfCpf().grabFocus();
        this.viewVendedor.getJtfIdade().setEditable(true);
        this.viewVendedor.getCbSexo().setEnabled(true);
        this.viewVendedor.getJtfNome().setEditable(true);
        this.viewVendedor.getCbCidade().setEnabled(true);
        this.viewVendedor.getCbEstado().setEnabled(true);
        this.viewVendedor.getJtfAreaDeVendas().setEditable(true);
        this.viewVendedor.getJtfSalario().setEditable(true);
    }

    public void limparCampos() {
        this.viewVendedor.getJtfIdade().setText(null);
        this.viewVendedor.getCbSexo().setSelectedIndex(0);
        this.viewVendedor.getJtfNome().setText(null);
        this.viewVendedor.getCbCidade().setSelectedIndex(0);
        this.viewVendedor.getCbEstado().setSelectedIndex(0);
        this.viewVendedor.getJtfAreaDeVendas().setText(null);
        this.viewVendedor.getJtfSalario().setText(null);
    }

    public void acaoBotaoCancelar() {
        this.viewVendedor.getJbtNovo().setEnabled(true);
        this.viewVendedor.getJbtAlterar().setEnabled(true);
        this.viewVendedor.getJbtExcluir().setEnabled(true);
        this.viewVendedor.getJbtSair().setEnabled(true);
        this.viewVendedor.getJbtSalvar().setEnabled(false);
        this.viewVendedor.getJbtCancelar().setEnabled(false);
        limparCampos();
        bloquearCampos();
    }

    public void acaoBotaoAlterar() {
        this.viewVendedor.getJbtNovo().setEnabled(false);
        this.viewVendedor.getJbtAlterar().setEnabled(false);
        this.viewVendedor.getJbtExcluir().setEnabled(false);
        this.viewVendedor.getJbtSair().setEnabled(false);
        this.viewVendedor.getJbtSalvar().setEnabled(true);
        this.viewVendedor.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.viewVendedor.getJtfNome().setEditable(false);
        this.viewVendedor.getJtfIdade().setEditable(false);
        this.viewVendedor.getJtfAreaDeVendas().grabFocus();
        this.viewVendedor.getCbSexo().setEnabled(false);
    }

    public void acaoBotaoNovo() {
        this.viewVendedor.getJbtNovo().setEnabled(false);
        this.viewVendedor.getJbtAlterar().setEnabled(false);
        this.viewVendedor.getJbtExcluir().setEnabled(false);
        this.viewVendedor.getJbtSair().setEnabled(false);
        this.viewVendedor.getJbtSalvar().setEnabled(true);
        this.viewVendedor.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.alterar = false;
    }

    public void acaoBotaoSair() {
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do cadastro ?", "Atenção",
                JOptionPane.YES_OPTION,
                JOptionPane.CANCEL_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            viewVendedor.dispose();
        }

    }

}
