import org.graviton.sync.object.ActiveObject;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        ActiveObject<Integer> syncInt = new ActiveObject<>(20);

        syncInt.getRemoveHandlers().add(object -> System.out.printf("Active Object with value %d was removed \n", object));
        syncInt.getUpdateHandlers().add((lastObject,newObject) -> System.out.printf("Update Active Object %d to %d \n", lastObject, newObject));

        IntStream.range(0,20).forEach(syncInt::update);

        syncInt.cleanRemove();
    }

}
