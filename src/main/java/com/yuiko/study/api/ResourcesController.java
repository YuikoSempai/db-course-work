package com.yuiko.study.api;

import com.yuiko.study.api.response.ResourcesPageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/resources", produces = APPLICATION_JSON_VALUE)
public class ResourcesController {

    @GetMapping("/{userId}")
    public ResourcesPageDto getResourcesByUser(@PathVariable String userId) {
        //todo implement
        return new ResourcesPageDto();
    }
}
