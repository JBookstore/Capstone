<template>
    <div>
        <h2>Plant Search</h2>

        <div class="searchForm">
            <input class="searchBar" type="text" v-model="searchString" />
            <button class="searchButton" v-on:click="searchPlants(this.searchString)">Search</button>
        </div>

        <div v-if="isLoading" class="loadingImage">
            <img src="../assets/loading_animation.gif" id="loadingImage" />
        </div>

        <div v-else class="searchResults" v-for="plant in plants" v-bind:key="plant.id">
            <plant-card v-bind:plant="plant" />
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
            plants: [],
            isLoading: false
        };
    },
    components: {
        PlantCard
    },
    methods: {
        searchPlants(searchString) {
            this.isLoading = true;

            if (searchString == '') {
                alert("You're not searching for anything!");
                this.isLoading = false;
            } else {

                plantService.getPlants(searchString)
                    .then(response => {
                        this.plants = response.data;
                        this.isLoading = false;
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

.searchForm {
    text-align: center;
}

.loadingImage {
    text-align: center;
}

#loadingImage {
    height: 50vh;
    width: auto;
}

/* @media only screen and (min-width: 481px) {
    .searchResults {
        width: 80vw;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        background-color: pink;
    }
} */
</style>