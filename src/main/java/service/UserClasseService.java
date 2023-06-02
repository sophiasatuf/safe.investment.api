package service;

import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.StringJoiner;

import dao.UserClasseDAO;
import model.Classe;
import model.UserClasse;
public class UserClasseService {
  private UserClasseDAO userClasseDAO = new UserClasseDAO();

  public UserClasseService() {}

  // Cadastro de userclasse
  public String insert(Request request, Response response) {
    int classeId = Integer.parseInt(request.queryParams("classeId"));
    int userId = Integer.parseInt(request.queryParams("userId"));

    try {
      userClasseDAO.insert(
        new UserClasse(
          -1,
          userId,
          classeId
        )
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "";
  }

  // Confere se está inscrito
  public Boolean isInscrito(Request request, Response response) {
    int classeId = Integer.parseInt(request.queryParams("classeId"));
    int userId = Integer.parseInt(request.queryParams("userId"));
    Boolean resp = false;

    try {
      resp = userClasseDAO.isInscrito(
        new UserClasse(
          -1,
          userId,
          classeId
        )
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return resp;
  }

    // Busca classes inscritas pelo usuario
    public String buscaPorUserId(Request request, Response response) {
      int userId = Integer.parseInt(request.params(":userId"));
      ArrayList<Classe> lista = new ArrayList<Classe>();
  
      try {
        lista = userClasseDAO.getClassesByUserId(userId);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
  
      StringJoiner joiner = new StringJoiner(", ");
      for (Classe objeto : lista) {
        joiner.add(
          "{ \"codigo\": \"" + Integer.toString(objeto.getCodigo()) + "\""
        );
        joiner.add(" \"titulo\":  \"" + objeto.getTitulo() + "\"");
        joiner.add(" \"descicao\":  \"" + objeto.getDescricao() + "\"");
        joiner.add(
          " \"professorId\":  \"" +
          Integer.toString(objeto.getProfessorId()) +
          "\" }"
        );
      }
  
      String resultado = joiner.toString();
  
      return "[" + resultado + "]";
    }

  // Deleta userclasse
  public String delete(Request request, Response response) {
    int classeId = Integer.parseInt(request.queryParams("classeId"));
    int userId = Integer.parseInt(request.queryParams("userId"));

    try {
      userClasseDAO.delete(
        new UserClasse(
          -1,
          userId,
          classeId
        )
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "";
  }
}
