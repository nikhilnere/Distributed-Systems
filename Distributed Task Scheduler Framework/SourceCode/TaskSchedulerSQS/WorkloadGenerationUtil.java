import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class WorkloadGenerationUtil {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int noOfTasks = Integer.parseInt(args[0]);
		String task = args[1];
		String filename = args[2];
		BufferedWriter w = new BufferedWriter(new FileWriter(filename));
		
		for (int i=0; i<noOfTasks; i++){
			w.write(task);
			w.newLine();
		}
		w.close();
	}

}
