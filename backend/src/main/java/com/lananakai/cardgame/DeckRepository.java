package com.lananakai.cardgame;

import org.springframework.data.jpa.repository.JpaRepository;

// Database access for decks (and their cards, via cascade).
// Spring generates the implementation: findAll, findById, save, deleteById, etc.
public interface DeckRepository extends JpaRepository<Deck, String> {
}
