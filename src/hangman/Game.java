package hangman;

public class Game {
    
    // Atributos
    private String[] word;
    private int charFound;
    private int tries;
    private String characterX;
   
    //Constructors

    public Game() {
    }

    public Game(String[] word, int charFound, int tries, String characterX) {
        this.word = word;
        this.charFound = charFound;
        this.tries = tries;
        this.characterX = characterX;
    }
    
    //Getters and Setters

    public String[] getWord() {
        return word;
    }

    public void setWord(String[] word) {
        this.word = word;
    }

    public int getCharFound() {
        return charFound;
    }

    public void setCharFound(int charFound) {
        this.charFound = charFound;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public String getCharacterX() {
        return characterX;
    }

    public void setCharacterX(String characterX) {
        this.characterX = characterX;
    }
    
    
}
