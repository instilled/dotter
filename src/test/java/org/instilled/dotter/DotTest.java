package org.instilled.dotter;

import static org.junit.Assert.assertEquals;

import org.instilled.dotter.dom.Direction;
import org.instilled.dotter.dom.Dot;
import org.instilled.dotter.dom.Node;
import org.junit.Test;

public class DotTest
{
    /**
     * Tests {@link Dot} behaves as it should.
     */
    @Test
    public void testGraph()
    {
        Dot dot = Dotter.graph("First");

        dot.node("a").attr("shape", "box").attr("label", "Root");
        dot.edge("a", "b").attr("color", "blue");
        dot.edge("b", "c");
        dot.edge("b", "d");

        assertEquals("First", dot.getName());
        assertEquals(Direction.UNDIRECTED, dot.getDirection());
        assertEquals(4, dot.getNodes().size());
        assertEquals(3, dot.getEdges().size());
    }

    /**
     * Same as above but that its output is truly a directed graph
     * (digraph).
     */
    @Test
    public void testDigraph()
    {
        Dot dot = Dotter.digraph("Digraph");
        assertEquals("Digraph", dot.getName());
        assertEquals(Direction.DIRECTED, dot.getDirection());

        dot.edge("a", "b");
        assertEquals(2, dot.getNodes().size());
        assertEquals(1, dot.getEdges().size());
    }

    /**
     * Test support for Object.
     */
    @Test
    public void testObjectSupport()
    {
        Dot dot = Dotter.graph("ObjectSupport");
        assertEquals("ObjectSupport", dot.getName());
        assertEquals(Direction.UNDIRECTED, dot.getDirection());

        dot.edge(new Object1(), "node2", new Object2());
        assertEquals(3, dot.getNodes().size());
        assertEquals(1, dot.getEdges().size());

        // Make sure the correct Dot#node method was invoked
        // for String arguments
        boolean found = false;
        for (Node node : dot.getNodes())
        {
            if ("node2".equals(node.getId()))
            {
                found = true;
                break;
            }
        }
        assertEquals("Could not find the nodes name.", true, found);
    }

    @Test(expected = IllegalStateException.class)
    public void testTooFewEdges()
    {
        Dot dot = Dotter.digraph("Digraph");
        assertEquals("Digraph", dot.getName());
        assertEquals(Direction.DIRECTED, dot.getDirection());

        dot.edge("a");
    }
}
