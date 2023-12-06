<template>
    <div>
        <h2>Plant Search</h2>

        <div class="searchForm">
            <input class="searchBar" type="text" v-model="searchString"/>
            <button class="searchButton" v-on:click="searchPlants(this.searchString)">Search</button>
        </div>

        <div class="searchResults" v-for="i in plants" v-bind:key="i">
            <plant-card v-bind:plantId="plants[i]"/>
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
                plantService.getPlants(searchString)
                .then( response => {
                    this.plants = response.data;
                })
            },

            testAlertMethod() {
                let testSearch = this.searchString;
                if(testSearch == '') {
                    alert('Empty query.');
                } else {
                    alert('Searching for: ' + testSearch);
                }
            },
            
            testPopulateArrayMethod() {
                this.plants.push('1', '2');
            },

            testPlantCall() {
                let testSearch = this.searchString;
                return plantService.getPlants(testSearch);
            }
        }
    };
</script>

<style scoped>
div {
    background-color: aqua;
}
h2 {
    text-align: center;
}
</style>