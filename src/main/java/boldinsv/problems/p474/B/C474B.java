package boldinsv.problems.p474.B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C474B {
	public static class Heap {
		public int begin = 0;
		public int end = 0;
		public Heap(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	protected static int rank(int key, Heap[] heap, int lo, int hi) {
		if (hi < lo) return lo;

		int mid = lo + (hi - lo) / 2;

		//System.out.println(heap[mid].begin + " " + heap[mid].end + " " + mid + " key " + key);
		if (key < heap[mid].begin) {
			return rank(key, heap, lo, mid - 1);
		} else if (key > heap[mid].end) {
			return rank(key, heap, mid + 1, hi);
		} else {
			return mid;
		}
 	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] an = reader.readLine().split(" ");
		int m = Integer.parseInt(reader.readLine());
		String[] qm = reader.readLine().split(" ");
		reader.close();

		Heap[] heap = new Heap[n];
		int begin = 1;
		int end = 0;
		for (int index = 0; index < n; index++) {
			int delta = Integer.parseInt(an[index]);
			end += delta;
			heap[index] = new Heap(begin, end);
			begin += delta;
		}

		for (int index = 0; index < m; index++) {
			int pos = Integer.parseInt(qm[index]);
			System.out.println("" + (rank(pos, heap, 0, heap.length - 1) +1));
		}
	}
}