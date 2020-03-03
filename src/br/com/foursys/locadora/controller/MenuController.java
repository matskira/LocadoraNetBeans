/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.view.MenuView;
import javax.swing.JOptionPane;

/**
 *
 * @author mpoda
 */
public class MenuController {
     private MenuView viewMenu;

    public MenuController(MenuView viewMenu) {
        this.viewMenu = viewMenu;
    }
    
      public void acaoBotaoSair(){
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do Sistema ?","Atenção",
                                                      JOptionPane.YES_OPTION,
                                                      JOptionPane.CANCEL_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            System.exit(0);
        }
            
    }
}
