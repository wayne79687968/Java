package myjava.homework.part1;

public class ARGSomething {

    public int avg(int... num){
        int sum = 0;
        for(int i : num)
            sum = sum + i;
        return sum / num.length;
    }
    public double avg(double... num){
        double sum = 0;
        for(double i : num)
            sum = sum + i;
        return sum / num.length;
    }

    public static String avg(String... word){
        double sum = 0;
        for(String i : word)
            sum = sum + i.length();
        if(word.length == 1){
            System.out.print("The number of this word is ");
            return Integer.toString((int)sum);
        }
        else{
            System.out.print("The avg number of these word is ");
            return String.valueOf(sum / word.length);
        }
    }
    public static double avg(Apple... item){
        double Wsum = 0;
        double Psum = 0;
        for(Apple i : item){
            Wsum = Wsum + i.getWeight();
            Psum = Psum + i.getPrice();
        }
        System.out.print("The price per gram is ");
        return Wsum / Psum;
    }

    public static void main(String[] args) {
        // You cannot modify anything in the main function!!!!
        ARGSomething t=new ARGSomething();
        System.out.println(t.avg(10,20));
        System.out.println(t.avg(20,60,120));
        System.out.println(t.avg(10,20,30,40));

        System.out.println(t.avg(0.1,0.2));
        System.out.println(t.avg(20,60.0,120));
        System.out.println(t.avg(10,20,new Integer(30),40.0));

        System.out.println(avg("Apple"));
        System.out.println(avg("Apple","Cat"));
        System.out.println(avg("Apple","Cat",new String("Bee")));

        System.out.println(avg(new Apple(10,59.5)));
        System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3)));
        Apple green=new Apple("Granny Smith",30,88);
        System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3),green));
    }

}
