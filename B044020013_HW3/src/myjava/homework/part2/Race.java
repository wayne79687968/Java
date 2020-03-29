package myjava.homework.part2;
import java.util.*;

public class Race {
    private int ra;
    public Race(int ra){
        this.ra = ra;
    }

    public String card(int suit, int num){
        String _suit,_num;
        _suit = null;
        switch (suit){
            case 1:
                _suit = "_Spade_";
                break;
            case 2:
                _suit = "_Heart_";
                break;
            case 3:
                _suit = "Dimond";
                break;
            case 4:
                _suit = "_Club_";
                break;
        }
        if(num == 14)
            _num = "ACE";
        else
            _num = num + " ";

        return _suit + _num;
    }

    public void start(){
        Random ran = new Random();
        int user_num,user_suit,com_num,com_suit;
        user_num = ran.nextInt(13)+2;
        user_suit = ran.nextInt(4)+1;
        com_num = ran.nextInt(13)+2;
        com_suit = ran.nextInt(4)+1;
        System.out.println("Your hand is " + card(user_suit,user_num));
        System.out.println("Com's hand is " + card(com_suit,com_num));
        switch (this.ra){
            case 0x111:
                if(user_num > com_num) {
                    System.out.println(card(user_suit, user_num) + " is bigger than " + card(com_suit, com_num));
                    System.out.println("So, you win!");
                }
                else if (user_num == com_num){
                    if (user_suit < com_num){
                        System.out.println(card(user_suit,user_num) + " is bigger than " + card(com_suit,com_num));
                        System.out.println("So, you win!");
                    }
                    else if (user_suit == com_suit)
                        System.out.println("Draw!!");
                    else{
                        System.out.println(card(com_suit,com_num) + " is bigger than " + card(user_suit,user_num));
                        System.out.println("So, Com win!");
                    }
                }
                else{
                    System.out.println(card(com_suit,com_num) + " is bigger than " + card(user_suit,user_num));
                    System.out.println("So, Com win!");
                }
                break;
            case 0x222:
                if(user_num < com_num) {
                    System.out.println(card(user_suit, user_num) + " is smaller than " + card(com_suit, com_num));
                    System.out.println("So, you win!");
                }
                else if (user_num == com_num){
                    if (user_suit > com_num){
                        System.out.println(card(user_suit,user_num) + " is smaller than " + card(com_suit,com_num));
                        System.out.println("So, you win!");
                    }
                    else if (user_suit == com_suit)
                        System.out.println("Draw!!");
                    else{
                        System.out.println(card(com_suit,com_num) + " is smaller than " + card(user_suit,user_num));
                        System.out.println("So, Com win!");
                    }
                }
                else{
                    System.out.println(card(com_suit,com_num) + " is smaller than " + card(user_suit,user_num));
                    System.out.println("So, Com win!");
                }
                break;
        }
    }
}
