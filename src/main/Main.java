package main;
 
import GameSettings.Session;
 
//@Ehitel I Rodríguez

public class Main {
    
    public static void main(String[] args){
    
//        Game game = new Game();
//        User user = new User();
//        game.createsUser(user);
//       
//        Cpu cpu = new Cpu();
//        game.createsCpu(cpu);
//       
//        game.prints(cpu, user);

       
        Session session = new Session();
        session.StartGame();
        
    }
}
