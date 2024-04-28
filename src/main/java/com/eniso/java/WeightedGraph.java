/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

/**
 *
 * @author zaine
 */
import java.util.*;

public class WeightedGraph extends Graph {
    private final Map<Integer, Map<Integer, Integer>> weights; // Map pour stocker les poids des arêtes

    public WeightedGraph() {
        weights = new HashMap<>();
    }

    // Méthode pour ajouter une arête pondérée entre deux sommets avec un poids spécifié
    public void addEdge(int from, int to, int weight) {
        weights.putIfAbsent(from, new HashMap<>());
        weights.get(from).put(to, weight);
    }
    // Méthode pour ajouter une arête pondérée entre deux sommets avec un poids spécifié
    @Override
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0); // Par défaut, le poids est 0
    }

    // Méthode pour supprimer une arête pondérée entre deux sommets
    public void removeEdge(int from, int to) {
        if (weights.containsKey(from)) {
            weights.get(from).remove(to);
        }
    }

    // Méthode pour vérifier si une arête pondérée existe entre deux sommets
    public boolean hasEdge(int from, int to) {
        return weights.containsKey(from) && weights.get(from).containsKey(to);
    }

    // Méthode pour obtenir les voisins d'un sommet dans un graphe pondéré
    @Override
    public List<Integer> getNeighbors(int vertex) {
        return new ArrayList<>(weights.containsKey(vertex) ? weights.get(vertex).keySet() : Collections.emptySet());
    }

    // Méthode pour obtenir le poids d'une arête entre deux sommets dans un graphe pondéré
    public int getWeight(int from, int to) {
        return weights.containsKey(from) && weights.get(from).containsKey(to) ? weights.get(from).get(to) : 0;
    }

    // Méthode de parcours en largeur (BFS) pour un graphe pondéré
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

    // Méthode de parcours en profondeur (DFS) pour un graphe pondéré
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

    // Algorithme de Dijkstra pour les graphes pondérés
    @Override
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
                int newDist = dist + getWeight(vertex, neighbor);
                if (!distance.containsKey(neighbor) || newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    queue.add(new int[]{neighbor, newDist});
                }
            }
        }
        return distance;
    }
}

