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

    getGardenById(id) {
        return axios.get(`/gardens/${id}`)
    },

    addGarden(garden) {
        return axios.post('/gardens', garden)
    }
}