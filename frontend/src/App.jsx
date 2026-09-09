import { useState } from "react";
import TicketList from "./components/TicketList";
import TicketForm from "./components/TicketForm";

function App() {
  const [refreshKey, setRefreshKey] = useState(0);

  return (
      <div style={{ maxWidth: "600px", margin: "40px auto", fontFamily: "sans-serif" }}>
        <TicketForm onTicketCreated={() => setRefreshKey(refreshKey + 1)} />
        <hr />
        <TicketList key={refreshKey} />
      </div>
  );
}

export default App;