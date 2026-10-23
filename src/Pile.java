import java.util.Scanner;
public class Pile extends QueueArray<Card> {
    public Pile(String cards) {
// Allows for the construction of a pile of cards from a string
//
// Example:
// Pile pile = new Pile("As 2s 3s Jh Qh Kh 4d 5d 6d 7c 8c 9c");
        Scanner scanner = new Scanner(cards);
        while (scanner.hasNext()) {
            String card = scanner.next();
            this.enqueue(new Card(card.charAt(0),card.charAt(1)));
        }
        scanner.close();
    }
    public String toString() {
// Creates a string containing 10 cards per line, for as many lines
// as necessary to show all cards in the pile
//
// Example:
// 6h 3h Jc Ts 3c 7h 2h 9c 3s 7s
// 8c 5d Js Jh 6d Ah Qh 9d Ks 6c
// Kc Td Jc Th 9s Kd
        String s = "";
        int i = 0;
        for (Card card : this) {
            s += card + " ";
            if (i % 10 == 9)
                s += System.lineSeparator();
            i = (i+1) % 10;
        }
        return s;
    }
    /*
    * A riffle shuffle cuts the pile into two halves and then
    interleaves
    * them by alternating cards from each half. Split this pile so the
    first
    * half contains the first size/2 cards and the second half contains
    all
    * remaining cards. If the pile has an odd number of cards, the first
    half
    * gets the extra card.
    *
    * Then repeatedly dequeue one card from the first half and enqueue
    it
    * back into this pile, followed by dequeuing one card from the
    second half
    * and enqueuing it back into this pile. Continue alternating until
    both
    * halves are empty; handle the final card from a half if the halves
    have
    * different sizes. Use size() to determine the cut and isEmpty() to
    check
    * whether a half has cards; dequeue() removes a card and enqueue()
    adds it.
    *
    * Hint: Use two temporary Pile objects (or QueueArray<Card> objects)
    to
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
            if (j % 2 == 0 && !half1.isEmpty())
                this.enqueue(half1.dequeue());
            else if (!half2.isEmpty())
                this.enqueue(half2.dequeue());
        }
    }
}
