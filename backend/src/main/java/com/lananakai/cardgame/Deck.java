package com.lananakai.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

// Represents a Card Deck with a name (theme) and list of cards.
@Entity
public class Deck {

    @Id
    private String id;
    @Column(name = "name")
    private String deckName;
    // a deck owns its cards: saving/deleting the deck saves/deletes them too
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id")
    private List<Card> cards;
    // the user this deck belongs to; many decks can share one owner.
    // @JsonIgnore keeps the owner (and their password hash) out of API responses
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private AppUser owner;

    // EFFECTS: empty constructor for JPA to fill in fields when loading from the database
    protected Deck() {
    }

    /*
     * REQUIRES: deckName has a non-zero length
     * EFFECTS: the deck's name is set to deckName;
     * the deck is set to an empty ArrayList containing no Cards
     */
    @JsonCreator
    public Deck(@JsonProperty("name") String deckName) {
        this.id = UUID.randomUUID().toString();
        this.deckName = deckName;
        cards = new ArrayList<Card>();
    }

    // MODIFIES: this
    // EFFECTS: makes owner the owner of this deck
    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    // REQUIRES: this is not an empty ArrayList
    // EFFECTS: draws a random card from the deck
    public Card pullRandomCard() {
        if (cards.isEmpty()) {
            return null;
        }
        int cardNum = (int) (Math.random() * cards.size());
        return cards.get(cardNum);
    }

    // EFFECTS: draws a random card of specified location from the deck
    public Card pullRandomCard(Boolean outdoor) {
        List<Card> filtered = filterCards(outdoor);
        if (filtered.size() == 0) {
            return null;
        }
        int cardNum = (int) (Math.random() * filtered.size());

        return filtered.get(cardNum);
    }

    // MODIFIES: this
    // EFFECTS: adds given card to the deck if it is not yet in the deck
    public void addCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given card from the deck
    public void removeCard(String cardId) {
        cards.removeIf(c -> c.getId().equals(cardId));
    }

    // EFFECTS: filters cards in the deck by their activity location
    public List<Card> filterCards(Boolean outdoor) {
        List<Card> filtered = new ArrayList<Card>();

        for (Card c : cards) {
            if (c.getLocation() == outdoor) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    // getters
    public String getName() {
        return deckName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getId() {
        return id;
    }
}
