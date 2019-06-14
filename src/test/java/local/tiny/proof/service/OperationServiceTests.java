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

import local.tiny.proof.model.Operation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTests {

    @Autowired
    private OperationService operationService;

    @Test
    public void createTest() {
        Operation operation = new Operation();
        operation.setName("lpModelOne");
        operationService.create(operation);
        Assert.assertTrue(operationService.readByName("lpModelOne").isPresent());
    }

    @Test
    public void readByIdTest() {
        Operation operation = new Operation();
        operation.setName("lpModelTwo");
        operationService.create(operation);
        Integer id = operationService.readByName("lpModelTwo").get().getId();
        Assert.assertTrue(operationService.readById(id).isPresent());
    }

    @Test
    public void readAllTest() {
        Operation op1 = new Operation();
        Operation op2 = new Operation();
        operationService.create(op1);
        operationService.create(op2);
        Assert.assertTrue(operationService.readAll().spliterator().estimateSize() == 2L);
    }

    @Test
    public void updateNameByIdTest() {
        Operation operation = new Operation();
        operation.setName("lpModelThree");
        operationService.create(operation);
        Integer id = operationService.readByName("lpModelThree").get().getId();
        operationService.updateNameById(id, "lpSample");
        Assert.assertTrue(operationService.readByName("lpSample").get().getId() == id);
    }

    @Test
    public void deleteByIdTest() {
        Operation operation = new Operation();
        operation.setName("lpModelFour");
        operationService.create(operation);
        Integer id = operationService.readByName("lpModelFour").get().getId();
        operationService.deleteById(id);
        Assert.assertFalse(operationService.readById(id).isPresent());
    }

    @Test
    public void deleteAllTest() {
        Operation op1 = new Operation();
        Operation op2 = new Operation();
        operationService.create(op1);
        operationService.create(op2);
        operationService.deleteAll();
        Assert.assertTrue(operationService.readAll().spliterator().estimateSize() == 0L);
    }
}
