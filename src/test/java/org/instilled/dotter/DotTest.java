package org.instilled.dotter;

import org.instilled.dotter.dom.Direction;
import org.instilled.dotter.dom.Dot;
import org.instilled.dotter.dom.Node;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DotTest {
    /**
     * Tests {@link Dot} behaves as it should.
     */
    @Test
    public void testGraph() {
        Dot dot = Dotter.graph("First");

        dot.node("a").attr("shape", "box").attr("label", "Root");
        dot.edge("a", "b").attr("color", "blue");
        dot.edge("b", "c");
        dot.edge("b", "d");

        assertThat(dot.getName()).isEqualTo("First");
        assertThat(dot.getDirection()).isEqualTo(Direction.UNDIRECTED);
        assertThat(dot.getNodes()).hasSize(4);
        assertThat(dot.getEdges()).hasSize(3);
    }

    /**
     * Same as above but that its output is truly a directed graph
     * (digraph).
     */
    @Test
    public void testDigraph() {
        Dot dot = Dotter.digraph("Digraph");
        assertThat(dot.getName()).isEqualTo("Digraph");
        assertThat(dot.getDirection()).isEqualTo(Direction.DIRECTED);

        dot.edge("a", "b");
        assertThat(dot.getNodes()).hasSize(2);
        assertThat(dot.getEdges()).hasSize(1);
    }

    /**
     * Test support for Object.
     */
    @Test
    public void testObjectSupport() {
        Dot dot = Dotter.graph("ObjectSupport");
        assertThat(dot.getName()).isEqualTo("ObjectSupport");
        assertThat(dot.getDirection()).isEqualTo(Direction.UNDIRECTED);

        dot.edge(new Object1(), "node2", new Object2());
        assertThat(dot.getNodes()).hasSize(3);
        assertThat(dot.getEdges()).hasSize(1);
        assertThat(dot.getNodes()).extracting(Node::getId).contains("node2");
    }

    @Test
    public void testTooFewEdges() {
        Dot dot = Dotter.digraph("Digraph");
        assertThat(dot.getName()).isEqualTo("Digraph");
        assertThat(dot.getDirection()).isEqualTo(Direction.DIRECTED);
        assertThatThrownBy(() -> dot.edge("a")).isInstanceOf(IllegalStateException.class);
    }
}
