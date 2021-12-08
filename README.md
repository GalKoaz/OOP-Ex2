# Object-Oriented Programming Exercise 2


<!-- Graphical user -->
### Graphical user interface

<p align="center">
<img align="center" src="https://s10.gifyu.com/images/GalKoaz.gif"/>
</p>

---

<p align="center">
<img align="center" src="https://s10.gifyu.com/images/Untitled611bff3bc2c46a20e.gif"/>
</p>
(For zoom in click on the image)

In this project we were asked to display the graph visually,

we chose to represent the graph using Java Swing with a panel that allows uploading a graph using a JSON file.

Saving a graph to a JSON file, adding and deleting a vertex, adding and deleting an Edge,

Access to see the whole process of the algorithms in real time (Shorted path, isConnected, Travelling Salesman Problem (TSP), Center).

In addition, we added a help button that links directly to Git.

----------------

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Content</summary>
  <ol>
    <li><a href="#graphical-user-interface">Graphical user interface</a></li>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#code-details">Code Details</a></li>
    <li><a href="#algorithms">Algorithms</a></li>
    <li><a href="#how-to-run">How  to run</a></li>
    <li><a href="#languages-and-tools">Languages and Tools</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

----------------

<!-- ABOUT THE PROJECT -->
# About The Project
**_Object-Oriented Programming Exercise 2 - Directed Weighted Graph:_**

This Exercise is dedicated to the design and implementation of data structures and algorithms on graphs (directed and weighted). As usual, it is recommended to start planning the Exercise (without a code) and only after you have a basic plan you should move on to the implementation phase of the code.

The Exercise are defined as interfaces in the epic package, you need to plan and implement two really key ones:
Interface of a weighted directed graph
Interface of algorithms on graphs (weighted tuners).

When the Exercise is checked through three public static functions, place fences in the Ex2.java class - note that you must complete the three steps below - through which your Exercise will be checked. Directed Graph Class - should support uploading json files, please note: We have attached some json files of graphs for you.

"The origins of graph theory are humble, even frivolous :round_pushpin:"

---------

<!-- code-details -->

## Code Details


Unified Modeling Language (UML) :

Click the image for zoom in.

<p align="center">
<img align="center" src="https://s10.gifyu.com/images/UML.png" />
</p>

As you can see in UML we implement the main Directed Weighted Graph interfaces that with the help of other interfaces and class we can build the whole project.
The interface is implemented
DirectedWeightedGraphAlgorithm the EdgeData, NodeData, GeoLocation.

We have built a JSON Operation class which receives a JSON file and initializes the entire graph using the interfaces mentioned above.
Sends all data to DirectedWeightedGraph, so we can build a graph. In the same class there are options for saving a given graph as a JSON file.

The DirectedWeightedGraphAlgorithms class contains all the algorithms that can be run on the given graph such as DijkstraAlgorithm and BFS, in addition you can find other functions related to solving various problems in directed graphs.

In addition to all the departments and interfaces mentioned above, we have prepared a test folder that checks all the functions that are in the project, from the simplest function to the most complex function and algorithms that appear in the project.

We have prepared a folder for the graphical interface which contains all the departments of the interface we need from the visual graph presentation to the structure and execution of the algorithms within it, the option to delete and add edges and vertices.

---------
<!-- algorithms -->
## Algorithms

In this project we used a number of algorithms, we will present the algorithms that were implemented in this project.

[Dijkstra's algorithm]((https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)) to find the shortest path between a and b.
It picks the unvisited vertex with the lowest distance, calculates the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller. Mark visited (set to red) when done with neighbors.

[Breadth-first search (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search) is an algorithm for traversing or searching tree or graph data structures.
The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking.

[Graph Center](https://en.wikipedia.org/wiki/Graph_center) A graph with central points colored red. These are the three vertices A such that ```d(A, B) ≤ 3``` for all vertices B. Each black vertex is a distance of at least 4 from some other vertex.

[Connected Graph](https://mathworld.wolfram.com/ConnectedGraph.html) A connected graph is graph that is connected in the sense of a topological space, i.e., there is a path from any point to any other point in the graph. A graph that is not connected is said to be disconnected. This definition means that the null graph and singleton graph are considered connected, while empty graphs on ```n >= 2``` nodes are disconnected.

[Traveling salesman problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem) The travelling salesman problem (also called the travelling salesperson problem or TSP) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?" It is an ```NP-hard``` problem in combinatorial optimization, important in theoretical computer science and operations research.



---------
<!-- how-to-run -->
## How to run

_Jar file:_

* _Jar  file name:  Ex2.jar_

_in Jar folder Extract the folder including  the json files_

Run Jar file  in commend line:

```
java -jar Ex2.jar G1.json
```


In this project we used some external libraries in the JAVA language, in order to make life easier these libraries are located within the project called external libraries.

First, it's important to make sure you clone this project in IntelliJ through Project From Version Control.
To be sure:
```
File -> New -> Project From Version Control -> Repository URL
```

Second, in this project we used some external libraries in the JAVA language, in order to make life easier these libraries are located within the project called external libraries.
In order to update these libraries in this project, we will do the following:
```
File-> Project Structure -> Libraries and select the folder with all external libraries.
```

_Java SDK Verison:_ ```15```

_External libraries:_
* _gson-2.8.2_
* _javax.json-1.1.4_
* _javax.ws.rs-api-2.1.1_
* _json-simple-1.1.1_

<big><u>_**Analysis of the performance of algorithms on graphs:**_</big></u>

<small>(Graphs with an average edge rank of 20 edges per vertex: incoming + outgoing - total 20)</small>
<br><small>These analyzes were tested on a very basic computer with an Intel i5 processor and 16GB of RAM</small>

* Graph size: 1000, Shortest path Run time ~0.027 seconds, Center ~2.158 seconds, isConnected ~0.007 seconds

* Graph size: 10000, Shortest path Run time ~0.14 seconds, Center ~314.63 seconds, isConnected ~0.153 seconds

* Graph size: 100000, Shortest path ~1.345 seconds, Center timeout, isConnected ~0.946 seconds

* Graph size: 1000000. Shortest path ~9.13 seconds, Center  timeout, isConnected ~4.21 seconds
---------


## Languages and Tools

  <div align="center">
  
 <code><img height="50" width="50" src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg"></code>
 <code><img height="40" width="70" src="https://upload.wikimedia.org/wikipedia/commons/d/d5/UML_logo.svg"/></code>
 <code><img height="40" width="40" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/768px-IntelliJ_IDEA_Icon.svg.png"/></code>
 <code><img height="40" height="40" src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/git/git.png"></code>
 <code><img height="40" height="40" src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/terminal/terminal.png"></code>
  </div>


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Java](www.java.com)
* [UML](https://en.wikipedia.org/wiki/Unified_Modeling_Language)
* [Git](https://git-scm.com/)
* [IntelliJ](https://www.jetbrains.com/)
* [Git-scm](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)


<!-- CONTACT -->
## Contact <small>[Top▲](#graphical-user-interface)</small>


 Gal - [here](https://github.com/GalKoaz/)
 
 Amir - [here](https://github.com/amirg00/)

Project Link: [here](https://github.com/GalKoaz/OOP-Ex2)

___

Copyright © _This Project was created on Dec 08, 2021, by [Gal](https://github.com/GalKoaz/)  & [Amir](https://github.com/amirg00/)_.
