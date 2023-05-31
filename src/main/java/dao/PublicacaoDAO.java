package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Publicacao;

public class PublicacaoDAO extends DAO {
	public PublicacaoDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	// Adiciona 1 publicacao
	public void insert(Publicacao publicacao) throws Exception {
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO publicacao (urlVideo, hashtags, titulo, classeId, visualizacoes, likes, dislikes, dataPostagem)"
					   + "VALUES ('"
					   + publicacao.getUrlVideo() + "', '"
					   + publicacao.getHashtags() + "', '"
					   + publicacao.getTitulo() + "', '"
					   + publicacao.getClasseId() + "', '"
					   + publicacao.getVisualizacoes() + "', '"
					   + publicacao.getLikes() + "', '"
					   + publicacao.getDislikes() + "', '"
					   + publicacao.getDataPostagem() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			
			st.close();
		}
		catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	// Pesquisa publicacoes com base no título
	public ArrayList<Publicacao> getPublicacaoByTitulo(String stringBusca) {
		ArrayList<Publicacao> resultado = new ArrayList<Publicacao>();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM " + "publicacao WHERE titulo LIKE '%" + stringBusca + "%';";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {	            	
	        	Publicacao publicacao = new Publicacao(rs.getInt("codigo"), rs.getString("urlvideo"), rs.getString("hashtags"), rs.getString("titulo"), rs.getInt("classeid"), rs.getInt("visualizacoes"), rs.getInt("likes"), rs.getInt("dislikes"), rs.getString("datapostagem"));
	            resultado.add(publicacao);
	        }
			st.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	// Pesquisa publicacao pelo id
	public Publicacao get(int publicacaoId) {
		Publicacao resultado = new Publicacao();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM publicacao WHERE codigo = " + publicacaoId + ";";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        resultado = new Publicacao(rs.getInt("codigo"), rs.getString("urlvideo"), rs.getString("hashtags"), rs.getString("titulo"), rs.getInt("classeid"), rs.getInt("visualizacoes"), rs.getInt("likes"), rs.getInt("dislikes"), rs.getString("datapostagem"));
      }
			st.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	// Incrementa Likes através de publicacaoId
	public void updateLikes(int publicacaoId) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE publicacao" +
						 "SET likes = likes + 1" +
						 "WHERE codigo = " + publicacaoId + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Incrementa Dislikes através de publicacaoId
	public void updateDislikes(int publicacaoId) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "UPDATE publicacao" +
						 "SET dislikes = dislikes + 1" +
						 "WHERE codigo = " + publicacaoId + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}