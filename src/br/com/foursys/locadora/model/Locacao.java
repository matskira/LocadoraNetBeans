/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.model;

import java.util.List;

/**
 *
 * @author mpoda
 */
public class Locacao {

    private Cliente cliente;
    private Vendedor vendedor;
    private List<Filme> listaFilmes;
    private Filme filme;
    private double valorTotal;
    private double valorPago;
    private double troco;
    private String formaPagamento;
    
    public Locacao(){
        
    }

    public Locacao(Cliente cliente, Vendedor vendedor, List<Filme> listaFilmes, Filme filme, double valorTotal, double valorPago, double troco, String formaPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.listaFilmes = listaFilmes;
        this.filme = filme;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.troco = troco;
        this.formaPagamento = formaPagamento;
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public void setListaFilmes(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
