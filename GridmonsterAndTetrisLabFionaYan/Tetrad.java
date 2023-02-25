import java.awt.Color;
/**
 * The tetrad class creates and manipulates tetrads by adding, removing, and 
 * translate them on a grid. A tetrad is made up of four blocks arranged to
 * form various shapes.
 *
 * @author Fiona Yan
 * @version 1/22/2022
 */
public class Tetrad
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> tetradGrid;
    private Block[] blocks = new Block[4];
    private int tetradCode;
    /**
     * Constructor for objects of class Tetrad
     * 
     * @param gridOfTetrads the grid the Tetrad is on.
     */
    public Tetrad(MyBoundedGrid<Block> gridOfTetrads)
    {
        tetradGrid = gridOfTetrads;
        tetradCode = (int) (Math.random()*7+1);


        if(tetradCode == 1)//I Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.RED);
            }
            Location[] iLocs = new Location[4];
            iLocs[0] = new Location(1,4);
            iLocs[1] = new Location(0,4);
            iLocs[2] = new Location(2,4);
            iLocs[3] = new Location(3,4);
            addToLocations(tetradGrid,iLocs);
        }
        else if (tetradCode == 2)//T Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.GRAY);
            }
            Location[] tLocs = new Location[4];
            tLocs[0] = new Location(0,4); 
            tLocs[1] = new Location(0,3);
            tLocs[2] = new Location(0,5);
            tLocs[3] = new Location(1,4);
            addToLocations(tetradGrid,tLocs);
        }
        else if (tetradCode == 3)//O Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.CYAN);
            }
            Location[] oLocs = new Location[4];
            oLocs[0] = new Location(0,4);
            oLocs[1] = new Location(0,5);
            oLocs[2] = new Location(1,4);
            oLocs[3] = new Location(1,5);
            addToLocations(tetradGrid,oLocs);
        }
        else if (tetradCode == 4)//L Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.YELLOW);
            }
            Location[] lLocs = new Location[4];
            lLocs[0] = new Location(1,4);
            lLocs[1] = new Location(0,4);
            lLocs[2] = new Location(2,4);
            lLocs[3] = new Location(2,5);
            addToLocations(tetradGrid,lLocs);
        }
        else if (tetradCode == 5)//J Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.MAGENTA);
            }
            Location[] jLocs = new Location[4];
            jLocs[0] = new Location(1,4);
            jLocs[1] = new Location(0,4);
            jLocs[2] = new Location(2,4);
            jLocs[3] = new Location(2,3);
            addToLocations(tetradGrid,jLocs);
        }
        else if (tetradCode == 6)//S Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.BLUE);
            }
            Location[] sLocs = new Location[4];
            sLocs[0] = new Location(0,4);
            sLocs[1] = new Location(0,5);
            sLocs[2] = new Location(1,3);
            sLocs[3] = new Location(1,4);
            addToLocations(tetradGrid,sLocs);
        }
        else if (tetradCode == 7)//Z Tetrad
        {
            for (int i=0; i<4; i++)
            {
                blocks[i]= new Block();
                blocks[i].setColor(Color.GREEN);
            }
            Location[] zLocs = new Location[4];
            zLocs[0] = new Location(0,5);
            zLocs[1] = new Location(0,4);
            zLocs[2] = new Location(1,5);
            zLocs[3] = new Location(1,6);
            addToLocations(tetradGrid,zLocs);
        }
    }

    /**
     * Adds a tetrad to the given location
     *
     * @param  grid  the grid to which the tetrad is added.
     * @param  locs  the array of Locations of the locations of each of 
     *               blocks that make up the tetrad.
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i=0; i<locs.length; i++)
        {
            Block current = blocks[i];
            current.putSelfInGrid(tetradGrid,locs[i]);
        }
    }

    /**
     * removes the tetrad by removing all of the blocks that it's made up of.
     * 
     * @return  the array of the locations of all the blocks that were removed.
     */
    private Location[] removeBlocks()
    {
        Location[] removedBlocks = new Location[4];
        for (int i=0; i<blocks.length; i++)
        {
            removedBlocks[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return removedBlocks;
    }

    /**
     * Tests if each of the locations of the blocks of the tetrad is valid
     * and empty.
     * 
     * @param grid  The grid on which the locations of the blocks will be tested.
     * @param locs  The locations of the blocks to be removed.
     * 
     * @return true if the locations are both valid and empty; otherwise,
     *         false
     */
    private boolean areEmpty (MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int index=0; index<locs.length; index++)
        {
            boolean oneValid = tetradGrid.isValid(locs[index]) && 
                (tetradGrid.get(locs[index])==null);
            if (!oneValid)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * translates the tetrad by a specified number of rows and columns.
     * 
     * @param deltaRow   the number of rows to translate the tetrad.
     * @param deltaCol   the number of columns to translate the tetrad.
     * 
     * @return  true if the tetrad was translated; otherwise,
     *          false.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] removedLocs = removeBlocks();
        Location[] futureLoc = new Location[4];
        for (int ind=0; ind<blocks.length; ind++)
        {
            Location currLoc = removedLocs[ind];//gets the location of the block
            int newRow = currLoc.getRow() + deltaRow;//calculates the new row of the block
            int newColumn = currLoc.getCol() + deltaCol;//calculates the new col of the block
            Location newLoc = new Location(newRow, newColumn);//sets the new location of the block
            futureLoc[ind] = newLoc;
        }

        if (areEmpty(tetradGrid, futureLoc))
        {
            addToLocations(tetradGrid, futureLoc);
            return true; 
        }
        addToLocations(tetradGrid, removedLocs);
        return false;
    }
    
    /**
     * Rotates the tetrad, using the mid block as the center.
     * 
     * @return  true if the block was rotated; otherwise,
     *          false
     */
    public boolean rotate()
    {
        if (tetradCode == 3)
            return true;
        Location[] removedLocs = removeBlocks();
        Location[] futureLoc = new Location[4];
        Location fixedBlock = removedLocs[0];
        int row0 = fixedBlock.getRow();
        int col0 = fixedBlock.getCol();
        for (int index=0; index<blocks.length; index++)
        {
            Location currLoc = removedLocs[index];
            int newRow = row0 - col0 + currLoc.getCol();
            int newCol = row0 + col0 - currLoc.getRow();
            Location newLoc = new Location(newRow,newCol);
            futureLoc[index]= newLoc;
        }//end for
        if (areEmpty(tetradGrid, futureLoc))
        {
            addToLocations(tetradGrid,futureLoc);
            return true;
        }
        addToLocations(tetradGrid, removedLocs);
        return false;
    }
}

