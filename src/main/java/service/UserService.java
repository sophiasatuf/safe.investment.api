package service;

import dao.UserDAO;
import spark.Request;
import spark.Response;
import model.User;

// Cria as regras de negócio e recebe os dados da request e retorna uma response

public class UserService {
	private UserDAO userDAO = new UserDAO();
	
	public UserService() {}
	
	// Cadastro de usuário
	public String insert(Request request, Response response) {
		int age = Integer.parseInt(request.queryParams("age"));
		String cpf = request.queryParams("cpf");
		String email = request.queryParams("email");
		String fullName = request.queryParams("fullName");
		String senha = request.queryParams("senha");
		
		try {
			userDAO.insert(new User(cpf, email, fullName, senha, age, -1));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "";
	}
	
	// Login de usuário
	public String login(Request request, Response response) {
		String resposta = "";
		
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		
		User u = userDAO.buscaUser(email, senha);
		if(u != null) {
			resposta = u.getEmail();
		}
		
		return resposta;
	}
}