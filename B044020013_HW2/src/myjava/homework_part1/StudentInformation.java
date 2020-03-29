package myjava.homework_part1;

public class StudentInformation {
    public StudentInformation() {
        this.id = "";
        this.name = "";
        this.score = 0;
    }
    private String id;
    private String name;
    private int score;
    public void setID(String i) {
        this.id = i;
    }
    public void setName(String nam) {
        this.name = nam;
    }
    public void setScore(int scor) {
        this.score = scor;
    }
    public String getID() {
        return  id;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public void setdata(String i,String nam, int scor) {
        this.id = i;
        this.name = nam;
        this.score = scor;
    }
    public void showdata() {
        System.out.println("Student id :" + this.id);
        System.out.println("Student name :" + this.name);
        if (this.score >= 60)
            System.out.println(this.name + " pass this project");
        else
            System.out.println(this.name + " fail this project");
    }
}

