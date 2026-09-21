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
  const [drawnCard, setDrawnCard] = useState<Card | null>(null);
  const [newActivity, setNewActivity] = useState("");
  const [newDescription, setNewDescription] = useState("");
  const [newOutdoor, setNewOutdoor] = useState(false);

  const handleDraw = async (outdoor: boolean | null = null) => {
    const result = await drawCard(deck.id, outdoor);
    if (result == null) return;
    setDrawnCard(result);
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

  return (
    <div className="max-w-2xl mx-auto mt-16 px-4">
      <button onClick={onBack} className="text-[#7a77c8] hover:text-[#2a2d4a] mb-4 font-semibold transition-colors">
        ← Back
      </button>
      <h1 className="text-4xl font-bold mb-8 text-[#2a2d4a]">{deck.name}</h1>

      {/* Drawn card display */}
      <section className="bg-[#2a2d4a] rounded-2xl p-6 mb-8 shadow-md">
        {drawnCard ? (
          <div className="mb-4">
            <p className="text-xl font-semibold text-[#e9e6ff]">{drawnCard.activity}</p>
            <p className="text-[#b6b3f2]">{drawnCard.description}</p>
          </div>
        ) : (
          <p className="text-[#b6b3f2] mb-4">Draw a card to get started!</p>
        )}
        <div className="flex gap-3 flex-wrap">
          <button onClick={() => handleDraw(null)}
            className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-5 py-2 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
            Draw card
          </button>
          <button onClick={() => handleDraw(true)}
            className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-5 py-2 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
            Outdoor
          </button>
          <button onClick={() => handleDraw(false)}
            className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-5 py-2 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
            Indoor
          </button>
        </div>
      </section>

      {/* Add card form */}
      <section className="bg-white/60 rounded-2xl p-6 mb-8 shadow-sm">
        <div className="flex flex-col gap-3">
          <input
            type="text"
            placeholder="New card activity..."
            value={newActivity}
            onChange={(e) => setNewActivity(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && handleAddCard()}
            className="rounded-xl px-4 py-3 bg-white text-[#2a2d4a] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#7a77c8]"
          />
          <input
            type="text"
            placeholder="Add a description..."
            value={newDescription}
            onChange={(e) => setNewDescription(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && handleAddCard()}
            className="rounded-xl px-4 py-3 bg-white text-[#2a2d4a] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#7a77c8]"
          />
          <label className="flex items-center gap-2 text-[#2a2d4a]">
            <input type="checkbox" checked={newOutdoor} onChange={(e) => setNewOutdoor(e.target.checked)} />
            Outdoor activity?
          </label>
          <button onClick={handleAddCard}
            className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-6 py-3 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all self-start">
            + Create
          </button>
        </div>
      </section>

      {/* Card list */}
      <section className="flex flex-col gap-3">
        {deck.cards.length === 0 ? (
          <p className="text-[#7a77c8] text-center">empty deck</p>
        ) : (
          deck.cards.map((card) => (
            <div key={card.id}
              className="flex items-center justify-between bg-[#2a2d4a] border border-[#7a77c8] rounded-xl px-5 py-3 shadow-sm">
              <div>
                <p className="font-semibold text-[#e9e6ff]">{card.activity}</p>
                <p className="text-sm text-[#b6b3f2]">{card.description}</p>
              </div>
              <button onClick={() => handleDeleteCard(card.id)}
                className="text-[#7a77c8] hover:text-red-400 transition-colors text-xl px-2">
                ✕
              </button>
            </div>
          ))
        )}
      </section>
    </div>
  );
}