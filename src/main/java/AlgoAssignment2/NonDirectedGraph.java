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
        //String[] lines = graphContent.split("\n");
        String startNodeLabel, endNodeLabel;
        Vertex startNode, endNode;
        for(int i = 0; i < adjacentMatrix.length; i++){
            for (int j = i + 1; j < adjacentMatrix[0].length; j++) {
                //
                if (adjacentMatrix[i][j] == 1) {
                    startNodeLabel = String.valueOf(i);
                    endNodeLabel = String.valueOf(j);
                    endNode = nonDirectedGraph.get(endNodeLabel);
                    if(endNode == null){
                        endNode = new Vertex(endNodeLabel);
                        nonDirectedGraph.put(endNodeLabel, endNode);
                    }

                    startNode = nonDirectedGraph.get(startNodeLabel);
                    if(startNode == null){
                        startNode = new Vertex(startNodeLabel);
                        nonDirectedGraph.put(startNodeLabel, startNode);
                    }
                    Edge e = new Edge(endNode);
                    //对于无向图而言,起点和终点都要添加边
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
     * @param source
     */
    private void unweightedShortestPath(Vertex source){
        //初始化
        Queue<Vertex> queue = new LinkedList<>();
        source.dist = 0;
        queue.offer(source);//将源点dist设置为0并入队列

        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            for (Edge e : v.adjEdges) {//扫描v的邻接边(点)
                if(e.endVertex.dist == Integer.MAX_VALUE){//如果这个顶点(e.endVertex)未被访问(每个顶点只会入队列一次)
                    e.endVertex.dist = v.dist + 1;//更新该顶点到源点的距离
                    queue.offer(e.endVertex);
                }//end if
            }//end for
        }//end while

        //Create hashmap to save the shortest path values
        Collection<Vertex> vertexs = nonDirectedGraph.values();
        for (Vertex vertex : vertexs) {
            valueMap.put(vertex.vertexLabel, vertex.dist);
        }

    }

    public int findShortestPath(String target) {
        return valueMap.get(target);
    }

}