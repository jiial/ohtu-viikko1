
package ohtu;
      

public class Course {
    
    private String name;
    private String id;
    private String term;
    private String url;
    private int week;
    private boolean enabled;
    private int _v;
    private int[] exercises;

    public int[] getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public int getWeek() {
        return week;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
