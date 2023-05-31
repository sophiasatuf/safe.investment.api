package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Comentario;

public class ComentarioDAO extends DAO{
    public ComentarioDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}

    // Adiciona 1 comentario
	public void insert(Comentario comentario) throws Exception {
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO comentarios (userId, publicacaoId, likes, dislikes, descricao)"
					   + "VALUES ('"
					   + comentario.getUserId() + "', '"
					   + comentario.getPublicacaoId() + "', '"
					   + comentario.getLikes() + "', '"
					   + comentario.getDislikes() + "', '"
					   + comentario.getDescricao() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			
			st.close();
		}
		catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

    // Incrementa Likes através de comentarioId
	public void updateLikes(int comentarioId) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE comentarios" +
						 "SET likes = likes + 1" +
						 "WHERE codigo = " + comentarioId + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Incrementa Dislikes através de comentarioId
	public void updateDislikes(int comentarioId) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE comentarios" +
						 "SET dislikes = dislikes + 1" +
						 "WHERE codigo = " + comentarioId + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
