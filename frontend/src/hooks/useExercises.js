import { useEffect, useState } from "react";
import exerciseRepository from "../repository/exerciseRepository";

const useExercises = (muscle) => {
    const [exercises, setExercises] = useState([]);

    useEffect(() => {
        if (!muscle) return;

        exerciseRepository
            .getByBodyPart(muscle)
            .then((res) => {
                setExercises(res.data);
            })
            .catch((err) => console.log(err));
    }, [muscle]);

    return exercises;
};

export default useExercises;