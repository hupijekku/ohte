/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwg.domain;

/**
 *
 * @author emu
 */

public class Node implements Comparable {
    public Tile tile;
    public double distance;
    public Node prev;
    
    public Node(Tile tile, double distance) {
        this.tile = tile;
        this.distance = distance;
    }

    @Override
    public int compareTo(Object t) {
        if (t instanceof Node) {
            return (int)(this.distance - ((Node) t).distance);
        } else {
            return 0;
        }
    }
}
