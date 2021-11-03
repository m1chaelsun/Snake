public abstract class Item extends GameObj {
    public static final int VELOCITY = 0;
    public static final int SIZE = 30;

    public Item(
            int px, int py, int courtWidth,
            int courtHeight
    ) {
        super(VELOCITY, VELOCITY, px, py, SIZE, SIZE, courtWidth, courtHeight);
    }

    public abstract void addSpecific(int px, int py);
}
