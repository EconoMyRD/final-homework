package br.com.economy.model;

import java.util.ArrayList;
import java.util.List;

import br.com.economy.entities.Transacao;

public class ModelAllTransaction {
	
	private List<Transacao> data;

	public List<Transacao> getData() {
		return data;
	}

	public void setData(List<Transacao> data) {
		this.data = data;
	}
	
}
