package boldinsv.problems.p474.B.test;

import boldinsv.problems.p474.B.C474B;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.PrintStream;
import java.io.IOException;

public class C474BTest {
	public static void main(String[] args) throws IOException {
		StringBuffer sBuf = new StringBuffer("");
		sBuf.append("5").append("\n");
		sBuf.append("2 7 3 4 9").append("\n");
		sBuf.append("3").append("\n");
		sBuf.append("1 25 11").append("\n");

		ByteArrayInputStream iStream = new ByteArrayInputStream(sBuf.toString().getBytes());
		InputStream in = System.in;
		System.setIn(iStream);

		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		PrintStream pStream = new PrintStream(oStream);
		PrintStream out = System.out;
		System.setOut(pStream);

		C474B c474b = new C474B();
		c474b.main(null);

		System.setIn(in);
		System.setOut(out);

		BufferedReader reader = new BufferedReader(new StringReader(oStream.toString()));
		String sTemp;
		while ( (sTemp = reader.readLine()) != null) {
			System.out.println(sTemp);
		}
	}
}