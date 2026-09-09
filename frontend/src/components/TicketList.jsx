import { useEffect, useState } from "react";
import { getAllTickets, deleteTicket } from "../api/ticketApi";

function TicketList() {
    const [tickets, setTickets] = useState([]);

    const fetchTickets = async () => {
        const response = await getAllTickets();
        setTickets(response.data);
    };

    useEffect(() => {
        fetchTickets();
    }, []);

    const handleDelete = async (id) => {
        await deleteTicket(id);
        fetchTickets();
    };

    return (
        <div>
            <h2>Tickets</h2>
            {tickets.map((ticket) => (
                <div key={ticket.id} style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "8px" }}>
                    <h3>{ticket.title}</h3>
                    <p>{ticket.description}</p>
                    <p>Status: {ticket.status} | Category: {ticket.category || "Not yet classified"}</p>
                    <button onClick={() => handleDelete(ticket.id)}>Delete</button>
                </div>
            ))}
        </div>
    );
}

export default TicketList;