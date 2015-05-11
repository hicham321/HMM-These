
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class FileUriAndUrl {

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
