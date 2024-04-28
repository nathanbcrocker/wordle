### Wordle Cheat Tool

This is a simple tool to help you cheat at Wordle. It will give you a list of words for you to try based on your input.

### How to use
There are two modes that you can use this tool in:
- pattern mode
- sequence mode

pattern mode is the default mode. In this mode, you provide a pattern of the word you are trying to guess. 
The pattern is a string of letters and underscores. The underscores represent the letters that you have not guessed yet. The letters represent the letters that you have guessed. 

For example, if you have guessed the letter 'A' and the letter 'E' in the word 'apple', the pattern would be 'A__LE'.
shell:> `wordle --pattern _____`

sequence mode is an alternative mode that you can use if you have a sequence of letters that you are looking for.

Either mode supports the following options:
--exclude <letters> - do not return words that contain any of the specified letters
--include <letters> - return words that contain ALL the specified letters
--starters true - returns historically "good" starting words
--recent true - suppressing words that have appeared "recently" in Wordle

### Installation
To install the tool, you can use the following command:
`./gradlew clean build`

This will create a jar file in the build/libs directory. You can then run the tool using the following command:
`java -jar build/libs/wordle-1.0-SNAPSHOT.jar wordle --pattern CL___ --include A --exclude B`

Alternatively, you could run the application interactively by running the following command: `java -jar build/libs/wordle-1.0-SNAPSHOT.jar` 
Then type in the command `wordle --pattern CL___ --include A --exclude B`

You should see a list of words that match the pattern 'CL___' and contain the letter 'A' but do not contain the letter 'B'.
For example, [CLANK, CLACK, CLAVE, CLASH, CLOAK, CLASP, CLANG, CLAMP, CLAIM, CLEAT, CLEAR, CLEAN, CLANS, CLASS, CLAMS, CLADS, CLAPS, CLAWS, CLAYS]

### Implementation details
This tool builds a Trie data structure from a list of words. The Trie is then used to find words that match the specified pattern and criteria. A special, recursive function is used to find words that match the pattern and criteria. This differs from a standard Trie , somewhat. If the Trie encounters the wildcard symbol "_",  then it will traversal all children of that TrieNode.

The Trie is populated using the file five-letter-words.txt, which contains over 5000 five-letter words. This file is located in the resources directory. Additionally, a separate file, recent-words.txt, contains a list of words that have appeared in recent Wordle games. This file too is also located in the resources directory.

Additionally, the tool uses a simple scoring system that ranks the probability that a given character will be in a specific position in the word. This scoring system is based on the frequency of the character taken from recent Wordle games. The tool will then sort the words based on this score, meaning the results will not necessarily be in alphabetical order.

One additional feature is the usage of the Chain of Responsibility design pattern. The chain processes the Request by performing actions on the Request, as it moves throughout the chain. 

### Future improvements
The scoring system could be improved by taking into account the position of a letter relative to the position of other letters. A Markov chain could be used to calculate the probability of a letter being in a specific position given the previous letters. One other improve could be to factor in the position of letters that have been "found", but are not in the correct location. 
For example, if the pattern is "___LE" and the word is "APPLE", and I guessed "STALE," then the "A" is in the word, but not in the correct location. Therefore all words that contain "A" in the third position (index 2) should be discounted.