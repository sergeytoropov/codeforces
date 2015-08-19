package boldinsv.problems.p4.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class C4C {
	public final static long P = 31;
	public final static long[] P32;
	
	static {
		P32 = new long[32 + 6];
		for (int pow = 0; pow < P32.length; pow++) {
			long result = 1;

			for (int index = 0; index < pow; index++) {
				result *= P;
			}
			P32[pow] = result;
		}
	}
	
	public static int hash(String s) {
		long result = 0;
		for (int index = 0; index < s.length(); index++) {
			result += (s.charAt(index) - 'a' + 1) * P32[index];
		}
		return (int) (result % Integer.MAX_VALUE);
	}
	
	public static class User {
		public final String fName;
		public final int fHash;
		
		public User(String name) {
			fName = name;
			fHash = C4C.hash(fName);
		}
		
		@Override
		public int hashCode() {
			return fHash;
		}
	
		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (other == null) {
				return false;
			}
			if (getClass() != other.getClass()) {
				return false;
			}
			User otherUser = (User) other;
			return fName.equals(otherUser.fName);
		}
	}
	
	
	public interface Algorithm {
		public void init();
		public void run();
		public void print();
	}
	
	public abstract class Problem {
		Algorithm algorithm;
		
		public void solve() {
			algorithm.init();
			algorithm.run();
			algorithm.print();
		}
	}
	
	public class P4C extends Problem {
		public P4C() {
			algorithm = new Algorithm4C();
		}
	}
	
	public class Algorithm4C implements Algorithm {
		public int n;
		public List<User> users = new ArrayList<User>();
		public List<String> answer = new ArrayList<String>();
		
		public void init() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				users.clear();
				try {
					n = Integer.parseInt(reader.readLine());
					for (int index = 0; index < n; index++) {
						users.add(new User(reader.readLine()));
					}
				} finally {
					reader.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		public void run() {
			answer.clear();
			Database database = new MiniDatabase();
			for (User user : users) {
				answer.add(database.add(user));
			}
		}
		
		public void print() {
			for (String each : answer) {
				System.out.println(each);
			}
		}
	}
	
	public interface Database {
		public String add(User user);
	}
	
	public class MiniDatabase implements Database {
		public class InternalUser {
			public final User fUser;
			public int fQuantity;
			
			public InternalUser(User user) {
				fUser = user;
				fQuantity = 0;
			}
		}
		
		public Map<Integer, List<InternalUser>> fUsers = new TreeMap<Integer, List<InternalUser>>();
		
		public String add(User user) {
			int hash = user.fHash;
			String answer = "OK";
			
			if (fUsers.containsKey(hash)) {
				List<InternalUser> similarUsers = fUsers.get(hash);
				InternalUser similarUser = find(similarUsers, user);
				
				if (similarUser == null) {
					similarUsers.add(new InternalUser(new User(user.fName)));
				} else {
					similarUser.fQuantity += 1;
					answer = similarUser.fUser.fName + similarUser.fQuantity;
					
					User newUser = new User(answer);
					if (hash == newUser.fHash) {
						similarUsers.add(new InternalUser(newUser));
					} else {
						put(newUser);
					}
				}
			} else {
				put(user);
			}
			return answer;
		}
		
		private InternalUser find(List<InternalUser> similarUsers, User user) {
			for (InternalUser each : similarUsers) {
				if (each.fUser.equals(user)) {
					return each;
				}
			}
			return null;
		}
		
		private void put(User user) {
			List<InternalUser> similarsUsers = new ArrayList<InternalUser>();
			similarsUsers.add(new InternalUser(new User(user.fName)));
			fUsers.put(user.fHash, similarsUsers);
		}
	}
	
	public static void main(String[] args) {
		Problem problem = (new C4C()).new P4C();
		problem.solve();
	}
}
