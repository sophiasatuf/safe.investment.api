package model;

public class UserClasse {
    private int codigo;
    private int userId;
    private int classeId;

    public UserClasse(int codigo, int userId, int classeId) {
        this.codigo = codigo;
        this.userId = userId;
        this.classeId = classeId;
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

    public int getClasseId() {
        return classeId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }
}
