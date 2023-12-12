<template>
    <div class="forumDisplay">
        <div class="marketPlace">
            <h2>Market Place</h2>
            <img class="cardImage" src="../assets/garden.jpg" v-on:click="marketForum" />
            <forum-card v-bind:post="market" v-bind:plant="plant" />

            <hr>
        </div>

        <div class="seasonalPlants">
            <h2>Seasonal Plants</h2>
            <img class="cardImage" src="../assets/garden.jpg" v-on:click="seasonalForum" />
            <forum-card v-bind:post="seasonal" v-bind:plant="plant" />

            <hr>
        </div>

        <div class="plantHealthCare">
            <h2>Plant Healthcare</h2>
            <img class="cardImage" src="../assets/garden.jpg" v-on:click="diseaseForum" />
            <forum-card v-bind:post="disease" v-bind:plant="plant" />
        </div>
    </div>
</template>

<script>
import ForumCard from '../components/ForumCard.vue';
import ForumService from '../services/ForumService';
import PlantService from '../services/PlantService';

export default {
    data() {
        return {
            market: {},
            seasonal: {},
            disease: {},
            plant: this.$store.state.active_plant
        }
    },

    components: {
        ForumCard
    },
    created() {
        ForumService.getPostByForums(1)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.marketplacePreview = response.data[lastIndex];

                PlantService.getPlantById(this.post.plant_id)
                    .then(response => {
                        this.$store.commit('SET_ACTIVE_PLANT', response.data)
                    });
            });
        ForumService.getPostByForums(2)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.seasonalPreview = response.data[lastIndex];
                PlantService.getPlantById(this.post.plant_id)
                    .then(response => {
                        this.$store.commit('SET_ACTIVE_PLANT', response.data)
                    });
            });
        ForumService.getPostByForums(3)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.diseasePreview = response.data[lastIndex];
                this.market = this.$store.state.marketplacePreview;
                this.seasonal = this.$store.state.seasonalPreview;
                this.disease = this.$store.state.diseasePreview;
                PlantService.getPlantById(this.post.plant_id)
                    .then(response => {
                        this.$store.commit('SET_ACTIVE_PLANT', response.data)
                    });
            });

    },
    methods: {
        marketForum() {
            this.$router.push({ name: 'forumById', params: { id: 1 } })
        },
        seasonalForum() {
            this.$router.push({ name: 'forumById', params: { id: 2 } })
        },
        diseaseForum() {
            this.$router.push({ name: 'forumById', params: { id: 3 } })
        }
    }
}
</script>

<style scoped>
.forumDisplay {
    display: flex;
    text-align: center;
    width: 80vw;
    justify-content: center;
    flex-wrap: wrap;
}

.cardImage {
    width: 75vw;
}

/* .marketPlace{
    background-color: hotpink;
} */
</style>