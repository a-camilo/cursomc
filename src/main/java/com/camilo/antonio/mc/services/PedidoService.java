package com.camilo.antonio.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilo.antonio.mc.domain.Pedido;
import com.camilo.antonio.mc.repositories.PedidoRepository;
import com.camilo.antonio.mc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Pedido.class.getName()));
	}
}
