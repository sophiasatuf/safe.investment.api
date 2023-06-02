package dao;

import java.sql.*;
import model.Rating;

public class RatingDAO extends DAO {

  public RatingDAO() {
    super();
    conectar();
  }

  public void finalize() {
    close();
  }

  public void insert(Rating rating) throws Exception {
    try {
      Statement st = conexao.createStatement();
      String sql =
        "INSERT INTO userrating (userid, publicacaoId, rating)" +
        "VALUES ('" +
        rating.getUserId() +
        "', '" +
        rating.getPublicacaoId() +
        "', '" +
        rating.getRating() +
        "');";
      System.out.println(sql);
      st.executeUpdate(sql);

      st.close();
    } catch (SQLException u) {
      throw new RuntimeException(u);
    }
  }
}
