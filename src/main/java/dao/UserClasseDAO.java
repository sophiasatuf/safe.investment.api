package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Classe;
import model.UserClasse;

public class UserClasseDAO extends DAO {

  public UserClasseDAO() {
    super();
    conectar();
  }

  public void finalize() {
    close();
  }

  // Adiciona 1 inscricao em classe
  public void insert(UserClasse userClasse) throws Exception {
    try {
      Statement st = conexao.createStatement();
      String sql =
        "INSERT INTO userclasse (userid, classeid)" +
        "VALUES ('" +
        userClasse.getUserId() +
        "', '" +
        userClasse.getClasseId() +
        "');";
      System.out.println(sql);
      st.executeUpdate(sql);

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }
  }

  // Confere se está inscrito
  public Boolean isInscrito(UserClasse userClasse) throws Exception {
    Boolean resp = false;
    try {
      Statement st = conexao.createStatement();
      String sql =
        "SELECT * FROM userclasse WHERE userid = " +
        userClasse.getUserId() +
        " AND classeid = " +
        userClasse.getClasseId() +
        ";";
      System.out.println(sql);
      ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        resp = true;
      }

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }

    return resp;
  }

  // Deleta inscrição
  public void delete(UserClasse userClasse) throws Exception {
    try {
      Statement st = conexao.createStatement();
      String sql =
        "DELETE FROM userclasse WHERE userid = " +
        userClasse.getUserId() +
        " AND classeid = " +
        userClasse.getClasseId() +
        ";";
      System.out.println(sql);
      st.executeUpdate(sql);

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }
  }

  // Confere se está inscrito
  public int getNInscritos(int classeId) throws Exception {
    int resp = -1;
    try {
      Statement st = conexao.createStatement();
      String sql =
        "SELECT COUNT(*) FROM userclasse WHERE classeid = " + classeId + ";";
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

  // Pesquisa classes com base no userId
  public ArrayList<Classe> getClassesByUserId(int userId) {
    ArrayList<Classe> resultado = new ArrayList<Classe>();
    try {
      Statement st = conexao.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY
      );
      String sql = "SELECT c.* FROM public.userClasse uc JOIN public.classe c ON uc.classeid = c.codigo WHERE uc.userid = " + userId + ";";
      System.out.println(sql);
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        Classe classe = new Classe(
          rs.getInt("codigo"),
          rs.getString("titulo"),
          rs.getString("descricao"),
          rs.getInt("professorid")
        );
        resultado.add(classe);
      }
      st.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return resultado;
  }
}
