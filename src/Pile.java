import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pile extends QueueArray<Card> {

    public Pile(String cards) {

        // Allows for the construction of a pile of cards from a string
        //
        // Example:
        //   Pile pile = new Pile("As 2s 3s Jh Qh Kh 4d 5d 6d 7c 8c 9c");

        // TODO: implement pile constructor
        Scanner reader = new Scanner(cards);
        while (reader.hasNext()){
            String s = reader.next();
            this.enqueue(new Card(s.charAt(0), s.charAt(1)));
        }
    }

    public String toString() {

        // Creates a string containing 10 cards per line, for as many lines
        // as necessary to show all cards in the pile
        //
        // Example:
        //   6h  3h  Jc  Ts  3c  7h  2h  9c  3s  7s
        //   8c  5d  Js  Jh  6d  Ah  Qh  9d  Ks  6c
        //   Kc  Td  Jc  Th  9s  Kd
        // TODO: implement toString. Use a  'for (Card card : this) {}' loop to print them
        String output = "";
        int line= 0;
        for (Card c : this){
            if (c != null) {
                output += c.toString() + "  ";
                if (line % 10 == 0) {
                    output += "\n";
                }
                line++;
            }
        }

        return output;

    }

    /*
     * A riffle shuffle cuts the pile into two halves and then interleaves
     * them by alternating cards from each half.
     *
     * Hint: Use two temporary Pile objects (or QueueArray<Card> objects) to
     * hold the two halves while you rebuild this pile.
     */
    public void shuffle() {
        // TODO: implement the riffle shuffle
        QueueArray<Card> half1 = new QueueArray<>();
        QueueArray<Card> half2 = new QueueArray<>();
        int count = this.size();
        for (int i = 0; i < this.size(); i++){
            if (i < this.size()/2)
                half1.enqueue(this.dequeue());
            else
                half2.enqueue(this.dequeue());
        }
        for (int j = 0; j < count; j++){
            if (j % 2 == 0)
                this.enqueue(half1.dequeue());
            else
                this.enqueue(half2.dequeue());
        }
    }
}
