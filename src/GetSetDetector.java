import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class GetSetDetector implements IDetector {
	
	Settings settings;
	Graph graph;
	
	public GetSetDetector() {
		this.settings = Settings.getInstance();
		this.graph = this.settings.getGraph();
	}

	@Override
	public void detect() {
		ArrayList<ClassInfo> classes = this.graph.getClasses();
		
		for (ClassInfo ci: classes) {
			List<FieldNode> fields = ci.getFields();
			List<MethodNode> methods = ci.getMethods();
			
			HashSet<String> setFieldNames = new HashSet<>();
			HashSet<String> getFieldNames = new HashSet<>();
			HashSet<String> methodNames = new HashSet<>();
			
			for (MethodNode m: methods) {
				String method = m.name;
				methodNames.add(method.toLowerCase());
			}
			
			for (FieldNode f: fields) {
				String field = f.name;
				getFieldNames.add(field.toLowerCase());
				setFieldNames.add(field.toLowerCase());
			}
			
			for (String m: methodNames) {
				String get = "get";
				String set = "set";
				String firstThree = m.substring(0, 3);
				String rest = m.substring(3);
				
				if (firstThree.equals(get)) {
					if (getFieldNames.contains(rest)) {
						getFieldNames.remove(rest);
					}
				} else if (firstThree.equals(set)) {
					if (setFieldNames.contains(rest)) {
						setFieldNames.remove(rest);
					}
				}
			}
			
			if (getFieldNames.size() != 0 && setFieldNames.size() != 0) {
				ci.setColor("gold");
			}
		}
	}

}
