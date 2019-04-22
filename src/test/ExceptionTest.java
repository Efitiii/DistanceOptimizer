import com.distanceOptimizer.exceptions.ExceptionMessages;
import com.distanceOptimizer.exceptions.InvalidInputValueForNodeException;
import com.distanceOptimizer.service.IDistanceOptimizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Efrem  on 4/19/19
 * @project com.distanceOptimizer.service.DistanceOptimizer
 */
public class ExceptionTest extends SpringBootDistanceOptimizerTests {

    @Autowired
    private IDistanceOptimizer optimizer;


    @Test
    public void testInValidInput() {

        Exception exception = Assertions.assertThrows(InvalidInputValueForNodeException.class, () -> optimizer.conditionDataAndBuildGraph("1", "b", "5.0", "y"));
        assertEquals(ExceptionMessages.INVALID_NODE_NAME.getMessage(), exception.getMessage());


        Exception exception_invalidDistance = Assertions.assertThrows(InvalidInputValueForNodeException.class, () -> optimizer.conditionDataAndBuildGraph("a", "b", "g", "y"));
        assertEquals(ExceptionMessages.INVALID_DISTANCE_INPUT.getMessage(), exception_invalidDistance.getMessage());


        Exception exception_invalidDirection = Assertions.assertThrows(InvalidInputValueForNodeException.class, () -> optimizer.conditionDataAndBuildGraph("a", "b", "5", "k"));
        assertEquals(ExceptionMessages.INVALID_DIRECTION_INPUT.getMessage(), exception_invalidDirection.getMessage());


    }


}
