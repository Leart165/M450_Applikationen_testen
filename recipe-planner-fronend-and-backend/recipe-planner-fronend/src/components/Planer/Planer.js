import React, { useEffect, useState } from "react";

// Planer.js
// Ein kompakter Wochen-Planer als einzelne React-Komponente.
// Voraussetzungen: TailwindCSS in Deinem Projekt. Einfach importieren und als <Planer /> verwenden.

export default function Planer() {
  const weekdays = ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"];

  const [weekStart, setWeekStart] = useState(getMonday(new Date()));
  const [items, setItems] = useState(() => loadItems());
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ id: null, title: "", day: 0, time: "", notes: "" });
  const [pendingDelete, setPendingDelete] = useState(null);

  useEffect(() => {
    saveItems(items);
  }, [items]);

  function prevWeek() {
    setWeekStart(addDays(weekStart, -7));
  }
  function nextWeek() {
    setWeekStart(addDays(weekStart, 7));
  }

  function openNew(dayIndex) {
    setForm({ id: null, title: "", day: dayIndex, time: "", notes: "" });
    setShowForm(true);
  }

  function openEdit(item) {
    setForm(item);
    setShowForm(true);
  }

  function saveForm(e) {
    e.preventDefault();
    if (!form.title.trim()) return;
    if (form.id == null) {
      const newItem = { ...form, id: Date.now() };
      setItems((s) => [...s, newItem]);
    } else {
      setItems((s) => s.map((it) => (it.id === form.id ? { ...form } : it)));
    }
    setShowForm(false);
  }

  function removeItemConfirmed() {
    if (pendingDelete != null) {
      setItems((s) => s.filter((it) => it.id !== pendingDelete));
      setPendingDelete(null);
    }
  }

  function moveItemDay(id, newDay) {
    setItems((s) => s.map((it) => (it.id === id ? { ...it, day: newDay } : it)));
  }

  function exportJson() {
    const blob = new Blob([JSON.stringify(items, null, 2)], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = `planer-${formatDate(weekStart)}.json`;
    a.click();
    URL.revokeObjectURL(url);
  }

  return (
    <div className="p-4 max-w-5xl mx-auto">
      <div className="flex items-center justify-between mb-4">
        <div>
          <h2 className="text-2xl font-semibold">Wochen-Planer</h2>
          <div className="text-sm text-gray-500">Woche ab {formatDate(weekStart)}</div>
        </div>
        <div className="space-x-2">
          <button onClick={prevWeek} className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300">← Woche</button>
          <button onClick={() => setWeekStart(getMonday(new Date()))} className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300">Diese Woche</button>
          <button onClick={nextWeek} className="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300">Woche →</button>
          <button onClick={exportJson} className="px-3 py-1 rounded bg-blue-600 text-white">Export</button>
        </div>
      </div>

      <div className="grid grid-cols-7 gap-3">
        {weekdays.map((dayName, di) => (
          <div key={di} className="bg-white rounded shadow p-3">
            <div className="flex justify-between items-start mb-2">
              <div>
                <div className="font-medium">{dayName}</div>
                <div className="text-xs text-gray-500">{formatDate(addDays(weekStart, di))}</div>
              </div>
              <button onClick={() => openNew(di)} className="text-sm px-2 py-1 rounded bg-green-500 text-white">Neu</button>
            </div>

            <div className="space-y-2">
              {items
                .filter((it) => it.day === di)
                .sort((a, b) => (a.time || "").localeCompare(b.time || ""))
                .map((it) => (
                  <div key={it.id} className="border rounded p-2">
                    <div className="flex justify-between items-start">
                      <div>
                        <div className="font-semibold">{it.title}</div>
                        <div className="text-xs text-gray-600">{it.time} {it.notes ? "• " + it.notes : ""}</div>
                      </div>
                      <div className="flex flex-col items-end space-y-1">
                        <div className="text-xs">
                          <select value={it.day} onChange={(e) => moveItemDay(it.id, Number(e.target.value))} className="text-sm">
                            {weekdays.map((w, i) => (
                              <option key={i} value={i}>{w}</option>
                            ))}
                          </select>
                        </div>
                        <div className="flex space-x-1 mt-1">
                          <button onClick={() => openEdit(it)} className="px-2 py-0.5 rounded bg-yellow-300 text-xs">Bearb.</button>
                          <button onClick={() => setPendingDelete(it.id)} className="px-2 py-0.5 rounded bg-red-400 text-xs text-white">Löschen</button>
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
            </div>

          </div>
        ))}
      </div>

      {/* Formular als Modal */}
      {showForm && (
        <div className="fixed inset-0 flex items-center justify-center bg-black/40 z-50">
          <form onSubmit={saveForm} className="bg-white p-4 rounded shadow w-full max-w-md">
            <h3 className="text-lg font-semibold mb-2">Eintrag</h3>
            <div className="mb-2">
              <label className="block text-sm">Titel</label>
              <input autoFocus value={form.title} onChange={(e) => setForm({ ...form, title: e.target.value })} className="w-full border rounded px-2 py-1" />
            </div>
            <div className="grid grid-cols-2 gap-2 mb-2">
              <div>
                <label className="block text-sm">Tag</label>
                <select value={form.day} onChange={(e) => setForm({ ...form, day: Number(e.target.value) })} className="w-full border rounded px-2 py-1">
                  {weekdays.map((w, i) => <option key={i} value={i}>{w}</option>)}
                </select>
              </div>
              <div>
                <label className="block text-sm">Zeit</label>
                <input value={form.time} onChange={(e) => setForm({ ...form, time: e.target.value })} className="w-full border rounded px-2 py-1" placeholder="z. B. 18:30" />
              </div>
            </div>
            <div className="mb-2">
              <label className="block text-sm">Notiz</label>
              <textarea value={form.notes} onChange={(e) => setForm({ ...form, notes: e.target.value })} className="w-full border rounded px-2 py-1" rows={3} />
            </div>
            <div className="flex justify-end space-x-2">
              <button type="button" onClick={() => setShowForm(false)} className="px-3 py-1 rounded bg-gray-200">Abbrechen</button>
              <button type="submit" className="px-3 py-1 rounded bg-blue-600 text-white">Speichern</button>
            </div>
          </form>
        </div>
      )}

      {/* Bestätigungsdialog für Löschen */}
      {pendingDelete && (
        <div className="fixed inset-0 flex items-center justify-center bg-black/40 z-50">
          <div className="bg-white p-4 rounded shadow w-full max-w-sm">
            <p className="mb-3">Eintrag wirklich löschen?</p>
            <div className="flex justify-end space-x-2">
              <button onClick={() => setPendingDelete(null)} className="px-3 py-1 bg-gray-200 rounded">Abbrechen</button>
              <button onClick={removeItemConfirmed} className="px-3 py-1 bg-red-500 text-white rounded">Löschen</button>
            </div>
          </div>
        </div>
      )}

    </div>
  );
}

// -------------------- Hilfsfunktionen --------------------

function getMonday(d) {
  const date = new Date(d);
  const day = date.getDay();
  const diff = date.getDate() - ((day + 6) % 7);
  return new Date(date.setDate(diff));
}
function addDays(d, days) {
  const r = new Date(d);
  r.setDate(r.getDate() + days);
  return r;
}
function formatDate(d) {
  const dd = String(d.getDate()).padStart(2, "0");
  const mm = String(d.getMonth() + 1).padStart(2, "0");
  const yyyy = d.getFullYear();
  return `${dd}.${mm}.${yyyy}`;
}

function loadItems() {
  try {
    const raw = localStorage.getItem("planer-items");
    return raw ? JSON.parse(raw) : [];
  } catch (e) {
    return [];
  }
}
function saveItems(items) {
  try {
    localStorage.setItem("planer-items", JSON.stringify(items));
  } catch (e) {
    // ignore
  }
}