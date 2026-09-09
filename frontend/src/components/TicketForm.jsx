import { useState } from "react";
import { createTicket } from "../api/ticketApi";

function TicketForm({ onTicketCreated }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [createdBy, setCreatedBy] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        await createTicket({ title, description, createdBy });
        setTitle("");
        setDescription("");
        setCreatedBy("");
        onTicketCreated();
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>New Ticket</h2>
            <input placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} required />
            <br />
            <textarea placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} />
            <br />
            <input placeholder="Your email" value={createdBy} onChange={(e) => setCreatedBy(e.target.value)} required />
            <br />
            <button type="submit">Create Ticket</button>
        </form>
    );
}

export default TicketForm;