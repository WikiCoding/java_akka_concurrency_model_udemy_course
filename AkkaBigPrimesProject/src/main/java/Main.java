import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        ActorSystem<ManagerBehaviour.Command> bigPrimes = ActorSystem.create(ManagerBehaviour.create(), "bigPrimes");
        bigPrimes.tell(new ManagerBehaviour.InstructionCommand("give me the prime numbers"));
    }
}
