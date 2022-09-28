# Project 6: Percolation

This is the directions document for Project P6 Percolation in CompSci 201 at Duke University, Spring 2022. Please follow the directions carefully while you complete the project. Please refer to the directions at https://coursework.cs.duke.edu/201spring22/p6-percolation rather than any forks or local copies in the event that any changes are made to the document.

## Outline 
- [Project Introduction](#project-introduction)

## Project Introduction

In this assignment, you will write a program to estimate the value of the [percolation threshold](http://en.wikipedia.org/wiki/Percolation_thresholds) via [Monte Carlo](http://en.wikipedia.org/wiki/Monte_Carlo_method) simulation. In doing so, you will better understand depth-first-search, breadth-first-search, union-find structures, and the use of computer simulations for statistical inquiry. _**Your goal will be to explore trade-offs in several approaches to estimate the percolation threshold in an NxN system.**_ 

<details>
<summary>Acknowledgements</summary>
This assignment originated at Princeton; thanks to Kevin Wayne from whom staff at Duke first learned of it, and to Jeff Forbes when he was at Duke for ensuring we kept it as part of the assignment-stack at Duke.
</details>


### Vocabulary
In a system of rectangular/square NxN grid cells, each cell is sometimes referred to as a _site_ in the assignment and in supporting material, including this background.
- All sites start as **blocked**
- When a site is chosen at random it is **open**
- Open sites connected to the top row are **full**.
- A system in which there's a full cell in the bottom row (and thus a path of full cells from top to bottom) **percolates**.

Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Given a composite system composed of randomly distributed insulating and metallic materials, what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Scientists have defined an abstract process known as percolation to model such situations.

We model a percolation system using an N-by-N grid of sites. **Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.** In diagrams we color full sites blue to model water flowing from the top through the system. We say the **system percolates if there is at least one full site in the bottom row.** In other words, a system percolates if there is a path of open sites from the top row to the bottom row. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.

For more on percolation see the [Princeton Case Study](http://introcs.cs.princeton.edu/java/24percolation/).

![An example of a percolating vs non-percolating system](./p6-figures/P6-percolates.png)

### Percolation Threshold
The percolation threshold problem is: if sites are independently set to be open with probability p (and therefore blocked with probability (1 − p), what is the probability that the system percolates? In other words, in a N-by-N grid, would the system percolate if N^2 randomly chosen cells are opened?  When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

![Threshold of percolating vs non-percolating system](./p6-figures/P6-threshold.png)

When N is sufficiently large, there is a threshold value p* such that when p < p* a random N-by-N grid almost never percolates, and when p > p*, a random N-by-N grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. Your task is to write a suite of computer programs to visualize the percolation process and estimate p* using Monte Carlo techniques. As you can see above, the percolation threshold in an NxN grid is about 0.593. The size of the grid doesn't matter as your simulations will show. 

### Helpful Videos 
The videos linked here show (1) an interactive simulation where you choose to open sites and (2) help explain the techniques you'll read about. These videos may be helpful after reading the assignment, or to get grounded before reading. These videos help understand two parts of the assignment.

1. [Open, full, DFS, and what percolation means](https://www.youtube.com/watch?v=ikVIiuCR4pk)
2. [From DFS to Union-Find: two approaches compared/contrasted](https://www.youtube.com/watch?v=lpYvgV5m1qM)

### MazeDemo
The program `MazeDemo.java` within the project you get (`src/MazeDemo.java`) is a model for DFS, BFS, and Union-Find.  You should study the program to reinforce your understanding of these concepts. Two videos of solving a maze using DFS and BFS, respectively, can be found linked below, looking at them and the source code for `MazeDemo.java` may help in understanding how the two searches work.

- [dfs maze](https://www.youtube.com/watch?v=95igA_fWQtc)
- [bfs maze](https://www.youtube.com/watch?v=dffMgIjfczI)

### Overview: What to Do

Here's a high-level view of the assignment. This is enough information to know what to do, but not necessarily how to do it. 

You'll create three new implementations of the `IPercolate` interface that you'll test, time, and answer questions about in analyzing tradeoffs. 
1. Create class `PercolationDFSFast` that extends `PercolationDFS`. Implement constructors appropriately. You only have to `@Override` the method `updateOnOpen`. 
2. Create class `PercolationBFS` that extends `PercolationDFSFast`. Implement constructors appropriately. You only have to `@Override` one method: `search` to implement BFS instead of DFS. 
3. Create class `PercolationUF` that implements the `IPercolate` interface. Implement a constructor and every method from the interface. Instance variables and methods are described in the write-up and include a grid/two-dimensional array of boolean values, an `IUnionFind` object `myFinder`, and a few int types. You'll use the union-find methods from the IUnionFind object as described in the video above.

For all three classes, test the implementation using the JUnit test class `TestPercolate` by changing which of the three implementations is used. You *must* also test using the `InteractivePercolationVisualizer` simulating the percolation using your own click-tests. **Do not depend only on the JUnit tests.**

For the analysis you will run `PercolationStats` and answer the analysis questions. 

