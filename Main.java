import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int POKEMONS_NUM_AT_LEVEL_1 = 6;
    private static final int PERFORM_ATTACK = 1;
    private static final int WAITING = 2;
    private static final int TO_DEVELOP = 3;
    private static final int SPECIAL_ACTION = 4;
    private static final int MAX_BONUS_OPTIONS = 3;
    private static final int MIN_LIFE_POINTS_BONUS = 5;
    private static final int MAX_LIFE_POINTS_BONUS = 30;
    private static final int MIN_ATTACK_POINTS_BONUS = 0;
    private static final int MAX_ATTACK_POINTS_BONUS = 40;
    private static final int ADD_LIFE_POINTS_OPTION = 1;
    private static final int ADD_ATTACK_POINTS_OPTION = 2;

    private static final boolean HAS_TRIPLE_ATTACK = true;


    //O(N)
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int roundCount = 1;
        boolean isAttackDoneSuccessfully = false;
        boolean isSpecialOptionDoneSuccessfully = false;
        Pokemon firstPlayer = randomPokemonForPlayer();
        Pokemon secondPlayer = randomPokemonForPlayer();
        System.out.println("Welcome to POKEMON GAME");
        do {
            System.out.println("Status pokemon of FIRST player");
            System.out.println(firstPlayer);
            System.out.println("--------------------------------------------------------------");
            System.out.println("Status pokemon of SECOND player");
            System.out.println(secondPlayer);
            System.out.println("--------------------------------------------------------------");
            if (isFirstPlayer(roundCount))
            {
                System.out.println("FIRST player turn");
            }else {
                System.out.println("SECOND player turn");
            }
            System.out.println("Please choose action");
            System.out.println("Press 1 for PERFORM ATTACK");
            System.out.println("Press 2 for WAITING");
            System.out.println("Press 3 for DEVELOP");
            System.out.println("Press 4 for SPECIAL ACTION");
            int playerSelection = scanner.nextInt();
            while (conditionMaimMenu(playerSelection))
            {
                System.out.println("Please choose action between 1 to " + SPECIAL_ACTION);
                playerSelection = scanner.nextInt();
            }
            if(playerSelection == PERFORM_ATTACK)
            {
                if (isFirstPlayer(roundCount))
                 {
                   isAttackDoneSuccessfully = firstPlayer.performAttack(secondPlayer);
                   if (!isAttackDoneSuccessfully)
                   {
                       System.out.println("You dont have enough Attack Points");
                   }else {
                       System.out.println("The attack done successfully");
                   }
                 }else {
                     isAttackDoneSuccessfully = secondPlayer.performAttack(firstPlayer);
                     if (!isAttackDoneSuccessfully)
                     {
                         System.out.println("You dont have enough Attack Points");
                     }else {
                         System.out.println("The attack done successfully");
                     }
                 }
            } else if (playerSelection == WAITING) {
                if (isFirstPlayer(roundCount))
                {
                    wittingBonus(firstPlayer);

                }else {
                    wittingBonus(secondPlayer);

                }

            } else if (playerSelection == TO_DEVELOP) {
                boolean isDevelop = false;
                if (isFirstPlayer(roundCount))
                {
                    if (firstPlayer.isAtMaxDevelopLevel())
                    {
                        System.out.print("You are at MAX develop level");
                    }else {
                        isDevelop = firstPlayer.toDevelop();
                    }
                }else {
                    if (secondPlayer.isAtMaxDevelopLevel())
                    {
                        System.out.print("You are at MAX develop level");
                    }else {
                        isDevelop = secondPlayer.toDevelop();
                    }
                }
                if (!isDevelop)
                {
                    System.out.print("You dont have enough points to develop :(");
                }
            }else if (playerSelection == SPECIAL_ACTION){
                if (isFirstPlayer(roundCount))
                {
                   specialOption(firstPlayer,secondPlayer);
                }else {
                   specialOption(secondPlayer,firstPlayer);
                }
            }

            if (isFirstPlayer(roundCount))
            {
               firstPlayer.randomBonusForEachTurnAndPrintBonus();
            }else {
                secondPlayer.randomBonusForEachTurnAndPrintBonus();
            }

            if (firstPlayer instanceof PokemonElectric)
            {
                ((PokemonElectric) firstPlayer).chargePowerForEachTurn();
            }

            if (secondPlayer instanceof PokemonElectric)
            {
                ((PokemonElectric) secondPlayer).chargePowerForEachTurn();
            }

            roundCount++;

        }while (!isGameOver(firstPlayer,secondPlayer));

        if (!isFirstPlayer(roundCount))
        {
            System.out.print("The WINNER is ");
            firstPlayer.printPokemonName();
            System.out.print("! (First player)");
        }else {
            System.out.print("The WINNER is ");
            secondPlayer.printPokemonName();
            System.out.print("! (Second player)");
        }


    }

    public static boolean isFirstPlayer(int roundCount)
    {
        return roundCount % 2 != 0;
    }
    public static void wittingBonus(Pokemon pokemonPlayer)
    {
        Random random = new Random();

       int optionNum = random.nextInt(1,MAX_BONUS_OPTIONS+1);

       if (optionNum == ADD_LIFE_POINTS_OPTION)
       {
           int bonusLifePoints = random.nextInt(MIN_LIFE_POINTS_BONUS,MAX_LIFE_POINTS_BONUS+1);
           pokemonPlayer.addLifePoints(bonusLifePoints);
           System.out.println("The bonus is " + bonusLifePoints + "more life points");
       } else if (optionNum == ADD_ATTACK_POINTS_OPTION) {
           int bonusAttackPoints = random.nextInt(MIN_ATTACK_POINTS_BONUS,MAX_ATTACK_POINTS_BONUS+1);
           pokemonPlayer.addAttackPoints(bonusAttackPoints);
           System.out.println("The bonus is " + bonusAttackPoints + "more attack points");
       } else {

           pokemonPlayer.setHasTripleAttack(HAS_TRIPLE_ATTACK);
           System.out.println("The bonus is TRIPLE power attack at the NEXT turn");
       }

    }

    public static boolean conditionMaimMenu(int playerSelection )
    {
        return (playerSelection != PERFORM_ATTACK && playerSelection != WAITING &&
        playerSelection != TO_DEVELOP && playerSelection != SPECIAL_ACTION);
    }
    public static boolean isGameOver(Pokemon player1,Pokemon player2 )
    {
        return (player1.isDead() || player2.isDead());
    }

    public static Pokemon randomPokemonForPlayer(){
        Random random = new Random();
        Charmander charmander = new Charmander();
        Salandit salandit = new Salandit();
        Moltres moltres = new Moltres();
        Pikachu pikachu = new Pikachu();
        Blitzle blitzle = new Blitzle();
        Electabuzz electabuzz = new Electabuzz();
        Pokemon[] pokemonForPlayer ={charmander,salandit,moltres,pikachu,blitzle,electabuzz};
        int pokemonPlayerNum = random.nextInt(0,POKEMONS_NUM_AT_LEVEL_1);
        return pokemonForPlayer[pokemonPlayerNum];
    }

    public static void specialOption(Pokemon player, Pokemon rival)
    {
        boolean isSpecialOptionDoneSuccessfully = false;
        if (player instanceof PokemonElectric)
        {
            isSpecialOptionDoneSuccessfully=((PokemonElectric) player).spacialOption();
            if (isSpecialOptionDoneSuccessfully)
            {
                System.out.print("Congratulations!,your life points and attack points raised to MAX");
            }else {
                System.out.print("You can use that option only ONE time");
            }

        }else if (player instanceof PokemonFire){
            isSpecialOptionDoneSuccessfully=((PokemonFire) player).spacialOption(rival);
            System.out.print("You've won 2 consecutive attacks that have been drawn");
            if (!isSpecialOptionDoneSuccessfully)
            {
                System.out.println("You dont have enough Attack Points for the 2 attack");
            }
        }
    }
}
