package com.yuiko.study.service;

import com.yuiko.study.api.response.ResourcesPageDto;

public interface ResourcesService {
    ResourcesPageDto getResourcesByUserId(long uid);
}
