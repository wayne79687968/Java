package myjava.homework.part1;

public class Apple {
    public Apple(int pri, double wei){
        this.price = pri;
        this.weight = wei;
    }
    public Apple(String var, int pri, double wei){
        this.variety = var;
        this.price = pri;
        this.weight = wei;
    }
    public Apple(String var){
        this.variety = var;
    }
    private String variety;
    private int price;
    private double weight;
    public int getPrice(){
        return this.price;
    }
    public void setPrice(int pri){
        this.price = pri;
    }
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(int wei){
        this.weight = wei;
    }
    public void setVariety(String var){
        this.variety = var;
    }
}
