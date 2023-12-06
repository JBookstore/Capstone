import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// });

export default {

    // Consolidated
    getPlants(search) {
        return axios.get(`/${search}`)
    }

    // NAME SEARCH 9000/name is an external API call
}