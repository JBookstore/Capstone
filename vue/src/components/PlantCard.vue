<template>
    <div class="card" v-on:click="$router.push({ name: 'detailById', params: {id: plant.id}})">
        <img v-bind:src="plant.default_image.original_url" id="cardImage" />
        <h2>{{ plant.common_name }}</h2>
    </div>
</template>

<script>
    export default {
        props: {
            plant: {type: Object, required: true}
        },

        computed: {
            // plant() {
            //     let plant = this.$store.state.plants.find( p => p.userID == this.id );
            //     return plant;
            // }
        }
    }
</script>

<!-- We can fix image ratio by specifying width and leaving height undefined -->
<style scoped>
.card {
  display: flex;
  border: 2px solid black;
  border-radius: 10px;
  width: 80vw;
  height: 35vh;
  margin: 10px;
}

#cardImage {
  width: auto;
  height: 35vh;
}
</style>