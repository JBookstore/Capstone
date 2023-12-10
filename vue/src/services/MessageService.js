import axios from "axios";

export default {

    getUserMessages(id) {
        return axios.get(`/messages/user/${id}`)
    },

    getMessageById(id) {
        return axios.get(`/messages/${id}`)
    }

}