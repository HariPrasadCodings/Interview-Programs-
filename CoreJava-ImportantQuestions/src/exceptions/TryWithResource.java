package exceptions;

import java.io.FileReader;
import java.io.IOException;

/**
 * After java v7 onwards i.e from java8 onwards there is no mandatory to use try
 * block along with catch or finally block. There is a concept called try with
 * resource, which will close the resources automatically.
 */

public class TryWithResource {

	public static void main(String[] args) throws IOException {

		try (FileReader fileReader = new FileReader(
				"/home/hkathi/Documents/HariPrasadResume.txt")) {
			System.out.println("Reading file: " + fileReader.read());
		}

	}

}
