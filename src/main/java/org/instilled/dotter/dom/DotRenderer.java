package org.instilled.dotter.dom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An trivial {@link Dot} renderer. It supports rendering {@link Dot} to
 * string or a file.
 */
public abstract class DotRenderer
{
    /**
     * Renders the {@link Dot} to a string.
     * 
     * @return
     */
    public static String render(Dot dot)
    {
        return renderDot(dot);
    }

    /**
     * Renders {@link Dot} spec to a file.
     * 
     * @param fileName
     *            The name of the output file.
     * @return The string written to the file.
     */
    public static String render(Dot dot, String fileName)
    {
        try
        {
            FileWriter out = new FileWriter(fileName);
            String rendered = renderDot(dot);
            out.write(rendered);
            out.close();
            return rendered;
        } catch (IOException e)
        {
            // TODO replace exception
            throw new IllegalStateException("Rendering failed.", e);
        }
    }

    /**
     * {@link Dot} class renderer. Calls
     * {@link #renderNodes(StringBuilder, Dot)} and
     * {@link #renderEdges(StringBuilder, Dot)}.
     * 
     * @param dot
     *            The {@link Dot} to be rendered.
     * @return The string representation of <code>dot</code>.
     */
    private static String renderDot(Dot dot)
    {
        StringBuilder out = new StringBuilder();

        String type = Direction.UNDIRECTED//
                .equals(dot.getDirection()) ? "graph" : "digraph";
        out.append(type).append(" ").append(dot.getName())
                .append(" {\n");

        renderNodes(out, dot);
        renderEdges(out, dot);

        out.append("}\n");
        return out.toString();
    }

    /**
     * {@link Node} renderer.
     * 
     * @param out
     *            {@link StringBuilder} into which to render.
     * @param dot
     *            The {@link Dot} instance to be rendered.
     */
    private static void renderNodes(StringBuilder out, Dot dot)
    {
        for (Node node : dot.getNodes())
        {
            if (!node.getAttributes().isEmpty())
            {
                out.append("   \"").append(node.getId()).append("\"");
                renderAttributes(out, node.getAttributes());
                out.append(";\n");
            }
        }
    }

    /**
     * {@link Edge} renderer.
     * 
     * @param out
     *            {@link StringBuilder} into which to render.
     * @param dot
     *            The {@link Dot} instance to be rendered.
     */
    private static void renderEdges(StringBuilder out, Dot dot)
    {
        String direction = Direction.UNDIRECTED//
                .equals(dot.getDirection()) ? "--" : "->";
        for (Edge edge : dot.getEdges())
        {
            String sep = "";
            out.append("   ");
            for (Node node : edge.getNodes())
            {
                out.append(sep);
                out.append("\"").append(node.getId()).append("\"");
                sep = " " + direction + " ";
            }
            renderAttributes(out, edge.getAttributes());
            out.append(";\n");
        }
    }

    /**
     * Renders {@link Node} or {@link Edge} attributes.
     * 
     * @param out
     *            {@link StringBuilder} into which to render.
     * @param attrs
     *            Map of attributes where the key is the attribute name
     *            and the value its value.
     */
    private static void renderAttributes(StringBuilder out,
            Map<String, String> attrs)
    {
        if (!attrs.isEmpty())
        {
            String sep = "";
            out.append(" [");
            for (Entry<String, String> attr : attrs.entrySet())
            {
                String id = attr.getKey();
                String val = attr.getValue();
                out.append(sep);
                out.append(id).append("=").append("\"").append(val)
                        .append("\"");
                sep = ", ";
            }
            out.append("]");
        }
    }
}
