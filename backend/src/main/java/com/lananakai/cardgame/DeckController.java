package com.lananakai.cardgame;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "http://localhost:3000")
public class DeckController {

    private final DeckRepository decks;

    // Spring passes in the repository it generated (dependency injection)
    public DeckController(DeckRepository decks) {
        this.decks = decks;
    }

    // GET all decks
    @GetMapping
    public List<Deck> getDecks() {
        return decks.findAll();
    }

    // POST create a deck
    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        Deck newDeck = new Deck(deck.getName());
        return decks.save(newDeck);
    }

    // DELETE a deck
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable String id) {
        decks.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // GET all cards in a deck
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getCards(@PathVariable String id) {
        Deck deck = findDeck(id);
        if (deck == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deck.getCards());
    }

    // POST add a card to a deck
    @PostMapping("/{id}/cards")
    public ResponseEntity<Card> addCard(@PathVariable String id, @RequestBody Card card) {
        Deck deck = findDeck(id);
        if (deck == null)
            return ResponseEntity.notFound().build();
        Card newCard = new Card(card.getActivity(), card.getLocation(), card.getDescription());
        deck.addCard(newCard);
        decks.save(deck);
        return ResponseEntity.ok(newCard);
    }

    // DELETE a card from a deck
    @DeleteMapping("/{id}/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id, @PathVariable String cardId) {
        Deck deck = findDeck(id);
        if (deck == null)
            return ResponseEntity.notFound().build();
        deck.removeCard(cardId);
        decks.save(deck);
        return ResponseEntity.ok().build();
    }

    // GET draw a random card
    @GetMapping("/{id}/draw")
    public ResponseEntity<Card> drawCard(
            @PathVariable String id,
            @RequestParam(required = false) Boolean outdoor) {
        Deck deck = findDeck(id);
        if (deck == null)
            return ResponseEntity.notFound().build();
        Card card = (outdoor != null) ? deck.pullRandomCard(outdoor) : deck.pullRandomCard();
        if (card == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(card);
    }

    // helper
    private Deck findDeck(String id) {
        return decks.findById(id).orElse(null);
    }
}