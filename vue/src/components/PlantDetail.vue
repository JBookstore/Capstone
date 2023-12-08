<template>
    <div id="plantDetails">
        <h2>{{ this.plant.common_name }}</h2>

        <img v-if="plant.default_image === null" v-bind:src="getDefaultImage"/>
        <img v-else v-bind:src="plant.default_image.original_url"  id="displayImage" />

        <h3>{{ plant.scientific_name }}</h3>

        <h3></h3>

        <p>{{ plant.description }}</p>
    </div>

    
    <div id="careDetails">
        <h3>Cycle: {{ plant.cycle }}</h3>
        <h3>Watering: {{ plant.watering }}</h3>
        <h3>Sunlight: {{ plant.sunlight }}</h3>
    </div>
    <button v-on:click="onAddClick">ADD TO GARDEN</button>
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
                this.$store.commit('ADD_PLANT_TO_GARDEN', this.plant);
                alert('Added ' + this.plant.common_name + ' to your garden!');
            },
        },

    } 

    // TESTING PURPOSES
    // export default {
    //     computed: {
    //         plant() {
    //             let plant = this.$store.state.plants.find( p => p.id == 1 );
    //             return plant;
    //         }
    //     }
    // }
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