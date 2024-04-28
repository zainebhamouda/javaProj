/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaine
 */


public abstract class Graph {
    protected List<Integer> vertices; // Liste des sommets dans le graphe
    protected Map<Integer, List<Integer>> edges; // Tableau des paires de sommets représentant les connexions entre les sommets

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new HashMap<>();
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public Map<Integer, List<Integer>> getEdges() {
        return edges;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(Map<Integer, List<Integer>> edges) {
        this.edges = edges;
    }

    // Méthode pour ajouter un sommet au graphe
    public void addVertex(int vertex) {
        vertices.add(vertex);
        edges.put(vertex, new ArrayList<>());
    }

    // Méthode abstraite pour ajouter une arête au graphe
    public abstract void addEdge(int v1, int v2);

    // Méthode pour afficher le graphe
    public void printGraph() {
        for (int v : vertices) {
            System.out.print(v + " : ");
            for (int neighbor : edges.get(v)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Méthode abstraite pour le parcours en largeur (BFS)
    public abstract void BFS(int startVertex);

    // Méthode abstraite pour le parcours en profondeur (DFS)
    public abstract void DFS(int startVertex);

    // Méthode abstraite pour l'algorithme de Dijkstra
    public abstract Map<Integer, Integer> dijkstra(int start);

    // Méthode pour obtenir les voisins d'un sommet
    public List<Integer> getNeighbors(int vertex) {
        return edges.getOrDefault(vertex, Collections.emptyList());
    }
}

