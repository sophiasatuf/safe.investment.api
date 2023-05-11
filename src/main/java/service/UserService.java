package service;

import dao.ProfessorDAO;
import dao.UserDAO;
import spark.Request;
import spark.Response;
import model.User;
import model.Professor;

// Cria as regras de negócio e recebe os dados da request e retorna uma response

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private ProfessorDAO professorDAO = new ProfessorDAO();
	
	public UserService() {}
	
	// Cadastro de usuário
	public String insert(Request request, Response response) {
		int age = Integer.parseInt(request.queryParams("age"));
		String cpf = request.queryParams("cpf");
		String email = request.queryParams("email");
		String fullName = request.queryParams("fullName");
		String senha = request.queryParams("senha");
		boolean isProfessor = Boolean.parseBoolean(request.queryParams("isProfessor"));
		
		try {
			User user = userDAO.insert(new User(cpf, email, fullName, senha, age, -1));
			int userID = user.getCodigo();
			if(isProfessor) {
				professorDAO.insert(new Professor(userID, 0, false, -1));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	// Login de usuário
	public int login(Request request, Response response) {
		int resposta = -1;
		
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		
		User u = userDAO.buscaUser(email, senha);
		if(u != null) {
			resposta = u.getCodigo();
		}
		
		return resposta;
	}
}