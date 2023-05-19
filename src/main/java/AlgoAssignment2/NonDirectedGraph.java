package AlgoAssignment2;

import java.awt.*;
import java.util.*;
import java.util.List;

/*
 * 求解无向图的单源最短路径
 */
public class NonDirectedGraph {

    //The relationships between the vertices and edges in the graph are saved in the form of a list
    // within the Vertex class, keeping track of all the vertices.
    private Map<String, Vertex> nonDirectedGraph;
    private Vertex startVertex;//The starting vertex of the graph.

    private HashMap<String, Integer> valueMap;//Store all shortest path from source node to every node

    private class Vertex{
        private String vertexLabel;// Node label
        private List<Edge> adjEdges;// The edges (or vertices) adjacent to that vertex.
        private int dist;// The distance from the vertex to the starting vertex
                        // (the distance between the vertex and the starting vertex).

        public Vertex(String vertexLabel) {
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            dist = Integer.MAX_VALUE;
        }
    }
    private class Edge{
        private Vertex endVertex;
        public Edge(Vertex endVertex) {
            this.endVertex = endVertex;
        }
    }

    public NonDirectedGraph(int[][] graphContent, String sourceNode) {
        nonDirectedGraph = new HashMap<>();
        valueMap = new HashMap<>();
        buildGraph(graphContent, sourceNode);
    }

    /**
     * Construct the graph using the adjacent matrix.
     * @param adjacentMatrix the input adjacent matrix of graph
     * @param sourceNode the source node of this graph
     */
    private void buildGraph(int[][] adjacentMatrix, String sourceNode){
        String startNodeLabel, endNodeLabel;
        Vertex startNode, endNode;
        for(int i = 0; i < adjacentMatrix.length; i++){
            //traverse every node above the diagonal of adjacent matrix, which means every edges
            for (int j = i + 1; j < adjacentMatrix[0].length; j++) {
                //if adjacentMatrix[i][j] == 1, means this is an edge
                if (adjacentMatrix[i][j] == 1) {
                    startNodeLabel = String.valueOf(i);
                    endNodeLabel = String.valueOf(j);
                    //if this node is not in graph yet, add it
                    endNode = nonDirectedGraph.get(endNodeLabel);
                    if(endNode == null){
                        endNode = new Vertex(endNodeLabel);
                        nonDirectedGraph.put(endNodeLabel, endNode);
                    }
                    //if this node is not in graph yet, add it
                    startNode = nonDirectedGraph.get(startNodeLabel);
                    if(startNode == null){
                        startNode = new Vertex(startNodeLabel);
                        nonDirectedGraph.put(startNodeLabel, startNode);
                    }
                    Edge e = new Edge(endNode);
                    //For an undirected graph, edges are added to both the start and end points
                    endNode.adjEdges.add(e);
                    startNode.adjEdges.add(e);
                }
            }
        }
        startVertex = nonDirectedGraph.get(sourceNode);
    }

    public void unweightedShortestPath(){
        unweightedShortestPath(startVertex);
    }


    /**
     *  To compute the shortest paths from the source vertex s to all other vertices in an undirected graph:
     *  We need a queue to store the vertices in the graph. Initially, enqueue the source vertex.
     *  Then, using a breadth-first search approach, expand outward to compute the shortest paths for other vertices.
     *  Last of all, put all (vertex label : distance to source node) pair into value map.
     * @param sourceNode the source
     */
    private void unweightedShortestPath(Vertex sourceNode){
        //initialization
        Queue<Vertex> queue = new LinkedList<>();
        sourceNode.dist = 0;
        queue.offer(sourceNode);//Set the source point dist to 0 and enqueue

        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            for (Edge e : v.adjEdges) {//Scan the adjacent edges (points) of v
                if(e.endVertex.dist == Integer.MAX_VALUE){//If this vertex (e.endVertex) has not been visited
                    e.endVertex.dist = v.dist + 1;//Update the distance from the vertex to the source point
                    queue.offer(e.endVertex);
                }//end if
            }//end for
        }//end while

        //Create hashmap to save the shortest path values
        Collection<Vertex> vertexes = nonDirectedGraph.values();
        for (Vertex vertex : vertexes) {
            valueMap.put(vertex.vertexLabel, vertex.dist);
        }

    }

    public int findShortestPath(String target) {
        return valueMap.get(target);
    }

}