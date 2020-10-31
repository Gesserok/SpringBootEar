package org.example.multimodule.application.infrastructure;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Log4j2
public class TimedCachebleScope implements Scope {

    public static final String TIMED_CACHEBLE = "timedCacheble";
    private static final long EXPIRY_TIME_MINUTES = 5;

    private final Map<String, TimedCacheItem> cache = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Runnable> destructionCallbacks = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (!cache.containsKey(name)) {
            cache.put(name, new TimedCacheItem(objectFactory.getObject()));
        }

        final TimedCacheItem cacheItem = cache.get(name);

        if (Objects.isNull(cacheItem.getObject())) {
            cacheItem.update(objectFactory.getObject());
        }
        return cacheItem.getObject();
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return cache.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        destructionCallbacks.put(name, runnable);
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return TIMED_CACHEBLE;
    }

    private static class TimedCacheItem {
        private long creationTime;
        private Object object;

        TimedCacheItem(Object object) {
            update(object);
        }

        public Object getObject() {
            return expiry() ? null : object;
        }

        void update(Object object) {
            log.info("Configuration timed cache has updated");
            this.creationTime = System.currentTimeMillis();
            this.object = object;
        }

        private boolean expiry() {
            return Duration.ofMillis(System.currentTimeMillis())
                    .minusMillis(creationTime)
                    .toMinutes() >= EXPIRY_TIME_MINUTES;
        }
    }
}
