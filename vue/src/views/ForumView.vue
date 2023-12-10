<template>
    <div class="forumDisplay">
        <div class="marketPlace">
            <h2>Market Place</h2>
            <!-- image here, on click push -->
            <img class="cardImage" src="../assets/garden.jpg" />

            <!-- forum card most recent post -->
            <forum-card />

            <hr>
        </div>


        <div class="winterPlants">
            <h2>Winter Plants</h2>
            <!-- image here, on click push -->
            <img class="cardImage" src="../assets/garden.jpg" />

            <!-- forum card most recent post -->
            <forum-card />

            <hr>
        </div>

        <div class="plantHealthCare">
            <h2>Plant Healthcare</h2>
            <!-- image here, on click push -->
            <img class="cardImage" src="../assets/garden.jpg" />

            <!-- forum card most recent post -->
            <forum-card />
        </div>
    </div>
</template>

<script>
import ForumCard from '../components/ForumCard.vue';

export default {
    components: {
        ForumCard
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
</style>