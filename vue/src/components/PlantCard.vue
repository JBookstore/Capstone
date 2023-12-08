<template>
    <div class="card">
        <div class="cardTop">
            <h2>{{ plant.common_name }}</h2>

            <button v-on:click="onDeleteClick" v-if="this.$store.state.user_garden.includes(plant)" class="cardButton">DELETE</button>
            <button v-on:click="onAddClick" v-else class="cardButton">ADD</button>
            <button v-on:click="onDetailsClick" class="cardButton">DETAILS</button>
        </div>


        <img v-if="plant.default_image === null" v-bind:src="getDefaultImage" class="cardImage"/>
        <img v-else v-bind:src="plant.default_image.original_url"  class="cardImage" v-on:click="onDetailsClick"/>
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
                alert('Added one ' + this.plant.common_name + ' to your garden!');
            },
            onDeleteClick() {
                this.$store.commit('DELETE_PLANT_FROM_GARDEN', this.plant);
                alert('Deleted this ' + this.plant.common_name + ' from your garden.');
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
  text-align: center;
  border: 2px solid black;
  background-color: rgb(204, 253, 204);
  border-radius: 10px;
  width: 80vw;
  height: auto;
  margin: 20px;
}

.cardImage {
  margin-top: 5px;
  width: 78vw;
  height: auto;
}

.cardTop {
    height: auto;
}

.cardButton {
    width: 40vw;
}

@media only screen and (min-width: 481px) {
    .card {
        width: 33vw;
        height: auto;
    }
    .cardTop {
        height: auto;
    }
    .cardImage {
        height: auto;
        width: 33vw;
    }
    .cardButton {
        width: 16.5vw;
    }

}
</style>