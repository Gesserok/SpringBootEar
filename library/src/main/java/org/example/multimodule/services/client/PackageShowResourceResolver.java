package org.example.multimodule.services.client;

import org.example.multimodule.models.package_show.Resource;

import java.util.List;

@FunctionalInterface
public interface PackageShowResourceResolver {
    List<Resource> getResources(String packageId);
}
