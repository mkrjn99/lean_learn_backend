import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

class KnowledgeBaseImpl implements KnowledgeBase {
    ArrayList<String> statements = new ArrayList<>();

    public void feedKnowledge(String statement) {
        statements.add(statement);
    }

    public void printAllKnowledge() {
        try(PrintWriter fw = new PrintWriter(new FileWriter("output.txt", /* append mode =  */ true))){
            for(String statement:statements) {
                fw.println(statement);
            }
        } catch (IOException e) {
            System.err.println("An IOException occurred: " + e.getMessage());
        }
    }
}
