# Exercise 2 Object-Oriented Programming

## **_Directed Weighted Graph_**

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Content</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#graph-graphical-user-interface-(gui)">Graph Graphical user interface (GUI)</a></li>
    <li><a href="#code-details">Code Details</a></li>
    <li><a href="#algorithms">Algorithms</a></li>
    <li><a href="#languages-and-tools">Languages and Tools</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

Exercise 2 Object-Oriented Programming

This Exercise is dedicated to the design and implementation of data structures and algorithms on graphs (directed and weighted). As usual, it is recommended to start planning the Exercise (without a code) and only after you have a basic plan you should move on to the implementation phase of the code.

The Exercise are defined as interfaces in the epic package, you need to plan and implement two really key ones:
Interface of a weighted directed graph
Interface of algorithms on graphs (weighted tuners).

When the Exercise is checked through three public static functions, place fences in the Ex2.java class - note that you must complete the three steps below - through which your Exercise will be checked. Directed Graph Class - should support uploading json files, please note: We have attached some json files of graphs for you.

"The origins of graph theory are humble, even frivolous :round_pushpin:"

---------

## Graph Graphical user interface (GUI) <small><sup>[▲](#directed-weighted-graph)</sup></small>
In this project we were asked to display the graph visually, 

we chose to represent the graph using Java Swing with a panel that allows uploading a graph using a JSON file.

Saving a graph to a JSON file, adding and deleting a vertex, adding and deleting an Edge, The whole process for the algorithms we implemented. Help button for more information.


---------

## Code Details <small><sup>[▲](#directed-weighted-graph)</sup></small>


Unified Modeling Language (UML) :

<a href="https://github.com/GalKoaz/OOP-Ex2"><img src="https://i.ibb.co/fQ8LM2x/UML.png" alt="UML" border="0"></a>


Explanation about the uml classes here !

---------
<!-- algorithms -->
## Algorithms <small><sup>[▲](#directed-weighted-graph)</sup></small>

In this project we used a number of algorithms, we will present the algorithms that were implemented in this project.

Dijkstra's algorithm to find the shortest path between a and b. 

It picks the unvisited vertex with the lowest distance, calculates the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller. Mark visited (set to red) when done with neighbors.

Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. 

The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking.

In addition, we have proposed a solution for a number of issues such as Traveling salesman problem and Shortest path problem which uses the algorithms mentioned above.

---------


## Languages and Tools <small><sup>[▲](#directed-weighted-graph)</sup></small>

  <div align="center">
  
 <code><img height="50" width="50" src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg"></code> 
 <code><img height="40" height="40" src="https://jupyter.org/assets/main-logo.svg"/></code>
 <code><img height="40" width="80" src="https://pandas.pydata.org/static/img/pandas_white.svg"/></code>
 <code><img height="40" width="70" src="https://upload.wikimedia.org/wikipedia/commons/d/d5/UML_logo.svg"/></code>
 <code><img height="40" width="40" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/768px-IntelliJ_IDEA_Icon.svg.png"/></code>
 <code><img height="40" height="40" src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/git/git.png"></code>
 <code><img height="40" height="40" src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/terminal/terminal.png"></code>
  </div>


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements <small><sup>[▲](#directed-weighted-graph)</sup></small>
* [Java](www.java.com)
* [UML](https://en.wikipedia.org/wiki/Unified_Modeling_Language)
* [Git](https://git-scm.com/)
* [IntelliJ](https://www.jetbrains.com/)
* [Jupyter](https://jupyter.org/)
* [Pandas](https://pandas.pydata.org/)
* [Git-scm](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)


<!-- CONTACT -->
## Contact <small><sup>[▲](#directed-weighted-graph)</sup></small>

 Gal - [here](https://github.com/GalKoaz/)
 
 Amir - [here](https://github.com/amirg00/)

Project Link: [here](https://github.com/GalKoaz/OOP-Ex2)
