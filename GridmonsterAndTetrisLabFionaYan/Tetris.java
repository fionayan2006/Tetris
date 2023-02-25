import javax.sound.sampled.*;
import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * The Tetris clas creates a grid for the tetris game and displays various
 * game pieces of the tetris game.
 *
 * @author Fiona Yan
 * @version 1/10/2022
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> tetrisGrid;
    private BlockDisplay display;
    private Tetrad tetrad;
    private boolean gameEnd = false;
    /**
     * Constructs a grid for the tetris game, creates the display, sets the
     * title of the window, draws the windoe, and populates the grid with 
     * a tetrad.
     */
    public Tetris()
    {
        tetrisGrid = new MyBoundedGrid<Block>(20,10);
        display = new BlockDisplay(tetrisGrid);
        display.setArrowListener(this);
        String title = "Tetris";
        display.setTitle(title);
        tetrad = new Tetrad(tetrisGrid);
        display.showBlocks();

    }

    /**
     * gets the grid of the Tetris
     * 
     * @return the grid of the tetris object.
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return tetrisGrid;
    }

    /**
     * Rotates the tetrad when the up arrow is pressed
     */
    @Override
    public void upPressed()
    {
        tetrad.rotate();
        display.showBlocks();
    }

    /**
     * Moves the tetrad down when the down arrow is pressed.
     */
    @Override
    public void downPressed()
    {
        tetrad.translate(1,0);
        display.showBlocks();
    }

    /**
     * Moves the tetrad to the left when the left arrow is pressed.
     */
    @Override
    public void leftPressed()
    {
        tetrad.translate(0,-1);
        display.showBlocks();
    }

    /**
     * Moves the tetrad to the right when the rightft arrow is pressed.
     */
    @Override
    public void rightPressed()
    {
        tetrad.translate(0,1);
        display.showBlocks();
    }

    /**
     * Drops the tetrad as far down as possible when space is pressed.
     */
    @Override
    public void spacePressed()
    {
        while(tetrad.translate(1,0))
        {
            tetrad.translate(1,0);
            display.showBlocks();
        }
    }

    /**
     * Plays the game. The Tetrad moves while it can.
     */
    public void play()
    {
        //display.showBlocks();

        try
        {
            //Pause for 1000 milliseconds.
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            //ignore
        }  
        display.showBlocks();
        boolean canMove = tetrad.translate(1,0);
        if (!canMove)
        {
            boolean endGame = isEnd();
            if (endGame)
            {
                gameEnd = true;
            }
            else
            {
                tetrad = new Tetrad(tetrisGrid);
                clearCompletedRows();
            }   
        }
    }

    /**
     * Checks whether a row is filled
     * 
     * @param row the row that will be checked
     * @return    true if the row is filled; otherwise
     *            false.
     */
    private boolean isCompletedRow(int row)
    {
        int numCol = tetrisGrid.getNumCols();
        for (int c=0; c<numCol; c++)
        {
            Location currLoc = new Location(row,c);
            Block current = tetrisGrid.get(currLoc);
            if (current==null)
                return false;
        }
        return true;
    }

    /**
     * Clears a row
     * 
     * @param row   the row to be cleared
     */
    private void clearRow(int row)
    {
        for (int c=0; c<tetrisGrid.getNumCols(); c++)
        {
            Location currLoc = new Location(row,c);
            Block thisBlock = tetrisGrid.get(currLoc);
            thisBlock.removeSelfFromGrid();
        }

        for (int r=row-1; r>=0; r--)
        {
            for (int col=0; col<tetrisGrid.getNumCols(); col++)
            {
                Location thisLocation = new Location (r, col);
                Block currBlock = tetrisGrid.get(thisLocation);
                Location targetLoc = new Location(r+1,col);
                if (currBlock != null)
                    currBlock.moveTo(targetLoc);
            }
        }

    }

    /**
     * Clears all rows that are filled
     */
    private void clearCompletedRows()
    {
        boolean anyCleared = true;
        int thisRow;
        while (anyCleared)
        {
            anyCleared = false;
            thisRow = tetrisGrid.getNumRows()-1;
            while (thisRow>=0)
            {
                if(isCompletedRow(thisRow))
                {
                    clearRow(thisRow);
                    anyCleared = true;
                }
                else
                {
                    thisRow--;
                }
            }

        }

    }

    /**
     * Checks whether the use has lost the game.
     * 
     * @return  true if the user has lost; otherwise,
     *          false
     */
    private boolean isEnd()
    {
        for (int c=0; c<tetrisGrid.getNumCols(); c++)
        {
            Location loc = new Location (0,c);
            if (tetrisGrid.get(loc)!=null)
            {
                return true;
            }
        }
        return false;
    }//only call if the tetrad can't move anymore

    /**
     * Plays music for the game
     * 
     * @return Clip the clip that is being played.
     */
    public Clip playMusic()
    {
        try
        {
            File song = new File("TetrisMusic.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
            Clip theSong = AudioSystem.getClip();
            theSong.open(audioStream);
            theSong.start();
            theSong.loop(100);
            return theSong;
        }
        catch(Exception e ) 
        {
            System.out.println(e);
            return null;
        }

    }

    /**
     * launches the game
     * 
     * @param args  information from the command line.
     */
    public static void main (String[] args)
    {
        Tetris player = new Tetris();
        player.playMusic();
        boolean alwaysTrue = true;
        //boolean test = true;
        player.play();
        while(!player.gameEnd)
        {
            player.play();
        }
        System.out.println("An additional block can't fit! You lost.");
    }

}
