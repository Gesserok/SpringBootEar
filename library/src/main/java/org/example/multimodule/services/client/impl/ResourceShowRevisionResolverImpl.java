package org.example.multimodule.services.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.annotations.constraints.ValidResponse;
import org.example.multimodule.dto.ResponseResourceShow;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.client.ResourceShowRevisionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceShowRevisionResolverImpl implements ResourceShowRevisionResolver {

    private final ODPClient<ResponseResourceShow> odpClient;

    @Override
    public List<ResourceTask> getAll(Resource resource) {

        @ValidResponse
        ResponseResourceShow resource_show = odpClient.getResponse(
                "resource_show",
                resource.getId(),
                ResponseResourceShow.class
        );



        return null;
    }
}
