package com.distanceOptimizer.service;

import com.distanceOptimizer.domain.Node;

import java.util.Map;

/**
 * @author Efrem  on 4/21/19
 * @project DistanceOptimizer
 */
public interface IGraph {

    void buildGraph(String parent, String child, Double distance, boolean bidirection);
    Map<String, Node> getNodes();
    void setNodes(Map nodes);

}
