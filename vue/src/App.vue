<template>
  <div id="capstone-app">
    <div id="userNav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Alerts</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Messages</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'accountView' }">Account</router-link>
    </div>
    <h1>JAVA GREEN PLANTS</h1>
    
    <!-- SECOND NAV BAR -->
    <div id="siteNav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'detailSearch' }">Search</router-link>
      <hr>

    </div>
      <div id="view">
        <router-view />
      </div>
  </div>
</template>

<script>

</script>

<style scoped>
#capstone-app {
}
#userNav {
  text-align: center;
}
#siteNav {
  text-align: center;
}
h1 {
  text-align: center;
}
#view {
  display: flex;
  justify-content: center;
  align-content: center;
  flex-wrap: wrap;
}
</style>
