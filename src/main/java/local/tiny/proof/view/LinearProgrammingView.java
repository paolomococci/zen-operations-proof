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

package local.tiny.proof.view;

import local.tiny.proof.model.LinearProgramming;
import local.tiny.proof.service.LinearProgrammingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "lpView")
@RequestScoped
public class LinearProgrammingView {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    @Autowired
    LinearProgrammingService linearProgrammingService;

    private List<LinearProgramming> linearProgrammingList;

    private String name;

    public String addLP() {
        try {
            LinearProgramming linearProgramming = new LinearProgramming();
            linearProgramming.setName(name);
            return SUCCESS;
        } catch (DataAccessException dae) {
            dae.getMessage();
        }
        return FAILURE;
    }

    public void reset() {
        setName("");
    }

    public LinearProgrammingService getLinearProgrammingService() {
        return linearProgrammingService;
    }

    public void setLinearProgrammingService(LinearProgrammingService linearProgrammingService) {
        this.linearProgrammingService = linearProgrammingService;
    }

    public List<LinearProgramming> getLinearProgrammingList() {
        linearProgrammingList = new ArrayList<>();
        linearProgrammingList.addAll(linearProgrammingService.retrieveLinearProgrammingModels());
        return linearProgrammingList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
