package myjava.homework;
import java.util.*;

public class Pikachu extends pokemon implements skill{
    Random ran = new Random();
    public Pikachu(){
        setAtk(40);
        setHp(80);
        setUnique(20);
    }
    public int attack_skill(){
        return ran.nextInt(11) + getAtk();
    }
    public int defense_skill(){
        int uni = getUnique();
        if (getUnique() < 100){
            if (getUnique() * 2 <= 100)
                setUnique(getUnique() * 2);
            else
                setUnique(100);
        }
        else
            setUnique(100);
        return uni;
    }
    public int buff_skill(){
        int buff = getAtk();
        setAtk(getAtk() * 2);
        return buff;
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
            System.out.println("    EVA:" + getUnique());
            System.out.println("---------------      --------------------");
            System.out.println("(1)Thunder Shock    (2)Double Team  (3)Thunder  (4)Catch");
            System.out.print("Action: (By default: (1))");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    int poke_atk = attack_skill();
                    System.out.println("[Thunder Shock]: " + poke_atk + " damage.");
                    wild_HP = wild_HP - poke_atk;
                    break;
                case "2":
                    int atk = ran.nextInt(11) + wild_ATK;
                    System.out.println("[Double Team]: EVA + " + defense_skill() + " points");
                    System.out.println("[Wild Pokemon]: " + atk + " damage.");
                    break;
                case "3":
                    System.out.println("[Thunder]: ATK + " + buff_skill() + " damage.");
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
                    poke_atk = attack_skill();
                    System.out.println("[Thunder Shock]: " + poke_atk + " damage.");
                    wild_HP = wild_HP - poke_atk;
                    break;
            }
            if (wild_HP > 0 || ca == false) {
                int atk = ran.nextInt(11) + wild_ATK;
                System.out.println("[Wild Pokemon]: " + atk + " damage.");
                int chance = ran.nextInt(100) + 1;
                if (chance <= getUnique())
                    System.out.println("Evasion Succeed");
                else {
                    System.out.println("[Pikachu]: HP - " + atk + " points.");
                    setHp(getHp() - atk);
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
