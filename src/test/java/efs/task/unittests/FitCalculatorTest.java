package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldIllegalArgumentException_whenDietRecommended() {

        //given
        double weight = 69.5;
        double height = 0.0;

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "Weight={0}")
    @ValueSource(doubles = {79.0, 82.0, 80.0})
    void shouldReturnTrue_forAllWeights(double weight) {
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Height={0}, Weight={1}")
    @CsvSource({
            "160.0, 50.0",
            "170.0, 60.0",
            "180.0, 70.0"
    })
    void shouldReturnFalse_forAllPairs(double height, double weight) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }


    @ParameterizedTest(name = "Height={0}, Weight={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_forAllPairsFromFile(double height, double weight) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnUserWithWorstBMI() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // when
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertEquals(97.3, userWithWorstBMI.getWeight());
        assertEquals(1.79, userWithWorstBMI.getHeight());
    }

    @Test
    void shouldReturnNullForEmptyList() {
        // given
        List<User> emptyUserList = Collections.emptyList();

        // when
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(emptyUserList);

        // then
        assertNull(userWithWorstBMI);
    }
    @Test
    void shouldCalculateBMIScore() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // when
        double[] calculatedBMIScore = FitCalculator.calculateBMIScore(users);

        // then
        double[] expectedBMIScore = TestConstants.TEST_USERS_BMI_SCORE;

        assertArrayEquals(expectedBMIScore, calculatedBMIScore);
    }


}
