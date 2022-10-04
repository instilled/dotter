package org.instilled.dotter.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.instilled.dotter.Dotter;

/**
 * {@link Dot} is the root of the dot spec. For example usages see
 * {@link Dotter}. <br>
 * <br>
 * To change a {@link Node}'s attributes after it has been created
 * implicitly by calling {@link Dot#edge(String...)} retrieve its
 * instance by calling {@link Dot#node(String)} and then adding its
 * attributes.<br>
 * <br>
 * Internas:
 * <ul>
 * <li>for each node added to the graph, either by invoking
 * {@link Dot#node(String)}, {@link Dot#node(String, String)}, or
 * {@link Dot#edge(String...)}, a new {@link Node} instance is created
 * and registered in {@link Dot}.</li>
 * <li>{@link Edge}s will be connected to {@link Node}s existing only in
 * the current {@link Dot} instance.</li>
 * </ul>
 *
 * @see Dot#node(String)
 * @see Dotter
 */
public final class Dot {
    private final String name;
    private final Direction direction;
    private final Map<String, Node> nodes = new HashMap<String, Node>();
    private final List<Edge> edges = new LinkedList<Edge>();

    /**
     * Not usually invoked directly but by invoking
     * {@link Dotter#graph(String)} or {@link Dotter#digraph(String)}
     * instead.
     *
     * @param name      The name uniquely identifying this graph.
     * @param direction The graphs direction.
     */
    public Dot(String name, Direction direction) {
        this.name = name;
        this.direction = direction;
    }

    /**
     * The name associated to this graph.
     *
     * @return The graph's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the graph's direction.
     *
     * @return The graph's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * A list of {@link Node}s the graph is made of.
     *
     * @return A list of {@link Node}s.
     */
    public List<Node> getNodes() {
        return new ArrayList<Node>(nodes.values());
    }

    /**
     * Retrieves a {@link Node} with the given id, creating it if it
     * does not yet exist.
     *
     * @param id The {@link Node}s unique <code>id</code>.
     * @return The {@link Node} associated to <code>id</code>.
     * @see Dot#node(String, String)
     */
    public Node node(String id) {
        return node(id, null);
    }

    /**
     * Takes an Object as a {@link Node}s id. Uses the (simple) class
     * name and it's hash code separated by '#' as label.
     *
     * @param id The Object to use as node id.
     * @return The {@link Node} associated to <code>id</code>.
     */
    public Node node(Object id) {
        /*
         * It seems that not the most specific method is called when
         * passing a String to the node(..) method. I was expecting
         * node(String) to be called for strings instead of
         * node(Object).
         */

        if (id instanceof String) {
            return node(id, null);
        }

        return node(
                id,
                id.getClass().getSimpleName() + "#"
                        + Integer.toHexString(id.hashCode()));
    }

    /**
     * Takes an Object as a {@link Node}s id.
     *
     * @param id The Object to use as node id.
     * @return The {@link Node} associated to <code>id</code>.
     */
    public Node node(Object id, String label) {
        return node(id.toString(), label);
    }

    /**
     * Retrieves a {@link Node} with the given id, creating it if it
     * does not yet exist, using <code>label</code> as the {@link Node}
     * 's label (adds the <i>label</i> attribute).
     *
     * @param id    The {@link Node}s unique <code>id</code>.
     * @param label
     * @return The {@link Node} associated to <code>id</code>.
     */
    public Node node(String id, String label) {
        Node node = nodes.get(id);
        if (node == null) {
            node = new Node(id);
            nodes.put(id, node);
        }

        if (label != null) {
            node.attr("label", label);
        }

        return node;
    }

    /**
     * Get the graphs {@link Edge}s.
     *
     * @return The {@link Edge}s this graph is made of.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Adds an {@link Edge} to this graph, implicitly creating
     * {@link Node} instances for each node that has not previously been
     * defined. If labels
     *
     * @param nodes A list of {@link Node}s to be connected.
     * @return An instance of {@link Edge}.
     */
    public Edge edge(Object... nodes) {
        // TODO an edge, technically is between two nodes.
        // In dot language an edge is also between multiple nodes.
        // Shall we normalize? For now we stick to the dot terminology.
        if (nodes.length < 2) {
            throw new IllegalStateException(
                    "An edge requires at least two nodes!");
        }

        List<Node> newNodes = new LinkedList<Node>();
        for (Object id : nodes) {
            Node node = node(id);
            newNodes.add(node);
        }

        Edge e = new Edge(newNodes);
        edges.add(e);
        return e;
    }

    /**
     * Adds an {@link Edge} to this graph, implicitly creating
     * {@link Node} instances for each node that has not previously been
     * defined.
     *
     * @param nodes A list of {@link Node}s to be connected.
     * @return An instance of {@link Edge}.
     */
    public Edge edge(String... nodes) {
        if (nodes.length < 2) {
            throw new IllegalStateException(
                    "An edge requires at least two nodes!");
        }

        List<Node> newNodes = new LinkedList<Node>();
        for (String id : nodes) {
            Node node = node(id, null);
            newNodes.add(node);
        }

        Edge e = new Edge(newNodes);
        edges.add(e);
        return e;
    }
}
