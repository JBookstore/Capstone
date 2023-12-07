<template>
    <plant-detail v-bind:plant="plant" />
</template>

<script>
    import plantDetail from "../components/PlantDetail.vue";
    import plantService from "../services/PlantService.js";

    export default { 
        data() {
            return { 
                plant: {}
            }
        },
        components: {
            plantDetail,
        },
        methods: {
            getPlantById(id) {
                plantService.getPlantById(this.plant.id).then( (response) => {
                    this.plant = response.data;
                })
            }
        }
    };
</script>

<style scoped>

</style>