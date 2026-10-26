package com.lananakai.cardgame;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.security.Principal;
import java.util.List;


// Every endpoint here requires a logged-in user (see SecurityConfig).
// Spring fills in `principal` with whoever is logged in, and users only ever see their own decks.
@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckRepository decks;
    private final AppUserRepository users;

    // Spring passes in the repositories it generated (dependency injection)
    public DeckController(DeckRepository decks, AppUserRepository users) {
        this.decks = decks;
        this.users = users;
    }

    // GET all of the user's decks
    @GetMapping
    public List<Deck> getDecks(Principal principal) {
        return decks.findByOwner(currentUser(principal));
    }

    // POST create a deck owned by the user
    @PostMapping
    public Deck createDeck(@RequestBody Deck deck, Principal principal) {
        Deck newDeck = new Deck(deck.getName());
        newDeck.setOwner(currentUser(principal));
        return decks.save(newDeck);
    }

    // DELETE a deck
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable String id, Principal principal) {
        Deck deck = findDeck(id, principal);
        if (deck == null)
            return ResponseEntity.notFound().build();
        decks.delete(deck);
        return ResponseEntity.ok().build();
    }

    // GET all cards in a deck
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getCards(@PathVariable String id, Principal principal) {
        Deck deck = findDeck(id, principal);
        if (deck == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deck.getCards());
    }

    // POST add a card to a deck
    @PostMapping("/{id}/cards")
    public ResponseEntity<Card> addCard(@PathVariable String id, @RequestBody Card card, Principal principal) {
        Deck deck = findDeck(id, principal);
        if (deck == null)
            return ResponseEntity.notFound().build();
        Card newCard = new Card(card.getActivity(), card.getLocation(), card.getDescription());
        deck.addCard(newCard);
        decks.save(deck);
        return ResponseEntity.ok(newCard);
    }

    // DELETE a card from a deck
    @DeleteMapping("/{id}/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id, @PathVariable String cardId,
            Principal principal) {
        Deck deck = findDeck(id, principal);
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
            @RequestParam(required = false) Boolean outdoor,
            Principal principal) {
        Deck deck = findDeck(id, principal);
        if (deck == null)
            return ResponseEntity.notFound().build();
        Card card = (outdoor != null) ? deck.pullRandomCard(outdoor) : deck.pullRandomCard();
        if (card == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(card);
    }

    // helpers
    private AppUser currentUser(Principal principal) {
        return users.findByUsername(principal.getName()).orElseThrow();
    }

    // someone else's deck gives null (404), same as a missing one, so ids can't be probed
    private Deck findDeck(String id, Principal principal) {
        return decks.findByIdAndOwner(id, currentUser(principal)).orElse(null);
    }
}
