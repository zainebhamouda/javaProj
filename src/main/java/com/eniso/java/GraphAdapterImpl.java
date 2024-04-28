/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

/**
 *
 * @author zaine
 */
import java.util.ArrayList;
import java.util.List;

public class GraphAdapterImpl implements GraphAdapter {
    private List<List<Integer>> adjacencyList;
    private int[][] adjacencyMatrix;

    public void fromAdjacencyList(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        // Convertir la liste d'adjacence en matrice d'adjacence
        int n = adjacencyList.size();
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = adjacencyList.get(i).contains(j) ? 1 : 0;
            }
        }
    }

    public void fromAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        // Convertir la matrice d'adjacence en liste d'adjacence
        int n = adjacencyMatrix.length;
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    neighbors.add(j);
                }
            }
            this.adjacencyList.add(neighbors);
        }
    }

    public List<List<Integer>> toAdjacencyList() {
        return adjacencyList;
    }

    public int[][] toAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}

