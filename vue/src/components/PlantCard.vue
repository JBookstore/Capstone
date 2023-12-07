<template>
    <div class="card">
        <img v-if="plant.default_image === null" v-bind:src="getDefaultImage"/>
        <img v-else v-bind:src="plant.default_image.original_url"  id="cardImage" />

        <h2>{{ plant.common_name }}</h2>

        <button v-on:click="onDeleteClick" v-if="this.$store.state.user_garden.includes(plant)">DELETE</button>
        <button v-on:click="onAddClick" v-else>ADD</button>
        <button v-on:click="onDetailsClick">DETAILS</button>
    </div>
</template>

<script>
    import plantService from '../services/PlantService.js';

    export default {
        props: {
            plant: { type: Object, required: true }
        },

        methods: {
            onDetailsClick() {

                this.$router.push({ name: 'detailById', params: {id: this.plant.id}});
                this.$store.commit('STORE_PLANT', this.plant);
                // alert('Test');
            },
            onAddClick() {
                this.$store.commit('ADD_PLANT_TO_GARDEN', this.plant);
                alert('Added ' + this.plant.common_name + ' to your garden!');
            },
            onDeleteClick() {
                this.$store.commit('DELETE_PLANT_FROM_GARDEN', this.plant);
                alert('Deleted ' + this.plant.common_name + ' from your garden.');
            }

        },

        computed: {
            getDefaultImage() {
                return 'https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png';
            }
        }
    }
</script>

<!-- We can fix image ratio by specifying width and leaving height undefined -->
<style scoped>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 80vw;
  height: 60vh;
  margin: 10px;
  text-align: center;
}

#cardImage {
  width: auto;
  height: 35vh;
}
</style>