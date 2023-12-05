import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      plants: [ // Plant object array for testing
        {
          id: 1,
          common_name: "Test European Silver Fir",
          scientific_name: [
            "Abies alba"
          ],
          other_name: [
            "Common Silver Fir"
          ],

          // Other data

          default_image: {
            license: 45,
            license_name: "Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)",
            license_url: "https://creativecommons.org/licenses/by-sa/3.0/deed.en", 
            original_url: "https://perenual.com/storage/species_image/1_abies_alba/og/1536px-Abies_alba_SkalitC3A9.jpg",
            regular_url: "https://perenual.com/storage/species_image/1_abies_alba/regular/1536px-Abies_alba_SkalitC3A9.jpg",
            medium_url: "https://perenual.com/storage/species_image/1_abies_alba/medium/1536px-Abies_alba_SkalitC3A9.jpg",
            small_url: "https://perenual.com/storage/species_image/1_abies_alba/small/1536px-Abies_alba_SkalitC3A9.jpg",
            thumbnail: "https://perenual.com/storage/species_image/1_abies_alba/thumbnail/1536px-Abies_alba_SkalitC3A9.jpg"
          }

        },
      ],
      // Ultimately this DOES need to be a two way binding
      user_garden: [
        { 
          id: 1,
          // plant: this.$store.state.plants[0],
        },
        {
          id: 2,
          // plant: this.$store.state.plants[0],
        },
        {
          id: 3,
          // plant: this.$store.state.plants[0],
        }
      ] 
      },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
  });
  return store;
}
