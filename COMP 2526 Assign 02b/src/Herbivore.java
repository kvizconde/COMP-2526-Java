import java.util.ArrayList;

/**
 * Herbivore.
 *
 * @author Kevin Vizconde
 * @version 2.0
 */
public class Herbivore extends Movable implements LifeForm  {
    
    private final int SAME_GRP = 1;
    
    private final int EMPTY_GRP = 2;
    
    private final int FOOD_GRP = 2;

    
    /**
     * Sets the color code to Yellow.
     *
     * @return the color code
     * @see LifeForm#getColorCode()
     */
    public String getColorCode() {
        return "#FFFF00";
    }
    

    /**
     * Constructs an object of type Herbivore.
     */
    public Herbivore() {
        moved = false;
        hungry = 0;
    }
    

    /**
     * @see Movable#resetMoved()
     */
    public void resetMoved() {
        moved = false;
    }

    
    /**
     * @see Movable#moveMe(Cell)
     * @param target
     */
    public void moveMe(Cell target) {
        target.setLife(this);
    }
    
    
    /**
     * @see Movable#isEdible(LifeForm)
     * @param life
     * @return
     */
    public boolean isEdible(LifeForm life) {
        return life instanceof Plant;
    }
    
    
    /**
     * Herbivore will give birth if all conditions
     * in this method are met.
     * 
     * @see Movable#giveBirth(Cell, java.util.ArrayList)
     * @param c the Cell
     * @param ns the neighbouring cells
     */
    public void giveBirth(Cell c, ArrayList<Cell> ns) {
        
        int countEmpty = 0,
            countSame = 0,
            countFood = 0;
        
        ArrayList<Cell> dest = new ArrayList<Cell>();
        for(Cell cell : ns) {
           LifeForm life = cell.getLife();
           if (life == null) {
               dest.add(cell);
               countEmpty++;
           } else if (life instanceof Herbivore) {
               countSame++;
           } else if (isEdible(life)) {
               countFood++;
           }
        }
        if (countEmpty >= EMPTY_GRP && countSame >= SAME_GRP && countFood >= FOOD_GRP) {
            Cell dc = dest.get(RandomGenerator.nextNumber(dest.size() - 1));
            dc.setLife(new Herbivore());
        } 
    }

}
