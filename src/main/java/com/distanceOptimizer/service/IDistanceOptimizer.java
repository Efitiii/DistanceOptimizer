package com.distanceOptimizer.service;

import com.distanceOptimizer.exceptions.InvalidInputValueForNodeException;

/**
 * @author Efrem  on 4/17/19
 * @project com.distanceOptimizer.service.DistanceOptimizer
 */

public interface IDistanceOptimizer {

    void conditionDataAndBuildGraph(String parent, String child, String distance, String bidirection) throws InvalidInputValueForNodeException;

    void addConnection(String parent, String child, Double distance, boolean bidirection);


    Double computeForShortestDistance(String departure, String arrival);

    IGraph getGraph();
}
