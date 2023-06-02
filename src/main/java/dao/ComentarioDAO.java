package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Comentario;

public class ComentarioDAO extends DAO {

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
      String sql =
        "INSERT INTO comentarios (userId, publicacaoId, likes, dislikes, descricao)" +
        "VALUES ('" +
        comentario.getUserId() +
        "', '" +
        comentario.getPublicacaoId() +
        "', '" +
        comentario.getLikes() +
        "', '" +
        comentario.getDislikes() +
        "', '" +
        comentario.getDescricao() +
        "');";
      System.out.println(sql);
      st.executeUpdate(sql);

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }
  }

  // Incrementa Likes através de comentarioId
  public void updateLikes(int comentarioId) {
    try {
      Statement st = conexao.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY
      );
      String sql =
        "UPDATE comentarios" +
        " SET likes = likes + 1" +
        " WHERE codigo = " +
        comentarioId +
        ";";
      System.out.println(sql);
      st.executeUpdate(sql);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // Incrementa Dislikes através de comentarioId
  public void updateDislikes(int comentarioId) {
    try {
      Statement st = conexao.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY
      );
      String sql =
        "UPDATE comentarios" +
        " SET dislikes = dislikes + 1" +
        " WHERE codigo = " +
        comentarioId +
        ";";
      System.out.println(sql);
      st.executeUpdate(sql);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // Retorna todas os comentarios de uma publicacao
  public ArrayList<Comentario> getComentariosPublicacao(int publicacaoId) {
    ArrayList<Comentario> resultado = new ArrayList<Comentario>();
    try {
      Statement st = conexao.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY
      );
      String sql = "SELECT * FROM comentarios WHERE publicacaoid = " + publicacaoId + ";";
      System.out.println(sql);
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        Comentario comentario = new Comentario(
          rs.getInt("codigo"),
          rs.getInt("userId"),
          rs.getInt("publicacaoId"),
          rs.getInt("likes"),
          rs.getInt("dislikes"),
          rs.getString("descricao")
        );
        resultado.add(comentario);
      }
      st.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return resultado;
  }

  // Busca numero de comentarios
  public int getContarComentariosPublicacao(int publicacaoId) {
    int resp = -1;
    try {
      Statement st = conexao.createStatement();
      String sql =
        "SELECT COUNT(*) FROM comentarios WHERE publicacaoid = " + publicacaoId + ";";
      System.out.println(sql);
      ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        resp = rs.getInt("count");
      }

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return resp;
  }
}
