import React, { useState } from "react";
import "./Planer.css";

const days = [
  "Montag",
  "Dienstag",
  "Mittwoch",
  "Donnerstag",
  "Freitag",
  "Samstag",
  "Sonntag",
];

const Planer = () => {
  const [plan, setPlan] = useState(null);
  const [error, setError] = useState(null);

  const createPlan = async () => {
    setError(null);
    try {
      const res = await fetch(`/api/mealplans/create?weekStartDate=${new Date().toISOString().split("T")[0]}`, {
        method: "POST",
      });
      if (!res.ok) throw new Error("Server returned " + res.status);
      const data = await res.json();
      setPlan(data);
    } catch (err) {
      console.error(err);
      setError("Fehler beim Erstellen des Plans!");
    }
  };

  return (
    <div className="Planer">
      <h1>Wochenplaner</h1>
      <button onClick={createPlan}>Neuen Wochenplan erstellen</button>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {plan && (
        <div className="plan-info">
          <h2>Plan erstellt!</h2>
          <p>Startdatum: {plan.weekStartDate}</p>
          <p>Plan-ID: {plan.id}</p>
        </div>
      )}
      <div className="days-grid">
        {days.map((day) => (
          <div key={day} className="day-box">
            <h3>{day}</h3>
            <select>
              <option>Essen auswählen</option>
            </select>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Planer;
