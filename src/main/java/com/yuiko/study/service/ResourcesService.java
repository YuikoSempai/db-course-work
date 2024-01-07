package com.yuiko.study.service;

import com.yuiko.study.api.response.ResourcesPageDto;
import com.yuiko.study.api.response.UserResources;

public interface ResourcesService {
    ResourcesPageDto getResourcesByUserId(long uid);

    boolean addResourcesForUser(long uid, UserResources userResources);

    ResourcesPageDto getBestEnv(long uid);
}
