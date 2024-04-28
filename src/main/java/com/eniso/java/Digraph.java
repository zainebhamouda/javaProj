/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author zaine
 */


public class Digraph extends Graph {
    private final List<int[]> directedEdges; // Liste des arêtes orientées du graphe

    public Digraph() {
        directedEdges = new ArrayList<>();
    }

    // Méthode pour ajouter une arête orientée entre deux sommets
    @Override
    public void addEdge(int from, int to) {
        directedEdges.add(new int[]{from, to});
    }

    // Méthode pour supprimer une arête orientée entre deux sommets
    public void removeEdge(int from, int to) {
        directedEdges.removeIf(edge -> edge[0] == from && edge[1] == to);
    }

    // Méthode pour obtenir les voisins d'un sommet dans un graphe orienté
    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        for (int[] edge : directedEdges) {
            if (edge[0] == vertex) {
                neighbors.add(edge[1]);
            }
        }
        return neighbors;
    }

    // Méthode de parcours en largeur (BFS) pour un graphe orienté
    @Override
    public void BFS(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // Méthode de parcours en profondeur (DFS) pour un graphe orienté
    @Override
    public void DFS(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
    }

    // Méthode récursive pour le parcours en profondeur
    private void dfsRecursive(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    // Algorithme de Dijkstra pour les graphes orientés
    public Map<Integer, Integer> dijkstra(int start) {
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        distance.put(start, 0);
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int vertex = current[0];
            int dist = current[1];

            if (distance.get(vertex) < dist) continue;

            for (int neighbor : getNeighbors(vertex)) {
                int newDist = dist + 1; // Assuming unit weight for each edge
                if (!distance.containsKey(neighbor) || newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    queue.add(new int[]{neighbor, newDist});
                }
            }
        }
        return distance;
    }
}
