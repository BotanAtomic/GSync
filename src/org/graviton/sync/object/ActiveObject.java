package org.graviton.sync.object;

import org.graviton.sync.handler.RemoveHandler;
import org.graviton.sync.handler.UpdateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ActiveObject<K> {
    private K object;

    private final ReentrantLock locker;

    private final List<RemoveHandler<K>> removeHandlers;
    private final List<UpdateHandler<K>> updateHandlers;

    public ActiveObject(K object) {
        this.object = object;
        this.removeHandlers = new ArrayList<>();
        this.updateHandlers = new ArrayList<>();
        this.locker = new ReentrantLock();
    }

    public void update(K newObject) {
        locker.lock();
        try {
            this.updateHandlers.forEach(handler -> handler.onUpdate(this.object, newObject));
            this.object = newObject;
        } finally {
            locker.unlock();
        }
    }

    public void cleanRemove() {
        locker.lock();
        try {
            this.removeHandlers.forEach(handler -> handler.onRemove(this.object));
            this.object = null;
        } finally {
            locker.unlock();
        }
    }


    public List<RemoveHandler<K>> getRemoveHandlers() {
        return removeHandlers;
    }

    public List<UpdateHandler<K>> getUpdateHandlers() {
        return updateHandlers;
    }



}
