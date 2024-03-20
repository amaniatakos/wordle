![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# Wordle Solver
This is a Wordle solver API build on Spring Boot.

If you are playing Wordle and you are out of ideas, this API can assist you with word suggestion based on a curated list.

It was created mainly for demo purposes.

You can play the original game at https://www.nytimes.com/games/wordle/index.html


## Getting Started
The project relies on devcontainer, the only thing you need is an IDE (like VSCode) with devcontainer support.
All the dependencies (jre, maven, spring) are "contained" in the docker image

## How to run the application
Execute ```mvn spring-boot:run```

Thne you can check if everything is ok by calling ```curl http://localhost:8080```.

There response is something like ```Greetings from Wordle! The dictionary contains 14855 words!```

## API
There is only one API method named the (tadah!) */propose*

The request is a json object with the following attributes
1. exactMatch - the letter exists and it is at the right position
2. misplaced - the letter exists and it is not at the right position
3. notExist - the letter does not exists

Each field accepts an array of objects that contain the attributes 
1. letter
2. position


## Request Example:
For example if you are playing Worlde and you are stuck in the following screen 

![Example of Wordle web game](/images/wordle-example.png)


you can call the API with the following attributes

```
{
	"exactMatch" : [{"letter":"e","position":"4"},
								 {"letter":"l","position":"5"}],
	"misplaced" : [{"letter":"r","position":"3"},
								{"letter":"r","position":"5"},
								 {"letter":"r","position":"2"}
								],
	"notExist" : ["t", "u", "i", "a", "h", "k", "c", "b"]	
}
```

The curl command is

```
curl --request POST \
  --url http://localhost:8080/propose \
  --header 'Content-Type: application/json' \
  --data '{
	"exactMatch" : [{"letter":"e","position":"4"}, {"letter":"l","position":"5"}],
	"misplaced" : [{"letter":"r","position":"3"}, {"letter":"r","position":"5"}, {"letter":"r","position":"2"}],
	"notExist" : ["t", "u", "i", "a", "h", "k", "c", "b"]	
}'
```
## Response Example:
The response is a list of suggested words.

```
[
	"ROWEL",
	"REFEL",
	"REPEL",
	"REVEL"
]
```

It seems to me that the correct word is Repel.

Let me try it...

![Example of a solved Wordle web game](/images/wordle-solved-example.png)


Yes! Now we are ready for the Wordle Finals!