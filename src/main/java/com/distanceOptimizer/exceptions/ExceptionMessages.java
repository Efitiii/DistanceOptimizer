package com.distanceOptimizer.exceptions;

/**
 * @author Efrem  on 4/19/19
 * @project DistanceOptimizer
 */
public enum ExceptionMessages {

    INVALID_NODE_NAME("Node name must be alpha-numeric values only"),
    INVALID_DISTANCE_INPUT("Invalid distance input."),
    INVALID_DIRECTION_INPUT("Invalid direction input.");

    private String message;

    ExceptionMessages(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
