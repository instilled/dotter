# Dotter
Dotter is a programmatic dot file generator. It is NOT a dot language renderer, i.e. a replacement for [graphviz][3].

[Wikipedia's introduction][1] on the dot language is a good starting point. Alternatively consult the
[full specification][2].

## Features
- programmatic dot generator
- support for Java objects as nodes
- partial type-safety

## Using the library

TBD

## Usage

Import the Dotter class and start using it with *Dotter.graph(..)* or *Dotter.digraph(..)*. For more
information read Dotter's Javadoc or check the test. 

## Contributing
Any contributions are of course welcome! 

## TODOs
* publish library
* subgraphs not yet supported
* rendering support from within Java building upon graphviz or any other 
   renderer, maybe even native Java?
* normalize edges: currently an edge can spawn multiple nodes > aligned to 
   dot language (multiple nodes connected on one line = one edge)
* add DotRenderer tests (necessary?)
* make a fully typed interface (attributes included)
* JSON renderer?

## Copyright and license
Licensed under the New BSD License (aka Modified BSD License). See LICENSE file.

[1]: http://en.wikipedia.org/wiki/DOT_language
[2]: http://www.graphviz.org/content/dot-language.
[3]: http://www.graphviz.org/
[allsrc]: https://github.com/instilled/dotter/zipball/master
