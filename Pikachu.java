import java.util.Random;

public class Pikachu extends PokemonElectric {

    Random random = new Random();
    private static final String PICHU_NAME = "Pichu";
    private static final int PICHU_LEVEL = 1;
    private static final int MAX_LIVE_POINTS_OF_PICHU = 40;
    private static final int MAX_ATTACK_POINTS_OF_PICHU = 30;
    private static final int QUICK_ATTACK_COST = 5;
    private static final int RIVAL_DAMAGE_QUICK_ATTACK_COST = 10;
    private static final String PIKACHU_NAME ="Pikachu";
    private static final int PIKACHU_LEVEL = 2;
    private static final int MAX_LIVE_POINTS_OF_PIKACHU = 50;
    private static final int MAX_ATTACK_POINTS_OF_PIKACHU = 40;
    private static final int ELECTRO_BALL_ATTACK_COST = 10;
    private static final int
            MIN_RIVAL_DAMAGE_ELECTRO_BALL_ATTACK_COST = 30;
    private static final int
            MAX_RIVAL_DAMAGE_ELECTRO_BALL_ATTACK_COST = 40;
    private static final String RAICHU_NAME ="Raichu";
    private static final int RAICHU_LEVEL = 3;
    private static final int MAX_LIVE_POINTS_OF_RAICHU= 160;
    private static final int MAX_ATTACK_POINTS_OF_RAICHU = 80;
    private static final int ELECTRIC_SURFER_ATTACK_COST = 60;
    private static final int
            MIN_RIVAL_DAMAGE_ELECTRIC_SURFER_ATTACK_COST = 20;
    private static final int
            MAX_RIVAL_DAMAGE_ELECTRIC_SURFER_ATTACK_COST = 120;
    private static final int MAX_DEVELOP_LEVEL = 3;
    private static final int QUICK_ATTACK = 2;
    private static final int ELECTRO_BALL_ATTACK = 3;
    private static final int ELECTRIC_SURFER_ATTACK = 4;

    public Pikachu()
    {
        super(MAX_LIVE_POINTS_OF_PICHU,MAX_ATTACK_POINTS_OF_PICHU,PICHU_NAME,MAX_DEVELOP_LEVEL);
    }

    private boolean quickAttack(Pokemon pokemonRival)
    {
        return this.preformSingleAttack(pokemonRival,QUICK_ATTACK_COST,RIVAL_DAMAGE_QUICK_ATTACK_COST);
    }

    private boolean electroBallAttack(Pokemon pokemonRival)
    {
        boolean isElectroBallAttackDone = false;
        if (!this.isEqualLevel(PICHU_LEVEL))
        {
            isElectroBallAttackDone = this.preformSingleAttackWithRandom(pokemonRival,ELECTRO_BALL_ATTACK_COST
            ,MIN_RIVAL_DAMAGE_ELECTRO_BALL_ATTACK_COST,MAX_RIVAL_DAMAGE_ELECTRO_BALL_ATTACK_COST);
        }

        return isElectroBallAttackDone;
    }

    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == QUICK_ATTACK) {
            isMakeAttackDone = quickAttack(pokemonRival);
        } else if (selectedAttack == ELECTRO_BALL_ATTACK) {
            isMakeAttackDone = electroBallAttack(pokemonRival);
        } else if (selectedAttack == ELECTRIC_SURFER_ATTACK) {
            isMakeAttackDone = electricSurferAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }


    protected void printAttackOptions(int level)
    {

        System.out.println("Please choose attack");
        System.out.println("Press 1 for KICK attack");
        System.out.println("Press 2 for QUICK attack");
        if (level == PIKACHU_LEVEL) {
            System.out.println("Press 3 for ELECTRO BALL attack");
        }else if (level == RAICHU_LEVEL){
            System.out.println("Press 4 for ELECTRIC SURFER attack");
        }
    }

    private boolean electricSurferAttack(Pokemon pokemonRival)
    {
        boolean isElectricSurferDone = false;
        if (!this.isEqualLevel(PICHU_LEVEL)&&
        (!this.isEqualLevel(PIKACHU_LEVEL)))
        {
            isElectricSurferDone = this.preformSingleAttackWithRandom(pokemonRival,ELECTRIC_SURFER_ATTACK_COST
                            ,MIN_RIVAL_DAMAGE_ELECTRIC_SURFER_ATTACK_COST,MAX_RIVAL_DAMAGE_ELECTRIC_SURFER_ATTACK_COST);
        }
        return isElectricSurferDone;
    }
    public boolean toDevelop() {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(PICHU_LEVEL))
        {
            isDevelopSuccessfully = checkAndSetDevelop(MAX_LIVE_POINTS_OF_PIKACHU,MAX_ATTACK_POINTS_OF_PIKACHU,PIKACHU_LEVEL,PIKACHU_NAME);

        }else if (this.isEqualLevel(PIKACHU_LEVEL))
        {
            isDevelopSuccessfully = checkAndSetDevelop(MAX_LIVE_POINTS_OF_RAICHU,MAX_ATTACK_POINTS_OF_RAICHU,RAICHU_LEVEL,RAICHU_NAME);
        }
        return isDevelopSuccessfully;
    }
}
