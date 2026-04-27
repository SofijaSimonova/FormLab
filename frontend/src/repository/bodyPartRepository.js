import axiosInstance from "../axios/axios";

const bodyPartRepository = {

    findAll: async () => {
        return await axiosInstance.get("/bodyParts");
    },

    findByName: async (name) => {
        return await axiosInstance.get("/bodyParts/by-name", {
            params: { name },
        });
    },

};

export default bodyPartRepository;