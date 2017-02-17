import java.io.IOException;

public class Launcher {
	
	public static void main(String [] args) throws IOException, InterruptedException {
		CommandLine cl = new CommandLine();
		
		cl.addDetector("-gs", GetSetDetector.class);
		cl.addDetector("-dem", DemeterDetector.class);
		cl.run(args);
		
		return;
	}
}
