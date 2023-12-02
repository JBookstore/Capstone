<template>
    <div>
        <h2>{{ plant.common_name }}</h2>
        <h3 v-for="name in plant.scientific_name" v-bind:key="name">{{ plant.scientific_name }}</h3>
        <p>Detailed text goes here. Ipsum lorem and whatnot.</p>
    </div>
</template>

<script>
    export default {
        props: ["id"],

        computed: {
            plant() {
                let plant = this.$store.state.plants.find( p => p.id == this.id );
                return plant;
            }
        }
    }
</script>

<style scoped>

</style>