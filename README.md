# Avation project - Hogeschool Utrecht
This project mimicks an entire airline system which is capable of the following:
- Managing bookings
- Managing airports
- Managing aircrafts
- Managing customers & employees
- Managing flight plans
- Managing flights
- Logging in and out with different roles
- ... and more

This project is an assignment for the course `prbed` in the field of study Software Development on the Hogeschool Utrecht. This will be made over the course of 4~ weeks in three different iterations.

## Table of contents
- [Documentation](#documentation)
  - [Domain model](#domain-model)
  - [Dependencies](#dependencies)
- [The team](#the-team)
- [Git strategy](#git-strategy)
- [Scrum](#scrum)
- [Coding standards](#coding-standars)
  - [Java](#java)
  - [Git](#git)
- [Integration Tests](#integration-tests)
  - [Postman Collections](#postman-collections)

## Documentation
For documentation, we've implemented a domain diagram that will be updated over the course of this project. We've also added the most important dependencies we will use in this project.

### Domain model
![Domain diagram](diagram/Aviation%20domain%20diagram.png)

### Dependencies
For this project, we use the following dependencies (the most important ones):
- [Spring](https://spring.io "Spring framework")
- [Lombok](https://projectlombok.org/ "Project Lombok")
- [Jackson](https://github.com/FasterXML/jackson "Jackson Github")

## The team
This project will be made by a group of five, each with their different strengths and weaknesses that complement eachother.

The team has the following members:
- Xander Vedder ([@xandervedder](https://github.com/xandervedder))
- Jort Willemsen ([@JortWillemsen](https://github.com/JortWillemsen))
- Milan Dol ([@JustMilan](https://github.com/JustMilan))
- Ruben van den Brink ([@Rubenvdbrink](https://github.com/Rubenvdbrink))
- Arjen Norbart ([@arjennorbart](https://github.com/arjennorbart))

## Git strategy
For our git strategy, we are using a modified version of git flow. In our strategy we use the following branches:
- `Master branch`
- `Development branch`
- `Feature branches`

Whatever is in the master branch, will be running on Heroku. The only things that will go in the master branch are releases, accompanied by `git tag` tags (v1, v2, etc.). 

All of the development work will be done in the `development` branch. This is to ensure that the deployed version (`master` branch) will always remain stable.

For every story or (sub)task we create a new `feature` branch, each team member can do whatever he wants in this branch (rebasing, force pushing, all of it). These `feature` branches will be used to make Pull Requests in Github. In these PR's there will be regular reviews to ensure high code quality.

## Scrum
This project will make use of the Agile workflow, implementing the Scrum method. This can be seen from our project [boards](https://github.com/huict/prbed-2021-v2b-1/projects). There is a different board for each iteration (1-3).

Currently we use the following lanes:
- `Backlog` (shared between iterations)
- `To Do`
- `In progress`
- `Done` (automated)

We have added all the different types of User Stories located in our backlog, however, each different User Story also has Sub Tasks related to that particular User Story. This way, we can assign different team members to the sub tasks. 

We have also given all the sub tasks points, so we've already thought about how long a task would take and/or if it has high complexity.

## Coding standards
This paragraph is primarily meant for the team, but it can give insights as to how we're keeping the quality of the code up.

### Java
To ensure the code quality of Java is high, we will implement certain patterns wherever needed and follow general rules that help keep our code clear.

We implement design patterns wherever possible, by using the explanations from the[refactoring.guru](https://refactoring.guru/) website.

Prefer `var` over explicitly typing your variable:
```java
// Don't do this:
Connection connection = new Connection();

// Instead, do this:
var connection = new Connection();
```

Do not add unnecessary comments:
```java
// Don't do this:

/**
 * Creates an object
 */
public Object createObject() { ... }

// Do this:
public Object createObject() { ... }
```

Remove unnecessary newlines:
```java
// Don't do this:
public class Xyz {

    private int i = 1;

}

// Do this:
public class Xyz {
    private int i = 1;
}
```

Prefer Lombok over standard boilerplate code:
```java
// Don't do this
public class Aircraft {
    private String id;
    private Type type;
    private Flight current;
    private List<Flight> past;
    private Fleet fleet;

    public Aircraft(String id, Type type, Flight current, List<Flight> past, Fleet fleet) {
        this.id = id;
        this.type = type;
        this.current = current;
        this.past = past;
        this.fleet = fleet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    ...
}

// Do this:
@Data
@AllArgsConstructor
public class Aircraft {
    private String id;
    private Type type;
    private Flight current;
    private List<Flight> past;
    private Fleet fleet;
}

```

### Git
This has been discussed many times by other people, thus it is only natural to link a clear and concise article about this topic: 

https://chris.beams.io/posts/git-commit/

The points mentioned in this article are the ones we should be using for making clear git messages. 

To keep our git history clean, we don't use the `git merge` command by ourselves, instead, we rebase our `feature` branches. No unnecessary merges from `development` to `feature/...`, only merges from the PR's in `development`.

## Integration Tests
Integration tests are done with [Postman](https://www.postman.com/). We've setup an environment to run all of our integration tests, this way our requests will always be up to date for everyone.

For each request we will add an appropriate test script. These tests are written in Javascript with access to Postman specific functions, that help with testing certain conditions (checking for HTTP codes, etc.).

### Postman Collections
To get around Postman's limitations of a maximum amount of requests (25), we export our collection **per** iteration. Not only do we export the Postman collection, we also export the (succesfull) Postman test run. 

These files are located [here](postman/).
