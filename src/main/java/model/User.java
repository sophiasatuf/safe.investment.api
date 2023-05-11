package model;

// Sempre que uma nova tabela é criada no banco de dados
// cria-se uma classe em model

public class User {
	private int codigo;
    private String CPF;
    private String email;
    private String fullName;
    private String senha;
    private int age;
    
    public User(String CPF, String email, String fullName, String senha, int age, int codigo) {
    	this.codigo = codigo;
        this.CPF = CPF;
        this.email = email;
        this.fullName = fullName;
        this.senha = senha;
        this.age = age;
    }
    
    // Getters
    public String getSenha() {
    	return senha;
    }
    
    public int getCodigo() {
    	return codigo;
    }
    
    public String getCPF() {
        return CPF;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setters
    public void setSenha(String senha) {
    	this.senha = senha;
    }
    
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }
    
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
