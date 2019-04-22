package com.distanceOptimizer.service;

import com.distanceOptimizer.domain.Node;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Efrem  on 4/17/19
 * @project com.distanceOptimizer.service.DistanceOptimizer
 */

/**
 * A graph model that stores information of each node based on its nodeName.
 */

@Component("graph")
public class Graph implements IGraph {


    private Map<String, Node> nodes;

    public Graph(){
        nodes= new HashMap<>();
    }

    /**
     * @param parent
     * @param child
     * @param distance
     * @param bidirection
     */
    @Override
    public void buildGraph(String parent, String child, Double distance, boolean bidirection){


        if (!nodes.containsKey(parent)) {
            nodes.put(parent.toLowerCase(), new Node(parent));
        }
        if (!nodes.containsKey(child)) {
            nodes.put(child.toLowerCase(), new Node(child));
        }

        Node existingChild = nodes.get(child);
        Node existingParent = nodes.get(parent);

        if (!existingParent.getChildren().containsKey(existingChild)) {
            existingParent.getChildren().put(existingChild, distance);
        } else {
            existingParent.getChildren().compute(existingChild, (key, value) -> value = distance);
        }

        if (bidirection)

        {
            existingChild.getChildren().putIfAbsent(existingParent, distance);
        }

    }

    @Override
    public Map<String, Node> getNodes() {
        return nodes;
    }

    @Override
    public void setNodes(Map nodes) {
        this.nodes = nodes;
    }
}
