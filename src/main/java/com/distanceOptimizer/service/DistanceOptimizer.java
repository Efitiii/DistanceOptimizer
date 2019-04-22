package com.distanceOptimizer.service;

import com.distanceOptimizer.domain.CurrentDestination;
import com.distanceOptimizer.domain.Node;
import com.distanceOptimizer.exceptions.ExceptionMessages;
import com.distanceOptimizer.exceptions.InvalidInputValueForNodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;

/**
 * @author Efrem  on 4/17/19
 */

@Service
public class DistanceOptimizer implements IDistanceOptimizer {

    @Autowired
    public IGraph graph;

    /**
     * Validates if right naming is followed for parent and child nodes which are assumed to be alphabetic Strings.
     * Validates if distance information is of a numeric String.
     * Validates if bidirectional information is either 'y' for yes or 'n' for no.
     * If all correct information is provided builds the graph.
     *
     * @param parent
     * @param child
     * @param distance
     * @param bidirection
     */

    @Override
    public void conditionDataAndBuildGraph(String parent, String child, String distance, String bidirection) throws InvalidInputValueForNodeException {


        if (!parent.matches("[a-zA-Z]{1,1}") || !child.matches("[a-zA-Z]{1,1}")) {
            throw new InvalidInputValueForNodeException(ExceptionMessages.INVALID_NODE_NAME.getMessage());
        }

        if (!distance.matches("^[0-9]{1,1}")) {
            throw new InvalidInputValueForNodeException(ExceptionMessages.INVALID_DISTANCE_INPUT.getMessage());
        }

        if (!bidirection.matches("(y|n){1,1}")) {
            throw new InvalidInputValueForNodeException(ExceptionMessages.INVALID_DIRECTION_INPUT.getMessage());
        }


        Double currentDistance = Double.parseDouble(distance);

        boolean biDirectional = (bidirection.equals("y")) ? true : false;

        addConnection(parent, child, currentDistance, biDirectional);


    }

    @Override
    public void addConnection(String parent, String child, Double distance, boolean bidirectional) {


        graph.buildGraph(parent, child, distance, bidirectional);

    }


    /**
     * Computes shortest distance from the departure node to the arrival node.
     *
     * @param departure
     * @param arrival
     * @return this is the shortest distance to the destination node.
     */

    @Override
    public Double computeForShortestDistance(String departure, String arrival) {

        Stack<CurrentDestination> stackOfNodes = new Stack<>();
        Node startingNode = graph.getNodes().get(departure);
        Node destinationNode = graph.getNodes().get(arrival);

        CurrentDestination outputPath = new CurrentDestination(startingNode.getNodeName());
        outputPath.setAggregatedDistance(Double.MAX_VALUE);

        CurrentDestination startingPath = new CurrentDestination(startingNode.getNodeName());
        startingPath.setAggregatedDistance(0.00);
        startingPath.setCurrentNode(startingNode);

        visited(startingNode);
        stackOfNodes.push(startingPath);

        while (!stackOfNodes.isEmpty()) {

            CurrentDestination currentPath = stackOfNodes.pop();
            Node currentNode = currentPath.getCurrentNode();

            if (currentNode != destinationNode) {
                currentNode.getChildren().entrySet().stream().forEach(e -> {
                    if (!e.getKey().isVisited()) {
                        visited(e.getKey());
                        CurrentDestination newPath = new CurrentDestination(currentPath);
                        newPath.setCurrentNode(e.getKey());
                        Double newDistance = newPath.getAggregatedDistance() + e.getValue();
                        newPath.setAggregatedDistance(newDistance);
                        stackOfNodes.push(newPath);
                    }
                });
            } else {
                Double currentPathDistance = currentPath.getAggregatedDistance();
                currentNode.setVisited(false);
                if (currentPathDistance < outputPath.getAggregatedDistance()) {
                    outputPath = currentPath;
                }
            }

        }

        if (outputPath.getAggregatedDistance() == Double.MAX_VALUE) {
            outputPath.setAggregatedDistance(-9999.00);
        }

        return outputPath.getAggregatedDistance();

    }


    private void visited(Node node) {
        node.setVisited(true);
    }

    public IGraph getGraph() {
        return graph;
    }


}
