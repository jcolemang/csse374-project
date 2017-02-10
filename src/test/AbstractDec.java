package test;

/**
 * Created by coleman on 2/9/17.
 */
public abstract class AbstractDec implements Inter {
    public ConcreteDec thing;
    
    public int a() {
        ConcreteDec aeou = new ConcreteDec();
        int a = this.thing.m1();
        return a;
    }

    public void b() {
        this.thing.a();
    }

    public void c() {
        int a = this.thing.a();
    }

}
