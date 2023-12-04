<template>
    <div id="gardenDisplay" v-for="i in 3" v-bind:key="i">
     <plant-card v-bind:userId="id"/>
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