/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwg.domain;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Finds a path from (x1, y1) to (x2, y2). 
 * Uses Dijkstra's algorithm.
 * Still slightly buggy, but causes cool occasional dead ends in dungeon,
 * so it is now a feature.
 * @author emu
 */
public class Pathfinder {
    
    Tile[][] grid;
    Node[][] nodes;
    int size = 0;
    /**
     * Constructor for Pathfinder.
     * Assigns the tilemap and creates a grid of Nodes.
     * @param tilemap 
     */
    public Pathfinder(Tile[][] tilemap) {
        this.grid = tilemap;
        this.size = this.grid.length;
        this.nodes = new Node[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.nodes[i][j] = new Node(this.grid[i][j], 999999);
            }
        }
    }
    
    /**
     * Finds the shortest path between (x1, y1) -> (x2, y2) using Dijkstra's algorithm
     * @param x1    X-coordinate of the first point.
     * @param y1    Y-coordinate of the first point.
     * @param x2    X-coordinate of the second point.
     * @param y2    Y-coordinate of the second point.
     * @return      Returns a list of Tile-objects which create a path.
     */
    public ArrayList<Tile> path(int x1, int y1, int x2, int y2) {
        boolean[][] visited = new boolean[this.size][this.size];
        PriorityQueue<Node> que = new PriorityQueue();
        ArrayList<Node> path = new ArrayList();
        Node first = this.nodes[x1][y1];
        first.distance = 0;
        que.add(first);
        while (!que.isEmpty()) {
            Node node = que.poll();
            int x = node.tile.getX();
            int y = node.tile.getY();
            if (x == x2 && y == y2) {
                path.add(node);
                break;
            }
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            double now = node.distance;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i < 0 || i > this.size - 1 || j < 0 || j > this.size - 1
                            || (i == x && j == y)) {
                        continue;
                    }
                    double next = now;
                    if (i == x || j == y) {
                        if (this.grid[i][j].isPassable()) {
                            next += 1.4;
                        } else {
                            next += 1;
                        }
                    } else {
                        if (this.grid[i][j].isPassable()) {
                            next += 2.8;
                        } else {
                            next += 2.4;
                        }
                    }
                    if (next < this.nodes[i][j].distance) {
                        Node toAdd = this.nodes[i][j];
                        toAdd.distance = next;
                        toAdd.prev = node;
                        path.add(toAdd);
                        que.add(toAdd);
                    }
                }
            }
        }
        Node u = path.get(path.size() - 1);
        ArrayList<Tile> tiles = new ArrayList();
        while (u != null) {
            tiles.add(u.tile);
            u = u.prev;
        }
        return tiles;
    }
}
