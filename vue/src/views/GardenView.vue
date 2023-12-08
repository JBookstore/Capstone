<template>
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
                gardenPlant: []
            }
        },
        components: {
            PlantCard
        },
        methods: {
            getGardenById(id) {
                plantService.getGardenById(id).then( (response) => {
                    this.gardenPlant = response.data;
                })
            }
        },
        created() {
            this.getGardenById(this.$route.params.id);
        }
    };
</script>

<style scoped>

</style>