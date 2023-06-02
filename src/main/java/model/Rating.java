package model;

public class Rating {

  private int codigo;
  private int userId;
  private int publicacaoId;
  private int rating;

  public Rating(int codigo, int userId, int publicacaoId, int rating) {
    this.codigo = codigo;
    this.userId = userId;
    this.publicacaoId = publicacaoId;
    this.rating = rating;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getPublicacaoId() {
    return publicacaoId;
  }

  public void setPublicacaoIdId(int classeId) {
    this.publicacaoId = classeId;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int newRating) {
    this.rating = newRating;
  }
}
