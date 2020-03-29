package myjava.homework_part2;

public class ItemList {
    private String[] id = new String[10];
    private int count = 1;
    public ItemList() {//Constructor
        //
    }
    public void addItem(String i){
        this.id[count] = i;
        count ++;
    }
    public void Reomove(String i){
        int flag = 0;
        for (int x = 1; x < count; x++) {
            if (this.id[x].equals(i)){
                this.id[x] = "";
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.println("Data no found");
    }
    public void printList(){
        int y = 1;
        for (int x = 1; x < count; x++){
            if(this.id[x] != ""){
                System.out.println(y + " : " + this.id[x]);
                y++;
            }
        }
    }
}