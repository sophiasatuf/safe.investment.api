package model;

public class Professor {
    private int userID;
    private float avgGrade;
    private boolean stamp;
    private int codigo;

    public Professor(int userID, float avgGrade, boolean stamp, int codigo) {
        this.userID = userID;
        this.avgGrade = avgGrade;
        this.stamp = stamp;
        this.codigo = codigo;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public boolean getStamp() {
        return stamp;
    }

    public void setStamp(boolean stamp) {
        this.stamp = stamp;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
