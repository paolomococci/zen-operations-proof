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

package local.tiny.proof.service;

import local.tiny.proof.model.LinearProgramming;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinearProgrammingServiceTests {

    @Autowired
    LinearProgrammingService linearProgrammingService;

    @Test
    public void retrieveLinearProgrammingModelsTest() {
        LinearProgramming linearProgramming = new LinearProgramming();
        linearProgramming.setName("sampleOne");
        linearProgrammingService.addLinearProgrammingModel(linearProgramming);
        List<LinearProgramming> linearProgrammingList = linearProgrammingService
                .retrieveLinearProgrammingModels();
        Assert.assertFalse(linearProgrammingList.isEmpty());
    }

    @Test
    public void retrieveLinearProgrammingModelByNameTest() {
        LinearProgramming linearProgramming = new LinearProgramming();
        linearProgramming.setName("sampleTwo");
        linearProgrammingService.addLinearProgrammingModel(linearProgramming);
        LinearProgramming result = linearProgrammingService
                .retrieveLinearProgrammingModelByName("sampleTwo");
        Assert.assertTrue(result.getId() == linearProgramming.getId());
    }
}
