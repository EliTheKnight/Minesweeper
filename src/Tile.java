public class Tile {

    private int val, appearance;

    public Tile(int val){
        this.val = val;
        appearance = 0;
    }

    public int getVal(){return val;}

    public void setVal(int val) {
        this.val = val;
    }

    public int getAppearance(){return appearance;}

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }
}
