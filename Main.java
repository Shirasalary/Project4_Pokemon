public class Main {

    public static void main(String[] args) {

        Charmander c = new Charmander();
        Charmander c2 = new Charmander();
       boolean isDevelop = c.toDevelop();
       if (isDevelop == true)
       {
           System.out.println(c);
       }else {
           System.out.println("you cant");
       }
    }
}
