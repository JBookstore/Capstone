<template>
    <button v-if="this.garden.garden_type == 'Community'">Voulnteer To Help Out</button>
    <div id="gardenDisplay" v-for="plant in this.gardenPlant" v-bind:key="plant.id">
      <plant-card v-bind:plant="plant"/>
    </div>
</template>

<script>
    import PlantCard from '../components/PlantCard.vue';
    import plantService from '../services/PlantService.js';

    export default { 
        data() {
            return { 
                garden: {},
                gardenPlant: []
            }
        },
        components: {
            PlantCard
        },
        methods: {
            getGardenById(id) {
                plantService.getPlantsByGarden(id).then( (response) => {
                    this.gardenPlant = response.data;
                })
            },
            getGarden(id) {
                plantService.getGardenById(id).then(response => {
                    this.garden = response.data;
                })
            }
        },
        created() {
            this.getGardenById(this.$route.params.id),
            this.getGarden(this.$route.params.id)
        }
    };
</script>

<style scoped>

</style>