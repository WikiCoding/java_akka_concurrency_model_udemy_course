import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class WorkerBehaviour extends AbstractBehavior<WorkerBehaviour.Command> {

    // this is done so we can share the nextBigPrime with the ManagerBehaviour
    // general good practice to make this a subclass of worker with name Command and make it Serializable
    // this class is static since we will need to call it from Manager
    public static class Command implements Serializable {
        private final String message;
        private final ActorRef<ManagerBehaviour.Command> sender;

        public Command(String message, ActorRef<ManagerBehaviour.Command> sender) {
            this.message = message;
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public ActorRef<ManagerBehaviour.Command> getSender() {
            return sender;
        }
    }

    private WorkerBehaviour(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(WorkerBehaviour::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onAnyMessage(command -> {
                    if (command.getMessage().equals("start")) {
                        BigInteger bigInteger = new BigInteger(2000, new Random());
                        command.getSender().tell(new ManagerBehaviour.ResultCommand(bigInteger.nextProbablePrime()));
                    }
                    return this;
                })
                .build();
    }
}
