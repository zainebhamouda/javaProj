/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zaine
 */
public class GraphManaging implements IGraphManaging {
    private Connection connection;

    public GraphManaging() throws SQLException {
        // Initialisation de la connexion à la base de données
        // (assurez-vous d'avoir configuré correctement JDBC pour PostgreSQL)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphes", "username", "password");

        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void createGraph(Graph graph) {
        // Logique pour insérer un graphe dans la base de données
    }

    @Override
    public void updateGraph(Graph graph) {
        // Logique pour mettre à jour un graphe dans la base de données
    }

    @Override
    public void deleteGraph(Graph graph) {
        // Logique pour supprimer un graphe de la base de données
    }
}

