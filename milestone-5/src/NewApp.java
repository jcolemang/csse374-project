import CommandLineArgument.Configuration;
import analyzers.ClassNodeTraverser;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.GraphParser;
import projectFile.TextAggregator;

import java.io.IOException;

/**
 * Created by coleman on 2/14/17.
 */
public class NewApp {
    public static void main(String[] args) {

        Configuration conf = Configuration.getInstance();
        conf.readFromSettingsFile();

        GraphParser gp = new GraphParser();
        DOMGraph dg = new DOMGraph();
        DOMGraph fine = new $$$NewDOMGraph$$$(dg);

        ClassNodeGraph cng;
        try {
            cng = gp.parse(conf.getWhitelist());
            fine.generateDOMTree(cng);
            ClassNodeTraverser traverser = new ClassNodeTraverser(cng, fine);
            traverser.analyzeAll();
            TextAggregator agg = new TextAggregator();
            agg.writeFile("out.dot", fine);
        } catch (IOException e) {
            System.out.println("SOMETHING BAD HAPPENED!!!!");
            return;
        } catch (InstantiationException e) {
            System.out.println("Another bad thing?");
            return;
        } catch (IllegalAccessException e) {
            System.out.println("Honestly not even sure what this one means.");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Look at me, catching this like a good programmer!!!");
            return;
        }

        System.out.println("DONE");





    }
}
