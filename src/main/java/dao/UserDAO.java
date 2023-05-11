package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

// Conectar com a tabela do banco de dados

public class UserDAO extends DAO {
	
	public UserDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	// Adiciona 1 usuário
	public User insert(User user) {
		User userResponse = null;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO \"user\" (cpf, email, full_name, senha, age)"
				       + " VALUES ('"
				       + user.getCPF() + "', '" 
				       + user.getEmail() + "', '"
				       + user.getFullName() + "', '"
				       + user.getSenha() + "', '"
				       + user.getAge() + "');";
			System.out.println(sql);
			st.executeQuery(sql);
	        
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		userResponse = buscaUser(user.getEmail(), user.getSenha());
		return userResponse;
	}
	
	// Pesquisa 1 usuário baseado no codigo (chave única)
	public User get(int codigo) {
		User user = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM user WHERE codigo=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 user = new User(rs.getString("cpf"), rs.getString("email"), rs.getString("fullName"), rs.getString("senha"), rs.getInt("age"), rs.getInt("codigo"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
	}
	
	
	public List<User> get() {
		return get("");
	}

	// Pesquisa vários usuários baseados em parâmetro string
	public List<User> getOrderByCodigo() {
		return get("codigo");		
	}
	
	private List<User> get(String orderBy) {	
	
		List<User> xs = new ArrayList<User>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + "user" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	User user = new User(rs.getString("cpf"), rs.getString("email"), rs.getString("fullName"), rs.getString("senha"), rs.getInt("age"), rs.getInt("codigo"));
	            xs.add(user);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return xs;
	}
	
	// Atualiza um usuário existente
	public boolean update(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql =
			        "UPDATE usuario SET email = '" +
			        user.getEmail() +
			        "', full_name = '" +
			        user.getFullName() +
			        "', age = '" +
			        user.getAge() +
			        "', senha = '" +
			        user.getSenha() +
			        "', cpf = '" +
			        user.getCPF() +
			        "'" +
			        " WHERE codigo = " +
			        user.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	// Deleta 1 usuário de acordo com o codigo
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM user WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	// Busca 1 usuário de acordo com email e senha
	public User buscaUser(String email, String senha) {
		User user = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"user\" WHERE email='" + email + "' AND senha='" + senha + "\'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){
	        	 user = new User(rs.getString("cpf"), rs.getString("email"), rs.getString("full_name"), rs.getString("senha"), rs.getInt("age"), rs.getInt("codigo"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
	}
}