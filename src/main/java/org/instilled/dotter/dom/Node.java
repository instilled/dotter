package org.instilled.dotter.dom;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in the dot language.
 */
public class Node {
    private final String id;

    private final Map<String, String> attrs = new HashMap<String, String>();

    public Node(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getAttributes() {
        return attrs;
    }

    public Node attr(String id, String value) {
        attrs.put(id, value);
        return this;
    }

}
