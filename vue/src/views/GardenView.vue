<template>
    <div id="gardenDisplay" v-for="i in 3" v-bind:userId="id">
     <plant-card v-bind:userId="id"/>
    </div>
</template>

<script>
    import plantCard from '../components/PlantCard.vue';

    export default { 
        data() {
            return { 
                id: this.$route.params.userId
            }
        },
        components: {
            plantCard
        }
    };
</script>

<style scoped>
</style>