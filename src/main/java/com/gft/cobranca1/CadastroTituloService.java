package com.gft.cobranca1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.cobranca1.model.Titulo;
import com.gft.cobranca1.repository.Titulos;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
		titulos.save(titulo);
	} catch (DataIntegrityViolationException e) {
		throw new IllegalArgumentException("Formato de data inválido");
	}
}

}