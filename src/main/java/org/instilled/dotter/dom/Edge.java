package org.instilled.dotter.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing an {@link Edge} between two or more {@link Node}s.
 * Note that each {@link Node} instance should also exist in the parent
 * {@link Dot} instance, that is you should always create {@link Edge}s
 * by invoking {@link Dot#edge(String...)}.
 */
public class Edge {
    private final List<Node> nodes;

    private final Map<String, String> attrs = new HashMap<String, String>();

    public Edge(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Map<String, String> getAttributes() {
        return attrs;
    }

    public Edge attr(String id, String value) {
        attrs.put(id, value);
        return this;
    }
}