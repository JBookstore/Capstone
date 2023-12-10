<template>
  <div id="capstone-app">
    <div id="userNav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'home' }">Alerts</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'inbox' }">Messages</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'accountView' }">Account</router-link>
    </div>
    <h1 id="header">JAVA GREEN PLANTS</h1>
    
    <!-- SECOND NAV BAR -->
    <div id="siteNav" v-if="$store.state.token != ''">
      <router-link class="link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="link" v-bind:to="{ name: 'detailSearch' }">Search</router-link>
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

@media screen and (min-width: 600px) {
  #capstone-app {
    display: grid;
    grid-template-areas: 
    "header header header"
    "sitenav usernav usernav"
    "sitenav view view"
    ;
    grid-template-columns: 50px auto auto;
    grid-template-rows: 30px 50px auto;
    height: 100vh;
  }

  #header {
    grid-area: header;
    background-color: pink;
  }

  #userNav {
    grid-area: usernav;
    text-align: end;
    position: absolute;
    top: 70px;
    width: 97vw;
  }

  #siteNav {
    display: flex;
    grid-area: sitenav;
    flex-direction: column;
    font-size: 15px;
  }


  #view {
    grid-area: view;
    justify-content: center;
    align-content: start;
  }
}
</style>
