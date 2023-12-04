import axios from 'axios';

export default {

    getPlantById(id) {
        return axios.get(`/plants/${id}`)
    }
}