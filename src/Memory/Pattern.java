package Memory;

public class Pattern {

private final boolean[][] pattern;

private final int size;

private final int time;

public Pattern(final boolean[][] pPattern, final int pSize, final int pTime){
    pattern = pPattern;
    size = pSize;
    time = pTime;
}


    public boolean[][] getPattern() {
        return pattern;
    }

    public int getSize() {
        return size;
    }

    public int getTime() {
        return time;
    }
}
