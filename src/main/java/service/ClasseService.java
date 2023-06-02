package service;

import dao.ClasseDAO;
import dao.UserClasseDAO;
import java.util.ArrayList;
import java.util.StringJoiner;
import model.Classe;
import spark.Request;
import spark.Response;

// Cria a classe
public class ClasseService {

  public ClasseDAO classeDAO = new ClasseDAO();
  public UserClasseDAO userClasseDAO = new UserClasseDAO();

  public ClasseService() {}

  // Cadastro de classe
  public String insert(Request request, Response response) {
    String titulo = request.queryParams("titulo");
    String descricao = request.queryParams("descricao");
    int professorId = Integer.parseInt(request.queryParams("professorId"));

    try {
      classeDAO.insert(new Classe(-1, titulo, descricao, professorId));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }

  // Busca classe com base no título
  public String busca(Request request, Response response) {
    String stringBusca = request.queryParams("stringBusca");
    ArrayList<Classe> lista = new ArrayList<Classe>();

    try {
      lista = classeDAO.getClasseByTitulo(stringBusca);
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

  // Busca classes com base no professorId
  public String buscaProfessor(Request request, Response response) {
    int professorId = Integer.parseInt(request.queryParams("professorId"));
    ArrayList<Classe> lista = new ArrayList<Classe>();

    try {
      lista = classeDAO.getClasseByProfessorId(professorId);
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

  // busca classe com base na classe id
  public String buscaPorId(Request request, Response response) {
    int classeId = Integer.parseInt(request.params(":classeId"));
    Classe c = new Classe();
    int nInscritos = -1;

    try {
      c = classeDAO.get(classeId);
      nInscritos = userClasseDAO.getNInscritos(classeId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    StringJoiner joiner = new StringJoiner(", ");
    joiner.add("{ \"codigo\": \"" + Integer.toString(c.getCodigo()) + "\"");
    joiner.add(" \"titulo\": \"" + c.getTitulo() + "\"");
    joiner.add(" \"descricao\": \"" + c.getDescricao() + "\"");
    joiner.add(" \"nInscritos\": \"" + Integer.toString(nInscritos) + "\"");
    joiner.add(" \"professorId\":  \"" + Integer.toString(c.getProfessorId()) + "\" }");

    String resultado = joiner.toString();

    return resultado;
  }
}
