import axiosInstance from "../axios/axios";

const exerciseRepository = {

    getByBodyPart: async (name) => {
        return await axiosInstance.get("/exercises/by-body-part", {
            params: { name },
        });
    },

    findAll: async () => {
        return await axiosInstance.get("/exercises");
    },

};

export default exerciseRepository;