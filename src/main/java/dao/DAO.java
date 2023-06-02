// Criar conexão com o banco de dados
package dao;

import java.sql.*;

public class DAO {
    protected Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        //String serverName = "localhost";
        String serverName = "safeinvestment.postgres.database.azure.com";
        //String mydatabase = "postgres";
        String mydatabase = "safeinvestment";
        int porta = 5432;
        //String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        //String username = "postgres";
        String username = "safeinvestmentADM@safeinvestment";
        //String password = "26092002";
        String password = "9hDY6c9^";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}