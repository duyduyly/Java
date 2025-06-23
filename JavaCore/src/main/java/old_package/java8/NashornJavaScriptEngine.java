package old_package.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornJavaScriptEngine {

    /**
     * 1.Integration with Java: Nashorn enables seamless interaction between Java and JavaScript code,
     *      allowing you to call Java code from JavaScript and vice versa.
     * 2.JavaScript Execution: It allows the embedding and execution of JavaScript code
     *      within Java applications using the javax.script API (ScriptEngine).
     * 3.Command-Line Tool: Nashorn can be used as a standalone command-line tool (jjs),
     *      enabling you to run JavaScript files or scripts directly.
     *
     *      ===>Starting with Java 11, Nashorn was deprecated and later removed in Java 15
     */

    public static void main(String[] args) {
        // Create a ScriptEngineManager and get the Nashorn engine
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        // Execute JavaScript code
        try {
            engine.eval("print('Hello from Nashorn');");
            engine.eval("var list = new java.util.ArrayList();");
            engine.eval("list.add('Hello');");
            engine.eval("list.add('World');");
            engine.eval("print(list.get(0) + ' ' + list.get(1));");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
