package boldinsv.problems.p380.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C380A2 {
	public interface Solve {
		public void parsing(String[] params);
		public String[] run(String[] params);
	}

	public static class Algorithm implements Solve {
		private int n;
		private String[] sActions;
		private int m;
		private long[] indexes;

		public void parsing(String[] params) {
			int position = 0;
			n = Integer.parseInt(params[position]);

			sActions = new String[n];
			for ( ; position < n; position++ ) {
				sActions[position] = params[position + 1];
			}

			position += 1;
			m = Integer.parseInt(params[position]);

			int idx = 0;
			indexes = new long[m];

			position += 1;
			String[] sIndexes = params[position].split(" ");
			for ( String index: sIndexes ) {
			 	indexes[idx] = Long.parseLong(index);
				idx += 1;
			}
		}

		public String[] run(String[] params) {
			parsing(params);

			final int stateInit = 0;
			final int stateExit = 1;
			final int stateCompare = 2;
			final int stateNext = 3;
			final int stateAnswer = 4;

			ActionsList aList = null;
			Actions miniActions = null;
			PolynomList pList = null;
			Polynom polynom = null;

			StringBuffer sBuf = new StringBuffer("");

			int state = stateInit;
			for ( int i = 0; i < indexes.length; i++ ) {
				long index = indexes[i];

				boolean isStoped = false;
				while ( !isStoped ) {
					switch ( state ) {
						case stateInit:
							aList = new ActionsList(sActions);
							miniActions = null;
							if ( aList.hasNext() ) {
								miniActions = aList.next();
								pList = new PolynomList(miniActions);
								polynom = pList.getPolynom(miniActions);
							}
							state = stateCompare;
							break;

						case stateExit:
							isStoped = true;
							state = stateCompare;
							break;

						case stateCompare:
							state = stateExit;
							if ( miniActions != null ) {
								state = stateNext;
								if ( index >= polynom.begin && index <= polynom.end ) {
									state = stateAnswer;
								}
							}
							break;

						case stateNext:
							miniActions = null;
							if ( aList.hasNext() ) {
								miniActions = aList.next();
							}
							state = stateCompare;
							break;

						case stateAnswer:
							//sBuf.append(polynom.get(index) + " ");
                            System.out.print(polynom.get(index) + " ");
							state = stateExit;
							break;
					}
				}
			}
			String[] answer = { "" };
			//answer[0] = sBuf.toString();

			return answer;
		}
	}

	public static enum ActionType {ITEM, POLYNOM};

	public static class Action {
		public ActionType at;
		public int value;
		public int li;
		public int ci;
		public int position = 0;

		public Action(ActionType at, int value) {
			this.at = at;
			this.value = value;
		}

		public Action(ActionType at, int li, int ci) {
			this.at = at;
			this.li = li;
			this.ci = ci;
		}
	}

	public static class Actions {
		public long begin;
		public long end;
		private ArrayList<Action> actions = new ArrayList<Action>();

		public Actions(long begin) {
			this.begin = begin;
			this.end = 0;
		}

		public void add(Action action) {
			actions.add(action);
		}

		public ListIterator<Action> listIterator() {
			return actions.listIterator();
		}

		public int size() {
			return actions.size();
		}
	}

	public static class ActionsList implements Iterator {
		final private int MAX = 200000;
		private ArrayList<Action> actions = new ArrayList<Action>();
		private int index = 0;
		private long end = 0;

		public ActionsList(String[] sActions) {
			for ( String sAction: sActions ) {
				String[] sParams = sAction.split(" ");

				if ( Integer.parseInt(sParams[0]) == 1 ) {
					actions.add(new Action(ActionType.ITEM, Integer.parseInt(sParams[1])));
				} else if ( Integer.parseInt(sParams[0]) == 2 ) {
					actions.add(new Action(ActionType.POLYNOM, Integer.parseInt(sParams[1]), Integer.parseInt(sParams[2])));
				}
			}
		}

		public boolean hasNext() {
			int counter = 0;
			for ( int i = index; i < actions.size(); i++ ) {
				Action action = actions.get(i);

				if ( action.at == ActionType.ITEM ) {
					counter += 1;
				} else if ( action.at == ActionType.POLYNOM ) {
					for ( int repeat = action.position; repeat < action.ci; repeat++ ) {
						counter += action.li;
						break;		
					}
				}

				if ( counter > 0 ) {
					return true;
				}
			}
			return false;
		}

		public Actions next() {
			int length = 0;

			Actions miniActions = new Actions(end + 1);
			for (int i = index; i < actions.size(); i++ ) {
				Action action = actions.get(i);

				if ( action.at == ActionType.ITEM ) {

					miniActions.add(new Action(ActionType.ITEM, action.value));
					length += 1;

					index += 1;

					if ( length >= MAX ) {
						break;
					}
				} else if ( action.at == ActionType.POLYNOM ) {

					int ci = 0;
					for ( int repeat = action.position; repeat < action.ci; repeat++ ) {
						length += action.li;
						action.position += 1;
						ci += 1;

						if ( length >= MAX ) {
							break;
						}
					}
					miniActions.add(new Action(ActionType.POLYNOM, action.li, ci));

					if ( !(ci < action.ci) ) {
						index += 1;
					}
				}

				if ( length >= MAX ) {
					break;
				}
			}

			end += (long) length;
			miniActions.end = end;

 			return miniActions;
		}

		public void remove() {
			// Нет необходимости
		}		
	}

	public static class Polynom {
		public long begin;
		public long end;
		private int[] array;
		private int length;

		public Polynom() {
			this.begin = 0;
			this.end = 0;
			this.array = new int[222222];
			this.length = 0;
		}

		public int get(long index) {
			return array[(int) (index - begin)];
		}

		public int[] getArray() {
			return array;
		}

		public void setLength(int length) {
			this.length = length;
		}
	}

	public static class PolynomList {
		private Polynom pA;
		private Polynom pTemp;

		public PolynomList(Actions miniActions) {
			pA = new Polynom();
			pA.begin = miniActions.begin;
			pA.end = miniActions.end;

			int[] array = pA.getArray();
			int position = 0;

			ListIterator<Action> iter = miniActions.listIterator();
			while ( iter.hasNext() ) {
				Action a = iter.next();

				if ( a.at == ActionType.ITEM ) {

					array[position] = a.value;
					position += 1;
				} else if ( a.at == ActionType.POLYNOM ) {

					for ( int repeat = 0; repeat < a.ci; repeat++ ) {
						for ( int i = 0; i < a.li; i++ ) {

							array[position] = array[i];
							position += 1;
						}
					}
				}
			}
			pA.setLength(position);

			pTemp = new Polynom();
		}

		public Polynom getPolynom(Actions miniActions) {
			int[] polynomA = pA.getArray();

			pTemp.begin = miniActions.begin;
			pTemp.end = miniActions.end;

			int[] array = pTemp.getArray();
			int position = 0;

			ListIterator<Action> iter = miniActions.listIterator();
			while ( iter.hasNext() ) {
				Action a = iter.next();

				if ( a.at == ActionType.ITEM ) {

					array[position] = a.value;
					position += 1;
				} else if ( a.at == ActionType.POLYNOM ) {

					for ( int repeat = 0; repeat < a.ci; repeat++ ) {
						for ( int i = 0; i < a.li; i++ ) {

							array[position] = polynomA[i];
							position += 1;
						}
					}
				}
			}
			pTemp.setLength(position);

			return pTemp;	
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        int n = Integer.parseInt(line);
        String[] inputData = new String[n + 3];
        inputData[0] = line;

        for ( int i = 0; i < n + 2; i++ ) {
            inputData[i + 1] = reader.readLine();
        }
		reader.close();

        Algorithm algorithm = new Algorithm();
        String[] answer = algorithm.run(inputData);

        //System.out.println(answer[0]);
	}
}

//9223372036854775808