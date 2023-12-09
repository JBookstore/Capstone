<template>
  <div class="home">
    <p>Hello, {{ this.$store.state.user.username }}'s homepage</p>
  </div>
</template>

<script>
import PlantService from '../services/PlantService';
export default {
};
</script>
