package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
  private final List<Card> cards = new ArrayList<>();

  Hand() {
  }

  Hand(List<Card> cards) {
    this.cards.addAll(cards);
  }

  void drawCardFrom(Deck deck) {
    cards.add(deck.draw());
  }

  int value() {
    int handValue = cards
        .stream()
        .mapToInt(Card::rankValue)
        .sum();

    boolean hasAce = cards
        .stream()
        .anyMatch(card -> card.rankValue() == 1);

    // if the total hand value <= 11, then count the Ace as 11 by adding 10
    if (hasAce && handValue <= 11) {
      handValue += 10;
    }

    return handValue;
  }

  // specific to Dealer
  Card firstCard() {
    return cards.get(0);
  }

  void display() {
    System.out.println(cards.stream()
                            .map(Card::display)
                            .collect(Collectors.joining(
                               ansi().cursorUp(6).cursorRight(1).toString())));
  }

  boolean isBusted() {
    return value() > 21;
  }

  // ?? Should this be a special kind of Hand, e.g., DealerHand
  boolean shouldDealerHit() {
    return value() <= 16;
  }

  boolean pushes(Hand hand) {
    return hand.value() == value();
  }

  boolean beats(Hand hand) {
    return hand.value() < value();
  }
}
