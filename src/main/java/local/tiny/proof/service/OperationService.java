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
import local.tiny.proof.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Transactional
    public void create(Operation operation) {
        operationRepository.save(operation);
    }

    public Optional<Operation> read(Integer id) {
        return operationRepository.findById(id);
    }

    public Iterable<Operation> readAll() {
        return operationRepository.findAll();
    }

    @Transactional
    public void updateName(Integer id, String name) {
        if (operationRepository.existsById(id)) {
            operationRepository.findById(id)
                    .get().setName(name);
            operationRepository.flush();
        }
    }

    @Transactional
    public void delete(Integer id) {
        operationRepository.deleteById(id);
    }
}
