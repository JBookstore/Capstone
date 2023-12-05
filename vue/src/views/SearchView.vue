<template>
    <div>
        <h2>Search for any plant by ID (as long as the ID is 1)</h2>

        <form>
            <input type="text" v-model="searchString"/>
            <button v-bind:searchString="searchString" onclick="alert('!!!' + searchString + '111')">Search</button>
        </form>

        <div class="searchResults" v-for="plant in searchPlants" v-bind:key="plant.id">
            <plant-card v-bind:plant="plant"/>
        </div>
    </div>
</template>

<script>
    import PlantCard from '../components/PlantCard.vue';
    import plantService from '../services/PlantService.js';

    export default { 
        data() {
            return {
                searchString: '',
                plants: []
            };
        },
        components: {
            PlantCard
        },
        methods: {
            searchPlants(searchString) {
            plantService.getPlantByQuery(searchString)
            .then( response => {
                this.plants = response.data;
            })
        }
        }
    };
</script>

<style scoped>
div {
    background-color: aqua;
}
</style>