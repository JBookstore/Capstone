<template>
    <h2>Test 1</h2>
    <plant-detail v-bind:id="plantId" />
    <h2>Test 2</h2>
</template>

<script>
    import plantDetail from "../components/PlantDetail.vue";

    export default { 
        data() {
            return { 
                plantId: this.$route.params.id
            }
        },
        components: {
            plantDetail
        }
    };
</script>