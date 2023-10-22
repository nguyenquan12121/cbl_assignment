import java.util.Random;

class Round{
    private Integer currScore;
    Random random;
    private double accelerate;

    /**
     * @param currScore
     * @param currRound
     * @param accelerate
     */
    public Round() {
        random = new Random();
    }
    /**
     * @return the currScore
     */
    public Integer getCurrScore() {
        return currScore;
    }    
    public double generateAccelerate(){
        //Curr g var in the game engine is 0.07 and it seems to work best around that range
        accelerate = random.nextDouble(0.01, 0.1);
        return accelerate;
    }
    /**
     * @param currScore the currScore to set
     */
    public void setCurrScore(Integer currScore) {
        this.currScore = currScore;
    }
}