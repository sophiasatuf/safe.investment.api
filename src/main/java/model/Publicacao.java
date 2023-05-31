package model;

public class Publicacao {
    private int codigo;
    private String urlVideo;
    private String hashtags;
    private String titulo;
    private int classeId;
    private int visualizacoes;
    private int likes;
    private int dislikes;
    private String dataPostagem;

    public Publicacao() {
      this.codigo = -1;
      this.urlVideo = "";
      this.hashtags = "";
      this.titulo = "";
      this.classeId = -1;
      this.visualizacoes = -1;
      this.likes = -1;
      this.dislikes = -1;
      this.dataPostagem = "";
    }

    public Publicacao(int codigo, String urlVideo, String hashtags, String titulo, int classeId, int visualizacoes, int likes, int dislikes, String dataPostagem) {
        this.codigo = codigo;
        this.urlVideo = urlVideo;
        this.hashtags = hashtags;
        this.titulo = titulo;
        this.classeId = classeId;
        this.visualizacoes = visualizacoes;
        this.likes = likes;
        this.dislikes = dislikes;
        this.dataPostagem = dataPostagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClasseId() {
        return classeId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
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

    public String getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(String dataPostagem) {
        this.dataPostagem = dataPostagem;
    }
}

