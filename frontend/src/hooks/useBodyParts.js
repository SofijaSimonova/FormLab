import { useEffect, useState } from "react";
import bodyPartRepository from "../repository/bodyPartRepository";

const useBodyParts = () => {
    const [bodyParts, setBodyParts] = useState([]);

    useEffect(() => {
        bodyPartRepository
            .findAll()
            .then((res) => {
                setBodyParts(res.data);
            })
            .catch((err) => console.log(err));
    }, []);

    return bodyParts;
};

export default useBodyParts;