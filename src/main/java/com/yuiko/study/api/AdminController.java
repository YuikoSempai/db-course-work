package com.yuiko.study.api;

import com.yuiko.study.api.response.EnvironmentPage;
import com.yuiko.study.model.Environment;
import com.yuiko.study.service.AdminDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final AdminDbService adminDbService;

    public AdminController(AdminDbService adminDbService) {
        this.adminDbService = adminDbService;
    }

    @PostMapping("/best_env/add")
    public boolean addNewBestEnv(
            @RequestBody Environment environment
    ) {
        return adminDbService.addNewEnvironment(environment);
    }

    @GetMapping("/best_env")
    public EnvironmentPage getBestEnvironments() {
        return new EnvironmentPage(adminDbService.getAllEnvironments());
    }
}
