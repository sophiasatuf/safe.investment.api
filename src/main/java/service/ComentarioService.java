package service;

import dao.ComentarioDAO;
import model.Comentario;

import java.util.ArrayList;
import java.util.StringJoiner;
import spark.Request;
import spark.Response;

public class ComentarioService {
  private ComentarioDAO comentarioDAO = new ComentarioDAO();
    
  public ComentarioService() {}

  // Cadastro de Comentario
  public String insert(Request request, Response response) {
    int userId = Integer.parseInt(request.queryParams("userId"));
    int publicacaoId = Integer.parseInt(request.queryParams("publicacaoId"));
    String descricao = request.queryParams("descricao");

    try {
      comentarioDAO.insert(
        new Comentario(
          -1,
          userId,
          publicacaoId,
          0,
          0,
          descricao
        )
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "";
  }

  // Likes
  public String incrementaLike(Request request, Response response) {
    int comentarioId = Integer.parseInt(request.params(":comentarioId"));

    try {
      comentarioDAO.updateLikes(comentarioId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }

  // Dislikes
  public String incrementaDislike(Request request, Response response) {
    int comentarioId = Integer.parseInt(request.params(":comentarioId"));

    try {
      comentarioDAO.updateDislikes(comentarioId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }
}
