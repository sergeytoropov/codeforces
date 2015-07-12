package boldinsv.problems.p474.A.test;

import java.lang.StringBuffer;
import java.io.*;
import boldinsv.problems.p474.A.C474A;

public class C474ATest {
	public static void main(String[] args) throws IOException {
		StringBuffer sBuf = new StringBuffer("");
		sBuf.append("R").append("\n");
		sBuf.append("s;;upimrrfod;pbr").append("\n");

		ByteArrayInputStream iStream = new ByteArrayInputStream(sBuf.toString().getBytes());
		InputStream in = System.in;

		System.setIn(iStream);

		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		PrintStream pStream = new PrintStream(oStream);
		PrintStream out = System.out;

		System.setOut(pStream);

		C474A c474a = new C474A();
		c474a.main(null);

		System.setIn(in);
		System.setOut(out);

		BufferedReader reader = new BufferedReader(new StringReader(oStream.toString()));
		if ("allyouneedislove".equals(reader.readLine())) {
			System.out.println("Test number 1 is successufully!");
		} else {
			System.out.println("Test number 1 is NOT successufully!");
		}
	}
}