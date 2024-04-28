/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author zaine
 */



public class UndirectedGraph extends Graph {

    public UndirectedGraph() {
        super();
    }

    // Méthode pour ajouter une arête entre deux sommets dans un graphe non orienté
    @Override
    public void addEdge(int v1, int v2) {
        edges.get(v1).add(v2);
        edges.get(v2).add(v1);
    }

    @Override
    public void BFS(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            // Parcourt tous les voisins du sommet courant
            for (int neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public void DFS(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Parcourt tous les voisins du sommet en profondeur
        for (int neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

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
                int newDist = dist + 1; // Poids unitaire pour chaque arête
                if (!distance.containsKey(neighbor) || newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    queue.add(new int[]{neighbor, newDist});
                }
            }
        }
        return distance;
    }
}
