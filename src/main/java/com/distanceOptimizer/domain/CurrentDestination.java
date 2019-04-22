package com.distanceOptimizer.domain;

/**
 * @author Efrem  on 4/18/19
 * @project com.distanceOptimizer.service.DistanceOptimizer
 */

/**
 * A model class that defines the current node in a search and the aggregated distance from the starting node to the current node
 */

public class CurrentDestination {

    Node currentNode;
    Double aggregatedDistance;

    public CurrentDestination(String nodeName){
       currentNode= new Node(nodeName);
       aggregatedDistance =0.0;
    }

    public CurrentDestination(CurrentDestination currentPath) {
        this.currentNode=currentPath.getCurrentNode();
        this.aggregatedDistance =currentPath.getAggregatedDistance();
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void setAggregatedDistance(Double aggregatedDistance) {
        this.aggregatedDistance = aggregatedDistance;
    }


    public Node getCurrentNode() {
        return currentNode;
    }

    public Double getAggregatedDistance() {
        return aggregatedDistance;
    }

}
