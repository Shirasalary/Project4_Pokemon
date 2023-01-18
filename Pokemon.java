import java.util.Random;
import java.util.Scanner;

public abstract class Pokemon {

    Random random = new Random();
   private int lifePoints;
   private int maxLivePoints;
    private int attackPoints;
    private int maxAttackPoints;
   private int level;

   private int maxDevelopLevel;
   private String name;

   private boolean hasTripleAttack;


    private static final int DEVELOP_LEVEL_1 = 1;
    private static final int DEVELOP_LEVEL_2= 2;

    private static final int DEVELOP_LEVEL_3= 3;

    private static final int KICK_ATTACK_COST = 2;
    private static final int START_LEVEL = 1;

    private static final int LIFE_POINTS_FOR_LEVEL_2 = 20;
    private static final int LIFE_POINTS_FOR_LEVEL_3 = 30;
    private static final int ATTACK_POINTS_FOR_LEVEL_2 = 25;
    private static final int ATTACK_POINTS_FOR_LEVEL_3 = 40;

    private static final int MIN_LIFE_BONUS_FOR_EACH_TURN = 0;
    private static final int MAX_LIFE_BONUS_FOR_EACH_TURN = 4;
    private static final int MIN_ATTACK_BONUS_FOR_EACH_TURN = 0;
    private static final int MAX_ATTACK_BONUS_FOR_EACH_TURN = 4;

    protected static final boolean DOESNT_HAS_TRIPLE_ATTACK = false;

    protected static final int KICK_ATTACK = 1;




    //O(1)
    protected Pokemon (int maxLivePoints, int maxAttackPoints, String name, int maxDevelopLevel) {
       this.name = name;
       this.level = START_LEVEL;
       this.maxAttackPoints=maxAttackPoints;
       this.maxLivePoints =maxLivePoints;
       this.lifePoints = maxLivePoints;
       this.attackPoints = (int) (0.75 * maxAttackPoints) ;
       this.hasTripleAttack = DOESNT_HAS_TRIPLE_ATTACK;
       this.maxDevelopLevel = maxDevelopLevel;

   }

    //O(1)
    public String toString()
    {
      String output = "";
      output+= this.name + "\n";
      output+="Life points: " + this.lifePoints + "/"+ this.maxLivePoints +"\n";
      output+="Attack points: " + this.attackPoints + "/"+ this.maxAttackPoints;
      return output;
    }

    //O(1)
    protected void increaseLifePointsToMax()
    {
        this.lifePoints = this.maxLivePoints;
    }

    //O(1)
    protected void increaseAttackPointsToMax()
    {
        this.attackPoints = this.maxAttackPoints;
    }

    //O(1)
   public void randomBonusForEachTurnAndPrintBonus()
   {
       int lifePointsBonus = random.nextInt(MIN_LIFE_BONUS_FOR_EACH_TURN,MAX_LIFE_BONUS_FOR_EACH_TURN+1);
       int attackPointsBonus = random.nextInt(MIN_ATTACK_BONUS_FOR_EACH_TURN,MAX_ATTACK_BONUS_FOR_EACH_TURN+1);
       this.addLifePoints(lifePointsBonus);
       this.addAttackPoints(attackPointsBonus);
       System.out.println("At this turn you get "+ lifePointsBonus +" life points bonus and "
       + attackPointsBonus + " attack points bonus");
   }

    //O(1)
    public boolean isAtMaxDevelopLevel()
    {
        return this.maxDevelopLevel == this.level;
    }

    //O(1)
    protected void pokemonFireFineForSpacialAttack()
    {
        this.attackPoints=0;
        this.lifePoints = this.lifePoints/2;
    }

    //O(1)
    protected void kickAttack(Pokemon pokemonRival)
    {
        if (pokemonRival.lifePoints >= KICK_ATTACK_COST)
        {
            pokemonRival.lifePoints = pokemonRival.lifePoints-KICK_ATTACK_COST;
        }else {
            pokemonRival.lifePoints =0;
        }
    }

    //O(1)
    public void addLifePoints(int addLifePoints)
    {
        if (this.maxLivePoints - this.lifePoints >= addLifePoints)
        {
            this.lifePoints += addLifePoints;
        }else {
            this.lifePoints = this.maxLivePoints;
        }
    }

    //O(1)
    protected boolean isEqualLevel(int level) {
        return this.level==level;
    }

    //O(1)
    protected void decreaseAttackPoints(int decreaseAttackPoints)
    {
        if (this.attackPoints >= decreaseAttackPoints)
        {
            this.attackPoints -= decreaseAttackPoints;
        }else {
            this.attackPoints =0;
        }
    }

    //O(1)
    protected boolean isLifePointsUnder20PercentFromMax()
    {
        int twentyPercentLifePointsFromMax = (int) (0.20 * this.maxLivePoints);
        return this.lifePoints < twentyPercentLifePointsFromMax;
    }

    //O(1)
    protected void decreaseLifePoints(int decreaseLifePoints)
    {
        if (this.lifePoints >= decreaseLifePoints)
        {
            this.lifePoints -=decreaseLifePoints;
        }else {
            this.lifePoints =0;
        }

    }

    //O(1)
    protected boolean isEqualOrBiggerAttackPoints(int costAttackPoints )
    {
        return this.attackPoints >= costAttackPoints;
    }


    //O(1)
    public void addAttackPoints(int addAttackPoints)
    {
        if (this.maxAttackPoints - this.attackPoints >= addAttackPoints)
        {
            this.attackPoints += addAttackPoints;
        }else {
            this.attackPoints = this.maxAttackPoints;
        }
    }

    //O(1)
    protected boolean isEnoughPointsForLevel2()
    {
        return this.lifePoints >= LIFE_POINTS_FOR_LEVEL_2 && this.attackPoints >= ATTACK_POINTS_FOR_LEVEL_2;
    }

    //O(1)
    private void setPokemonForNextLevel(int maxLivePoints,int maxAttackPoints,int level,String name) {
        this.maxLivePoints = maxLivePoints;
        this.maxAttackPoints = maxAttackPoints;
        this.level = level;
        this.name = name;
    }


    //O(1)
    private void finePointsForLevel2()
    {
        this.decreaseLifePoints(LIFE_POINTS_FOR_LEVEL_2);
        this.decreaseAttackPoints(ATTACK_POINTS_FOR_LEVEL_2);

    }

    //O(1)
    private boolean isEnoughPointsForLevel3()
    {
        return this.lifePoints >= LIFE_POINTS_FOR_LEVEL_3 && this.attackPoints >= ATTACK_POINTS_FOR_LEVEL_3;
    }

    //O(1)
    private void finePointsForLevel3()
    {
        this.decreaseLifePoints(LIFE_POINTS_FOR_LEVEL_3);
        this.decreaseAttackPoints(ATTACK_POINTS_FOR_LEVEL_3);

    }

    //O(1)
    protected void setHasTripleAttack(boolean hasTripleAttack) {
        this.hasTripleAttack = hasTripleAttack;
    }


    //O(1)
    protected boolean preformSingleAttack(Pokemon pokemonRival, int attackCost , int attackCostForRival )
    {
        boolean isSingleAttackDone = false;

        if (this.attackPoints >= attackCost) {
            if (this.hasTripleAttack)
            {
                attackCostForRival = attackCostForRival*3;
                this.hasTripleAttack=DOESNT_HAS_TRIPLE_ATTACK;
            }
            this.decreaseAttackPoints(attackCost);
            pokemonRival.decreaseLifePoints(attackCostForRival);
            System.out.println("Damage to the rival: " + attackCostForRival);
            isSingleAttackDone = true;
        }
        return isSingleAttackDone;
    }

    //O(1)
    public boolean isHasTripleAttack() {
        return this.hasTripleAttack;
    }

    //O(1)
    public void printPokemonName() {
        System.out.print(this.name);
    }

    //O(1)
    protected boolean checkAndSetDevelop(int maxLivePoints, int maxAttackPoints, int newLevel, String newName) {
        boolean isDevelopSuccessfully = false;
        if (this.isEnoughPointsForLevel2())
        {
            this.finePointsForLevel2();
            this.setPokemonForNextLevel(maxLivePoints,maxAttackPoints,newLevel,newName);
            isDevelopSuccessfully = true;
        }else if (this.isEnoughPointsForLevel3())
        {
            this.finePointsForLevel3();
            this.setPokemonForNextLevel(maxLivePoints,maxAttackPoints,newLevel,newName);
            isDevelopSuccessfully = true;
        }
        return isDevelopSuccessfully;
    }


    //O(1)
    protected boolean preformSingleAttackWithRandom(Pokemon pokemonRival, int attackCost
            , int minAttackCostForRival , int maxAttackCostForRival )
    {
        boolean isSingleAttackDone = false;

        if (this.attackPoints >= attackCost) {
            this.decreaseAttackPoints(attackCost);
            int pokemonRivalHarmLifePoints = random.nextInt(minAttackCostForRival,
                    maxAttackCostForRival + 1);
            if (this.hasTripleAttack)
            {
                pokemonRivalHarmLifePoints *=3;
                this.hasTripleAttack=DOESNT_HAS_TRIPLE_ATTACK;
            }
            pokemonRival.decreaseLifePoints(pokemonRivalHarmLifePoints);
            System.out.println("Damage to the rival: " + pokemonRivalHarmLifePoints);
            isSingleAttackDone = true;
        }
        return isSingleAttackDone;
    }

    //O(1)
    public boolean performAttack(Pokemon pokemonRival){

        Scanner scanner = new Scanner(System.in);
        boolean isMakeAttackDone = false;
        int playerSelection;
        if (this.level == DEVELOP_LEVEL_1) {
            printAttackOptions(this.level);
            playerSelection = scanner.nextInt();
            while (!conditionPerformAttack(playerSelection) ) {

                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        } else if (this.level == DEVELOP_LEVEL_2 ) {
            printAttackOptions(this.level);
            playerSelection = scanner.nextInt();
            while (!conditionPerformAttack(playerSelection)) {

                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        } else if (this.level == DEVELOP_LEVEL_3){
            printAttackOptions(this.level);
            playerSelection = scanner.nextInt();
            while (!conditionPerformAttack(playerSelection)) {

                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        }
        return isMakeAttackDone;
    }

    //O(1)
    protected boolean conditionPerformAttack( int playerSelection)
    {
        boolean conditionResult = false;
        if (this.level == DEVELOP_LEVEL_1)
        {
            conditionResult = playerSelection >= KICK_ATTACK && playerSelection <= (DEVELOP_LEVEL_1+1);
            if (!conditionResult)
            {
                System.out.println("Please choose number between 1 to " +(DEVELOP_LEVEL_1+1) );
            }

        } else if (this.level == DEVELOP_LEVEL_2) {

            conditionResult = playerSelection >= KICK_ATTACK && playerSelection <= (DEVELOP_LEVEL_2+1);
            if (!conditionResult)
            {
                System.out.println("Please choose number between 1 to " +(DEVELOP_LEVEL_2+1) );
            }

        }else if (this.level == DEVELOP_LEVEL_3){
            conditionResult = conditionResult = playerSelection >= KICK_ATTACK && playerSelection <= (DEVELOP_LEVEL_3+1);;
            if (!conditionResult)
            {
                System.out.println("Please choose number between 1 to " +(DEVELOP_LEVEL_3+1) );
            }
        }
        return conditionResult;
    }

    //O(1)
    public boolean isDead()
    {
        return this.lifePoints == 0;
    }

    public abstract boolean toDevelop();

    protected abstract void printAttackOptions(int level);

    protected abstract boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival);

}
