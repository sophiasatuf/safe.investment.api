package service;

import model.Classe;
import dao.ClasseDAO;
import spark.Request;
import spark.Response;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.StringJoiner;

// Cria a classe
public class ClasseService {
	public ClasseDAO classeDAO = new ClasseDAO();
	
	public ClasseService() {}
	
	// Cadastro de classe
	public String insert(Request request, Response response) {
	    String titulo = request.queryParams("titulo");
	    String descricao = request.queryParams("descricao");
	    int professorId = Integer.parseInt(request.queryParams("professorId"));
	    
	    try {
			classeDAO.insert(new Classe(-1, titulo, descricao, professorId));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	    return "";
	}
	
	// Busca classe com base no título
	public String busca(Request request, Response response){
		String stringBusca = request.queryParams("stringBusca");
		ArrayList<Classe> lista = new ArrayList<Classe>();
		
		try {
			lista = classeDAO.getClasseByTitulo(stringBusca);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		StringJoiner joiner = new StringJoiner(", ");
        for (Classe objeto : lista) {
        	joiner.add("{ \"codigo\": \"" + Integer.toString(objeto.getCodigo()) + "\"");
        	joiner.add(" \"titulo\":  \"" + objeto.getTitulo() + "\"");
            joiner.add(" \"descicao\":  \"" + objeto.getDescricao() + "\"");
            joiner.add(" \"professorId\":  \"" + Integer.toString(objeto.getProfessorId()) + "\" }");
        }

        String resultado = joiner.toString();
		
		//String resultado = lista.stream().map(Object::toString).collect(Collectors.joining(", "));
		
		System.out.println(resultado);
		return resultado;
	}
	
	// Busca classes com base no professorId
	public String buscaProfessor(Request request, Response response){
		int professorId = Integer.parseInt(request.queryParams("professorId"));
		ArrayList<Classe> lista = new ArrayList<Classe>();
		
		try {
			lista = classeDAO.getClasseByProfessorId(professorId);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		StringJoiner joiner = new StringJoiner(", ");
        for (Classe objeto : lista) {
        	joiner.add("{ \"codigo\": \"" + Integer.toString(objeto.getCodigo()) + "\"");
        	joiner.add(" \"titulo\":  \"" + objeto.getTitulo() + "\"");
            joiner.add(" \"descicao\":  \"" + objeto.getDescricao() + "\"");
            joiner.add(" \"professorId\":  \"" + Integer.toString(objeto.getProfessorId()) + "\" }");
        }

        String resultado = joiner.toString();
		
		return resultado;
	}
	
}
