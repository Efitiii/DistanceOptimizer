# Introduction

Distance Optimizer is a project based on nodal information that builds a graph and computes the shortest distance between two nodes.

## Key Features and Technologies

### Spring and Spring Boot
Spring framework is an open source Java platform that provides comprehensive infrastructure support for developing robust Java applications very easily and very rapidly.
Spring Boot features is used in this project to decouple dependencies by the use of Inversion of Control container utilizing dependency injection.

## Junit 5
JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM. This includes focusing on Java 8 and above, as well as enabling many different styles of testing.

## Node
Representation of a single instance in a graph. It contains the name of the node, the children nodes it is related to and the distance to each. 

## Graph
Stores the information of the nodes in an easy look up format based on Hash-Map. Provided all the information of a node [its name, child node it is related to and distance, if the connection is bidirectional or unidirectional] it is added to the graph.

## CurrentDestination

In the search for the destination node records the current node and the distance from the starting node.


## Distance Optimizer

Provides the logic to compute the shortest distance between two nodes. 
* To compute distance this follows an iterative approach based on Breadth First Search algorithm.
* While searching for destination node at each individual node it stores information of the current node and aggregated distance in a model CurrentDestination.
* It then adds the CurrentDestination to a stack 'stackOfNodes' for the search to be proceeded later with the children nodes of the current node.
* If a destination node is found it is distance is compared with already found distances. If the distance if found to be shorter it is stored in the outputPath.
* Once the search is complete the shortest distance is returned
     

## Installation

To run Spring Boot the following dependencies should be added.
  
    compile 'org.springframework.boot:spring-boot-starter-test:2.1.2.RELEASE'
    compile 'org.springframework.boot:spring-boot-starter-parent:2.1.2.RELEASE'
    compile 'org.springframework.boot:spring-boot-starter-web:2.1.2.RELEASE'

Junit 5 should also be added to the class path

 'org.junit.jupiter:junit-jupiter-engine:5.2.0'
 
 
 ## Future Scope
 A recursive way of finding the shortest distance has also been tried for this project and even though it provides an easy way to store the paths followed the iterative solution was found to provide better results at a reasonable time and space complexity.
 But to record the encounter of the specific nodes to reach the destination node the recursive approach was found to be easier. 