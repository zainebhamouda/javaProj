/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

/**
 *
 * @author zaine
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Cette classe implémente le Singleton Pattern pour gérer la connexion à la base de données en utilisant JDBC et PostgreSQL.
 * Elle assure qu'une seule instance de la connexion à la base de données est créée dans toute l'application.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Constructeur privé pour empêcher l'instanciation en dehors de la classe
    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/database", "username", "password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la connexion à la base de données", e);
        }
    }

    // Méthode statique pour obtenir l'instance unique de la classe DatabaseConnection
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion à la base de données
    public Connection getConnection() {
        return connection;
    }

    // Méthode pour fermer la connexion à la base de données
     public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la fermeture de la connexion à la base de données", e);
            }
        }
    }

    // Méthode pour exécuter une requête SQL et obtenir un ResultSet
    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour exécuter une mise à jour (insertion, mise à jour, suppression) SQL
    public int executeUpdate(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Méthode pour fermer un ResultSet
    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour fermer un PreparedStatement
    public void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Exemple de méthode pour insérer des données dans une table
    public void insertData(String name, int age) {
        String query = "INSERT INTO users (name, age) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exemple de méthode pour récupérer des données d'une table
    public void getData() {
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("Name: " + name + ", Age: " + age);
            }
            closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
