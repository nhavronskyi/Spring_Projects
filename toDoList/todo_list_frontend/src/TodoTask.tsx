import React, {useEffect, useState} from "react";
import {ITask} from "./ITask";

export function TodoTask() {
    const [tasks, setTasks] = useState<ITask[]>([]);
    const url = "http://localhost:8080/notes";

    const getAll = () => fetch(url)
        .then(resp => resp.json())
        .then(json => setTasks(json))

    const deleteTask = (id: number) => fetch(`${url}/${id}`, {method: "delete"})
        .then(() => setTasks(tasks => tasks.filter(task => task.id !== id)))

    useEffect((): void => {
        getAll().then(r => r)
    }, []);

    return (
        <div>
            <ol>
                {tasks.map((task: ITask) => (
                    <li key={task.id}>
                        <span>{task.title} </span>
                        <span>{task.description} </span>
                        <button onClick={() => deleteTask(task.id)}>Delete</button>
                    </li>
                ))}
            </ol>
        </div>
    )

}

export default TodoTask;