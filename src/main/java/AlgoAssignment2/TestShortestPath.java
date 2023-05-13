package AlgoAssignment2;




public class TestShortestPath {//hapjin test

    public static void main(String[] args) {
        String graphFilePath;
        if (args.length == 0) {
            graphFilePath = "D:\\_Develop\\UniMelb\\AlgorithmsAndComplexity\\src\\main\\java\\AlgoAssignment2\\a.txt";
        }

        else {
            graphFilePath = args[0];
        }
        //String graphContent = FileUtil.read(graphFilePath, null);
        String graphContent = "0,0,1\n" +
                "1,0,2\n" +
                "2,0,3\n" +
                "3,1,2\n" +
                "4,1,4\n" +
                "5,3,4\n" +
                "6,2,5\n" +
                "7,4,5";
        NonDirectedGraph graph = new NonDirectedGraph(graphContent);
        graph.unweightedShortestPath();
        graph.showDistance();
    }
}