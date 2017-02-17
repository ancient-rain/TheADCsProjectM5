
public class DemeterDetector implements IDetector {
	
	Settings settings;
	Graph graph;
	
	public DemeterDetector() {
		this.settings = Settings.getInstance();
		this.graph = this.settings.getGraph();
	}

	@Override
	public void detect() {
		
	}

}
