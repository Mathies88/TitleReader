import java.io.*;
import java.util.*;

public class TitleReader {

   private HashMap<String, String> extensions = new HashMap<String, String>();
   private TreeMap<String, String> result = new TreeMap<String, String>();
	
   public void addExtensions(){
		
      extensions.put(".iso", "DvDR");
      extensions.put(".img", "DvDR");
      extensions.put(".m2ts", "DvDR");
      extensions.put(".avi", "XVid");
      extensions.put(".mkv", "HD  ");
   }
	
   public void findMovies(String directoryName) throws IOException {
      File directory = new File(directoryName);
      String[] children = directory.list();
      for (int i = 0; i < children.length; i++) {
	 File f = new File(directoryName + "/"+ children[i]);
	 if (f.isDirectory()) 
	    findMovies(directoryName + "/"+ children[i]);
	 else 
	    addMovieName(children[i]);
      }
   }
	
   public void addMovieName(String fileName) {
      String extension = fileName.substring(fileName.lastIndexOf('.'));
      extension = extension.toLowerCase();
      for (String s : extensions.keySet()) {
			if (extension.equals(s)) {
	    	String name = fileName.substring(0, fileName.lastIndexOf('.'));
	    	result.put(name, extensions.get(s));
	 		}
      }
   }
	
   public void printResults(String listName) throws IOException {
      File list = new File(listName);
      BufferedWriter output = new BufferedWriter(new FileWriter(list));
		
      output.write("*******************************");
      output.newLine();
      output.write("Signaturforklaring:");
      output.newLine();
      output.newLine();
      output.write("DvDR = iso, img, m2ts");
      output.newLine();
      output.write("HD = mkv");
      output.newLine();
      output.write("Xvid = avi");
      output.newLine();
      output.write("*******************************");
      output.newLine();
      output.newLine();
      output.newLine();
		
      int counter = 0;
      for (String s : result.keySet()) {
	  		if (s != null){
	    		output.write(String.format("%04d", counter) + " - " + result.get(s) + " - " + s);
	    		output.newLine();
	    		counter++;
	 		}			
      }
      output.close();
   }
	
   //args[0] = name of the movie list text file.
   //args[1] = directory of the movie folder
   public static void main(String[] args) throws IOException{
      TitleReader tr = new TitleReader();
      tr.addExtensions();
      tr.findMovies(args[1]);
      tr.printResults(args[0]);
   }
}
