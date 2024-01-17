/**
 * Life stores the internal representtation of a cellular world.
 * 
 * @author Gordon Royle and Lyndon While
 * @version April 2019
 */
public class Life extends Life
{
    private boolean[][] map, nextMap;
    private int width, height;
    
    public Life(boolean[][] initial)
    {
        map = initial; // we assume that initial is non-empty and rectangular
        width  = map.length;
        height = map[0].length; 
        nextMap = new boolean[width][height];
    }
    
    public Life(int width, int height, double probability)
    {
        map = new boolean[width][height];
        this.width  = width;
        this.height = height;
        initializeMap(probability);
        nextMap = new boolean[width][height];
    }
    
    public Life()
    {
        this(128, 128, 0.1);
    }
    
    private void initializeMap(double probability)
    {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                map[i][j] = Math.random() < probability;
    }
    
    public boolean[][] getMap()
    {
        return map;
    }
    
    public void nextGeneration()
    {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
                int z = numNeighbours(i, j);
                if (z == 2) nextMap[i][j] = map[i][j];
                else        nextMap[i][j] = z == 3;
            }
        boolean[][] swap = map;
        map = nextMap; 
        nextMap = swap;
    }
    
    private int numNeighbours(int i, int j)
    {
        int n = 0;
        int ip = (i + 1)          % width;
        int im = (i - 1 + width)  % width;
        int jp = (j + 1)          % height;
        int jm = (j - 1 + height) % height;
        if (map[im][jm]) n++;
        if (map[im][j])  n++;
        if (map[im][jp]) n++;
        if (map[i] [jm]) n++;
        if (map[i] [jp]) n++;
        if (map[ip][jm]) n++;
        if (map[ip][j])  n++;
        if (map[ip][jp]) n++;
        return n;
    }
}
