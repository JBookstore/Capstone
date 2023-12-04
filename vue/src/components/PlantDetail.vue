<template>
    <div>
        <img v-bind:src="plant.default_image.original_url" id="displayImage" />
        <h2>{{ plant.common_name }}</h2>
        <h3 v-for="name in plant.scientific_name" v-bind:key="name">{{ plant.scientific_name }}</h3>
        <p>Detailed text goes here. Ipsum lorem and whatnot.</p>
    </div>
</template>

<script>
/*   export default {
        props: {
            plant: {type: Object}
        }
    } */

    // TESTING PURPOSES
    export default {
        computed: {
            plant() {
                let plant = this.$store.state.plants.find( p => p.id == 1 );
                return plant;
            }
        }
    }
</script>

<style scoped>
#displayImage {
    width: 80vw;
    height: auto;
}
</style>