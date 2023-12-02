<template>
     <plant-card v-bind:userId="id"/>
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