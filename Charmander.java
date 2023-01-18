import java.util.Random;
import java.util.Scanner;

public class Charmander extends PokemonFire {

    Random random = new Random();
    private static final int CHARMANDER = 1;

    private static final String CHARMANDER_NAME = "Charmander";
    private static final int MAX_LIVE_POINTS_OF_CHARMANDER = 80;
    private static final int MAX_ATTACK_POINTS_OF_CHARMANDER = 40;
    private static final int CHARMELEON = 2;

    private static final String CHARMELEON_NAME = "Charmeleon";
    private static final int MAX_LIVE_POINTS_OF_CHARMELEON = 90;
    private static final int MAX_ATTACK_POINTS_OF_CHARMELEON = 60;
    private static final int CHARIZARD = 3;

    private static final String CHARIZARD_NAME = "Charizard";
    private static final int MAX_LIVE_POINTS_OF_CHARIZARD = 130;
    private static final int MAX_ATTACK_POINTS_OF_CHARIZARD = 80;
    private static final int SCRATCH_ATTACK_COST = 15;

    private static final int MIN_RIVAL_DAMAGE_SCRATCH_ATTACK_COST = 25;
    private static final int MAX_RIVAL_DAMAGE_SCRATCH_ATTACK_COST = 30;
    private static final int FLAME_TAIL_ATTACK_COST = 40;
    private static final int MIN_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST = 30;
    private static final int MAX_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST = 50;
    private static final int FIERY_BLAST_ATTACK_COST = 50;
    private static final int RIVAL_DAMAGE_FIERY_BLAST_ATTACK_COST = 50;
    private static final int SCRATCH_ATTACK = 2;
    private static final int FLAME_TAIL_ATTACK = 3;
    private static final int FIERY_BLAST_ATTACK = 4;

    private static final int MAX_DEVELOP_LEVEL = 3;

    public Charmander() {
        super(MAX_LIVE_POINTS_OF_CHARMANDER,MAX_ATTACK_POINTS_OF_CHARMANDER,CHARMANDER_NAME,MAX_DEVELOP_LEVEL);

    }

    private boolean scratchAttack(Pokemon pokemonRival) {
       return super.preformSingleAttackWithRandom(pokemonRival,SCRATCH_ATTACK_COST,
                MIN_RIVAL_DAMAGE_SCRATCH_ATTACK_COST,MAX_RIVAL_DAMAGE_SCRATCH_ATTACK_COST );

    }

    private boolean flameTailAttack(Pokemon pokemonRival) {
        boolean isFlameTailAttackDone = false;
        if (!this.isEqualLevel(CHARMANDER)){
            isFlameTailAttackDone = super.preformSingleAttackWithRandom(pokemonRival,
                    FLAME_TAIL_ATTACK_COST, MIN_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST
                    ,MAX_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST );
        }
        return isFlameTailAttackDone;
    }

    private boolean fieryBlastAttack(Pokemon pokemonRival) {
        boolean isFieryBlastAttackDone = false;
        if (!this.isEqualLevel(CHARMANDER) && !this.isEqualLevel(CHARMELEON)) {
            isFieryBlastAttackDone = super.preformSingleAttack(pokemonRival,
                    FIERY_BLAST_ATTACK_COST,RIVAL_DAMAGE_FIERY_BLAST_ATTACK_COST);
        }
        return isFieryBlastAttackDone;
    }

    public boolean toDevelop() {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(CHARMANDER))
        {
            isDevelopSuccessfully = super.checkAndSetDevelop(MAX_LIVE_POINTS_OF_CHARMELEON,MAX_ATTACK_POINTS_OF_CHARMELEON,CHARMELEON,CHARMELEON_NAME);
        }else if (this.isEqualLevel(CHARMELEON))
        {
            isDevelopSuccessfully = super.checkAndSetDevelop(MAX_LIVE_POINTS_OF_CHARIZARD,MAX_ATTACK_POINTS_OF_CHARIZARD,CHARIZARD,CHARIZARD_NAME);
        }

        return isDevelopSuccessfully;
    }

    protected void printAttackOptions(int level)
    {

            System.out.println("Please choose attack");
            System.out.println("Press 1 for KICK attack");
            System.out.println("Press 2 for SCRATCH attack");
        if (level == CHARMELEON) {
            System.out.println("Press 3 for FLAME TAIL attack");
        }else if (level == CHARIZARD){
            System.out.println("Press 4 for FIERY BLAST attack");
        }
    }


    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == SCRATCH_ATTACK) {
            isMakeAttackDone = scratchAttack(pokemonRival);
        } else if (selectedAttack == FLAME_TAIL_ATTACK) {
            isMakeAttackDone = flameTailAttack(pokemonRival);
        } else if (selectedAttack == FIERY_BLAST_ATTACK) {
            isMakeAttackDone = fieryBlastAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }


    protected int lotteryOneAttack()
    {
        int numAttack;
        if (this.isEqualLevel(CHARMANDER))
        {
            numAttack = random.nextInt(KICK_ATTACK,SCRATCH_ATTACK+1);
        } else if (this.isEqualLevel(CHARMELEON)) {
            numAttack = random.nextInt(KICK_ATTACK,FLAME_TAIL_ATTACK+1);
        }else {
            numAttack = random.nextInt(KICK_ATTACK,FIERY_BLAST_ATTACK+1);
        }
        return numAttack;
    }


}
