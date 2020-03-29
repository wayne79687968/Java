package myjava.homework;

public class Combination extends fourStarGame{
    public Combination(){}
    public void checkOfWin() {
        int flag = 0;
        if (this.getUserNums().isEmpty() == false) {
            Integer[] UserNums = getUserNums().toArray(new Integer[4]);
            Integer[] WinNums = getWinNums().toArray(new Integer[4]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++){
                    if (UserNums[i] == WinNums[j] && UserNums[i] != 0){
                        WinNums[j] = 0;
                        break;
                    }
                    else if (UserNums[i] != WinNums[j] && j == 3)
                        flag = 1;
                }
            }
            if (flag == 0)
                System.out.println("**You win!");
            else
                System.out.println("**You lose!");
        }
    }
}
