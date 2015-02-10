import java.io.File;

import static org.junit.Assert.*;
import org.junit.Test;

public class TextBuddyTest {
	
	@Test
	public void testIsFileEmpty() {
		TextBuddy tester = new TextBuddy();
		
		assertEquals(true,tester.isFileEmpty(new File("mytextfile.txt")));
	}

}
