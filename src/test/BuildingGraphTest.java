import com.distanceOptimizer.domain.Node;
import com.distanceOptimizer.exceptions.InvalidInputValueForNodeException;
import com.distanceOptimizer.service.IDistanceOptimizer;
import com.distanceOptimizer.service.IGraph;
import com.distanceOptimizer.utility.IDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Efrem  on 4/17/19
 */


public class BuildingGraphTest extends SpringBootDistanceOptimizerTests {

    @Autowired
    private IDistanceOptimizer optimizer;

    @Autowired
    private IDataUtil dataUtil;


    private Set<String> setOfUniqueParents = new HashSet<>();

    @BeforeEach
    public void setupData() throws InvalidInputValueForNodeException, IOException {


        String filePath = "src/resources/input.txt";
        BufferedReader br = dataUtil.readFile(filePath);

        String input;
        while ((input = br.readLine()) != null) {
            if (!input.isEmpty()) {
                String[] strInput = input.split("\\,");
                optimizer.conditionDataAndBuildGraph(strInput[0], strInput[1], strInput[2], strInput[3]);
                setOfUniqueParents.add(strInput[0]);

            }

        }
    }


    @Test
    public void testForBiDirectionalNodes_DifferentDistance() {

        IGraph graph = optimizer.getGraph();
        Map<String, Node> nodesMap = graph.getNodes();

        assertTrue(nodesMap.get("c").getChildren().get(nodesMap.get("d")) == 3.0);
        assertTrue(nodesMap.get("d").getChildren().get(nodesMap.get("c")) == 2.0);


    }

    @Test
    public void testIfEachNodeIsBuiltCorrectly() throws IOException {

        try {
            String filePath = "src/resources/input.txt";
            BufferedReader br = dataUtil.readFile(filePath);
            IGraph graph = optimizer.getGraph();
            Map<String, Node> nodesMap = graph.getNodes();

            String input;
            while ((input = br.readLine()) != null) {
                if (!input.isEmpty()) {
                    String[] strInput = input.split("\\,");
                    Double distance = Double.parseDouble(strInput[2]);
                    Node parent = nodesMap.get(strInput[0]);
                    Node child = nodesMap.get(strInput[1]);
                    Double actualDistance = parent.getChildren().get(child);
                    boolean actualVisited = parent.isVisited();
                    assertTrue(actualDistance.equals(distance));
                    assertTrue(actualVisited == parent.isVisited());
                }

            }

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        }

    }


    @Test
    public void testForTotalNumberOfNodes() {
        IGraph graph = optimizer.getGraph();
        Map<String, Node> nodesMap = graph.getNodes();
        Integer expectedNumberOfNodes = setOfUniqueParents.size();
        Integer actualNumberOfNodes = nodesMap.size();
        assertTrue(expectedNumberOfNodes == actualNumberOfNodes);
    }


    @Test
    public void testForUniDirectionalNodes() {
        IGraph graph = optimizer.getGraph();
        Map<String, Node> nodesMap = graph.getNodes();

        assertTrue(nodesMap.get("a").getChildren().get(nodesMap.get("k")) == 4.0);
        assertFalse(nodesMap.get("k").getChildren().containsKey("a"));
        assertTrue(nodesMap.get("k").getChildren().get(nodesMap.get("g")) == 5.0);
    }


}
