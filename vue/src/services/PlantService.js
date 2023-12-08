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
        return axios.get(`/plants/${id}`)
    },

    addPlantToGarden(plant, gardenId) {
        return axios.post('/plants', plant, gardenId)
    },

    // We need an end point to add plants directly to gardens, not a general add

    getPlantsByGarden(id) {
        return axios.get(`/plants/garden/${id}`)
    },

    getGardenById(id) {
        return axios.get(`/gardens/${id}`)
    },

    getGardenByUserId(id) {
        return axios.get(`/gardens/user/${id}`)
    },

    addGarden(garden) {
        return axios.post('/gardens', garden)
    }
}