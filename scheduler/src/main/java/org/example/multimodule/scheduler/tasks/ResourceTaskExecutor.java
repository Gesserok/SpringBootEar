package org.example.multimodule.scheduler.tasks;

import lombok.AllArgsConstructor;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

//@Service
//@Scope(
//        value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
//        proxyMode = ScopedProxyMode.TARGET_CLASS)
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class ResourceTaskExecutor implements Runnable{

    private ResourceTask resourceTask;
    private ResourceTaskLoader resourceTaskLoader;

    @Override
    public void run() {
        System.out.println("ResourceTask = " + resourceTask.getName());
        resourceTaskLoader.saveRegions(resourceTask);
    }

}
