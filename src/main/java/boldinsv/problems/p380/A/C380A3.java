package boldinsv.problems.p380.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class C380A3 {

    public static class ItemSequence {
        private enum SequenceType {VALUE, SEQUENCE};
        private SequenceType type;

        private long li;
        private long ci;
        private long value;

        public ItemSequence(long value) {
            type = SequenceType.VALUE;
            this.value = value;

            li = 0;
            ci = 1;
        }

        public ItemSequence(long li, long ci) {
            type = SequenceType.SEQUENCE;
            this.li = li;
            this.ci = ci;

            value = 0;
        }

        public boolean isValue() {
            return type == SequenceType.VALUE;
        }

        public boolean isSequence() {
            return type == SequenceType.SEQUENCE;
        }

        public boolean hasValue() {
            if (isValue()) {
               return ci > 0;
            }
            throw new IllegalStateException();
        }

        public long value() {
            if (isValue()) {
                if (ci > 0) {
                    ci -= 1;

                    return value;
                }
            }
            throw new IllegalStateException();
        }

        public boolean hasDecrementCi() {
            if (isSequence()) {
                return ci > 0;
            }
            throw new IllegalStateException();
        }

        public long decrementCi() {
            if (isSequence()) {
                if (ci > 0) {
                    ci -= 1;

                    return li;
                }
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean equals(Object obj) {
            ItemSequence item = (ItemSequence) obj;

            if (item != null) {
                if (type == item.type && li == item.li && ci == item.ci && value == item.value) {
                    return true;
                }
            }
            return false;
        }
    }

    interface Data {
        void init();
    }

    interface Readable {
        Data data();
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Readable, Printable {
        // Входные данные
        public long m;
        public ArrayList<ItemSequence> tables = new ArrayList<ItemSequence>();
        public long n;
        public LinkedList<Long> indexes = new LinkedList<Long>();

        // Алгоритм
        public ArrayList<Long> sequence = new ArrayList<Long>();
        public Iterator<ItemSequence> iterTables = null;
        public ItemSequence item;
        public long maxIndex = 0;
        public Iterator<Long> iterIndexes;
        public long currentIndex;
        public long begin;
        public long end;
        private enum Actions {INIT, CREATE_SEQ, GET_INDEX, SEARCH, PART2, NEXT_INTERVAL, VALUE, EXIT};

        // Выходные данные
        public LinkedList<Long> answer = new LinkedList<Long>();

        public long MAX = 100000;

        public Algorithm() {}

        public Algorithm(long MAX) {
            this.MAX = MAX;
        }

        private boolean next() {
            if (iterTables == null) {
                iterTables = tables.iterator();
            }

            if (iterTables.hasNext()) {
                item = iterTables.next();
            } else {
                return false;
            }
            return true;
        }

        public void createSeq() {
            while (next()) {

                if (item.isValue()) {

                    if (item.hasValue()) {
                        sequence.add(item.value());

                        maxIndex += 1;
                    }
                } else {

                    while (item.hasDecrementCi()) {
                        int li = (int) item.decrementCi();
                        maxIndex += li;

                        for (int index = 0; index < li; index++) {
                            sequence.add(sequence.get(index));
                        }

                        if (maxIndex > MAX) {
                            begin = 0;
                            end = sequence.size() - 1;
                            return;
                        }
                    }
                }
            }

            begin = 0;
            end = sequence.size() - 1;
        }

        public void run() {
            sequence.clear();
            answer.clear();

            Actions action = Actions.INIT;
            for (boolean isContinue = true; isContinue; ) {

                switch (action) {
                    /*
                     * Поиск в сформировавшейся последовательности
                     */
                    case INIT:
                        action = Actions.CREATE_SEQ;
                        iterIndexes = indexes.iterator();
                        break;

                    case CREATE_SEQ:
                        action = Actions.GET_INDEX;
                        createSeq();
                        break;

                    case GET_INDEX:
                        action = Actions.EXIT;

                        if (iterIndexes.hasNext()) {
                            currentIndex = iterIndexes.next();
                            action = Actions.SEARCH;
                        }
                        break;

                    case SEARCH:
                        action = Actions.PART2;
                        if (currentIndex >= begin && currentIndex <= end) {
                            answer.add(sequence.get((int) (currentIndex - begin)));
                            action = Actions.GET_INDEX;
                        }
                        break;

                    case PART2:
                        action = Actions.NEXT_INTERVAL;
                        break;

                    case NEXT_INTERVAL:
                        action = Actions.EXIT;

                        if (item.isValue() && item.hasValue()) {
                            begin = end + 1;
                            end = begin;

                            action = Actions.VALUE;

                        } else if (item.isSequence() && item.hasDecrementCi()) {
                            begin = end + 1;
                            end = end + item.decrementCi();

                            action = Actions.SEARCH;

                        }else {
                            if (next()) {
                                action = Actions.NEXT_INTERVAL;
                            }
                        }
                        break;

                    case VALUE:
                        action = Actions.NEXT_INTERVAL;

                        if (currentIndex == begin) {
                            if (item.hasValue()) {
                                answer.add(item.value());

                                action = Actions.GET_INDEX;
                            }
                        }
                        break;

                    case EXIT:
                        isContinue = false;
                        break;
                }
            }
        }

        private class InputData implements Data {

            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;

                try {
                    m = Long.parseLong(reader.readLine());

                    for (long index = 0; index < m; index++) {
                        items = reader.readLine().split(" ");

                        switch (Integer.parseInt(items[0])) {
                            case 1:
                                tables.add(new ItemSequence(Long.parseLong(items[1])));
                                break;

                            case 2:
                                tables.add(new ItemSequence(Long.parseLong(items[1]), Long.parseLong(items[2])));
                                break;
                        }
                    }

                    n = Long.parseLong(reader.readLine());

                    items = reader.readLine().split(" ");
                    for (int index = 0; index < items.length; index++) {
                        indexes.add(Long.parseLong(items[index]) - 1);
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        private class OutputData implements Answer {

            public void print() {
                StringBuilder builder = new StringBuilder("");

                for (Long item: answer) {
                    builder.append(item);
                    builder.append(" ");
                }
                System.out.println(builder.toString().trim());
            }
        }

        public Data data() {
            return new InputData();
        }

        public Answer answer() {
            return new OutputData();
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();
    }
}
