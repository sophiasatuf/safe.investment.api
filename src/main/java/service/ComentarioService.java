package service;

import dao.ComentarioDAO;
import java.util.ArrayList;
import java.util.StringJoiner;
import model.Comentario;
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
        new Comentario(-1, userId, publicacaoId, 0, 0, descricao)
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

  // Busca classes inscritas pelo usuario
  public String getComentariosPublicacao(Request request, Response response) {
    int publicacaoId = Integer.parseInt(request.params(":publicacaoId"));
    ArrayList<Comentario> lista = new ArrayList<Comentario>();

    try {
      lista = comentarioDAO.getComentariosPublicacao(publicacaoId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    StringJoiner joiner = new StringJoiner(", ");
    for (Comentario objeto : lista) {
      joiner.add(
        "{ \"codigo\": \"" + Integer.toString(objeto.getCodigo()) + "\""
      );
      joiner.add(
        " \"userId\": \"" + Integer.toString(objeto.getUserId()) + "\""
      );
      joiner.add(
        " \"publicacaoId\": \"" +
        Integer.toString(objeto.getPublicacaoId()) +
        "\""
      );
      joiner.add(" \"likes\": \"" + Integer.toString(objeto.getLikes()) + "\"");
      joiner.add(" \"descricao\":  \"" + objeto.getDescricao() + "\"");
      joiner.add(
        " \"dislikes\":  \"" + Integer.toString(objeto.getDislikes()) + "\" }"
      );
    }

    String resultado = joiner.toString();

    return "[" + resultado + "]";
  }

  // Busca numero de comentarios
  public int getContarComentariosPublicacao(Request request, Response response) {
    int comentarioId = Integer.parseInt(request.params(":comentarioId"));
    int resp = -1;

    try {
      resp = comentarioDAO.getContarComentariosPublicacao(comentarioId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return resp;
  }
}
