<template>
    <div id="gardenDisplay" v-for="plant in this.$store.state.user_garden" v-bind:key="plant.id">
     <plant-card v-bind:plant="plant"/>
    </div>
</template>

<script>
    import plantCard from '../components/PlantCard.vue';

    export default { 
        data() {
            return { 
                id: 1
            }
        },
        components: {
            plantCard
        }
    };
</script>

<style scoped>

</style>