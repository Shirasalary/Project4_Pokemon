public abstract class Pokemon {

   private int livePoints;
   private int maxLivePoints;
    private int attackPoints;
    private int maxAttackPoints;
   private int level;

   private String name;

    private static final int KICK_ATTACK_COST = 2;
    private static final int START_LEVEL = 1;

    private static final int LIFE_POINTS_FOR_LEVEL_2 = 20;
    private static final int LIFE_POINTS_FOR_LEVEL_3 = 30;
    private static final int ATTACK_POINTS_FOR_LEVEL_2 = 25;
    private static final int ATTACK_POINTS_FOR_LEVEL_3 = 40;
   protected Pokemon (int maxLivePoints,int maxAttackPoints,String name) {
       this.name = name;
       this.level = START_LEVEL;
       this.maxAttackPoints=maxAttackPoints;
       this.maxLivePoints =maxLivePoints;
       this.livePoints = maxLivePoints;
       this.attackPoints = (int) (0.75 * maxAttackPoints) ;

   }

    public String toString()
    {
      String output = "";
      output+= this.name + "\n";
      output+="Life points: " + this.livePoints + "\n";
      output+="Attack points: " + this.attackPoints;
      return output;
    }

    protected void kickAttack(Pokemon pokemonRival)
    {
        if (pokemonRival.livePoints >= KICK_ATTACK_COST)
        {
            pokemonRival.livePoints-=KICK_ATTACK_COST;
        }else {
            pokemonRival.livePoints =0;
        }
    }

    protected void addLifePoints(int addLifePoints)
    {
        if (this.maxLivePoints - this.livePoints >= addLifePoints)
        {
            this.livePoints += addLifePoints;
        }else {
            this.livePoints= this.maxLivePoints;
        }
    }

    protected boolean isEqualLevel(int level) {
        return this.level==level;
    }

    protected void downloadAttackPoints(int downloadAttackPoints)
    {
        if (this.attackPoints >= downloadAttackPoints)
        {
            this.attackPoints -= downloadAttackPoints;
        }else {
            this.attackPoints =0;
        }
    }

    protected boolean isLifePointsUnder20PercentFromMax()
    {
        int twentyPercentLifePointsFromMax = (int) (0.20 * this.maxLivePoints);
        return this.livePoints < twentyPercentLifePointsFromMax;
    }

    protected void downloadLifePoints( int downloadLifePoints)
    {
        if (this.livePoints >= downloadLifePoints)
        {
            this.livePoints-=downloadLifePoints;
        }else {
            this.livePoints =0;
        }

    }
    protected boolean isEqualOrBiggerAttackPoints(int costAttackPoints )
    {
        return this.attackPoints >= costAttackPoints;
    }

    protected void addAttackPoints(int addAttackPoints)
    {
        if (this.maxAttackPoints - this.attackPoints >= addAttackPoints)
        {
            this.attackPoints += addAttackPoints;
        }else {
            this.attackPoints = this.maxAttackPoints;
        }
    }

    protected boolean isEnoughPointsForLevel2()
    {
        return this.livePoints >= LIFE_POINTS_FOR_LEVEL_2 && this.attackPoints >= ATTACK_POINTS_FOR_LEVEL_2;
    }

    protected void setPokemonForNextLevel(int maxLivePoints,int maxAttackPoints,int level,String name) {
        this.maxLivePoints = maxLivePoints;
        this.maxAttackPoints = maxAttackPoints;
        this.level = level;
        this.name = name;
    }


    protected void finePointsForLevel2()
    {
        this.downloadLifePoints(LIFE_POINTS_FOR_LEVEL_2);
        this.downloadAttackPoints(ATTACK_POINTS_FOR_LEVEL_2);

    }

    protected boolean isEnoughPointsForLevel3()
    {
        return this.livePoints >= LIFE_POINTS_FOR_LEVEL_3 && this.attackPoints >= ATTACK_POINTS_FOR_LEVEL_3;
    }

    protected void finePointsForLevel3()
    {
        this.downloadLifePoints(LIFE_POINTS_FOR_LEVEL_3);
        this.downloadAttackPoints(ATTACK_POINTS_FOR_LEVEL_3);

    }

    public abstract boolean performAttack(Pokemon pokemonRival);

}
