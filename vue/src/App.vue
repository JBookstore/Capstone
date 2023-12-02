<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1>JAVA GREEN PLANTS</h1>
    
    <!-- SECOND NAV BAR -->
    <div v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'detailSearch' }">Search</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'gardenView' }">My Garden</router-link>

    </div>
    <router-view />
  </div>
</template>
