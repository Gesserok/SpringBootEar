package org.example.multimodule.services.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dto.ResponsePackageShow;
import org.example.multimodule.exceptions.ODPResponseSuccessFalseException;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.client.PackageShowResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class PackageShowResourceResolverImpl implements PackageShowResourceResolver {

    private final ODPClient<ResponsePackageShow> odpClient;

    @Override
    public List<Resource> getResources(String packageId) {

        ResponsePackageShow responsePackageShow = odpClient.getResponse(
                "package_show",
                packageId,
                ResponsePackageShow.class
        );

        if (!responsePackageShow.getSuccess()) {
            log.error("package_show url return success = " + responsePackageShow.getSuccess());
            throw new ODPResponseSuccessFalseException("package_show url return success = " + responsePackageShow.getSuccess());
        }

        log.debug("Response \"package_show?id=" + packageId + " received successfully");

        return filter(responsePackageShow);
    }

    private List<Resource> filter(ResponsePackageShow responsePackageShow) {

        return responsePackageShow
                .getResult()
                .getResources()
                .stream()
                .filter(resource -> !resource.getName().contains("MVSWantedPassport_S"))
                .peek(log::debug)
                .collect(
                        Collectors.toList()
                );

    }
}
