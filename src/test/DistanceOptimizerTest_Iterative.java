import com.distanceOptimizer.exceptions.InvalidInputValueForNodeException;
import com.distanceOptimizer.service.IDistanceOptimizer;
import com.distanceOptimizer.utility.IDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Efrem  on 4/17/19
 */


public class DistanceOptimizerTest_Iterative extends SpringBootDistanceOptimizerTests {


    @Autowired
    private IDistanceOptimizer optimizer;

    @Autowired
    private IDataUtil dataUtil;

    @BeforeEach
    public void setupData() throws InvalidInputValueForNodeException, IOException {

        String filePath = "src/resources/input.txt";
        BufferedReader br = dataUtil.readFile(filePath);

        String input;
        while ((input = br.readLine()) != null) {
            if (!input.isEmpty()) {

                String[] strInput = input.split("\\,");
                optimizer.conditionDataAndBuildGraph(strInput[0], strInput[1], strInput[2], strInput[3]);
            }

        }
    }


    @Test
    public void testShortDistanceAG() {

        Double actualDistance = optimizer.computeForShortestDistance("a", "g");
        assertTrue(9.0 == actualDistance);
    }


    @Test
    public void testShortDistanceGH() {

        Double actualDistance = optimizer.computeForShortestDistance("g", "h");
        assertTrue(12.0 == actualDistance);

    }

    @Test
    public void testShortDistanceZA() {

        Double actualDistance = optimizer.computeForShortestDistance("z", "a");
        assertTrue(-9999.0 == actualDistance);

    }

    @Test
    public void testShortDistanceBE() {

        Double actualDistance = optimizer.computeForShortestDistance("b", "e");
        assertTrue(6.0 == actualDistance);

    }


    @Test
    public void testShortDistanceAK() {

        Double actualDistance = optimizer.computeForShortestDistance("a", "k");
        assertTrue(4.0 == actualDistance);

    }

    @Test
    public void testShortDistanceAI() {

        Double actualDistance = optimizer.computeForShortestDistance("a", "i");
        assertTrue(9.0 == actualDistance);
    }

    @Test
    public void testShortDistanceIA() {

        Double actualDistance = optimizer.computeForShortestDistance("i", "a");
        assertTrue(9.0 == actualDistance);

    }


    @Test
    public void testShortDistanceKA() {

        Double actualDistance = optimizer.computeForShortestDistance("k", "a");
        assertTrue(14.0 == actualDistance);

    }


}

