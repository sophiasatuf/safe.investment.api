package service;

import model.Classe;
import dao.ClasseDAO;
import spark.Request;
import spark.Response;
import java.util.ArrayList;

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
	public ArrayList<Classe> busca(Request request, Response response){
		String stringBusca = request.queryParams("stringBusca");
		ArrayList<Classe> resultado = new ArrayList<Classe>();
		
		try {
			resultado = classeDAO.getClasseByTitulo(stringBusca);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
}
