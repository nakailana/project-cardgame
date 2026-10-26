package com.lananakai.cardgame;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// Database access for decks (and their cards, via cascade).
// Spring generates the implementation: findAll, findById, save, deleteById, etc.
public interface DeckRepository extends JpaRepository<Deck, String> {
    // only the decks belonging to owner
    List<Deck> findByOwner(AppUser owner);

    // the deck with this id, but only if owner owns it
    Optional<Deck> findByIdAndOwner(String id, AppUser owner);
}
