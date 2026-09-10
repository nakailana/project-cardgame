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

  const handleDraw = async (outdoor: boolean | null = null) => {
    const card = await drawCard(deck.id, outdoor);
    setDrawnCard(card);
  };

  return (
    <div>
      <button onClick={onBack}>← Back</button>
      <h1>{deck.name}</h1>
    </div>
  );
}