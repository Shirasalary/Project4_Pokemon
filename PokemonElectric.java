public  abstract class PokemonElectric extends Pokemon {

    private int powerPercent;

    private static final int POWER_CHARGE_PERCENT_PER_QUEUE = 5;
    private static final int MAX_POWER_PERCENT = 100;

    public PokemonElectric(int maxLivePoints,int maxAttackPoints,String name)
    {
       super(maxLivePoints, maxAttackPoints,name);
       this.powerPercent = 0;
       //אחוז החשמל מתחיל מ0?
    }

    public void addPower()
    {
        if ( MAX_POWER_PERCENT - this.powerPercent >POWER_CHARGE_PERCENT_PER_QUEUE)
        {
            this.powerPercent += POWER_CHARGE_PERCENT_PER_QUEUE;
        }
    }

    protected void kickAttack(Pokemon pokemonRival)
    {
        super.kickAttack(pokemonRival);
        if(super.isLifePointsUnder20PercentFromMax())
        {
            this.powerPercent = 0;
        }

    }

    protected void downloadLifePoints( int downloadLifePoints)
    {
        super.downloadLifePoints(downloadLifePoints);
        if(super.isLifePointsUnder20PercentFromMax())
        {
            this.powerPercent = 0;
        }

    }


}
