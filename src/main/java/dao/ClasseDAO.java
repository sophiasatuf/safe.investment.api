package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Classe;
import model.User;

public class ClasseDAO extends DAO {
	public ClasseDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	// Adiciona 1 classe
	public void insert(Classe classe) throws Exception {
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO classe (titulo, descricao, professorid)"
					   + "VALUES ('"
					   + classe.getTitulo() + "', '"
					   + classe.getDescricao() + "', '"
					   + classe.getProfessorId() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			
			st.close();
		}
		catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	// Pesquisa classes com base no título
	public ArrayList<Classe> getClasseByTitulo(String stringBusca) {
		ArrayList<Classe> resultado = new ArrayList<Classe>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + "classe WHERE titulo LIKE '%" + stringBusca + "%';";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {	            	
	        	Classe classe = new Classe(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("descricao"), rs.getInt("professorid"));
	            resultado.add(classe);
	        }
			st.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
}