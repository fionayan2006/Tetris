import java.util.ArrayList;

/**
 * MyBoundedGrid is the grid on which the game is played and on which the blocks exist.
 * It is a rectangular grid with a finite number of rows and columns.
 * 
 * @author  Dave Feinberg
 * @author  Richard Page
 * @author  Susan King
 * @author  Fiona Yan
 * @version 12/10/2021
 * 
 * @param <E> the elements that may be put in the grid are any objects
 */
public class MyBoundedGrid<E>
{
    /**
     * The 2-D array that is used to store the grid's elements.
     */
    private Object[][] occupantArray; 

    /**
     * Constructs an empty MyBoundedGrid with the given dimensions.
     * 
     * @param rows  the grid's number of rows;  rows > 0 
     * @param cols  the grid's number of cols;  cols > 0
     */
    public MyBoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    /**
     * Retrieves the number of rows.
     * 
     * @return the grid's row count
     */
    public int getNumRows()
    {
        return occupantArray.length;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Retrieves the number of columns.
     * 
     * @return the grid's columns count
     */
    public int getNumCols()
    {
        return occupantArray[0].length;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Determines whether a location is valid.
     * 
     * @param  loc   the location in quesion.  loc != null
     * @return true  if loc is valid in this grid; otherwise, 
     *         false 
     */
    public boolean isValid(Location loc)
    {
        int rowLoc = loc.getRow();
        int colLoc = loc.getCol();
        return ((rowLoc < getNumRows() && rowLoc>=0) && (colLoc>=0 && colLoc < getNumCols()));
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Retrieves an element from this grid at a location, or
     * null if the location is unoccupied.
     * 
     * @param loc is a valid location in this grid
     * 
     * @return the object at location loc 
     *         or null if the location is unoccupied
     */
    public E get(Location loc)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return (E)occupantArray[loc.getRow()][loc.getCol()];
       /* if (loc != null)
        {
            E location = loc.toString();
            return location;
        }
        return null;*/
        //(You will need to promise the return value is of type E.)
    }

    /**
     * Puts an element at location loc on this grid.  Plus
     * returns the object previously at that location, or
     * null if the location is unoccupied.
     * 
     * @param loc is a valid location in this grid
     * @param obj  the object to put at location loc
     * 
     * @return the object at location loc 
     *         or null if the location is unoccupied
     */
    public E put(Location loc, E obj)
    {
        int objRow = loc.getRow();
        int objCol = loc.getCol();
        E orig = (E)occupantArray[objRow][objCol];
        occupantArray[objRow][objCol] = obj;
        return orig;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Removes an element from this grid at a location. Plus
     * returns the object previously at that location, or
     * null if the location is unoccupied.
     * 
     * @param loc is a valid location in this grid
     * 
     * @return the object that was at location loc 
     *         or null if the location is unoccupied
     */
    public E remove(Location loc)
    {
        int locsRow = loc.getRow();
        int locsCol = loc.getCol();
        E needsRemoval = (E)occupantArray[locsRow][locsCol];
        occupantArray[locsRow][locsCol]=null;
        return needsRemoval;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Returns all the occupied location in this grid.
     * 
     * @return all the occupied locations in an arry list; 
     *         0 <= list.size < getNumRows() * getNumCols()
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> occLocs = new ArrayList<Location>();
        for (int row=0; row < getNumRows(); row++)
        {
            for (int col=0; col<occupantArray[row].length; col++)
            {
                Location currLoc = new Location (row, col);
                E nullTest = get(currLoc);
                if (nullTest != null)
                {
                    occLocs.add(currLoc);
                }
            }
        }
        return occLocs;        
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }
}