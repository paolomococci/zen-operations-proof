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

import local.tiny.proof.dao.LinearProgrammingDAO;
import local.tiny.proof.model.LinearProgramming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LinearProgrammingService {

    @Autowired
    private LinearProgrammingDAO linearProgrammingDAO;

    @Transactional
    public void addLinearProgrammingModel(LinearProgramming linearProgramming) {
        linearProgrammingDAO.save(linearProgramming);
    }

    public LinearProgramming retrieveLinearProgrammingModelById(Integer id) {
        return linearProgrammingDAO.getOne(id);
    }

    public LinearProgramming retrieveLinearProgrammingModelByName(String name) {
        return linearProgrammingDAO.findByName(name);
    }

    public List<LinearProgramming> retrieveLinearProgrammingModels() {
        return linearProgrammingDAO.findAll();
    }

    @Transactional
    public void deleteLinearProgrammingModel(LinearProgramming linearProgramming) {
        linearProgrammingDAO.deleteById(linearProgramming.getId());
    }
}
