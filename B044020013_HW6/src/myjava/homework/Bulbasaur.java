package myjava.homework;
import java.util.*;

public class Bulbasaur extends pokemon implements skill{
    Random ran = new Random();
    public Bulbasaur(){
        setHp(40);
        setAtk(20);
        setUnique(0);
    }
    public int attack_skill(){
        return ran.nextInt(11) + getAtk() * 4;
    }
    public int defense_skill(){
        return ran.nextInt(11) + getAtk() * 4;
    }
    public int buff_skill(){
        return ran.nextInt(11) + getAtk() * 2;
    }

    public int action() {
        boolean flag = true;
        boolean ca = false;
        Random ran = new Random();
        Scanner scanner = new Scanner(System.in);
        int wild_ATK = ran.nextInt(6) + 30;
        int wild_HP = ran.nextInt(151) + 150;
        int hp = wild_HP;
        System.out.println("[Wild pokemon appeared!]");
        while (flag == true) {
            System.out.println("----Pokemon----      ----Wild Pokemon----");
            System.out.println("    HP:" + getHp() + "                HP:" + wild_HP);
            System.out.println("    ATK:" + getAtk() + "               ATK:" + wild_ATK);
            System.out.println("    LS:" + getUnique());
            System.out.println("---------------      --------------------");
            System.out.println("(1)Razor Leaf    (2)Light Screen  (3)Synthesis  (4)Catch");
            System.out.print("Action: (By default: (1))");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    int poke_atk = attack_skill();
                    System.out.println("[Razor Leaaf]: " + poke_atk + " damage.");
                    wild_HP = wild_HP - poke_atk;
                    break;
                case "2":
                    int def = defense_skill();
                    System.out.println("[Light Screen]: Shield + " + def + " points");
                    setUnique(def);
                    break;
                case "3":
                    int buf = buff_skill();
                    System.out.println("[Synthesis]: HP + " + buf + " point.");
                    if (getHp() < 40){
                        if (getHp() + buf <= 40)
                            setHp(getHp() + buf);
                        else
                            setHp(40);
                    }
                    else
                        setHp(40);
                    break;
                case "4":
                    System.out.println("[catch]: Throw the Poké Ball");
                    int chance = ran.nextInt(100) + 1;
                    double h = (double)(hp - wild_HP) * 100 / hp;
                    if (chance <= h) {
                        System.out.println("---------------      --------------------");
                        System.out.println("you caught the wild pokemon");
                        ca = true;
                    } else
                        System.out.println("you did not catch the wild pokemon");
                    break;
                default:

                    break;
            }
            if (wild_HP > 0 || ca == false) {
                int atk = ran.nextInt(11) + wild_ATK;
                System.out.println("[Wild Pokemon]: " + atk + " damage.");
                if (getUnique() - atk < 0){
                    System.out.println("[Light Shield]: Shield - " + getUnique() + " damage.");
                    System.out.println("[Bulbasaur]: HP - " + (atk - getUnique()) + " points.");;
                    setHp(getHp() - (atk - getUnique()));
                }
                else {
                    System.out.println("[Light Shield]: Shield - " + atk + " damage.");
                    setUnique(getUnique() - atk);
                }
            }

            if (getHp() <= 0) {
                System.out.println("----Pokemon----      ----Wild Pokemon----");
                System.out.println("    HP:0                HP:" + wild_HP);
                System.out.println("    ATK:" + getAtk() + "               ATK:" + wild_ATK);
                System.out.println("    EVA:" + getUnique());
                System.out.println("---------------      --------------------");
                System.out.println("You dead.");
                flag = false;
            } else if (wild_HP <= 0) {
                System.out.println("----Pokemon----      ----Wild Pokemon----");
                System.out.println("    HP:" + getHp() + "                HP:0" );
                System.out.println("    ATK:" + getAtk() + "               ATK:" + wild_ATK);
                System.out.println("    EVA:" + getUnique());
                System.out.println("---------------      --------------------");
                System.out.println("You win...");
                flag = false;
            } else if (ca == true)
                flag = false;

        }
        return 0;
    }
}
