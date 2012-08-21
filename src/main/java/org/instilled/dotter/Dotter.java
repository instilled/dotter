// @formatter:off
/**
 * Copyright (c) 2012, instilled.org
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 
 *  * Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer. 
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution. 
 *  * Neither the name instilled.org nor the names of its contributors may 
 *    be used to endorse or promote products derived from this software 
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL instilled.org
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
// @formatter:on
package org.instilled.dotter;

import javax.xml.soap.Node;

import org.instilled.dotter.dom.Direction;
import org.instilled.dotter.dom.Dot;
import org.instilled.dotter.dom.DotRenderer;
import org.instilled.dotter.dom.Edge;

/**
 * {@link Dotter} is a programmatic dot language generator supporting
 * (partial) type-safety. Dotter's API entry points are all in the
 * {@link Dotter} class. Use the methods of {@link Dot}, {@link Edge} or
 * {@link Node} to further modify the graph. It should never be
 * necessary to manually instantiate any of the classes in the
 * <code>org.instilled.dotter.dom</code> package.<br>
 * <br>
 * <b>Example usage:</b>
 * 
 * <pre>
 * // Use Dotter.digraph(...) for directed graph.
 * Dot g1 = Dotter.graph(&quot;name&quot;);
 * g1.node(&quot;a&quot;).attr(&quot;shape&quot;, &quot;box&quot;);
 * g1.edge(&quot;a&quot;, &quot;b&quot;, &quot;c&quot;).attr(&quot;color&quot;, &quot;blue&quot;).attr(&quot;style&quot;, &quot;dotted&quot;);
 * g1.edge(&quot;b&quot;, &quot;d&quot;);
 * 
 * // Now render the graph to a file
 * Dotter.render(g1, &quot;myFirstGraph.gv&quot;);
 * 
 * </pre>
 * 
 * See http://www.graphviz.org/content/dot-language for the DOT
 * language.
 */
public class Dotter
{
    /**
     * Create an undirected graph.
     * 
     * @param name
     *            The name of the graph.
     * @return A {@link Dot} instance.
     */
    public static Dot graph(String name)
    {
        return newGraph(name, Direction.UNDIRECTED);
    }

    /**
     * Create a directed graph.
     * 
     * @param name
     *            The name of the graph.
     * @return A {@link Dot} instance.
     */
    public static Dot digraph(String name)
    {
        return newGraph(name, Direction.DIRECTED);
    }

    /**
     * Generates a string representation of the {@link Dot} graph.
     * 
     * @param dot
     *            The graph to be rendered.
     * @return String representation of <code>dot</code>.
     */
    public static String render(Dot dot)
    {
        return DotRenderer.render(dot);
    }

    /**
     * Renders a {@link Dot} graph to a file in DOT syntax. Also returns
     * the generated text as string.
     * 
     * @param dot
     *            The graph to be rendered.
     * @return String representation of <code>dot</code>.
     */
    public static String render(Dot dot, String fileName)
    {
        return DotRenderer.render(dot, fileName);
    }

    private static Dot newGraph(String name, Direction direction)
    {
        return new Dot(name, direction);
    }
}
