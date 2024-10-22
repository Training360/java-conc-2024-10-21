package collections;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CollectionsMain {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("John", "Jack"));
        List<String> syncronizedName = Collections.synchronizedList(names);

        names.add("Jane");
        log.debug("Size: {}", syncronizedName.size());

    }
}
