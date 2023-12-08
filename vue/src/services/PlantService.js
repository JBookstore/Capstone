import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// });

export default {

    // Consolidated
    getPlants(search) {
        return axios.get(`/${search}`)
    },

    getPlantById(id) {
        return axios.get(`/${id}`)
    },

    addPlantToGarden(plant) {
        return axios.post('/plants', plant)
    },

    // We need an end point to add plants directly to gardens, not a general add

    getGardenById(id) {
        return axios.get(`/plants/garden/${id}`)
    },

    getGardenByUserId(id) {
        return axios.get(`/gardens/user/${id}`)
    },

    addGarden(garden) {
        return axios.post('/gardens', garden)
    }
}