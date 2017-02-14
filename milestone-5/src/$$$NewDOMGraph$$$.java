import DOMNodes.IDOMNode;
import projectFile.DOMGraph;

import javax.net.ssl.HandshakeCompletedEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by coleman on 2/14/17.
 *
 * Time for some Java trickery
 */
public class $$$NewDOMGraph$$$ extends DOMGraph {

    protected DOMGraph forRealGraph;
    protected List<IDOMNode> extras;

    public $$$NewDOMGraph$$$(DOMGraph g) {
        this.forRealGraph = g;
        this.extras = new LinkedList<>();
    }


    public void addDOMNode(IDOMNode dn) {
        this.extras.add(dn);
    }


    @Override
    public Iterator<IDOMNode> iterator() {
        return new HackIterator(this.forRealGraph.iterator(), this.extras.iterator());
    }


    private class HackIterator implements Iterator<IDOMNode> {

        private Iterator<IDOMNode> orig;
        private Iterator<IDOMNode> extra;

        public HackIterator(Iterator<IDOMNode> start, Iterator<IDOMNode> next) {
            this.orig = start;
            this.extra = next;
        }


        public boolean hasNext() {
            if (orig.hasNext()) {
                return true;
            }
            return extra.hasNext();
        }


        public IDOMNode next() {
            if (orig.hasNext()) {
                return orig.next();
            }
            return extra.next();
        }

    }

}
