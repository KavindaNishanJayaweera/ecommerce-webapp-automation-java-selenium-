package stepDefinitions;

import com.base.Core;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Core {

    public Scenario scenario;


    @Before
    public void testm(Scenario sce) {

        this.scenario = sce;
        setup(scenario);

    }

    @After
    public void afterTest() {
        tearDown(scenario);

    }

}
