package service;

import dao.ProfessorDAO;
import dao.UserDAO;
import java.util.StringJoiner;
import model.Professor;
import model.User;
import spark.Request;
import spark.Response;

public class ProfessorService {

  private ProfessorDAO professoroDAO = new ProfessorDAO();
  private UserDAO userDAO = new UserDAO();

  public ProfessorService() {}

  // busca de professor
  public String get(Request request, Response response) {
    int professorId = Integer.parseInt(request.params(":professorId"));
    Professor p = new Professor();
    User u = new User();

    try {
      p = professoroDAO.get(professorId);
      u = userDAO.get(p.getUserID());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    StringJoiner joiner = new StringJoiner(", ");
    joiner.add("{ \"codigo\": \"" + Integer.toString(p.getCodigo()) + "\"");
    joiner.add(" \"avggrade\": \"" + Float.toString(p.getAvgGrade()) + "\"");
    joiner.add(" \"userId\": \"" + Integer.toString(p.getUserID()) + "\"");
    joiner.add(" \"professorNome\": \"" + u.getFullName() + "\"");
    joiner.add(" \"stamp\":  \"" + Boolean.toString(p.getStamp()) + "\" }");

    String resultado = joiner.toString();

    return resultado;
  }
}
