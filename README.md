Dotter
======
Dotter is a programmatic dot file generator. It is NOT a dot language renderer 
(i.e. replacement for graphviz [3]).

Wikipedia's introduction on the dot language [1] is a good starting point. For 
the full specification see [2].

[1]: http://en.wikipedia.org/wiki/DOT_language
[2]: http://www.graphviz.org/content/dot-language.
[3]: http://www.graphviz.org/

Features
--------
- programmatic dot generator
- support for Java objects as nodes
- partial type-safety

Installation
------------
The library has not yet been pushed to any public maven repository but 
installation is still fairly easy. Just download the [src:source] and run

`mvn install`

Finally declare it as a maven dependency.
`<dependency>
	<groupId>org.instilled</groupId>
	<artifactId>Dotter</artifactId>
	<version>0.0.1</version>
</depencendy>`

If your project is not using maven then either compile and package the library 
or copy the source to your project.

[src:source] 

Usage
-----
Using dotter is intuitive and self-explanatory. Import the Dotter class and 
start using it with *Dotter.graph(..)* or *Dotter.digraph(..)*. For more
information read Dotter's Javadoc. 

Contributing
------------
Any contributions are of course welcome! 

TODOs
-----
* push to maven central
* subgraphs not yet supported
* rendering support from within Java building upon graphviz or any other 
   renderer, maybe even native Java?
* normalize edges: currently an edge can spawn multiple nodes > aligned to 
   dot language (multiple nodes connected on one line = one edge)
* add DotRenderer tests (necessary?)
* make a fully typed interface (attributes included)
* JSON renderer?

Copyright and license
---------------------
Licensed under the New BSD License (aka Modified BSD License). See LICENSE file.
