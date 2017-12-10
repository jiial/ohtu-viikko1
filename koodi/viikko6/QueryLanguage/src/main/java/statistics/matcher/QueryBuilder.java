
package statistics.matcher;


public class QueryBuilder {
    private Matcher m;

    public QueryBuilder() {
        this.m = null;
    }
    
    public Matcher build() {
        return m;
    }
    
    public QueryBuilder and() {
        this.m = new And();
        return this;
    }
    
    public QueryBuilder hasAtLeast(int i, String s) {
        this.m = new HasAtLeast(i, s);
        return this;
    }
    
    public QueryBuilder hasFewerThan(int i, String s) {
        this.m = new HasFewerThan(i, s);
        return this;
    }
    
    public QueryBuilder not() {
        this.m = new Not();
        return this;
    }
    
    public QueryBuilder or() {
        this.m = new Or();
        return this;
    }
    
    public QueryBuilder playsIn(String s) {
        this.m = new PlaysIn(s);
        return this;
    }
    
    
}
