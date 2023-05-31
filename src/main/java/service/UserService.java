package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public String esconderSenha(String senha) {
		String resp = null;
    try 
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(senha.getBytes());
      byte[] bytes = md.digest();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      resp = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return resp;
	}
	
	// Cadastro de usuário
	public String insert(Request request, Response response) {
		int age = Integer.parseInt(request.queryParams("age"));
		String cpf = request.queryParams("cpf");
		String email = request.queryParams("email");
		String fullName = request.queryParams("fullName");
		String senha = request.queryParams("senha");
		boolean isProfessor = Boolean.parseBoolean(request.queryParams("isProfessor"));
		
		try {
			userDAO.insert(new User(cpf, email, fullName, esconderSenha(senha), age, -1));
			User user = userDAO.buscaUser(email, senha);
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
	public String login(Request request, Response response) {
		String resposta = "";
		
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		
		User u = userDAO.buscaUser(email, esconderSenha(senha));
		Professor p = professorDAO.get(u.getCodigo());
		if(u != null) {
			resposta =  "ID: " + Integer.toString(u.getCodigo()) + " NOME: " + u.getFullName() + " ISPROFESSOR: " + Boolean.toString(p.getUserID() != -1 ? true : false);
		}
		
		return resposta;
	}
}