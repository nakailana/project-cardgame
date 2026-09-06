"use client"
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import type { Metadata } from "next";
import "./globals.css";
import App from './page.js'

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}