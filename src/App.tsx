import { useState, useEffect } from "react";
import { getDecks, createDeck, deleteDeck } from "./api/api";
import { Deck } from "./types";
import DeckList from "./components/DeckList";
import DeckView from "./components/DeckView";

export default function App() {
  const [decks, setDecks] = useState<Deck[]>([]);
  const [activeDeck, setActiveDeck] = useState<Deck | null>(null);

  useEffect(() => {
    getDecks().then(setDecks);
  }, []);

  const handleCreateDeck = async (name: string) => {
    const newDeck = await createDeck(name);
    setDecks([...decks, newDeck]);
  };

  const handleDeleteDeck = async (id: string) => {
    await deleteDeck(id);
    setDecks(decks.filter((d) => d.id !== id));
    if (activeDeck?.id === id) setActiveDeck(null);
  };

  const handleUpdateDeck = (updated: Deck) => {
    setDecks(decks.map((d) => (d.id === updated.id ? updated : d)));
    setActiveDeck(updated);
  };

  return (
    <div className="min-h-screen bg-[#1a0533] text-white font-sans p-6">
      {activeDeck ? (
        <DeckView
          deck={activeDeck}
          onUpdate={handleUpdateDeck}
          onBack={() => setActiveDeck(null)}
        />
      ) : (
        <DeckList
          decks={decks}
          onSelect={setActiveDeck}
          onCreate={handleCreateDeck}
          onDelete={handleDeleteDeck}
        />
      )}
    </div>
  );
}