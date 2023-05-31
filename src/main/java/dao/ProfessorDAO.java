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
	
	// Busca 1 professor
	public Professor get(int userId) {
		Professor professor = new Professor();
		try {  
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM professor WHERE userid = " + userId + ";";
			System.out.println(sql);
      ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        // System.out.println(rs.get);
        professor =
          new Professor(
            rs.getInt("userid"),
            rs.getFloat("avgGrade"),
            rs.getBoolean("stamp"),
            rs.getInt("codigo")
          );
      }
      st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return professor;
	}
}