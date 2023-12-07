<template>
    <div>
        <h2>Plant Search</h2>

        <div class="searchForm">
            <input class="searchBar" type="text" v-model="searchString"/>
            <button class="searchButton" v-on:click="searchPlants(this.searchString)">Search</button>
        </div>

        <div class="searchResults" v-for="plant in plants" v-bind:key="plant.id">
            <plant-card v-bind:plant="plant"/>
        </div>
    </div>
</template>

<!-- Some kind of loading screen may be necessary, 3 to 6 second delay -->
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
                if(searchString == '') {
                    alert("You're not searching for anything!");
                } else {

                plantService.getPlants(searchString)
                .then( response => {
                    this.plants = response.data;
                })
                }
            },
        }
    };
</script>

<style scoped>

h2 {
    text-align: center;
}
</style>