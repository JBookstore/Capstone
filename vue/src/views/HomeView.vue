<template>
  <div class="home">
    <p>Hello, {{ this.$store.state.user.username }}'s homepage</p>
  </div>
</template>

<script>
import plantService from '../services/PlantService';
import messageService from '../services/MessageService';

export default {
  data() {
    return {
      gardenArray: [],
      defaultGarden: {}
    }
  },

  created() {
    if (plantService.getGardenByUserId(this.$store.state.user.id).status == 404) {
      this.defaultGarden.user_id = this.$store.state.user.id;
      this.defaultGarden.is_public = true;

      alert('sup');

      plantService.addGarden(this.defaultGarden);

    } else {

      plantService.getGardenByUserId(this.$store.state.user.id)
        .then(response => {
          this.gardenArray = response.data;
          this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);

          plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
            .then(response => {
              this.$store.commit('SET_USER_PLANTS', response.data);
            });
        });

      messageService.getUserMessages(this.$store.state.user.id)
        .then(response => {
          this.$store.commit('SET_USER_MESSAGES', response.data)
        })
    }
  }
};
</script>
