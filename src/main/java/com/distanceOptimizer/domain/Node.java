package com.distanceOptimizer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Efrem  on 4/17/19
 * @project com.distanceOptimizer.service.DistanceOptimizer
 */

/**
 * A model class with representing each node in the graph along with the children it is connected to.
 */
public class Node {
    String nodeName;
    Map<Node, Double> children;
    boolean visited;

    public Node(String nodeName) {
        this.nodeName = nodeName;
        this.children = new HashMap<>();
        this.visited = false;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Map<Node, Double> getChildren() {
        return children;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
