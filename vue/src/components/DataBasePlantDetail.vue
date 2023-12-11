<template>
    <div id="plantDetails">
        <h2>{{ this.plant.common_name }}</h2>
        <h2>{{ this.plant.scientific_name }}</h2>

        <img v-if="plant.plant_img === null && plant.regular_img_url === null" v-bind:src="getDefaultImage"/>
        <img v-else-if="plant.plant_img != null" v-bind:src="plant.plant_img" />
        <img v-else v-bind:src="plant.regular_img_url"  id="displayImage" />

        <h3>{{ plant.scientific_name }}</h3>

        <p>{{ plant.plant_description }}</p>
    </div>
    
    <div id="careDetails">
        <h3>Cycle: {{ plant.cycle }}</h3>
        <h3>Watering: {{ plant.watering }}</h3>
        <h3>Sunlight: {{ plant.sunlight }}</h3>
    </div>
    <div>
        <button v-on:click="onAddClick">ADD TO GARDEN</button>
    </div>
</template>

<script>
        import plantService from '../services/PlantService.js';
    export default {
        data() {
            return {
                plant: {}
            }
        },


        methods: {
            getDefaultImage() {
                return 'https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png';
            },
            onAddClick() {
                
                this.plant.garden_id = this.$store.state.user_garden.garden_id;

                plantService.addPlantToGarden(this.plant);

                alert('Added one ' + this.plant.common_name + ' to your garden!');
            },
        },

        created() {
            plantService.getGardenByUserId(this.$store.state.user.id)
                .then( response => {
                    this.gardenArray = response.data;
                    this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);
        
            plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
                .then( response => {
                this.$store.commit('SET_USER_PLANTS', response.data);

                });

                this.plant = this.$store.state.plants[0];
            })
        }
    } 
</script>

<style scoped>
#displayImage {
    width: 80vw;
    height: auto;
}
#plantDetails {
    text-align: center;
}

</style>