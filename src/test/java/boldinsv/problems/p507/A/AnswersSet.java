package boldinsv.problems.p507.A;

public class AnswersSet {
    private String[] input;

    public int n;
    public int k;
    public int[] musicalInstruments;
    public int[][] orderMusicalInstruments;
    public int[][] sortOrderMusicalInstruments;
    public int quantity;
    public int[] order;

    private AnswersSet(String[] input) {
        this.input = input;
    }

    private static AnswersSet set001() {
        AnswersSet answer = new AnswersSet(new String[] {"4 10", "4 3 1 2"});

        answer.n = 4;
        answer.k = 10;
        answer.musicalInstruments = new int[] {4, 3, 1, 2};

        answer.orderMusicalInstruments = new int[][] { {4, 3, 1, 2}, {1, 2, 3, 4} };
        answer.sortOrderMusicalInstruments = new int[][] { {1, 2, 3, 4}, {3, 4, 2, 1} };

        answer.quantity = 4;
        answer.order = new int[] {3, 4, 2, 1};

        return answer;
    }

    private static AnswersSet set002() {
        AnswersSet answer = new AnswersSet(new String[] {"5 6", "4 3 1 1 2"});

        answer.n = 5;
        answer.k = 6;
        answer.musicalInstruments = new int[] {4, 3, 1, 1, 2};

        answer.orderMusicalInstruments = new int[][] { {4, 3, 1, 1, 2}, {1, 2, 3, 4, 5} };
        answer.sortOrderMusicalInstruments = new int[][] { {1, 1, 2, 3, 4}, {3, 4, 5, 2, 1} };

        answer.quantity = 3;
        answer.order = new int[] {3, 4, 5};

        return answer;
    }

    private static AnswersSet set003() {
        AnswersSet answer = new AnswersSet(new String[] {"1 3", "4"});

        answer.n = 1;
        answer.k = 3;
        answer.musicalInstruments = new int[] {4};

        answer.orderMusicalInstruments = new int[][] { {4}, {1} };
        answer.sortOrderMusicalInstruments = new int[][] { {4}, {1} };

        answer.quantity = 0;
        answer.order = null;

        return answer;
    }

    public static AnswersSet[] create() {
        AnswersSet[] answers = new AnswersSet[3];

        answers[0] = set001();
        answers[1] = set002();
        answers[2] = set003();

        return answers;
    }

    public String[] getInput() {
        return input;
    }
}
