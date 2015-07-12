package boldinsv.problems.p493.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C493A {
    enum Card {YELLOW, RED};

    public static class Init {
        public SoccerTeam houses;
        public SoccerTeam guest;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items;

            houses = new SoccerTeam(reader.readLine());
            guest = new SoccerTeam(reader.readLine());

            int n = Integer.parseInt(reader.readLine());

            int minute;
            char type;
            int playerNumber;
            Card card;

            for (int index = 0; index < n; index++) {
                items = reader.readLine().split(" ");

                minute = Integer.parseInt(items[0]);
                type = (items[1].charAt(0) == 'a') ? 'a' : 'h';
                playerNumber = Integer.parseInt(items[2]);
                card = (items[3].charAt(0) == 'y') ? Card.YELLOW : Card.RED;

                switch (type) {
                    case 'a':
                        guest.addFoul(playerNumber, new Foul(minute, card));
                        break;

                    case 'h':
                        houses.addFoul(playerNumber, new Foul(minute, card));
                        break;
                }

            }
            reader.close();
        }
    }

    public static class SoccerTeam {
        public String name;
        public Map<Integer, FootballPlayer> players = new LinkedHashMap<Integer, FootballPlayer>();

        public SoccerTeam(String name) {
            this.name = name;
        }

        public void addFoul(int numberPlayer, Foul foul) {
            if (!players.containsKey(numberPlayer)) {
                players.put(numberPlayer, new FootballPlayer(numberPlayer));
            }
            players.get(numberPlayer).addFoul(foul);
        }

        public Map<Integer, String> playersRedCard() {
            TreeMap<Integer, String> answer = new TreeMap<Integer, String>();

            Set<Integer> keys = players.keySet();
            for (Integer key: keys) {

                FootballPlayer player = players.get(key);

                int redCard = 0;
                int yellowCard = 0;

                for(Foul foul: player.fouls) {
                   if (foul.card == Card.RED) {
                       redCard += 1;
                   } else if (foul.card == Card.YELLOW) {
                       yellowCard += 1;
                   }

                   if (redCard >= 1 || yellowCard >= 2) {
                       answer.put(foul.minute, name + " " + player.number + " " + foul.minute);
                       break;
                   }
                }

            }
            return answer;
        }

        @Override
        public boolean equals(Object obj) {
            SoccerTeam team = (SoccerTeam) obj;
            boolean result = false;

            if (name.equals(team.name) && players.size() == team.players.size()) {

                Set<Integer> keys = players.keySet();
                for (Integer key: keys) {

                    result = false;
                    if (players.containsKey(key) && team.players.containsKey(key)) {
                        if (players.get(key).equals(team.players.get(key))) {
                            result = true;
                        }
                    }

                    if (result == false) {
                        break;
                    }
                }
            }
            return result;
        }
    }

    public static class FootballPlayer {
        public int number;
        public List<Foul> fouls = new LinkedList<Foul>();

        public FootballPlayer(int number) {
            this.number = number;
        }

        public void addFoul(Foul foul) {
            fouls.add(foul);
        }

        @Override
        public boolean equals(Object obj) {
            FootballPlayer player = (FootballPlayer) obj;
            boolean result = false;

            if (number == player.number && fouls.size() == player.fouls.size()) {
                result = true;

                Iterator<Foul> iterH = fouls.iterator();
                Iterator<Foul> iterA = player.fouls.iterator();

                while (iterH.hasNext() && iterA.hasNext()) {
                    if (!iterH.next().equals(iterA.next())) {
                        result = false;
                        break;
                    }
                }
            }
            return result;
        }
    }

    public static class Foul {
        public int minute;
        public Card card;

        public Foul(int minute, Card card) {
            this.minute = minute;
            this.card = card;
        }

        @Override
        public boolean equals(Object obj) {
            Foul foul = (Foul) obj;

            if (minute == foul.minute && card == foul.card) {
                return true;
            }
            return false;
        }
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Printable {
        private SoccerTeam houses;
        private SoccerTeam guest;
        private Map<Integer, String> answer = new TreeMap<Integer, String>();

        public Algorithm(SoccerTeam houses, SoccerTeam guest) {
            this.houses = houses;
            this.guest = guest;
        }

        public void run() {
            answer.clear();

            answer.putAll(houses.playersRedCard());
            answer.putAll(guest.playersRedCard());
        }

        private class A implements Answer {
            public void print() {
                Set<Integer> keys = answer.keySet();
                for (Integer key: keys) {
                    System.out.println(answer.get(key));
                }
            }
        }

        public Answer answer() {
            return new A();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.houses, init.guest);
        algorithm.run();
        algorithm.answer().print();
    }
}

/*
Чужое понравившиеся решение

public class Ideone
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        String home = sc.next();
        String away = sc.next();
        int foul[] = new int[1000];
        for(int i = 0;i<1000;i++){
            foul[i]=0;
        }
        int n = sc.nextInt();
        for(int i = 0;i<n;i++){
            int t = sc.nextInt();
            int index = 0;
            if(sc.next().equals("a")){
                index+=100;
            }
            index+=sc.nextInt();
            boolean ok = false;
            if(foul[index]<2){
                ok = true;
            }
            if(sc.next().equals("y")){
                foul[index]++;
            }
            else{
                foul[index]+=2;
            }
            if(foul[index]>=2&&ok){
                if(index>=100){
                    System.out.print(away);
                    index-=100;
                }
                else{
                    System.out.print(home);
                }
                System.out.print(" ");
                System.out.print(index + " ");
                System.out.println(t);
            }
        }
    }
}
 */