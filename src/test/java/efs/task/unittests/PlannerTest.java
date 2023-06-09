package efs.task.unittests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


public class PlannerTest {

    Planner planner;
    @BeforeEach
    void setup(){planner = new Planner();}

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {
        // when
        int calculatedCalories = planner.calculateDailyCaloriesDemand(TestConstants.TEST_USER, activityLevel);

        // then
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        assertEquals(expectedCalories, calculatedCalories);
    }


    @Test
    void shouldCalculateDailyIntake() {
        // when
        DailyIntake calculatedIntake = planner.calculateDailyIntake(TestConstants.TEST_USER);

        // then
        DailyIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        assertEquals(expectedIntake.getCalories(), calculatedIntake.getCalories());
        assertEquals(expectedIntake.getProtein(), calculatedIntake.getProtein());
        assertEquals(expectedIntake.getFat(), calculatedIntake.getFat());
        assertEquals(expectedIntake.getCarbohydrate(), calculatedIntake.getCarbohydrate());
    }
}


