package aferidor.dao;

import java.util.List;

interface Dao<T> {
	
	public T salvar();
	
	public T editar();
	
	public T excluir();
	
	public T obterObjeto(Integer id);
	
	public List<T> obterTodos(); 
	

}
