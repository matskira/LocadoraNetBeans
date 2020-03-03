/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.view;

import br.com.foursys.locadora.controller.MenuController;
import br.com.foursys.locadora.controller.VendedorController;

/**
 *
 * @author mpoda
 */
public class MenuView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    MenuController controllerMenu = new MenuController(MenuView.this);
    public MenuView() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtClientes = new javax.swing.JButton();
        jbtVendedor = new javax.swing.JButton();
        jbtFilme = new javax.swing.JButton();
        jbtSair = new javax.swing.JButton();
        jbtLocacao = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuItemCliente = new javax.swing.JMenuItem();
        jMenuItemVendedor = new javax.swing.JMenuItem();
        jMenuItemFilme = new javax.swing.JMenuItem();
        jMenuSistema = new javax.swing.JMenu();
        jMenuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jbtClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/view/Img/Icones/clientes.png"))); // NOI18N
        jbtClientes.setText("Clientes");
        jbtClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClientesActionPerformed(evt);
            }
        });

        jbtVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/view/Img/Icones/funcionario.png"))); // NOI18N
        jbtVendedor.setText("Vendedor");
        jbtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVendedorActionPerformed(evt);
            }
        });

        jbtFilme.setIcon(new javax.swing.ImageIcon("C:\\WorkspaceNetBeans\\Locadora\\Icones\\filme.png")); // NOI18N
        jbtFilme.setText("Filme");

        jbtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/view/Img/Icones/sair.png"))); // NOI18N
        jbtSair.setText("Sair");
        jbtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSairActionPerformed(evt);
            }
        });

        jbtLocacao.setIcon(new javax.swing.ImageIcon("C:\\WorkspaceNetBeans\\Locadora\\Icones\\locação.png")); // NOI18N
        jbtLocacao.setText("Locacao");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jbtClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtFilme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtLocacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtClientes)
                    .addComponent(jbtVendedor)
                    .addComponent(jbtFilme)
                    .addComponent(jbtSair, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtLocacao))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenuCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuCadastro.setText("Cadastro");

        jMenuItemCliente.setText("Cliente");
        jMenuItemCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jMenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClienteActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemCliente);

        jMenuItemVendedor.setText("Vendedor");
        jMenuItemVendedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenuItemVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendedorActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemVendedor);

        jMenuItemFilme.setText("Filme");
        jMenuItemFilme.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenuItemFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFilmeActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemFilme);

        jMenuBar1.add(jMenuCadastro);

        jMenuSistema.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenuSistema.setText("Sistema");

        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuSistema.add(jMenuItemSair);

        jMenuBar1.add(jMenuSistema);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendedorActionPerformed
        new VendedorView();
    }//GEN-LAST:event_jMenuItemVendedorActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        this.controllerMenu.acaoBotaoSair();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClienteActionPerformed
        new ClienteView();
    }//GEN-LAST:event_jMenuItemClienteActionPerformed

    private void jbtClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClientesActionPerformed
        new ClienteView();
    }//GEN-LAST:event_jbtClientesActionPerformed

    private void jbtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVendedorActionPerformed
        new VendedorView();
    }//GEN-LAST:event_jbtVendedorActionPerformed

    private void jMenuItemFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFilmeActionPerformed
   
    }//GEN-LAST:event_jMenuItemFilmeActionPerformed

    private void jbtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSairActionPerformed
        this.controllerMenu.acaoBotaoSair();
    }//GEN-LAST:event_jbtSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenuItem jMenuItemCliente;
    private javax.swing.JMenuItem jMenuItemFilme;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemVendedor;
    private javax.swing.JMenu jMenuSistema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtClientes;
    private javax.swing.JButton jbtFilme;
    private javax.swing.JButton jbtLocacao;
    private javax.swing.JButton jbtSair;
    private javax.swing.JButton jbtVendedor;
    // End of variables declaration//GEN-END:variables
}