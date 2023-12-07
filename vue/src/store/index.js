import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      plants: [ // Plant object array for testing
        
      ],
      // Ultimately this DOES need to be a two way binding
      user_garden: [
         
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
      },
      STORE_PLANT(state, plant) {
        state.plants.unshift(plant);
      },
      ADD_PLANT_TO_GARDEN(state, plant) {
        state.user_garden.push(plant);
      },
      DELETE_PLANT_FROM_GARDEN(state, plant) {
        let index = state.user_garden.indexOf(plant);
        state.user_garden.splice(index, 1);
      }
    },
  });
  return store;
}
