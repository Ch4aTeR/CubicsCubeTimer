public class Solve{
    public Integer number;
    public String username;
    public Double time;

    public Solve(Integer number, String username, Double time){
        this.number = number;
        this.username = username;
        this.time = time;
    }
    public Integer getNumber(){
        return number;
    }
    public String getUsername() {
        return username;
    }
    public Double getTime() {
        return time;
    }
}