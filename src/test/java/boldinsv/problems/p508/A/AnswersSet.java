package boldinsv.problems.p508.A;

public class AnswersSet {
    private String[] input;

    public int n;
    public int m;
    public int k;
    public int[][] steps;
    public int result;

    private AnswersSet(String[] input) {
        this.input = input;
    }

    private static AnswersSet set001() {
        AnswersSet answer = new AnswersSet(new String[] {"2 2 4", "1 1", "1 2", "2 1", "2 2"});

        answer.n = 2;
        answer.m = 2;
        answer.k = 4;
        answer.steps = new int[answer.k][2];

        answer.steps[0][0] = 1;
        answer.steps[0][1] = 1;

        answer.steps[1][0] = 1;
        answer.steps[1][1] = 2;

        answer.steps[2][0] = 2;
        answer.steps[2][1] = 1;

        answer.steps[3][0] = 2;
        answer.steps[3][1] = 2;

        answer.result = 4;

        return answer;
    }

    public static AnswersSet[] create() {
        AnswersSet[] answers = new AnswersSet[1];

        answers[0] = set001();

        return answers;
    }

    public String[] getInput() {
        return input;
    }
}
