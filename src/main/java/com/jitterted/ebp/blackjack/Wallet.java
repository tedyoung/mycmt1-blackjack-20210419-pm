package com.jitterted.ebp.blackjack;

public class Wallet {
  private int balance = 0;

  public boolean isEmpty() {
    return balance == 0;
  }

  public void addMoney(int amount) {
    requirePositiveAmount(amount);
    balance += amount;
  }

  public int balance() {
    return balance;
  }

  public void bet(int amount) {
    requireSufficientBalance(amount);
    balance -= amount;
  }

  private void requireSufficientBalance(int amount) {
    if (amount > balance) {
      throw new IllegalStateException();
    }
  }

  private void requirePositiveAmount(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException();
    }
  }
}
