"use client";
import { useState } from "react";
import { Deck } from "../types";

interface Props {
  deck: Deck;
  onUpdate: (deck: Deck) => void;
  onBack: (name: string) => void;
}

export default function DeckView({deck, onUpdate, onBack}: Props){

}