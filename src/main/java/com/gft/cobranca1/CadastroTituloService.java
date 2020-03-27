package com.gft.cobranca1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.cobranca1.model.StatusTitulo;
import com.gft.cobranca1.model.Titulo;
import com.gft.cobranca1.repository.Titulos;
import com.gft.cobranca1.repository.filter.tituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private  Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
		titulos.save(titulo);
	} catch (DataIntegrityViolationException e) {
		throw new IllegalArgumentException("Formato de data inválido");
	}
}
	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.findById(codigo).get();
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		return StatusTitulo.RECEBIDO.getDescricao();
	}

	public  List<Titulo> filtrar(tituloFilter filtro){
		String descricao = filtro.getDescricao()==null?"":filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}

}