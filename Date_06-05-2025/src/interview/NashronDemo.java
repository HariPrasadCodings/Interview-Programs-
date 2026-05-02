package interview;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashronDemo {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("graal.js");

		String script = "var name = 'Ramesh'; name;";

		try {
			Object results = engine.eval(script);
			System.out.println(results);
		} catch (ScriptException e) {

			e.printStackTrace();
		}
	}

}
