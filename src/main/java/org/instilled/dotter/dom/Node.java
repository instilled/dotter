package org.instilled.dotter.dom;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in the dot language.
 */
public class Node
{
    private String _id;

    private Map<String, String> _attrs = new HashMap<String, String>();

    public Node(String id)
    {
        _id = id;
    }

    public String getId()
    {
        return _id;
    }

    public Map<String, String> getAttributes()
    {
        return _attrs;
    }

    public Node attr(String id, String value)
    {
        _attrs.put(id, value);
        return this;
    }

}
