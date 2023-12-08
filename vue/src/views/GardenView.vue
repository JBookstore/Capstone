<template>
    <div id="gardenDisplay" v-for="plant in this.garden" v-bind:key="plant.id">
      <plant-card v-bind:plant="plant"/>
    </div>
</template>

<script>
    import PlantCard from '../components/PlantCard.vue';
    import plantService from '../services/PlantService.js';

    export default { 
        data() {
            return { 
                id: 1
            }
        },
        components: {
            PlantCard
        },
        methods: {
            getGardenById(id) {
                plantService.getGardenById(this.garden.id).then( (response) => {
                    this.garden = response.data;
                })
            }
        }
    };
</script>

<style scoped>

</style>