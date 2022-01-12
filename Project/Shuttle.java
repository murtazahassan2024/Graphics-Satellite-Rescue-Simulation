/**
 * The shuttle hovers on the left edge of the canvas. It can be moved up or down by pressing
 * the 'K' or 'J' key accordingly. The shuttle has 5 fuelcells, which can be launched one at
 * a time using the space bar. After launching all 5 fuelcells a refueling cannot occur.
 * 
 * @author Computer Science Department
 * @version September 2006
 */

public class Shuttle
{ 
    private Triangle shuttle; 
    private Canvas canvas;
    private SpaceStation spaceStation;
    private String direction;    // Which way to move, "up" or "down"
    private int numberFuelCells; // Note: Up to 5 fuel cells can be created and launched

    /**
     * Constructor create a Shuttle object:
     * Uses canvas when creating the shuttle object
     * The shuttle object needs a color, an initial position,
     * an initial direction of motion, and 5 fuel cells
     *    
     * @param canvas the canvas on which to render the shuttle
     */
    public Shuttle(Canvas theCanvas, SpaceStation theSpaceStation)
    {
        canvas = theCanvas;
        spaceStation = theSpaceStation;
        shuttle = new Triangle(canvas);
        shuttle.moveTo (30,canvas.getHeight( )/ 2);
        shuttle.changeColor ("blue");
        shuttle.makeVisible ();
        numberFuelCells=5;
        
        

    }

    /**
     * Change direction to "up" or "down"  
     * @param theDirection: "up" or "down".
     * 
     */
    public void setDirection(String theDirection)
    {  
        direction = theDirection; 
    }

    /**
     * Move the shuttle 10 pixels up or down in the direction 
     * specified by the direction instance field
    
     */
    public void moveSmallDistance()
    {   if (direction == "up" )
           {shuttle.moveDirection (0,-10);
           }
           else
           {shuttle.moveDirection (0, 10);
           }
        
    }

    /**
     * If there are more cells, create a new fuelCell and launch it
     * from the vertex of the shuttle. One is deducted from the number of available
     * cells.
     * 
     * NOTE: Don't forget to modify the getNumberFuelCells() method so that the
     *       controller will know there is a fuelcell to launch.
     *       
     * @return cell -- a launched FuelCell object or null, if the shuttle is out of cells
     */
    public FuelCell launchCell()
    {
        FuelCell cell = null;
        if (numberFuelCells > 0)
        {
            cell = new FuelCell(canvas,spaceStation);
            cell.launch (shuttle.getXPosition( ), shuttle.getYPosition( ));
            numberFuelCells-=1; //or numberFuelCells--;
        }
        return cell;
    }    

    /**
     * Send the shuttle out to the space station and back:
     * (1) move the shuttle toward the space station and dock
     * (2) Wait three seconds
     * (3) move the shuttle horizontally
     *     back to the left side of the window.
     *      
     * Pattern the docking operation after the similar operation
     * in the FuelCell class.
     * 
     */
    public void dock()
    {   while (shuttle.getXPosition ( )<spaceStation.getXPosition( ))
        {   shuttle.moveDirection (10,0);
            canvas.wait (30);
        }
        shuttle.moveTo (spaceStation.getXPosition( ), spaceStation.getYPosition( ));
        canvas.wait (2000);       
        while (shuttle.getXPosition ( )> 30)
        {   shuttle.moveDirection (-10, 0);
            canvas.wait (30);
        }
    }
            
    /**
     * @return -- the number of fuel cells remaining
     * 
     */
    public int getNumberFuelCells()
    {  
        return numberFuelCells;
    }     
}