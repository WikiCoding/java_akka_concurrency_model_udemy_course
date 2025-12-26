# The problem
We may have many concurrent time consuming task we want to speed up. One approach is with traditional Java multi-threading but we must make sure we handle interrupted exceptions, we make the code thread safe and that we make sure that we don't have thread blocking. That's complex and therefore, there's the Actor Model to help with that.

# Actor Model
Let's imagine human interaction. A manager can go to each of the employees and ask them for a certain task. That's one way to think about this model.

## What is an actor
1. An Actor is an Object that has `Name` and `Path` as fields so it can be identified.
2. Actors have a `Message Queue`. 
3. When an actor tells another actor something what happens is that the message is put in a queue. 
4. Then actors can have code that tells them what they should do when they receive a message and we call this the actors `Behavior`.
5. The `Behaviour` will take a message out of the `Message Queue` and process it once a time.

## Why does this model work from a thread safe point of view
1. Each actor has its own thread 
2. Actors won't share data with other actors (2 different actors can't share the same memory space to the heap). Also each Actor has it's own thread.
3. Each message (that can be shared with other actors) must be immutable, so by definition is Thread Safe
4. Messages are processed one at a time.

Let's imagine that the manager has the collection to store a list of BigPrimes. The Manager might send a message to each of the Employee actors asking for each one big prime, which can be a string. The Employee actors are never going to change that string. So the Employee actors will send the manager a message containing the big integer. And the manager can store those big integers in the ArrayList but it can't change the BigIntegers. I a Supervisor from the Manager asks for the current state of the ArrayList, then the Employees messages will sit in the Message Queue while the Manager processes the Supervisor request and then it will resume processing the messages in the queue one at a time.