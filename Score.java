public class Score{
    public int number;
    public String user;
    public double time;

    public Score(int number, String user, double time){
        this.number = number;
        this.user = user;
        this.time = time;
    }

    public int getNumber(){
        return number;
    }

    public String getUser(){
        return user;
    }

    public double getTime(){
        return time;
    }
}