package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Professor;

public class ProfessorDAO extends DAO {
	public ProfessorDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	// Adiciona 1 professor
	public void insert(Professor professor) {
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO \"professor\" (userID, avgGrade, stamp)"
				       + " VALUES ('"
				       + professor.getUserID() + "', '" 
				       + professor.getAvgGrade() + "', '"
				       + professor.getStamp() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
	}
}