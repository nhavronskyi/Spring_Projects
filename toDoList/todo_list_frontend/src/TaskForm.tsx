import React, {useState} from "react";
import TodoTask from "./TodoTask";

export function TaskForm() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleChanges = (e: React.ChangeEvent<HTMLInputElement>): void =>
        e.target.name === "title" ? setTitle(e.target.value) : setDescription(e.target.value);

    const addTask = (e: React.FormEvent): void => {
        const task = {title, description};

        fetch("http://localhost:8080/notes", {
            method: "post",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(task)
        })
    };

    return (
        <div>
            <form onSubmit={addTask}>
                <div>
                    <input
                        placeholder="Title"
                        value={title}
                        name="title"
                        onChange={handleChanges}/>
                </div>
                <div>
                    <input
                        placeholder="Description"
                        value={description}
                        name="description"
                        onChange={handleChanges}/>
                </div>
                <button type="submit">
                    Add Task
                </button>
            </form>
            <TodoTask/>
        </div>
    );
}
