package org.graviton.sync.handler;

import org.graviton.sync.object.ActiveObject;

public interface RemoveHandler<K> {

    void onRemove(K object);

}
