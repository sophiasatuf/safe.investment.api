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
	public Professor insert(Professor professor) {
		Professor professorResponse = null;
		System.out.println("Estou aqui");
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO \"professor\" (userID, avgGrade, stamp)"
				       + " VALUES ('"
				       + professor.getUserID() + "', '" 
				       + professor.getAvgGrade() + "', '"
				       + professor.getStamp() + "');";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){	
	        	professorResponse = new Professor(rs.getInt("userID"), rs.getFloat("avgGrade"), rs.getBoolean("stamp"), rs.getInt("codigo"));
	        }
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return professorResponse;
	}
}