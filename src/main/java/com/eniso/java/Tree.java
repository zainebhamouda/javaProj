/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eniso.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 *
 * @author zaine
 */


public class Tree extends Graph {
    private int root;
    private Map<Integer, Integer> parent;
    private Map<Integer, List<Integer>> children;
    private Map<Integer, Integer> depth;

    // Constructeur initialisant les structures de données pour stocker les parents, les enfants et les profondeurs
    public Tree() {
        this.parent = new HashMap<>();
        this.children = new HashMap<>();
        this.depth = new HashMap<>();
    }

    // Méthode pour obtenir le nœud racine de l'arbre
    public int getRoot() {
        return root;
    }

    // Méthode pour définir le nœud racine de l'arbre
    public void setRoot(int root) {
        this.root = root;
    }

    // Méthode pour obtenir la carte des parents de chaque nœud
    public Map<Integer, Integer> getParent() {
        return parent;
    }

    // Méthode pour définir la carte des parents de chaque nœud
    public void setParent(Map<Integer, Integer> parent) {
        this.parent = parent;
    }

    
    // Méthode pour obtenir les enfants d'un nœud
    public List<Integer> getChildren(int vertex) {
        return children.getOrDefault(vertex, Collections.emptyList());
    }

    // Méthode pour définir la carte des enfants de chaque nœud
    public void setChildren(Map<Integer, List<Integer>> children) {
        this.children = children;
    }

    // Méthode pour obtenir la carte des profondeurs de chaque nœud
    public Map<Integer, Integer> getDepth() {
        return depth;
    }

    // Méthode pour définir la carte des profondeurs de chaque nœud
    public void setDepth(Map<Integer, Integer> depth) {
        this.depth = depth;
    }

    // Méthode pour ajouter un enfant à un parent donné et mettre à jour les profondeurs
    public void addChild(int parent, int child) {
        if (!children.containsKey(parent)) {
            children.put(parent, new ArrayList<>());
        }
        children.get(parent).add(child);
        this.parent.put(child, parent);
        updateDepth(child, depth.get(parent) + 1);
    }

    // Méthode pour mettre à jour la profondeur d'un nœud et de ses enfants
    private void updateDepth(int node, int depth) {
        this.depth.put(node, depth);
        for (int child : children.getOrDefault(node, Collections.emptyList())) {
            updateDepth(child, depth + 1);
        }
    }

    // Méthode pour ajouter une relation parent-enfant à l'arbre et mettre à jour les profondeurs
    @Override
    public void addEdge(int parent, int child) {
        this.parent.put(child, parent);
        if (!children.containsKey(parent)) {
            children.put(parent, new ArrayList<>());
        }
        children.get(parent).add(child);
        int childDepth = depth.getOrDefault(parent, 0) + 1;
        depth.put(child, childDepth);
    }

    // Méthode pour afficher les nœuds de l'arbre avec leurs parents, enfants et profondeurs
    public void printTree() {
        for (int node : parent.keySet()) {
            System.out.print("Node: " + node);
            System.out.print(", Parent: " + parent.get(node));
            System.out.print(", Children: " + children.getOrDefault(node, Collections.emptyList()));
            System.out.println(", Depth: " + depth.get(node));
        }
    }

    // Méthode pour réaliser un parcours en largeur (BFS) sur l'arbre
    @Override
    public void BFS(int startVertex) {
        // Initialisation d'une file pour réaliser le parcours en largeur
        Queue<Integer> queue = new LinkedList<>();
        // Ajouter le sommet de départ à la file
        queue.add(startVertex);

        // Parcourir l'arbre en largeur
        while (!queue.isEmpty()) {
            // Retirer le premier sommet de la file
            int current = queue.poll();
            // Afficher le sommet en cours de traitement
            System.out.print(current + " ");
            // Ajouter les enfants du sommet en cours à la file
            for (int child : getChildren(current)) {
                queue.add(child);
            }
        }
    }

    // Méthode pour réaliser un parcours en profondeur (DFS) sur l'arbre
    @Override
    public void DFS(int startVertex) {
        // Appel de la méthode récursive pour réaliser le parcours en profondeur
        dfsRecursive(startVertex);
    }

    // Méthode récursive pour réaliser un parcours en profondeur (DFS) sur l'arbre
    private void dfsRecursive(int vertex) {
        // Afficher le sommet en cours de traitement
        System.out.print(vertex + " ");
        // Appeler récursivement la méthode DFS pour traiter les enfants du sommet en cours
        for (int child : getChildren(vertex)) {
            dfsRecursive(child);
        }
    }
    // L'algorithme de Dijkstra n'est pas applicable aux arbres
    
    @Override
    public Map<Integer, Integer> dijkstra(int start) {
        throw new UnsupportedOperationException("Dijkstra's Algorithm is not applicable for trees.");
    }
}