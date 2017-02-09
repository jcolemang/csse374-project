package projectFile;

import graphNodes.IClassVertex;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coleman on 2/8/17.
 */
public class CodeData {
    private List<IClassVertex> relevantClasses;

    public CodeData() {
        this.relevantClasses = new LinkedList<>();
    }

    public void addClass(IClassVertex classVertex) {
        this.relevantClasses.add(classVertex);
    }


    public List<IClassVertex> getClasses() {
        return this.relevantClasses;
    }
}
