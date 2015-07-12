package boldinsv.problems.p500.A;

public class AnswersSet {
    private String[] input;

    public int n;
    public int t;
    public int[] portals;
    public String result;

    private AnswersSet(String[] input) {
        this.input = input;
    }

    private static AnswersSet set001() {
        AnswersSet answer = new AnswersSet(new String[] {"8 4", "1 2 1 2 1 2 1"});

        answer.n = 8;
        answer.t = 4;
        answer.portals = new int[] {1, 2, 1, 2, 1, 2, 1};

        answer.result = "YES";

        return answer;
    }

    private static AnswersSet set002() {
        AnswersSet answer = new AnswersSet(new String[] {"8 5", "1 2 1 2 1 1 1"});

        answer.n = 8;
        answer.t = 5;
        answer.portals = new int[] {1, 2, 1, 2, 1, 1, 1};

        answer.result = "NO";

        return answer;
    }

    public static AnswersSet[] create() {
        AnswersSet[] answers = new AnswersSet[2];

        answers[0] = set001();
        answers[1] = set002();

        return answers;
    }

    public String[] getInput() {
        return input;
    }
}
