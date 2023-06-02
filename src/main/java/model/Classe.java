package model;

public class Classe {

  private int codigo;
  private String titulo;
  private String descricao;
  private int professorId;

  public Classe() {
    this.codigo = -1;
    this.titulo = "";
    this.descricao = "";
    this.professorId = -1;
  }

  public Classe(int codigo, String titulo, String descricao, int professorId) {
    this.codigo = codigo;
    this.titulo = titulo;
    this.descricao = descricao;
    this.professorId = professorId;
  }

  // Getters and Setters

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getProfessorId() {
    return professorId;
  }

  public void setProfessorId(int professorId) {
    this.professorId = professorId;
  }
}
