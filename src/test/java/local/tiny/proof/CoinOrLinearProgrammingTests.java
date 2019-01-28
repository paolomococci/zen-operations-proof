/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.tiny.proof;

import com.google.common.collect.ImmutableBiMap;
import com.quantego.clp.CLP;
import com.quantego.clp.CLPVariable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinOrLinearProgrammingTests {

    private final CLP clpModel = new CLP().maximization();

    @Test
    public void clpToStringTest() {
        CLPVariable x0 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        CLPVariable x1 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        clpModel.createExpression().add(x0, 2.0).add(x1, 1.0).leq(320.0);
        clpModel.createExpression().add(x0, 1.0).add(x1, 1.0).leq(240.0);
        clpModel.addObjective(
                ImmutableBiMap.<CLPVariable, Double>builder()
                        .put(x0, 40.0)
                        .put(x1, 30.0).build(),
                0.0);
        Assert.assertEquals(CLP.STATUS.OPTIMAL, clpModel.solve());
        String EXPECTED = "Maximize\n" +
                "obj: + 40.0 x_0 + 30.0 x_1\n" +
                "Subject To\n" +
                "ctr_0: + 2.0 x_0 + x_1 <= 320.0\n" +
                "ctr_1: + x_0 + x_1 <= 240.0\n" +
                "Bounds\n" +
                "End";
        Assert.assertEquals(EXPECTED, clpModel.toString());
    }

    @Test
    public void clpStatusTest() {
        CLPVariable x0 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        CLPVariable x1 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        clpModel.createExpression().add(x0, 2.0).add(x1, 1.0).leq(320.0);
        clpModel.createExpression().add(x0, 1.0).add(x1, 1.0).leq(240.0);
        Map<CLPVariable, Double> objective;
        objective = new HashMap<>();
        objective.put(x0, 40.0);
        objective.put(x1, 30.0);
        clpModel.addObjective(objective, 0.0);
        Assert.assertEquals(CLP.STATUS.OPTIMAL, clpModel.solve());
    }

    @Test
    public void clpX0SolutionTest() {
        CLPVariable x0 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        CLPVariable x1 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        clpModel.createExpression().add(x0, 2.0).add(x1, 1.0).leq(320.0);
        clpModel.createExpression().add(x0, 1.0).add(x1, 1.0).leq(240.0);
        Map<CLPVariable, Double> objective = new HashMap<CLPVariable, Double>() {
            {
                put(x0, 40.0);
                put(x1, 30.0);
            }
        };
        clpModel.addObjective(objective, 0.0);
        Assert.assertEquals(CLP.STATUS.OPTIMAL, clpModel.solve());
        Assert.assertEquals(80.0, clpModel.getSolution(x0), 0.0);
    }

    @Test
    public void clpX1SolutionTest() {
        CLPVariable x0 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        CLPVariable x1 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        clpModel.createExpression().add(x0, 2.0).add(x1, 1.0).leq(320.0);
        clpModel.createExpression().add(x0, 1.0).add(x1, 1.0).leq(240.0);
        clpModel.addObjective(
                ImmutableBiMap.<CLPVariable, Double>builder()
                .put(x0, 40.0)
                .put(x1, 30.0).build(),
                0.0);
        Assert.assertEquals(CLP.STATUS.OPTIMAL, clpModel.solve());
        Assert.assertEquals(160.0, clpModel.getSolution(x1), 0.0);
    }

    @Test
    public void clpObjectiveValueTest() {
        CLPVariable x0 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        CLPVariable x1 = clpModel.addVariable().bounds(0.0, Double.POSITIVE_INFINITY);
        clpModel.createExpression().add(x0, 2.0).add(x1, 1.0).leq(320.0);
        clpModel.createExpression().add(x0, 1.0).add(x1, 1.0).leq(240.0);
        clpModel.addObjective(
                ImmutableBiMap.<CLPVariable, Double>builder()
                        .put(x0, 40.0)
                        .put(x1, 30.0).build(),
                0.0);
        Assert.assertEquals(CLP.STATUS.OPTIMAL, clpModel.solve());
        Assert.assertEquals(8000.0, clpModel.getObjectiveValue(), 0.0);
    }
}
