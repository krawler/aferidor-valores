package aferidor.dao;

import java.util.List;

import javafx.util.Callback;

public interface Dao<T> {
	
	public T salvar();
	
	public T editar();
	
	public T excluir();
	
	public T obterObjeto(Integer id);
	
	public List<T> obterTodos(); 
	
	public List<String> listarNomesCombo();

	public List<String> listarNomesComboByFilterField(String campo, Integer id);

}
