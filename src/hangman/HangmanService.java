package hangman;

import java.util.Scanner;

public class HangmanService {
    
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    public void createGame(Game game) {
        System.out.println("Player 1:" + "\n" + "Insert a secret word:");
        String word = scanner.next().toUpperCase();

        int wordLength = word.length();
        game.setWord(new String[wordLength]);
        String[] vectorAux = new String[wordLength];

        for (int i = 0; i < wordLength; i++) {
            String letra = word.substring(i, i + 1);
            vectorAux[i] = letra;
        }
        game.setWord(vectorAux);
        game.setTries(7);
        game.setCharFound(0);
        System.out.println("\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
    }
    
    // Método longitud(): muestra la longitud de la palabra que se debe encontrar. 
    public void wordLength(Game game, String[] auxGraphic) {
        int dimension;
        dimension = game.getWord().length;

        System.out.println("Player 2:" + "\n" + "Guess the word! You have " + game.getTries()+ " tries.");
        printGraphic(game, auxGraphic);
        System.out.println("");
    }
    
    // Método buscar(letra): este método recibe una letra dada por el usuario 
    // y busca si la letra ingresada es parte de la palabra o no. 
    // También informará si la letra estaba o no.
    public void search(Game game, String[] abc, int contadorAbece, String[] auxGraphic) {
        boolean notFound = true;

        int foundAux = 0;

        System.out.println("Player 2:");
        // Pido que ingrese una letra hasta que ingrese una que no haya ingresado.
        // Se chequea si ya la ingresó con el método yaIngreso().
        do {
            System.out.println("Insert a letter:");
            game.setCharacterX(scanner.next());

        } while (alreadyEntered(game, abc, foundAux) == 1);

        // Si la letra no fue ingresada, la ingreso al vector
        for (int j = 0; j < game.getWord().length; j++) {
            notFound = true;
            String charPosI = game.getWord()[j];
            if (game.getCharacterX().equalsIgnoreCase(charPosI)) {
                game.setCharFound(game.getCharFound() + 1);
                abc[contadorAbece] = game.getCharacterX();
                foundAux++;
                contadorAbece++;
                notFound = false;

            } else {
                notFound = true;
            }
        }

        lettersFound(game, foundAux, auxGraphic);
    }
    
    
    // Método para comprobar si la letra ya fue ingresada previamente
    public int alreadyEntered(Game game, String[] abc, int foundAux) {

        foundAux = 0;
        for (int i = 0; i < 27; i++) {
            if (abc[i].equals(game.getCharacterX())) {
                System.out.println("Letter [" + game.getCharacterX() + "] has already been entered.");
                foundAux = 1;

            } else {
                foundAux = 0;
            }
            break;
        }        
        return foundAux;
    }
    
    // Método encontradas(letra): recibe una letra ingresada por el usuario 
    // y muestra cuántas letras han sido encontradas y cuántas le faltan. 
    // Este método además deberá devolver true si la letra estaba y false si la letra no estaba, 
    // ya que, cada vez que se busque una letra que no esté, se le restará uno a sus oportunidades.
    public void lettersFound(Game game, int foundAux, String[] auxGraphic) {
        boolean match;
        
        
        if (foundAux > 0) {
            System.out.println("\n" + "Letter found!" + "\n" + "Letter [" + game.getCharacterX().toUpperCase() + "] was found " + foundAux + " times.");
            System.out.println("There are " + (game.getWord().length - game.getCharFound()) + " letters left to complete the word." + "\n");
            fillGraphic(game, auxGraphic);
            printGraphic(game, auxGraphic);
            match = true;
            System.out.println("");
        } else if (foundAux == 0) {
            match = false;
            game.setTries(game.getTries()- 1);
            System.out.println("");
            System.out.println("Letter not found!" + "\n" + "You have " + game.getTries()+ " attempts left.");
            fillGraphic(game, auxGraphic);
            printGraphic(game, auxGraphic);
            System.out.println("");
        }
    }
    
    // Método llenarGrafico() para guardar las letras ya halladas y los espacios de las letras que aún faltan
    public void fillGraphic(Game game, String[] auxGraphic) {

        for (int i = 0; i < game.getWord().length; i++) {
            if (game.getWord()[i].equalsIgnoreCase(game.getCharacterX())) {
                auxGraphic[i] = game.getCharacterX().toUpperCase() + " ";
            }
        }
    }
    
     // Método imprimirGrafico() 
    public void printGraphic(Game game, String[] auxGraphic) {
        String magenta = "\033[35m";
        
        for (int i = 0; i < game.getWord().length; i++) {
            System.out.print(magenta + auxGraphic[i]);
        }
        System.out.println("");
    }
    
    // Método juego(): el método juego se encargará de llamar todos los métodos
    // previamente mencionados e informará cuando el usuario descubra toda la palabra o se quede sin intentos. 
    // Este método se llamará en el main.
    public void game(Game game) {

        // Relleno vector abece[] con espacios vacío para guardar las letras halladas luego
        String[] abece = new String[27];
        int contadorAbece = 0;
        for (int i = 0; i < 27; i++) {
            abece[i] = "";
        }

        createGame(game);

        // Relleno vector grafico[] con '_' para mostrar las posiciones de las letras
        String[] auxGrafico = new String[game.getWord().length];

        for (int i = 0; i < game.getWord().length; i++) {
            auxGrafico[i] = "_ ";
        }

        // Arranca el juego!
        wordLength(game, auxGrafico);
        do {
            search(game, abece, contadorAbece, auxGrafico);
        } while (game.getTries()> 0 && game.getWord().length - game.getCharFound()> 0);

        if (game.getWord().length - game.getCharFound()== 0) {
            System.out.println("Congratulations Player 2! You have found the word.");
        } else if (game.getTries()== 0) {
            System.out.println("Player 2: you have been defeated!" + "\n" + " The secret word was: ");
            for (int i = 0; i < game.getWord().length; i++) {
                System.out.print(game.getWord()[i]);
            }
        }
        System.out.println("");
    }
}
