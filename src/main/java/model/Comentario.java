package model;

public class Comentario {
    private int codigo;
    private int userId;
    private int publicacaoId;
    private int likes;
    private int dislikes;
    private String descricao;

    public Comentario(int codigo, int userId, int publicacaoId, int likes, int dislikes, String descricao) {
        this.codigo = codigo;
        this.userId = userId;
        this.publicacaoId = publicacaoId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.descricao = descricao;
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

    public void setPublicacaoId(int publicacaoId) {
        this.publicacaoId = publicacaoId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
