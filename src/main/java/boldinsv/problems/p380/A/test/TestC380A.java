package boldinsv.problems.p380.A.test;

import boldinsv.problems.p380.A.C380A2;
//import problems.p380
import java.util.ListIterator;

public class TestC380A extends C380A2 {
	
	private static boolean test001() {
		String[] params = {
			"6",
			"1 1",
			"1 2",
			"2 2 1",
			"1 3",
			"2 5 2",
			"1 4",
			"16",
			"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16"
		};

		Algorithm algorithm = new Algorithm();
		String[] answer = algorithm.run(params);

		for ( String line: answer ) {
			if (!line.trim().equals("1 2 1 2 3 1 2 1 2 3 1 2 1 2 3 4")) {
				return false;
			}
		}
		return true;
	}

	private static boolean testingMiniActions(Actions miniActions, String[] inputData, long begin, long end) {
		ListIterator<Action> iter = miniActions.listIterator();
		int index = 0;

		while ( iter.hasNext() ) {
			Action a = iter.next();

			String sA = "";
			if ( a.at == ActionType.ITEM ) {
				sA = "1 " + a.value;
			} else if ( a.at == ActionType.POLYNOM ) {
				sA = "2 " + a.li + " " + a.ci;
			}
			//System.out.println(sA);

			if ( !inputData[index].equals(sA) ) {
				return false;
			}
			index += 1;
		}

		//if ( !(miniActions.begin == begin && miniActions.end == end) ) {
		//	return false;
		//}

		//return true;

		return miniActions.begin == begin && miniActions.end == end;
	}

	private static boolean test002() {
		String[] inputData = {
			"1 1",
			"1 2",
			"2 2 1",
			"1 3",
			"2 5 2",
			"1 4"		
		};

		ActionsList aList = new ActionsList(inputData);
		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( !testingMiniActions(miniActions, inputData, 1, 16) ) {
				return false;
			}
		}
		return true;
	}

	private static boolean test003() {
		String[] inputData = {
			"1 1"
		};

		ActionsList aList = new ActionsList(inputData);
		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( !testingMiniActions(miniActions, inputData, 1, 1) ) {
				return false;
			}
		}
		return true;
	}

	private static boolean test004() {
		String[] inputData = {
			"1 1",
			"1 2",
			"1 3",
			"1 4",
			"2 4 24",
			"2 100 9",
			"2 1000 99"
		};

		ActionsList aList = new ActionsList(inputData);
		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( !testingMiniActions(miniActions, inputData, 1, 100000) ) {
				return false;
			}
		}
		return true;
	}

	private static boolean test005() {
		String[] inputData = {
			"1 1",
			"1 2",
			"1 3",
			"1 4",
			"2 4 24",
			"2 100 9",
			"2 1000 99",
			"1 9"
		};

		String[] inputData1 = {
			"1 1",
			"1 2",
			"1 3",
			"1 4",
			"2 4 24",
			"2 100 9",
			"2 1000 99"
		};

		String[] inputData2 = {
			"1 9"
		};

		ActionsList aList = new ActionsList(inputData);
		int index = 1;
		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( index == 1 ) {
                if (!testingMiniActions(miniActions, inputData1, 1, 100000)) {
                    return false;
                }
			} else if ( index == 2 ) {
                if ( !testingMiniActions(miniActions, inputData2, 100001, 100001) ) {
                    return false;
                }
			}

			if ( index > 2 ) {
				return false;
			}
			index += 1;
		}
		return true;
	}

	private static boolean test006() {
		String[] inputData = {
			"1 1",
			"1 2",
			"1 3",
			"1 4",
			"2 4 24",
			"2 100 9",
			"2 1000 99",
			"2 10000 90"
		};

		String[] inputData1 = {
			"1 1",
			"1 2",
			"1 3",
			"1 4",
			"2 4 24",
			"2 100 9",
			"2 1000 99",
		};

		String[] inputData2 = {
			"2 10000 10"
		};

		ActionsList aList = new ActionsList(inputData);
		int index = 1;
		long end = 0;
		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( index == 1 ) {
				if ( !testingMiniActions(miniActions, inputData1, end + 1, end + 100000) ) {
					return false;
				}
				end = miniActions.end;
			} else {
				if ( !testingMiniActions(miniActions, inputData2, end + 1, end + 100000) ) {
					return false;
				}
				end = miniActions.end;
			}
			index += 1;
		}
		return true;
	}

	private static boolean test007() {
		String[] inputData = {
			"1 1",
			"1 2",
			"2 2 1",
			"1 3",
			"2 5 2",
			"1 4"
		};

		ActionsList aList = new ActionsList(inputData);
		Actions miniActions = aList.next();

		PolynomList pList = new PolynomList(miniActions);
		Polynom polynom = pList.getPolynom(miniActions);

		StringBuffer sBuff = new StringBuffer("");
		for ( long index = 1; index <= 16; index++ ) {
			sBuff.append(polynom.get(index));
			sBuff.append(" ");
		}

		if (!sBuff.toString().trim().equals("1 2 1 2 3 1 2 1 2 3 1 2 1 2 3 4")) {
			return false;
		}
		
		if ( !(polynom.begin == 1 && polynom.end == 16) ) {
			return false;
		}
		return true;
	}

	private static boolean test008() {
		String[] inputData = {
			"1 7",
			"1 7",
			"1 7",
			"1 7",
			"2 4 24",
			"2 100 9",
			"2 1000 99",
			"2 10000 90"
		};

		PolynomList pList = null;
		long index = 1;

		ActionsList aList = new ActionsList(inputData);
		boolean isStart = true;

		while ( aList.hasNext() ) {
			Actions miniActions = aList.next();

			if ( isStart ) {
				pList = new PolynomList(miniActions);
				isStart = false;
			}

			Polynom polynom = pList.getPolynom(miniActions);
			for ( long i = polynom.begin; i <= polynom.end; i++ ) {
				if ( polynom.get(index) != 7 ) {
					return false;
				}
				index += 1;
			}
		}
		System.out.println("last index = " + (index - 1));
		return true;
	}	
	
	public static void main(String[] args) {
		String message;

		message = "Test number 001 is not successfully";
		if (test001()) {
			message = "Test number 001 is successfully";
		}
		System.out.println(message);

		message = "Test number 002 is not successfully";
		if ( test002() ) {
			message = "Test number 002 is successfully";
		}
		System.out.println(message);

		message = "Test number 003 is not successfully";
		if ( test003() ) {
			message = "Test number 003 is successfully";
		}
		System.out.println(message);

		message = "Test number 004 is not successfully";
		if ( test004() ) {
			message = "Test number 004 is successfully";
		}
		System.out.println(message);

		message = "Test number 005 is not successfully";
		if ( test005() ) {
			message = "Test number 005 is successfully";
		}
		System.out.println(message);

		message = "Test number 006 is not successfully";
		if ( test006() ) {
			message = "Test number 006 is successfully";
		}
		System.out.println(message);

		message = "Test number 007 is not successfully";
		if ( test007() ) {
			message = "Test number 007 is successfully";
		}
		System.out.println(message);

		message = "Test number 008 is not successfully";
		if ( test008() ) {
			message = "Test number 008 is successfully";
		}
		System.out.println(message);
	}
}