import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FirstSimpleBehaviour extends AbstractBehavior<String> {
    private FirstSimpleBehaviour(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        // lambda setup(context -> new FirstSimpleBehaviour(context))
        return Behaviors.setup(FirstSimpleBehaviour::new);
    }

    @Override
    public Receive<String> createReceive() { // message handler, the code that runs on message received
        return newReceiveBuilder()
                .onMessageEquals("say hello", () -> {
                    System.out.println("Hello");
                    return this;
                })
                .onMessageEquals("who are you", () -> {
                    System.out.println("My path is " + getContext().getSelf().path());
                    return this;
                })
                .onMessageEquals("create a child", () -> {
                    ActorRef<String> secondActor = getContext().spawn(FirstSimpleBehaviour.create(), "secondActor");
                    secondActor.tell("who are you");
                    return this;
                })
                .onAnyMessage(message -> {
                    System.out.println("I received the message: " + message);
                    return this;
                })
                .build();
    }
}
