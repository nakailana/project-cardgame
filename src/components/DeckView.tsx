"use client";
import { useState } from "react";
import { Deck, Card } from "../types";
import { addCard, deleteCard, drawCard } from "../api/api";

interface Props {
  deck: Deck;
  onUpdate: (deck: Deck) => void;
  onBack: () => void;
}

export default function DeckView({ deck, onUpdate, onBack }: Props) {
  // State for the card currently drawn (or null if none drawn yet)
  const [drawnCard, setDrawnCard] = useState<Card | null>(null);

  // State for the "add card" form inputs
  const [newActivity, setNewActivity] = useState("");
  const [newDescription, setNewDescription] = useState("");
  const [newOutdoor, setNewOutdoor] = useState(false);

  // --- Handlers ---

  const handleDraw = async (outdoor: boolean | null = null) => {
    const drawnCard = await drawCard(deck.id, outdoor);
    if (drawnCard == null) return;
    else {
        setDrawnCard(drawnCard);
    }
  };

  const handleAddCard = async () => {
    if (newActivity.trim() === "") return;
    const newCard = await addCard(deck.id, { activity: newActivity, outdoor: newOutdoor, description: newDescription });
    const updatedDeck = { ...deck, cards: [...deck.cards, newCard] };
    onUpdate(updatedDeck);
    setNewActivity("");
    setNewDescription("");
    setNewOutdoor(false);
  };

  const handleDeleteCard = async (cardId: string) => {
    await deleteCard(deck.id, cardId);
    const updatedDeck = { ...deck, cards: deck.cards.filter((c) => c.id !== cardId) };
    onUpdate(updatedDeck);
  };

  // --- Render ---

  return (
    <div>
      <button onClick={onBack}>← Back</button>
      <h1>{deck.name}</h1>

      {/* Drawn card display */}
      <section>
        {drawnCard && (
          <div>
            <p>{drawnCard.activity}</p>
            <p>{drawnCard.description}</p>
          </div>
        )}
        <button onClick={() => handleDraw(null)}>Draw card</button>
        <button onClick={() => handleDraw(true)}>Draw outdoor card</button>
        <button onClick={() => handleDraw(false)}>Draw indoor card</button>
      </section>

      {/* Add card form */}
      <section>
        <input
          type="text"
          placeholder="New card..."
          value={newActivity}
          onChange={(e) => setNewActivity(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleAddCard()}
          className="flex-1 rounded-xl px-4 py-3 bg-[#c7d6ff] text-[#e9e6ff] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#b6b3f2]"
        />
        <input
          type="text"
          placeholder="Add a description..."
          value={newDescription}
          onChange={(e) => setNewDescription(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleAddCard()}
          className="flex-1 rounded-xl px-4 py-3 bg-[#c7d6ff] text-[#e9e6ff] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#b6b3f2]"
        />
        <label className="flex items-center gap-2">
          <input type="checkbox" checked={newOutdoor} onChange={(e) => setNewOutdoor(e.target.checked)} />
            Outdoor activity?
        </label>
        <button
          onClick={handleAddCard}
          className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-6 py-3 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
          + Create
        </button>
      </section>

      {/* Card list */}
      <section>
        {deck.cards.length === 0 ? (
          <p>empty deck</p>
        ) : (
          deck.cards.map((card) => (
            <div key={card.id}>
              <p>{card.activity}</p>
              <p>{card.description}</p>
              <button onClick={() => handleDeleteCard(card.id)}>Delete</button>
            </div>
          ))
        )}
      </section>
    </div>
  );
}