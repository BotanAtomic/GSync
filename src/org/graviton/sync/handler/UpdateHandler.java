package org.graviton.sync.handler;

import org.graviton.sync.object.ActiveObject;

public interface UpdateHandler<K> {

    void onUpdate(K lastObject, K newObject);

}
