/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.eniso.java;

/**
 *
 * @author zaine
 */

import java.util.List;
public interface GraphAdapter {
    
  // Convertit un graphe depuis une liste d'adjacence
    void fromAdjacencyList(List<List<Integer>> adjacencyList);
    
    // Convertit un graphe depuis une matrice d'adjacence
    void fromAdjacencyMatrix(int[][] adjacencyMatrix);
  
    List<List<Integer>> toAdjacencyList();
    int[][] toAdjacencyMatrix();
}
