package myjava.homework;
import java.util.*;

public class Charizard extends pokemon implements skill{
    Random ran = new Random();
    public Charizard(){
        setHp(200);
        setAtk(60);
        setUnique(30);
    }
    public int attack_skill(){
        int cri = ran.nextInt(100) + 1;
        if (cri > getUnique())
            return ran.nextInt(11) + getAtk();
        else
            return (ran.nextInt(11) + getAtk()) * 2;
    }
    public int defense_skill(){
        return 0;
    }
    public int buff_skill(){
        if (getUnique() < 100){
            if (getUnique() + 25 <= 100){
                setUnique(getUnique() + 25);
                return getUnique() + 25;
            }
            else {
                setUnique(100);
                return 100;
            }
        }
        else{
            setUnique(100);
            return 100;
        }
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
            System.out.println("    CRI:" + getUnique());
            System.out.println("---------------      --------------------");
            System.out.println("(1)Flamethrower    (2)Parry  (3)Work Up    (4)Catch");
            System.out.print("Action: (By default: (1))");
            String option = scanner.nextLine();
            int atk = ran.nextInt(11) + wild_ATK;
            switch (option) {
                case "1":
                    int poke_atk = attack_skill();
                    System.out.println("[Flamethrower]: " + poke_atk + " damage.");
                    wild_HP = wild_HP - poke_atk;
                    break;
                case "2":
                    System.out.println("[Parry]: return next damage");
                    wild_HP = wild_HP - atk;
                    break;
                case "3":
                    System.out.println("[Work Up]: CRI + 25%");
                    setUnique(buff_skill());
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
                    System.out.println("[Flamethrower]: " + poke_atk + " damage.");
                    wild_HP = wild_HP - poke_atk;
                    break;
            }
            if (wild_HP > 0 || ca == false) {
                System.out.println("[Wild Pokemon]: " + atk + " damage.");
                System.out.println("[Charizard]: HP - " + atk + " points.");
                setHp(getHp() - atk);
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
