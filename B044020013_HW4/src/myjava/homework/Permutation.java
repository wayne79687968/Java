package myjava.homework;

public class Permutation extends fourStarGame {
    public Permutation() {
    }
    public void checkOfWin() {
        int flag = 0;
        if (this.getUserNums().isEmpty() == false) {
            for (int i = 0; i < 4; i++) {
                if (this.getWinNums().get(i) != this.getUserNums().get(i))
                    flag = 1;
            }
            if (flag == 0)
                System.out.println("**You win!");
            else
                System.out.println("**You lose!");
        }
    }
}
