"use client";
import { useState } from "react";
import { Deck } from "../types";

interface Props {
  decks: Deck[];
  onSelect: (deck: Deck) => void;
  onCreate: (name: string) => void;
  onDelete: (id: string) => void;
}

export default function DeckList({ decks, onSelect, onCreate, onDelete }: Props) {
  const [newDeckName, setNewDeckName] = useState("");

  const handleCreate = () => {
    if (newDeckName.trim() === "") return;
    onCreate(newDeckName.trim());
    setNewDeckName("");
  };

  return (
    <div className="max-w-2xl mx-auto mt-16">
      <h1 className="text-5xl font-bold mb-2 text-yellow-300">🃏 Cards Against Boredom</h1>
      <p className="text-purple-300 mb-10 text-lg">Pick a deck or create a new one</p>

      {/* Create deck */}
      <div className="flex gap-3 mb-10">
        <input
          type="text"
          placeholder="New deck name..."
          value={newDeckName}
          onChange={(e) => setNewDeckName(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleCreate()}
          className="flex-1 rounded-xl px-4 py-3 bg-[#2d0f4e] text-white placeholder-purple-400 outline-none focus:ring-2 focus:ring-yellow-300"
        />
        <button
          onClick={handleCreate}
          className="bg-yellow-300 text-black font-bold px-6 py-3 rounded-xl hover:bg-yellow-400 transition-all">
          + Create
        </button>
      </div>

      {/* Deck list */}
      {decks.length === 0 ? (
        <p className="text-purple-400 text-center mt-20">No decks yet — create one above!</p>
      ) : (
        <div className="flex flex-col gap-4">
          {decks.map((deck) => (
            <div key={deck.id}
              className="flex items-center justify-between bg-[#2d0f4e] rounded-2xl px-6 py-4 hover:bg-[#3d1a63] transition-all cursor-pointer"
              onClick={() => onSelect(deck)}>
              <div>
                <h2 className="text-xl font-semibold">{deck.name}</h2>
                <p className="text-purple-400 text-sm">{deck.cards.length} cards</p>
              </div>
              <button
                onClick={(e) => { e.stopPropagation(); onDelete(deck.id); }}
                className="text-purple-400 hover:text-red-400 transition-colors text-xl px-2">
                ✕
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}