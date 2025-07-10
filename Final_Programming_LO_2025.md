# Welcome to the Learning Outcomes Evaluation

Dear students,

Welcome to this Learning Outcomes Evaluation session. The goal is to assess your understanding and mastery of the learning outcomes for this semester as evidenced by your work that was submitted on your personal git account. Remember to answer each question thoroughly by referencing **Java** code and provide clear explanations where necessary.

Best regards,
Kay Berkling

## Ethics Section regarding generative and other forms of AI

The student acknowledges and agrees that the use of AI is strictly prohibited during this evaluation. By submitting this report, the student affirms that they have completed the form independently and without the assistance of any AI technologies. This agreement serves to ensure the integrity and authenticity of the students work, as well as their understanding of the learning outcomes.


## Checklist before handing in your work

* [ ] Review the assignment requirements to ensure you have completed all the necessary tasks.
* [ ] Double-check your links and make sure that links lead to where you intended. Each answer should have links to work done by you in your own git repository. (Exception is Speed Coding)
* [ ] Make sure you have at least 10 references to your project code (This is important evidence to prove that your project is substantial enough to support the learning outcome of object oriented design and coding within a larger piece of code.)
* [ ] Include comments to explain your referenced code and why it supports the learning outcome.
* [ ] Commit and push this markup file to your personal git repository and hand in the link and a soft-copy via email at the end of the designated time period.

Remember, this checklist is not exhaustive, but it should help you ensure that your work is complete, well-structured, and meets the required standards.

Good luck with your evaluation!

# Project Description (70%)

## Link

https://github.com/Ngoc901/card-flipping-memory-game-2.0

## TECH STACK

Programming language: Java
GUI Framework: Swing
Development Environment: IntelliJ IDEA
Version Control: GitHub 

Libraries, Packages:
Jackson: Working with JSON
Java.io.*: Working with files
Java.util.* for lists and input 


## What did you achieve?

I have created an interactive card flipping memory gama with Java. The project is built on fundamental programming concepts and also incorporates topics like GUI development and animation. 
I implement OOP principles. 
I added bonus features like Bonus cards with different penalties and scores.
Created a simple UI.

## Learning Outcomes

| Exam Question | Total Achievable Points | Points Reached During Grading |
|---------------|------------------------|-------------------------------|
| Q1. Algorithms    |           4            |                               |
| Q2.Data types    |           4            |                               |
| Q3.Complex Data Structures |  4            |                               |
| Q4.Concepts of OOP |          6            |                               |
| Q5.OO Design     |           6            |                               |
| Q6.Testing       |           3            |                               |
| Q7.Operator/Method Overloading | 6 |                               |
| Q8.Templates/Generics |       4            |                               |
| Q9.Class libraries |          4            |                               |


## Evaluation Questions

Please answer the following questions to the best of your ability to show your understanding of the learning outcomes. Please provide examples from your project code to support your answers.


## Evaluation Material


### Q1. Algorithms

Algorithms are manyfold and Java can be used to program these. Examples are sorting or search strategies but also mathematical calculations. Please refer to **two** areas in either your regular coding practice (for example from Semester 1) or within your project, where you have coded an algorithm. Do not make reference to code written for other classes, like theoretical informatics.

https://github.com/Ngoc901/projects-java-DHBW/blob/main/src/Algorithms/Algorithms.java

During the daily coding tasks, after exploring some fundamental algorithms, I decided it was a good daily coding habit to implement some of them.
Algorithms I explored: Binary Search, Quick Sort, Swapping, Bubble Sort, Selection Sort, Jump Search. 

I actually used swapping to randomly shuffle the cards in my memory game. 

(MatchCards.java line 224)
I store the card in a ArrayList<Card> cardSet; and it basicly iterates through each index of the cardSet and picks a random index. Then it swaps them using a temporary variable.

void shuffleCard(){
for (int i = 0; i < cardSet.size(); i++) {
int j = (int) (Math.random() * cardSet.size());
Card temp = cardSet.get(i);
cardSet.set(i, cardSet.get(j));
cardSet.set(j, temp);
}
}



| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4            |                               |


### Q2. Data types

Please explain the concept of data types and provide examples of different data types in Java.
typical data types in java are int, double, float, char, boolean, long, short, byte, String, and arrays. Please provide one example for each of the **four** following data types in your code.
* arrays:

I store the name of the cards in a array of strings, so when I call the setupCard method: I can iterate through to build the file path for the card images. (Matchcards.java)

(Configs.java)

this.cardList = new String[]{
   "balloondog",
   "bear",
   "cake",
   "catto",
   "fish",
   "flower",
   "letter",
   "milk",
   "panda",
   "strawberry"
   }

* Strings: "Start Game"

(MatchCards.java)

Whenever I want to print something out or store text I use Strings.

* boolean: boolean gameReady = false

I use a boolean to check if the game has started or not, since there are things that depends on the condition, if the game has not started yet then for example then the restart button should not be enabled.

* your choice -> int: public int rows

I store the configuration of the board in ints as they are just numbers. 

Data types define what types of data can the variable hold. 

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |



### Q3. Complex Data Structures

Examples of complex data structures in java are ArrayList, HashMap, HashSet, LinkedList, and TreeMap. Please provide an example of how you have used **two** of these complex data structures in your code and explain why you have chosen these data structures.

(UserManager.java)
List<User> sortedUsers = new ArrayList<>(users); 
Arraylist has flexible resizing, and that is perfect for me as I do not know how many users will I have. 


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |


### Q4. Concepts of OOP
Concepts of OOP are the basic building blocks of object-oriented programming, such as classes, objects, methods, and attributes.
Explain HOW and WHY your **project** demonstrates the use of OOP by using all of the following concepts:
* Classes/Objects
* Methods
* Attributes
  Link to the code in your project that demonstrates what you have explained above.

Classes/Objects
Two of the best example in my opinion is the Users and Card. With
I need them as objects, since I need to store multiple users and cards. 
My game is built upon card objects and users or created as they play.

Methods
(MatchCard.java)
With methods I can encapsulate the logic and just call them whenever I need to execute the same logic. It also makes the code cleaner. For example, handleDarkThemeChangeClick, handleLightThemeChangeClick, handleRestartClick had the same code: I just changed the cardList (light theme and dark theme). Now I decided to just pass in the cardList and string for the file path to the setupCard() method. so I just to call that once and then handleRestartClick. 
Also lots of methods need to be called when I start the game or restart it: like setupCard() or shuffleCard(). And many more.

Attributes
I define attributes for each class for example:
(User.java)
private String name;
private int score;
So each user has a name and score. 


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|             6           |                               |

### Q5. OO Design
Please showcase **two** areas where you have used object orientation and explain the advantage that object oriented code brings to the application or the problem that your code is addressing.
Examples in java of good oo design are encapsulation, inheritance, polymorphism, and abstraction.

Inheritance
Abstract class -> Card
And BonusCard and NormalCard shares attributes and methods from the parent Card class. 

Polymorphism
Abstract class -> Card
And BonusCard and NormalCard
We used the Card objects, and it is still using the correct method versions for BonusCard and Normals card

(MatchCard.java scoring system -> I assign randomly (50%) if a Card is a NormalCard or a BonusCard -> i click on the first card -> lets say it is a bonus card then the penalty will be 500 and not 30)
Card CardForScore = cardSet.get(indexForScore);
int Penalty = CardForScore.getMismatchPenalty();
int Score = CardForScore.getMatchScore();
System.out.println(Penalty);
System.out.println(Score);

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|              6          |                               |



### Q6. Testing
Java code is tested by using JUnit. Please explain how you have used JUnit in your project and provide a link to the code where you have used JUnit. Links do not have to refer to your project and can refer to your practice code. If you tested without JUnit, please explain how you tested your code.
Be detailed about what you are testing and how you argue for your test cases.

Feel free to refer to the vibe coding session where you explored testing. (pair programming - you may link to your partner git and name him/her.)

Test cases usually cover the following areas:
* boundary cases
* normal cases
* error cases / catching exceptions

I did not implement any testing. 
| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|         3               |                               |

### Q7. Operator/Method Overloading
An example of operator overloading is the "+" operator that can be used to add two numbers or concatenate two strings. An example of method overloading is having two methods with the same name but different parameters. Please provide an example of how you have used operator or method overloading in your code and explain why you have chosen this method of coding.
The link does not have to be to your project and can be to your practice code.

For error handling (UserManager.java)
System.err.println("Failed to save users.json: " + e.getMessage());


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|          6              |                               |



### Q8. Templates/Generics
Generics in java are used to create classes, interfaces, and methods that operate on objects of specified types. Please provide an example of how you have used generics in your code and explain why you have chosen to use generics. The link does not have to be to your project and can be to your practice code.

Arraylist has flexible resizing, and that is perfect for me as I do not know how many users will I have.
private List<User> users = new ArrayList<>();


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           6             |                               |

### Q9. Class Libraries
Examples of class libraries in java are the Java Standard Library, JavaFX, Apache Commons, JUnit, Log4j, Jackson, Guava, Joda-Time, Hibernate, Spring, Maven, and many more. Please provide an example of how you have used a class library in your **project** code and explain why you have chosen to use this class library.

Java Swing: For UI, it was either Swing or JavaFX. I decided to go for Swing as it was easier to set up.
Java Jackson: For working JSON, it is the standard option.
Java File I/O: For working, accessing, writing and reading in files.

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6            |                               |


# Creativity (10%)
Which one did you choose:

* [ ] Web Interface with Design
* [ ] Database Connected
* [ ] Multithreading
* [X] File I/O
* [ ] API
* [ ] Deployment



I choose the file i/O. I store the users in JSON, so I store the file path as  File. 
so whenever I create/update users 




| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            10          |                               |



# Speed Coding (20%)
Please enter **three** Links to your speed coding session GITs and name your partner.

David, Niki
https://github.com/Ngoc901/task-management-system

Joaquin, Daniela, Niki
https://github.com/Ngoc901/grades-management-system

Brian, Niki
https://github.com/Obrienmaina-Mosbach/CarRentalManager

Atai, Niki
https://github.com/NewStudy2024/programming-second-semestr-atai/tree/main/src/main/java/app/v1/week4

Paste your class diagram for your project that you developed during the peer review class here:

In GiHub repo
My presentation: https://www.canva.com/design/DAGsmUrwaq0/r10Hrad_09zugweXT5XtPQ/edit

It can be done very simply by just copying any image and pasting it while editing Readme.md.


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            16            |                               |





