import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        ActorSystem<String> bigPrimes = ActorSystem.create(ManagerBehaviour.create(), "bigPrimes");
        bigPrimes.tell("give me the prime numbers");
    }
}
