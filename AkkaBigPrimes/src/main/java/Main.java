import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehaviour.create(), "FirstActorSystem");

        actorSystem.tell("Hello World");
        actorSystem.tell("How's it going?");
        actorSystem.tell("say hello");
        actorSystem.tell("who are you");
        actorSystem.tell("create a child");
    }
}
