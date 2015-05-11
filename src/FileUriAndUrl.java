
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class FileUriAndUrl {
	
	public void iterateFile(){
		File dir = new File("myDirectoryPath");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      // Do something with child
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		
	}

	public static void main(String[] args) throws IOException {

		File file = new File("file name with spaces.txt");

		URI fileUri = file.toURI();
		System.out.println("URI:" + fileUri);

		URL fileUrl = file.toURI().toURL();
		System.out.println("URL:" + fileUrl);

		URL fileUrlWithoutSpecialCharacterHandling = file.toURL();
		System.out.println("URL (no special character handling):" + fileUrlWithoutSpecialCharacterHandling);

	}

}
