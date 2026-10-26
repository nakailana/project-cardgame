"use client";
import { useState } from "react";
import { login, register } from "../api/api";
import { User } from "../types";

interface Props {
  onLogin: (user: User) => void;
}

export default function AuthForm({ onLogin }: Props) {
  const [mode, setMode] = useState<"login" | "register">("login");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    try {
      const user = mode === "login" ? await login(username, password) : await register(username, password);
      onLogin(user);
    } catch (err) {
      setError((err as Error).message || "Something went wrong");
    }
  };

  const switchMode = () => {
    setMode(mode === "login" ? "register" : "login");
    setError("");
  };

  const inputClass =
    "rounded-xl px-4 py-3 bg-white text-[#2a2d4a] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#7a77c8] shadow-sm";

  return (
    <div className="max-w-md mx-auto mt-16 px-4">
      <h1 className="text-5xl font-bold mb-2 text-[#2a2d4a]">CARDS AGAINST BOREDOM</h1>
      <p className="text-[#7a77c8] mb-10 text-lg">
        {mode === "login" ? "Log in to see your decks" : "Create an account"}
      </p>

      <form onSubmit={handleSubmit} className="flex flex-col gap-3">
        <input
          type="text"
          placeholder="Username"
          autoComplete="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className={inputClass}
        />
        <input
          type="password"
          placeholder={mode === "register" ? "Password (8+ characters)" : "Password"}
          autoComplete={mode === "login" ? "current-password" : "new-password"}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className={inputClass}
        />
        {error && <p className="text-[#D9789E] text-sm font-semibold">{error}</p>}
        <button
          type="submit"
          className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-6 py-3 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
          {mode === "login" ? "Log in" : "Sign up"}
        </button>
      </form>

      <button onClick={switchMode} className="mt-6 text-[#7a77c8] hover:text-[#2a2d4a] transition-colors">
        {mode === "login" ? "No account? Sign up" : "Already have an account? Log in"}
      </button>
    </div>
  );
}
