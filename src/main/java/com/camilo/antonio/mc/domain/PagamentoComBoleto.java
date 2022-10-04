package com.camilo.antonio.mc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.camilo.antonio.mc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVenciemento;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVenciemento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}


	public Date getDataVenciemento() {
		return dataVenciemento;
	}

	public void setDataVenciemento(Date dataVenciemento) {
		this.dataVenciemento = dataVenciemento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
